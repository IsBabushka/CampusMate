/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLibs;

/**
 *
 * @author allen
 */
public class User {
    
    // details about the user
    private int userID;
    private String name;
    private java.sql.Date birthdate;
    private String gender;
    private String email;
    private String password;
    private Role role;
    private String department;

    // constructor
    public User(int userID, String name, java.sql.Date birthdate, String gender, String email, String password, Role role, String department) {
        this.userID = userID;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.role = role;
        this.department = department;
    }
    
    // constructor
    public User(String name, java.sql.Date birthdate, String gender, String email, String password, Role role, String department) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.role = role;
        this.department = department;
    }
    
    // constructor
    public User(String name, java.sql.Date birthdate, String gender, String email, String password, Role role) {
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.role = role;
        this.department = null;
    }
    
    

    // getter setter for all fields
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(java.sql.Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    // to string (for printing)
    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", name=" + name + ", birthdate=" + birthdate + ", gender=" + gender + ", email=" + email + ", password=" + password + ", role=" + role + ", department=" + department + '}';
    }
    

    // no arg constructor
    public User() {
    }
        
}
