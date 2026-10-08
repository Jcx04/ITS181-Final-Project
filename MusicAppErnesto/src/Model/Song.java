/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 *
 * @author eston
 */
@Entity
@Table(name = "Song")
public class Song {
    @Id
    private int id;
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
    
    // overloaded constructor for use with JPA
    public Song(int id, String title, String audioPath, String lyricPath, String imagePath) {

    this.id = id;
    this.title = title;
    this.audioPath = audioPath;
    this.lyricPath = lyricPath;
    this.imagePath = imagePath;
    }
    
    public int getId() {
        return id;
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


