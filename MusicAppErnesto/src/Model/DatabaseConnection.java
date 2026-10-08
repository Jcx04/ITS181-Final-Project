/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author eston
 */
public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database/music.db");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
