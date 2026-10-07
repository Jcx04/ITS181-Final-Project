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
    
    public void playSong(String filePath) {
        try {
            if (player != null) {
                player.close();
            }
            pauseLocation = 0;
            paused = false;
            
            File songFile = new File(filePath);
            currentSongPath = filePath;
            fileInputStream = new FileInputStream(songFile);
            songTotalLength = fileInputStream.available();
            player = new Player(fileInputStream);
            
            new Thread(() -> {
                try {
                    player.play();
                    
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
            if (player != null && fileInputStream != null) {
                pauseLocation = fileInputStream.available();
                player.close();
                paused = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void resumeSong() {
        try {
            File songFile = new File(currentSongPath);
            fileInputStream = new FileInputStream(songFile);
            player = new Player(fileInputStream);
            
            fileInputStream.skip(
                    songTotalLength - pauseLocation
            );
            
            new Thread(() -> {
                try {
                    player.play();
                    
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
            player.close();
        }
        
        player = null;
        fileInputStream = null;
        pauseLocation = 0;
        songTotalLength = 0;
        currentSongPath = null;
        paused = false;
    }
    
    public boolean isPaused() {
        return paused;
    }
}
