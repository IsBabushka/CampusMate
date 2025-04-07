/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package MyLibs;

/**
 *
 * @author allen
 */
import java.sql.*;
        
public class DatabaseManager {
    
    private static DatabaseManager instance;
    private Connection connection;
    
    private DatabaseManager() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/campusmate";
        String username = "root";
        String password = "admin";
        connection = DriverManager.getConnection(url, username, password);
    }

    public static DatabaseManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
