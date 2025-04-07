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
import java.util.Map;

public class StudentSessionDAO implements IStudentSessionDAO {
    private Connection connection;

    // creates a connection
    public StudentSessionDAO() throws SQLException {
        connection = DatabaseManager.getInstance().getConnection();
    }

    // saves studentsession, taking in studentid, session id, and status (whether present, late, absent) 
    @Override
    public void save(int sessionId, int studentId, SessionStatus status) {
        // sql query
        String sql = "INSERT INTO StudentSession (studentId, sessionId, status) VALUES (?, ?, ?)";
        // try catch block for prepared statement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, sessionId);
            statement.setString(3, status.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // updates student session (in case the teacher wants to convert an ABSENT into a LATE, or something like it)
    @Override
    public void update(int sessionId, int studentId, SessionStatus status) {
        // sql query
        String sql = "UPDATE StudentSession SET status = ? WHERE studentId = ? AND sessionId = ?";
        // try catch block for prepared statement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status.toString());
            statement.setInt(2, studentId);
            statement.setInt(3, sessionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    // finds a student session by id, returns one student session
    @Override
    public StudentSession findById(int studentId, int sessionId) {
        // sql query
        String sql = "SELECT * FROM StudentSession WHERE studentId = ? AND sessionId = ?";
        // try catch block for prepared statement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, sessionId);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // create a student session object from the result set
                    return createStudentSessionFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return nothing if no student session is found
        return null;
    }

    // finds all student sessions in a session
    @Override
    public ArrayList<StudentSession> findBySessionId(int sessionId) {
        // array list of student session
        ArrayList<StudentSession> studentSessions = new ArrayList<>();
        // sql query
        String sql = "SELECT * FROM StudentSession WHERE sessionId = ?";        
        // try catch block for prepared statement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sessionId);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // create a student session object from the result set, add to arr list
                    StudentSession studentSession = createStudentSessionFromResultSet(resultSet);
                    studentSessions.add(studentSession);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return the arraylist of student sessions
        return studentSessions;
    }

    // finds all student sessions for a student
    @Override
    public ArrayList<StudentSession> findByStudentId(int studentId) {
        // array list of student session
        ArrayList<StudentSession> studentSessions = new ArrayList<>();
        // sql query
        String sql = "SELECT * FROM StudentSession WHERE studentId = ?";
        // try catch block for prepared statement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // create a student session object from the result set, add to arr list
                    StudentSession studentSession = createStudentSessionFromResultSet(resultSet);
                    studentSessions.add(studentSession);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return arr list of student sessions
        return studentSessions;
    }

    // gets the total attendance rate of all student sessions
    @Override
    public double getOverallAttendanceRate() {
        // sql query, where total is the total number, and attended is the sum of all students PRESENT and LATE
        String sql = "SELECT COUNT(*) AS total, SUM(CASE WHEN status IN ('PRESENT', 'LATE') THEN 1 ELSE 0 END) AS attended FROM StudentSession";
        // try catch block for prepared statement
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                // get the total number of student sessions, and the number of students that attended (present or late)
                int total = resultSet.getInt("total");
                int attended = resultSet.getInt("attended");
                // return the attendance rate, but if total is 0 then return 0
                return total > 0 ? (double) attended / total : 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return what now
        return 0;
    }

    // gets the attendance rate for a specific course
    @Override
    public double getAttendanceRateForCourse(int courseId) {
        // sql query, total = all sessions in course, attended = sessions in course where student is PRESENT or LATE
        String sql = "SELECT COUNT(ss.studentId) AS total, SUM(CASE WHEN ss.status IN ('PRESENT', 'LATE') THEN 1 ELSE 0 END) AS attended FROM StudentSession ss JOIN Session s ON ss.sessionId = s.sessionId WHERE s.courseId = ?";
        // try catch block for prepared statement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // get the total number of student sessions, and the number of students that attended (present or late)
                    int total = resultSet.getInt("total");
                    int attended = resultSet.getInt("attended");
                    // return the attendance rate, but if total is 0 then return 0
                    return total > 0 ? (double) attended / total : 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return hoohaa
        return 0;
    }

    // attendance rate for a particular student in a course
    @Override
    public double getAttendanceRateForStudentInCourse(int studentId, int courseId) {
        // sql query, total = sessions for student in a course, attended = sum of present or late of student in a course
        String sql = "SELECT COUNT(ss.sessionId) AS total, SUM(CASE WHEN ss.status IN ('PRESENT', 'LATE') THEN 1 ELSE 0 END) AS attended FROM StudentSession ss JOIN Session s ON ss.sessionId = s.sessionId WHERE ss.studentId = ? AND s.courseId = ?";
        // try catch block for prepared statement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int total = resultSet.getInt("total");
                    int attended = resultSet.getInt("attended");
                    // return the attendance rate or 0, you get the idea!!!
                    return total > 0 ? (double) attended / total : 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return something? (it's for when the try doesnt work, if this gets executed then ggwp...)
        return 0;
    }

    // creates a student session object from resultset, private
    private StudentSession createStudentSessionFromResultSet(ResultSet resultSet) throws SQLException {
        int studentId = resultSet.getInt("studentId");
        int sessionId = resultSet.getInt("sessionId");
        SessionStatus status = SessionStatus.valueOf(resultSet.getString("status"));
        return new StudentSession(studentId, sessionId, status);
    }

    public Map<String, Double> getAttendanceRatesForAllCourses() {
        // sql query
        String sql = "SELECT c.courseCode, COUNT(ss.studentId) AS total, SUM(CASE WHEN ss.status IN ('PRESENT', 'LATE') THEN 1 ELSE 0 END) AS attended " +
                     "FROM StudentSession ss " +
                     "JOIN Session s ON ss.sessionId = s.sessionId " +
                     "JOIN Course c ON s.courseId = c.courseId " +
                     "GROUP BY c.courseCode";
        
        java.util.HashMap<String, Double> attendanceRates = new java.util.HashMap<>();
        // try catch block for prepared statement
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String courseCode = resultSet.getString("courseCode");
                int total = resultSet.getInt("total");
                int attended = resultSet.getInt("attended");
                double rate = total > 0 ? (double) attended / total : 0;
                attendanceRates.put(courseCode, rate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceRates;

    }

    // pass through user id, count present days
    public int countPresentDays(int userID) {
        String sql = "SELECT COUNT(*) FROM StudentSession WHERE studentId = ? AND status = 'PRESENT'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // pass through user id, count absent days
    public int countAbsentDays(int userID) {
        String sql = "SELECT COUNT(*) FROM StudentSession WHERE studentId = ? AND status = 'ABSENT'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // pass through user id, count late days
    public int countLateDays(int userID) {
        String sql = "SELECT COUNT(*) FROM StudentSession WHERE studentId = ? AND status = 'LATE'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getOverallAttendanceRateForUser(int userID) {
        String sql = "SELECT COUNT(*) AS total, SUM(CASE WHEN status IN ('PRESENT', 'LATE') THEN 1 ELSE 0 END) AS attended FROM StudentSession WHERE studentId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int total = resultSet.getInt("total");
                    int attended = resultSet.getInt("attended");
                    return total > 0 ? (double) attended / total : 0;
                }
            }
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
       
}
