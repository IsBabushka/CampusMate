/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLibs;

/**
 *
 * @author allen
 */
public class TaskSubmission {
    private Integer submissionId;        
    private String submissionTitle;  
    private String submissionContent;
    private Integer submissionGrade; 
    private TaskStatus status;
    private Integer studentId;       
    private Integer taskId;          

    public TaskSubmission() {
    }

    public TaskSubmission(int submissionId, String submissionTitle, String submissionContent, Integer submissionGrade, TaskStatus status, Integer studentId, Integer taskId) {
        this.submissionId = submissionId;
        this.submissionTitle = submissionTitle;
        this.submissionContent = submissionContent;
        this.submissionGrade = submissionGrade;
        this.status = status;
        this.studentId = studentId;
        this.taskId = taskId;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public String getSubmissionTitle() {
        return submissionTitle;
    }

    public void setSubmissionTitle(String submissionTitle) {
        this.submissionTitle = submissionTitle;
    }

    public String getSubmissionContent() {
        return submissionContent;
    }

    public void setSubmissionContent(String submissionContent) {
        this.submissionContent = submissionContent;
    }

    public Integer getSubmissionGrade() {
        return submissionGrade;
    }

    public void setSubmissionGrade(Integer submissionGrade) {
        this.submissionGrade = submissionGrade;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "TaskSubmission{" + "submissionId=" + submissionId + ", submissionTitle=" + submissionTitle + ", submissionContent=" + submissionContent + ", submissionGrade=" + submissionGrade + ", status=" + status + ", studentId=" + studentId + ", taskId=" + taskId + '}';
    }
    
}
