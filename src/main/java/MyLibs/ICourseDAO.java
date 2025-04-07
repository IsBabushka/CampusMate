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
public interface ICourseDAO {
    
//    Course operations
    
    // Saves a new Course to the database
    void save(String courseCode, String courseTitle);
    
    // Updates an existing Course's details by its ID.
    void update(int courseId, String courseCode, String courseTitle);
    
    // Deletes a Course by its ID. 
    void delete(int courseId);

    // Finds a Course by its unique ID
    Course findById(int courseId);
    
    // Retrieves an arr list of all Courses
    ArrayList<Course> findAll();
    
    
//    everything course student related
    
    // Enrolls a student in a specific course by creating an entry in the `coursestudent` table
    void enrollStudent(int courseId, int studentId);
    
    // Unenrolls a student from a course by removing the entry from `coursestudent`
    void unenrollStudent(int courseId, int studentId);
    
    // Retrieves a list of all Students (User objects) enrolled in a specific course.
    ArrayList<User> findStudentsByCourseId(int courseId);
    
    // Counts the number of students enrolled in a specific course.
    int countStudentsByCourseId(int courseId);

    
//     everything course-teacher related
    
    // Assigns a teacher to a specific course by creating an entry in the `courseteacher` table
    void assignTeacher(int courseId, int teacherId);
    
    // Unassigns a teacher from a course by removing the entry from `courseteacher`.
    void unassignTeacher(int courseId, int teacherId);
    
    // Retrieves an arr list of all Teachers (User objects) assigned to a specific course.
    ArrayList<User> findTeachersByCourseId(int courseId);
    
    // Counts the number of teachers assigned to a specific course.
    int countTeachersByCourseId(int courseId);
}
