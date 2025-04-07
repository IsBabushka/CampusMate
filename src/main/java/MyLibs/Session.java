/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLibs;

/**
 *
 * @author allen
 */
public class Session {
    private int sessionID;
    private String sessionTitle;
    private int courseID;

    public Session() {
    }

    public Session(int sessionID, String sessionTitle, int courseID) {
        this.sessionID = sessionID;
        this.sessionTitle = sessionTitle;
        this.courseID = courseID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionTitle() {
        return sessionTitle;
    }

    public void setSessionTitle(String sessionTitle) {
        this.sessionTitle = sessionTitle;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Session{" + "sessionID=" + sessionID + ", sessionTitle=" + sessionTitle + ", courseID=" + courseID + '}';
    }

}
