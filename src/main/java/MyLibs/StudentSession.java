/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLibs;

/**
 *
 * @author allen
 */
public class StudentSession {
    private int studentID;        
    private int sessionID;        
    private SessionStatus status;

    public StudentSession() {
    }

    public StudentSession(int studentID, int sessionID, SessionStatus status) {
        this.studentID = studentID;
        this.sessionID = sessionID;
        this.status = status;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentSession{" + "studentID=" + studentID + ", sessionID=" + sessionID + ", status=" + status + '}';
    }

}
