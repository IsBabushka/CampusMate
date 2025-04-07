/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MyLibs;

import java.util.ArrayList;

/**
 *
 * @author allen
 */
public interface ITaskDAO {
    // Saves a new Task to the database.
    void save(String taskTitle, String taskPrompt, int courseId);

    // Updates an existing Task's details.
    void update(int taskId, String taskTitle, String taskPrompt, int courseId);
    
    // Deletes a Task by its ID
    void delete(int taskId);
    
    // Finds a Task by its unique ID.
    Task findById(int taskId);
    
    // Retrieves all Tasks associated with a specific Course ID.
    ArrayList<Task> findByCourseId(int courseId);
}
