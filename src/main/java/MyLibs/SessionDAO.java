/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLibs;

/**
 *
 * @author allen
 */

import java.sql.*;
import java.util.ArrayList;

public class SessionDAO implements ISessionDAO {
    private Connection connection;

    // creates a connection
    public SessionDAO() throws SQLException {
        connection = DatabaseManager.getInstance().getConnection();
    }

    // saves a session to a course
    @Override
    public void save(int courseId, String sessionTitle) {
        // sql query, inserts the row into Session table
        String sql = "INSERT INTO Session (sessionTitle, courseId) VALUES (?, ?)";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sessionTitle);
            statement.setInt(2, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // deletes a session    
    @Override
    public void delete(int sessionId) {
        // sql query, deletes the row in Session table
        String sql = "DELETE FROM Session WHERE sessionId = ?";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sessionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // updates a session via sessionID
    @Override
    public void update(int sessionId, String sessionTitle) {
        // sql query, updates row in sessino table
        String sql = "UPDATE Session SET sessionTitle = ? WHERE sessionId = ?";
        // try catch something something blablabla
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sessionTitle);
            statement.setInt(2, sessionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // finds a session by its id    
    @Override
    public Session findById(int sessionId) {
        // sql query, selects the row in Session table
        String sql = "SELECT * FROM Session WHERE sessionId = ?";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sessionId);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // create a session object from the result set (check last-ish method)
                    return createSessionFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // if no session is found, return null
        return null;
    }
    

    // finds all sessions in a course
    @Override
    public ArrayList<Session> findByCourseId(int courseId) {
        // create new arr list of sessions
        ArrayList<Session> sessions = new ArrayList<>();
        // sql query
        String sql = "SELECT * FROM Session WHERE courseId = ?";
        // try catch for prepare statement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                // while there are values in the resultset
                while (resultSet.next()) {
                    // create a session object from the result set, add to arr list
                    Session session = createSessionFromResultSet(resultSet);
                    sessions.add(session);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return the arraylist of sessions
        return sessions;
    }

    // creates a session object from resultset, private
    private Session createSessionFromResultSet(ResultSet resultSet) throws SQLException {
        int sessionId = resultSet.getInt("sessionId");
        String sessionTitle = resultSet.getString("sessionTitle");
        int courseId = resultSet.getInt("courseId");
        return new Session(sessionId, sessionTitle, courseId);
    }
    

}
