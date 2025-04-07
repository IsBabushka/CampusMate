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


public class TaskDAO implements ITaskDAO {
    private Connection connection;

    // creates a connection
    public TaskDAO() throws SQLException {
        connection = DatabaseManager.getInstance().getConnection();
    }

    // save a task
    @Override
    public void save(String taskTitle, String taskPrompt, int courseId) {
        // sql query
        String sql = "INSERT INTO Task (taskTitle, taskPrompt, courseId) VALUES (?, ?, ?)";
        // try catch block
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, taskTitle);
            statement.setString(2, taskPrompt);
            statement.setInt(3, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update a task for editing
    @Override
    public void update(int taskId, String taskTitle, String taskPrompt, int courseId) {
        // sql query
        String sql = "UPDATE Task SET taskTitle = ?, taskPrompt = ?, courseId = ? WHERE taskId = ?";
        // try catch block
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, taskTitle);
            statement.setString(2, taskPrompt);
            statement.setInt(3, courseId);
            statement.setInt(4, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // remove a task
    @Override
    public void delete(int taskId) {
        // sql query
        String sql = "DELETE FROM Task WHERE taskId = ?";
        // try catch block
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // find a task by its taskid
    @Override
    public Task findById(int taskId) {
        // sql query
        String sql = "SELECT * FROM Task WHERE taskId = ?";
        // try catch block
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, taskId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // return the task
                    return createTaskFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // or return nothing
        return null;
    }

    // find tasks by courseid
    @Override
    public ArrayList<Task> findByCourseId(int courseId) {
        // arr list of task
        ArrayList<Task> tasks = new ArrayList<>();
        // sql query
        String sql = "SELECT * FROM Task WHERE courseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                // while there's still a task
                while (resultSet.next()) {
                    Task task = createTaskFromResultSet(resultSet);
                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return tasks
        return tasks;
    }

    // creates a task object from resultset, private    
    private Task createTaskFromResultSet(ResultSet resultSet) throws SQLException {
        int taskId = resultSet.getInt("taskId");
        String taskTitle = resultSet.getString("taskTitle");
        String taskPrompt = resultSet.getString("taskPrompt");
        int courseId = resultSet.getInt("courseId");
        return new Task(taskId, taskTitle, taskPrompt, courseId);
    }
}
