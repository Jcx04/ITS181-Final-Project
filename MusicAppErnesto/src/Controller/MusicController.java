/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author eston
 */
public class MusicController {
    private Player player;
    
    public void playSong(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
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
    
    public void stopSong() {
        if (player != null) {
            player.close();
        }
    }
}
