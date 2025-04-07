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
public interface ISessionDAO {
    // Saves a new Session to the database.
    void save(int courseId, String sessionTitle);
    
    // Deletes a Session by its ID
    void delete(int sessionId);

    // Updates an existing Session's details.
    void update(int sessionId, String sessionTitle);
    
    // Finds a Session by its unique ID.
    Session findById(int sessionId);
    
    // Retrieves all Sessions associated with a specific Course ID.
    ArrayList<Session> findByCourseId(int courseId);
    
}
