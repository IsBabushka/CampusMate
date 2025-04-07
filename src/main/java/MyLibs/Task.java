/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLibs;

/**
 *
 * @author allen
 */
public class Task {
    private int taskId;
    private String taskTitle;
    private String taskPrompt; 
    private int courseId;      

    public Task() {
    }

    public Task(int taskId, String taskTitle, String taskPrompt, int courseId) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskPrompt = taskPrompt;
        this.courseId = courseId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskPrompt() {
        return taskPrompt;
    }

    public void setTaskPrompt(String taskPrompt) {
        this.taskPrompt = taskPrompt;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Task{" + "taskId=" + taskId + ", taskTitle=" + taskTitle + ", taskPrompt=" + taskPrompt + ", courseId=" + courseId + '}';
    }
    
    
}
