/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

/**
 *
 * @author eston
 */
public class MusicController {
    private Player player;
    private FileInputStream fileInputStream;
    private String currentSongPath;
    private long songTotalLength;
    private long pauseLocation;
    private boolean paused = false;
    private boolean interruptedPlayback = false;
    private Runnable onSongFinished;
    
    public void setOnSongFinished(Runnable onSongFinished) {
        this.onSongFinished = onSongFinished;
    }
    
    public void playSong(String filePath) {
        try {
            if (player != null) {
                interruptedPlayback = true;
                player.close(); // Closes the currently running player
            }
            // Resets pause information
            interruptedPlayback = false;
            pauseLocation = 0;
            paused = false;
            
            // Converts string info to actual file object and then plays it
            File songFile = new File(filePath);
            currentSongPath = filePath;
            fileInputStream = new FileInputStream(songFile);
            songTotalLength = fileInputStream.available();
            player = new Player(fileInputStream);
            
            // Thread for playback of music
            new Thread(() -> {
                try {
                    player.play(); // Plays the MP3
                    
                    if (!interruptedPlayback && onSongFinished != null) {
                        onSongFinished.run();
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
    
    public void pauseSong() {
        try {
            // Only pauses if a song is currently loaded
            if (player != null && fileInputStream != null) {
                pauseLocation = fileInputStream.available(); // Reads unread bytes of the song and uses it as a checkpoint
                interruptedPlayback = true;
                player.close();
                paused = true; // Pause state handler
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void resumeSong() {
        try {
            // Recreates new stream and instances
            interruptedPlayback = false;
            File songFile = new File(currentSongPath);
            fileInputStream = new FileInputStream(songFile);
            player = new Player(fileInputStream);
            
            // Skips to the currently paused portion
            fileInputStream.skip(
                    songTotalLength - pauseLocation
            );
            
            new Thread(() -> {
                try {
                    player.play();
                    
                    if (!interruptedPlayback && onSongFinished != null) {
                        onSongFinished.run();
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            paused = false;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public void stopSong() {
        if (player != null) {
            interruptedPlayback = true;
            player.close();
            // Stops playback and closes the player
        }
        
        // Resets all values for music after stopping
        player = null;
        fileInputStream = null;
        pauseLocation = 0;
        songTotalLength = 0;
        currentSongPath = null;
        paused = false;
    }
    
    public boolean isPaused() {
        // Returns the paused state (true, false) for other classes
        return paused;
    }
    
}
