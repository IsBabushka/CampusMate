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


public class CourseDAO implements ICourseDAO {
    private Connection connection;

    // creates a connection
    public CourseDAO() throws SQLException {
        connection = DatabaseManager.getInstance().getConnection();
    }

    // create a course with given params
    @Override
    public void save(String courseCode, String courseTitle) {
        // sql query, inserts the row into Course table
        String sql = "INSERT INTO Course (courseCode, courseTitle) VALUES (?, ?)";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, courseCode);
            statement.setString(2, courseTitle);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update a course (for editing courses) 
    @Override
    public void update(int courseId, String courseCode, String courseTitle) {
        // sql query, updates the row in Course table
        String sql = "UPDATE Course SET courseCode = ?, courseTitle = ? WHERE courseId = ?";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, courseCode);
            statement.setString(2, courseTitle);
            statement.setInt(3, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete a course
    @Override
    public void delete(int courseId) {
        // sql query, deletes the row in Course table
        String sql = "DELETE FROM Course WHERE courseId = ?";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // finds a course by its id
    @Override
    public Course findById(int courseId) {
        // sql query, selects the row in Course table
        String sql = "SELECT * FROM Course WHERE courseId = ?";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // create a course object from the result set (check last-ish method)
                    return createCourseFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // if no course is found, return null
        return null;
    }

    // finds all courses
    @Override
    public ArrayList<Course> findAll() {
        ArrayList<Course> courses = new ArrayList<>();
        // sql query, selects all rows in Course table
        String sql = "SELECT * FROM Course";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
                // loop through the result set and create course objects
            while (resultSet.next()) {
                // create a course object from the result set, add to arr list
                Course course = createCourseFromResultSet(resultSet);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return the arraylist of courses
        return courses;
    }

    // adds student to a course, for "add a course" in the admin dashboard (maybe... idk ill see 2 days later when i eventually screw up something)
    @Override
    public void enrollStudent(int courseId, int studentId) {
        // sql query, inserts the row into CourseStudent table
        String sql = "INSERT INTO CourseStudent (courseId, studentId) VALUES (?, ?)";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // removes a student from a course,
    @Override
    public void unenrollStudent(int courseId, int studentId) {
        // sql query, deletes the row in CourseStudent table
        String sql = "DELETE FROM CourseStudent WHERE courseId = ? AND studentId = ?";
        // try catch block for prepare statement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.setInt(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // finds all students in a course
    @Override
    public ArrayList<User> findStudentsByCourseId(int courseId) {
        // creates an arr list of users
        ArrayList<User> students = new ArrayList<>();
        // sql query, selects all rows in User table
        String sql = "SELECT u.* FROM User u JOIN CourseStudent cs ON u.userID = cs.studentId WHERE cs.courseId = ?";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // create user object from result set, add to arr list
                    User student = createUserFromResultSet(resultSet);
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return the arraylist of students
        return students;
    }

    // coutns students in a particular course
    @Override
    public int countStudentsByCourseId(int courseId) {
        // sql query, counts all rows in CourseStudent table
        String sql = "SELECT COUNT(*) FROM CourseStudent WHERE courseId = ?";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
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

    // assigns a teacher to a course, uses courseid and teacherid as params
    @Override
    public void assignTeacher(int courseId, int teacherId) {
        // sql query, inserts the row into CourseTeacher table
        String sql = "INSERT INTO CourseTeacher (courseId, teacherId) VALUES (?, ?)";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.setInt(2, teacherId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // removes a teacher from a course    
    @Override
    public void unassignTeacher(int courseId, int teacherId) {
        // sql query, removes the row
        String sql = "DELETE FROM CourseTeacher WHERE courseId = ? AND teacherId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.setInt(2, teacherId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // find teachers assigned to a course
    @Override
    public ArrayList<User> findTeachersByCourseId(int courseId) {
        // creates an arr list of users
        ArrayList<User> teachers = new ArrayList<>();
        // sql query, selects all rows in User table
        String sql = "SELECT u.* FROM User u JOIN CourseTeacher ct ON u.userID = ct.teacherId WHERE ct.courseId = ?";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // create user object from result set, add to arr list
                    User teacher = createUserFromResultSet(resultSet);
                    teachers.add(teacher);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return the arraylist of teachers
        return teachers;
    }

    // counts teachers in a particular course (dont know where this might be used, just thougt it would be handy)
    @Override
    public int countTeachersByCourseId(int courseId) {
        // sql query, counts all rows in CourseTeacher table
        String sql = "SELECT COUNT(*) FROM CourseTeacher WHERE courseId = ?";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            // try catch block
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // if no result, return 0
        return 0;
    }

    // add room code to course in 'courseroom' table
    public void addRoomToCourse(int courseId, String roomCode) {
        // sql query, inserts the row into CourseRoom table
        String sql = "INSERT INTO CourseRoom (courseId, roomCode) VALUES (?, ?)";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.setString(2, roomCode);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // remove room from course
    public void removeRoomFromCourse(int courseId) {
        // sql query, removes the row from CourseRoom table
        String sql = "DELETE FROM CourseRoom WHERE courseId = ?";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Course> findCoursesByStudentId(int studentId) {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT c.* FROM Course c JOIN CourseStudent cs ON c.courseId = cs.courseId WHERE cs.studentId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = createCourseFromResultSet(resultSet);
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    // creates a course object from resultset, private
    private Course createCourseFromResultSet(ResultSet resultSet) throws SQLException {
        int courseId = resultSet.getInt("courseId");
        String courseCode = resultSet.getString("courseCode");
        String courseTitle = resultSet.getString("courseTitle");
        return new Course(courseId, courseCode, courseTitle);
    }

    // creates a user from resultset, private
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

    public Course getCourseByCode(String courseCode) {
        String sql = "SELECT * FROM Course WHERE courseCode = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, courseCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createCourseFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Course> getCoursesByTeacherId(int teacherId) {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT c.* FROM Course c JOIN CourseTeacher ct ON c.courseId = ct.courseId WHERE ct.teacherId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, teacherId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = createCourseFromResultSet(resultSet);
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Course";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Course course = createCourseFromResultSet(resultSet);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public ArrayList<String> getRoomsByCourseId(int courseId) {
        ArrayList<String> rooms = new ArrayList<>();
        String sql = "SELECT roomCode FROM CourseRoom WHERE courseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    rooms.add(resultSet.getString("roomCode"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public ArrayList<Course> findCoursesByUserId(int userID) {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT c.* FROM Course c LEFT JOIN CourseStudent cs ON c.courseId = cs.courseId LEFT JOIN CourseTeacher ct ON c.courseId = ct.courseId WHERE cs.studentId = ? OR ct.teacherId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            statement.setInt(2, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = createCourseFromResultSet(resultSet);
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public void removeRoomFromCourse(int courseId, String roomCode) {
        String sql = "DELETE FROM CourseRoom WHERE courseId = ? AND roomCode = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.setString(2, roomCode);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
