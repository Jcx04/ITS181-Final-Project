/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author eston
 */
public class Song {
    private String title;
    private String audioPath;
    private String lyricPath;
    private String imagePath;
    
    public Song(String title, String audioPath, String lyricPath, String imagePath) {
        this.title = title;
        this.audioPath = audioPath;
        this.lyricPath = lyricPath;
        this.imagePath = imagePath;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAudioPath() {
        return audioPath;
    }
    
    public String getLyricPath() {
        return lyricPath;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    @Override
    public String toString() {
        return title;
    }
}


