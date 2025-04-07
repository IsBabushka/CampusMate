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
public interface IUserDAO {
    // Saves a new User to the database
    void save(String name, java.sql.Date birthdate, String gender, String email, String password, Role role, String department);

    // Updates an existing User's details in the database
    void update(int userId, String name, java.sql.Date birthdate, String gender, String email, String password, Role role, String department);

    // Deletes a User from the database by their ID
    void delete(int userId);
    
    // Finds a User by their unique ID
    User findById(int userId);

    // Finds a User by their unique email address
    User findByEmail(String email);
    
    // Retrieves a list of all users with the role 'STUDENT'
    ArrayList<User> findAllStudents();
    
    // Retrieves a list of all users with the role 'TEACHER'
    ArrayList<User> findAllTeachers();
    
    // Counts the total number of users with the role 'STUDENT'
    int countTotalStudents();
    
    // Counts the total number of users with the role 'TEACHER'
    int countTotalTeachers();
}
