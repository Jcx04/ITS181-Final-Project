package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LYRICS")
public class Lyric {

    @Id
    @Column(name = "SONG_TITLE", length = 255)
    private String songTitle;

    @Column(name = "LYRIC_TEXT", length = 20000)
    private String lyricText;

    public Lyric() {
    }

    public Lyric(String songTitle, String lyricText) {
        this.songTitle = songTitle;
        this.lyricText = lyricText;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getLyricText() {
        return lyricText;
    }

    public void setLyricText(String lyricText) {
        this.lyricText = lyricText;
    }
}
