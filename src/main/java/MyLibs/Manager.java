/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package MyLibs;

import java.util.ArrayList;

/**
 *
 * @author allen
 */
public class Manager {
    
    private User currentUser;
    private Course currentCourse;
    // current id selected
    
    
    // singleton implementation of constructor

    private Manager() {
    }
    
    public static Manager getInstance() {
        return ManagerHolder.INSTANCE;
    }
    
    private static class ManagerHolder {

        private static final Manager INSTANCE = new Manager();
    }
    
    
    
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }
    
    
}
