/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLibs;

/**
 *
 * @author allen
 */
public class Course {
    private int courseId;
    private String courseCode;
    private String courseTitle;
    
    public Course() {
    }
    
    public Course(int courseId, String courseCode, String courseTitle) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    // --- toString(), equals(), hashCode() ---
    @Override
    public String toString() {
        return "Course{" +
               "courseId=" + courseId +
               ", courseCode='" + courseCode + '\'' +
               ", courseTitle='" + courseTitle + '\'' +
               '}';
    }

}
