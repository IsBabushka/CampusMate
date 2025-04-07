/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLibs;

/**
 *
 * @author allen
 */

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements IUserDAO {
    private Connection connection;

    // get a connection from DB manager 
    public UserDAO() throws SQLException {
        connection = DatabaseManager.getInstance().getConnection();
    }

    // save method
    @Override
    public void save(String name, Date birthdate, String gender, String email, String password, Role role, String department) {
        // sql query, inserts the row into User table with the given values
        String sql = "INSERT INTO User (name, birthdate, gender, email, password, role, department) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // try catch block for preparestatement, which will be executed after cols are filled up with values 
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setDate(2, birthdate);
            statement.setString(3, gender);
            statement.setString(4, email);
            statement.setString(5, Encryptor.encryptPassword(password));
            statement.setString(6, role.toString());
            statement.setString(7, department);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // save method overload, no dept in parameter, also save row to no dept
    public void save(String name, Date birthdate, String gender, String email, String password, Role role) {
        // sql query, inserts the row into User table with the given values, no dept!!!
        String sql = "INSERT INTO User (name, birthdate, gender, email, password, role) VALUES (?, ?, ?, ?, ?, ?)";
        // try catch block for preparestatement, which will be executed after cols are filled up with values 
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setDate(2, birthdate);
            statement.setString(3, gender);
            statement.setString(4, email);
            statement.setString(5, Encryptor.encryptPassword(password));
            statement.setString(6, role.toString());
            // dept. column will be left as null
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // save method but for a user object instead of indiv parameters
    public void save(User user) {
        // sql query, inserts the row into User table with the given values
        String sql = "INSERT INTO User (name, birthdate, gender, email, password, role, department) VALUES (?, ?, ?, ?, ?, ?, ?)";
        // try catch block for preparestatement, which will be executed after cols are filled up with values 
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setDate(2, user.getBirthdate());
            statement.setString(3, user.getGender());
            statement.setString(4, user.getEmail());
            statement.setString(5, Encryptor.encryptPassword(user.getPassword()));
            statement.setString(6, user.getRole().toString());
            statement.setString(7, user.getDepartment());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update method (for editing users), uses UserID to find which user to change
    @Override
    public void update(int userId, String name, Date birthdate, String gender, String email, String password, Role role, String department) {
        // sql query, updates the row in User table with the given values, uses userID to find which row to change
        String sql = "UPDATE User SET name = ?, birthdate = ?, gender = ?, email = ?, password = ?, role = ?, department = ? WHERE userID = ?";
        // try catch block for preparestatement, which will be executed after cols are filled up with values 
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setDate(2, birthdate);
            statement.setString(3, gender);
            statement.setString(4, email);
            statement.setString(5, Encryptor.encryptPassword(password));
            statement.setString(6, role.toString());
            statement.setString(7, department);
            statement.setInt(8, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // deptless update, no dept in parameter, also update row to no dept
    public void update(int userId, String name, Date birthdate, String gender, String email, String password, Role role) {
        // sql query, updates the row in User table with the given values, uses userID to find which row to change
        String sql = "UPDATE User SET name = ?, birthdate = ?, gender = ?, email = ?, password = ?, role = ?, department = ? WHERE userID = ?";
        // try catch block for preparestatement, which will be executed after cols are filled up with values 
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setDate(2, birthdate);
            statement.setString(3, gender);
            statement.setString(4, email);
            statement.setString(5, Encryptor.encryptPassword(password));
            statement.setString(6, role.toString());
            statement.setString(7, null);
            statement.setInt(8, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update method overload, for user object instead of indiv parameters
    public void update(User user) {
        String sql = "UPDATE User SET name = ?, birthdate = ?, gender = ?, email = ?, role = ?, department = ? WHERE userID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setDate(2, user.getBirthdate());
            statement.setString(3, user.getGender());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getRole().toString());
            statement.setString(6, user.getDepartment());
            statement.setInt(7, user.getUserID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // will be used for update operations, does NOT have password as param
    public void update(int userId, String name, Date birthdate, String gender, String email, Role role, String department) {
        String sql = "UPDATE User SET name = ?, birthdate = ?, gender = ?, email = ?, role = ?, department = ? WHERE userID = ?";
    
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setDate(2, birthdate);
            statement.setString(3, gender);
            statement.setString(4, email);
            statement.setString(5, role.toString());
            statement.setString(6, department);
            statement.setInt(7, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(int userId, String newPassword) {
        String sql = "UPDATE User SET password = ? WHERE userID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Encryptor.encryptPassword(newPassword)); // Encrypt the password before updating
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in a production environment
        }
    }
    
    
    
    // delete method, uses userID to find which user to delete (for expelling or whatnot)
    @Override
    public void delete(int userId) {
        // sql query, deletes the row in User table with the given userID
        String sql = "DELETE FROM User WHERE userID = ?";
        // try catch block for preparestatement, which will be executed after cols are filled up with values 
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // find method, uses userID to find which user to return
    @Override
    public User findById(int userId) {
        // sql query, selects the row in User table with the given userID
        String sql = "SELECT * FROM User WHERE userID = ?";
        // try catch block for preparestatement, which will be executed after cols are filled up with values 
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // create a user object from the result set (check last-ish method)
                    return createUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // if no user is found, return null
        return null;
    }


    // find method, uses email to find which user to return
    @Override
    public User findByEmail(String email) {
        // sql query, selects the row in User table with the given email
        String sql = "SELECT * FROM User WHERE email = ?";
        // try catch block for preparestatement, which will be executed after cols are filled up with values 
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // if no user is found, return null
        return null;
    }

    // returns a list of all students
    @Override
    public ArrayList<User> findAllStudents() {
        // create an arraylist of users
        ArrayList<User> users = new ArrayList<>();
        // sql query, selects all rows in User table with role = 'STUDENT'
        String sql = "SELECT * FROM User WHERE role = 'STUDENT'";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql);
            // execute the query and get the result set
             ResultSet resultSet = statement.executeQuery()) {

            // loop through the result set and create user objects
            while (resultSet.next()) {
                // create a user object from the result set
                User user = createUserFromResultSet(resultSet);
                // add the user to the arraylist
                users.add(user);
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        // return the arraylist of users
        return users;
    }

    // returns a list of all teachers
    @Override
    public ArrayList<User> findAllTeachers() {
        // create an arraylist of users
        ArrayList<User> users = new ArrayList<>();
        // sql query, selects all rows in User table with role = 'TEACHER'
        String sql = "SELECT * FROM User WHERE role = 'TEACHER'";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql);
            // execute the query and get the result set
             ResultSet resultSet = statement.executeQuery()) {
            // loop through the result set and create user objects 
            while (resultSet.next()) {
                // create a user object from the result set
                User user = createUserFromResultSet(resultSet);
                // add the user to the arraylist
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // counts total number of students duh!!
    @Override
    public int countTotalStudents() {
        // sql query, counts all rows in User table with role = 'STUDENT'
        String sql = "SELECT COUNT(*) FROM User WHERE role = 'STUDENT'";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            // if there is a result, return the count
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // if no result, return 0
        return 0;
    }

    // counts total num of teachers
    @Override
    public int countTotalTeachers() {
        // sql query, counts all rows in User table with role = 'TEACHER'
        String sql = "SELECT COUNT(*) FROM User WHERE role = 'TEACHER'";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            // if there is a result, return the count
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // if no result, return 0
        return 0;
    }

    // helper method to create a user object from a result set
    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("userID");
        String name = resultSet.getString("name");
        Date birthdate = resultSet.getDate("birthdate");
        String gender = resultSet.getString("gender");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        Role role = Role.valueOf(resultSet.getString("role"));
        String department = resultSet.getString("department");
        return new User(userId, name, birthdate, gender, email, password, role, department);
    }
       
}
