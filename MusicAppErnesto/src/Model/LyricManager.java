package Model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LyricManager {

    private static final String PERSISTENCE_UNIT = "MusicAppPU";
    private static final String NOT_AVAILABLE = "Lyrics not available.";

    private final EntityManagerFactory emf;

    public LyricManager() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    public String getLyrics(String songTitle) {
        Lyric lyric = find(songTitle);
        if (lyric == null || lyric.getLyricText() == null) {
            return NOT_AVAILABLE;
        }
        return lyric.getLyricText();
    }

    public boolean hasLyrics(String songTitle) {
        return find(songTitle) != null;
    }

    public void saveLyrics(String songTitle, String text) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Lyric> found = em.createQuery(
                    "SELECT l FROM Lyric l WHERE l.songTitle = :title", Lyric.class)
                    .setParameter("title", songTitle)
                    .getResultList();
            if (found.isEmpty()) {
                em.persist(new Lyric(songTitle, text));
            } else {
                found.get(0).setLyricText(text);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void importMissing(Song[] songs) {
        for (Song song : songs) {
            if (hasLyrics(song.getTitle())) {
                continue;
            }
            try {
                String text = new String(
                        Files.readAllBytes(Paths.get(song.getLyricPath())),
                        StandardCharsets.UTF_8);
                saveLyrics(song.getTitle(), text);
            } catch (IOException e) {
                System.err.println("Could not import lyrics for "
                        + song.getTitle() + ": " + e.getMessage());
            }
        }
    }

    public void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }

    private Lyric find(String songTitle) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Lyric> found = em.createQuery(
                    "SELECT l FROM Lyric l WHERE l.songTitle = :title", Lyric.class)
                    .setParameter("title", songTitle)
                    .getResultList();
            return found.isEmpty() ? null : found.get(0);
        } finally {
            em.close();
        }
    }
}
