/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLibs;

import java.util.ArrayList;

/**
 *
 * @author allen
 */
public interface ITaskSubmissionDAO {

    // Saves a TaskSubmission record.
    void save(String submissionTitle, String submissionContent, Integer submissionGrade, TaskStatus status, Integer studentId, Integer taskId);

    // Updates a student task record via id
    void update(int submissionId, String submissionTitle, String submissionContent, Integer submissionGrade, TaskStatus status, Integer studentId, Integer taskId);

    // Deletes a TaskSubmission by its ID
    void delete(int submissionId);
    
    // Finds a specific student's task record by its primary key.
    TaskSubmission findById(int submissionId);
    
    // Retrieves all TaskSubmission records associated with a specific task.
    ArrayList<TaskSubmission> findByTaskId(int taskId);
    
    // Retrieves all TaskSubmission records associated with a specific student.
    ArrayList<TaskSubmission> findByStudentId(int studentId);
    
    // Filters TaskSubmission records based on various optional criteria. parameter is -1 if not included, can be paired as such:
    // taskID and studentID (for seeing a specifc submission from a student in a task), returns AL<TaskSubmission> containing 1 item
    // taskID minGrade maxGrade (for seeing all submissions within a specific task that are under a range), returns AL<TaskSubmission>
    // taskID minGrade (for seeing all submissions within a specific task above minGrade), returns AL<TaskSubmission>
    // taskID maxGrade (for seeing all submissions within a specific task below maxGrade), returns AL<TaskSubmission>
    // studentID minGrade maxGrade (for seeing all submissions by a student that are under a range), returns AL<TaskSubmission>
    // studentID minGrade (for seeing all submissions by a student above minGrade), returns AL<TaskSubmission>
    // studentID maxGrade (for seeing all submissions by a student below maxGrade), returns AL<TaskSubmission>
    ArrayList<TaskSubmission> filterSubmissions(int taskId, int studentId, Integer minGrade, Integer maxGrade);
    
    // Calculates the average grade across all graded submissions (`studenttask`). Considers only records where grade is not null and status is SUBMITTED.
    double getOverallAverageGrade();
    
    // Calculates the average grade for submissions within a specific course
    double getAverageGradeForCourse(int courseId);
    
    // Calculates the average grade for a specific student within a specific course
    double getAverageGradeForStudentInCourse(int studentId, int courseId);
}
