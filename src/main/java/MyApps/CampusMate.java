/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package MyApps;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import MyLibs.DatabaseManager;
import MyLibs.Role;
import MyLibs.StudentSessionDAO;
import MyLibs.User;
import MyLibs.UserDAO;


/**
 *
 * @author allen
 */
public class CampusMate {

    public static void main(String[] args) throws SQLException {

        Login login = new Login();
        login.setVisible(true);
    }
}
