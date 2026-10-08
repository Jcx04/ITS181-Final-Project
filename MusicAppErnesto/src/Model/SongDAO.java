/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eston
 */
public class SongDAO {
    public List<Song> getAllSongs() {
        
        List<Song> songs = new ArrayList<>();
        
        try {
            Connection connection = DatabaseConnection.getConnection(); // Creates a connection to music.db
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Song"); // Gets all records from music.db
            
            // scans all rows and gets info for each track
            while (resultSet.next()) {
            Song song = new Song(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("audioPath"),
                    resultSet.getString("lyricPath"),
                    resultSet.getString("imagePath")
            );
            songs.add(song);
            }
            
            resultSet.close();
            statement.close();
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return songs;
    }
}
