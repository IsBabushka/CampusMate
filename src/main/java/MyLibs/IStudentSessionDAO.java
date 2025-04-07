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
public interface IStudentSessionDAO {
    // Saves a student's attendance status for a specific session
    void save(int sessionID, int studentID, SessionStatus status);

    // Updates a student's attendance status for a specific session
    void update(int sessionID, int studentID, SessionStatus status);
    
    // Finds a specific attendance record by key (studentId, sessionId).
    StudentSession findById(int studentId, int sessionId);
    
    // Retrieves all attendance records for a specific session.
    ArrayList<StudentSession> findBySessionId(int sessionId);
    
    // Retrieves all attendance records for a specific student.
    ArrayList<StudentSession> findByStudentId(int studentId);
    
    // Calculates the overall attendance rate (Present or Late / Total) across all records.
    double getOverallAttendanceRate();
    
    // Calculates the attendance rate for a specific course.
    double getAttendanceRateForCourse(int courseId);
    
    // Calculates the attendance rate for a specific student within a specific course.
    double getAttendanceRateForStudentInCourse(int studentId, int courseId);
}
