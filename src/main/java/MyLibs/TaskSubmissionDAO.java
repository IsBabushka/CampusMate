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
import java.util.HashMap;
import java.util.Map;
 

public class TaskSubmissionDAO implements ITaskSubmissionDAO {
    private Connection connection;

    // create a connection
    public TaskSubmissionDAO() throws SQLException {
        connection = DatabaseManager.getInstance().getConnection();
    }

    // save submission (for creating a submission as a student)
    @Override
    public void save(String submissionTitle, String submissionContent, Integer submissionGrade, TaskStatus status, Integer studentId, Integer taskId) {
        // sql query
        String sql = "INSERT INTO TaskSubmission (submissionTitle, submissionContent, submissionGrade, status, studentId, taskId) VALUES (?, ?, ?, ?, ?, ?)";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, submissionTitle);
            statement.setString(2, submissionContent);
            // if there is no grade given (for sure there wont be, then null), this will be given by the teacher later
            if (submissionGrade != null) {
                statement.setInt(3, submissionGrade);
            } else {
                statement.setNull(3, Types.INTEGER);
            }
            statement.setString(4, status.toString());
            statement.setInt(5, studentId);
            statement.setInt(6, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // update submission (for editing a submission as an admin, or grading as a teacher)
    @Override
    public void update(int submissionId, String submissionTitle, String submissionContent, Integer submissionGrade, TaskStatus status, Integer studentId, Integer taskId) {
        // sql query (i think its dumb that i put ALL parameters here, the only thing that's going to be changed by the teacher is the grade, but i digress, who knows maybe the admin needs to change something for some reason)
        String sql = "UPDATE TaskSubmission SET submissionTitle = ?, submissionContent = ?, submissionGrade = ?, status = ?, studentId = ?, taskId = ? WHERE submissionId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, submissionTitle);
            statement.setString(2, submissionContent);
            if (submissionGrade != null) {
                statement.setInt(3, submissionGrade);
            } else {
                statement.setNull(3, Types.INTEGER);
            }
            statement.setString(4, status.toString());
            statement.setInt(5, studentId);
            statement.setInt(6, taskId);
            statement.setInt(7, submissionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete submission (was added late, i think it's not really something that's needed, because i didn't really put a "delete" submission option for anyone, so this method can only be accessed via a separate class)
    @Override
    public void delete(int submissionId) {
        // sql query
        String sql = "DELETE FROM TaskSubmission WHERE submissionId = ?";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, submissionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();        
        }
    }

    // find a submission by submissionID
    @Override
    public TaskSubmission findById(int submissionId) {
        // sql query
        String sql = "SELECT * FROM TaskSubmission WHERE submissionId = ?";
        // try catch block
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, submissionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createTaskSubmissionFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // find submissions by taskID, so like the submissions for a particular task
    @Override
    public ArrayList<TaskSubmission> findByTaskId(int taskId) {
        // arr list for the submissions
        ArrayList<TaskSubmission> submissions = new ArrayList<>();
        // sql query
        String sql = "SELECT * FROM TaskSubmission WHERE taskId = ?";
        // try catch block for preparestatement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, taskId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // while theres a submission, 
                    // create a submission object from the result set, add to arr list
                    TaskSubmission submission = createTaskSubmissionFromResultSet(resultSet);
                    submissions.add(submission);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return the array list
        return submissions;
    }

    // find the submissions from a student by their ID
    @Override
    public ArrayList<TaskSubmission> findByStudentId(int studentId) {
        // ary list (hi k.)
        ArrayList<TaskSubmission> submissions = new ArrayList<>();
        // sql query
        String sql = "SELECT * FROM TaskSubmission WHERE studentId = ?";
        // try catch block
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                // while there's still a submission
                // create a submission object from the result set, add to arr list
                while (resultSet.next()) {
                    TaskSubmission submission = createTaskSubmissionFromResultSet(resultSet);
                    submissions.add(submission);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return the array list
        return submissions;
    }

    // filter the submissions 
    @Override
    public ArrayList<TaskSubmission> filterSubmissions(int taskId, int studentId, Integer minGrade, Integer maxGrade) {
        ArrayList<TaskSubmission> submissions = new ArrayList<>();
        // create a string builder
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM TaskSubmission WHERE 1=1");

        // if the value for task id is not -1 (theres an actual value)
        if (taskId != -1) {
            // add to sql query
            sqlBuilder.append(" AND taskId = ?");        
        }
        // if the value of student id is not -1
        if (studentId != -1) {
            // add to sql query
            sqlBuilder.append(" AND studentId = ?");
        }
        // if there is a value for min grade
        if (minGrade != null) {
            // add lower bound to sql query
            sqlBuilder.append(" AND submissionGrade >= ?");
        }
        // if there is a value for max grade
        if (maxGrade != null) {
            // add upper bound to sql query
            sqlBuilder.append(" AND submissionGrade <= ?");
        }

        // try catch block for prepare statement
        try (PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString())) {
            int parameterIndex = 1;
            // set the parameters, starts with 1, increments with whatever parameters are given. (if there are 3 parameters, like taskID, min, max, then it counts up 3 times to fill the rows up with those values)
            if (taskId != -1) {
                statement.setInt(parameterIndex++, taskId);
            }
            if (studentId != -1) {
                statement.setInt(parameterIndex++, studentId);
            }
            if (minGrade != null) {
                statement.setInt(parameterIndex++, minGrade);
            }
            if (maxGrade != null) {
                statement.setInt(parameterIndex++, maxGrade);
            }

            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TaskSubmission submission = createTaskSubmissionFromResultSet(resultSet);
                    submissions.add(submission);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // return the array list
        return submissions;
    }

    // overall average grade for all submissions (for admin dashboard)
    @Override
    public double getOverallAverageGrade() {
        String sql = "SELECT AVG(submissionGrade) FROM TaskSubmission WHERE submissionGrade IS NOT NULL AND status = 'SUBMITTED'";
        // try catch block
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
                // return the number
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // or return 0
        return 0;
    }

    // get overall average grade for a certain user
    public double getOverallAverageGrade(int studentId) {
        String sql = "SELECT AVG(submissionGrade) FROM TaskSubmission WHERE studentId = ? AND submissionGrade IS NOT NULL AND status = 'SUBMITTED'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<String> getDetailedGrades(int studentId) {
        ArrayList<String> detailedGrades = new ArrayList<>();
    String sql = "SELECT t.taskTitle, c.courseCode, ts.submissionGrade " +
                 "FROM TaskSubmission ts " +
                 "JOIN Task t ON ts.taskId = t.taskId " +
                 "JOIN Course c ON t.courseId = c.courseId " +
                 "WHERE ts.studentId = ? AND ts.submissionGrade IS NOT NULL AND ts.status = 'SUBMITTED'";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, studentId);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String taskTitle = resultSet.getString("taskTitle");
                String courseCode = resultSet.getString("courseCode");
                int grade = resultSet.getInt("submissionGrade");
                detailedGrades.add(courseCode + ": " + taskTitle + ": " + grade);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return detailedGrades;

    }
    

    // average grade for a particular course
    @Override
    public double getAverageGradeForCourse(int courseId) {
        // sql query
        String sql = "SELECT AVG(st.submissionGrade) FROM TaskSubmission st JOIN Task t ON st.taskId = t.taskId WHERE t.courseId = ? AND st.submissionGrade IS NOT NULL AND st.status = 'SUBMITTED'";
        // try catch block
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {                
                if (resultSet.next()) {
                    // return the number
                    return resultSet.getDouble(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // or return 0
        return 0;
    }

    // average grade for a particular student in a particular course
    @Override
    public double getAverageGradeForStudentInCourse(int studentId, int courseId) {
        // sql query
        String sql = "SELECT AVG(st.submissionGrade) FROM TaskSubmission st JOIN Task t ON st.taskId = t.taskId WHERE st.studentId = ? AND t.courseId = ? AND st.submissionGrade IS NOT NULL AND st.status = 'SUBMITTED'";
        // try catch block
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            // execute the query and get the result set
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // or return 0
        return 0;
    }

    public java.util.Map<String, Double> getAverageGradesForAllCourses() {
        String sql = "SELECT c.courseCode, AVG(st.submissionGrade) AS averageGrade " +
                     "FROM TaskSubmission st " +
                     "JOIN Task t ON st.taskId = t.taskId " +
                     "JOIN Course c ON t.courseId = c.courseId " +
                     "WHERE st.submissionGrade IS NOT NULL AND st.status = 'SUBMITTED' " +
                     "GROUP BY c.courseCode";

        java.util.Map<String, Double> averageGrades = new java.util.HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String courseCode = resultSet.getString("courseCode");
                double averageGrade = resultSet.getDouble("averageGrade");
                averageGrades.put(courseCode, averageGrade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageGrades;
    }

    // getGradeDistribution
    public java.util.Map<String, Integer> getGradeDistribution() {
        String sql = "SELECT submissionGrade, COUNT(*) AS count FROM TaskSubmission WHERE submissionGrade IS NOT NULL AND status = 'SUBMITTED' GROUP BY submissionGrade";
        java.util.Map<String, Integer> gradeDistribution = new java.util.HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int grade = resultSet.getInt("submissionGrade");
                int count = resultSet.getInt("count");
                gradeDistribution.put(String.valueOf(grade), count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gradeDistribution;
    }
    
    // getStudentTotalGrades
    public Map<Integer, Double> getStudentTotalGrades() {
        String sql = "SELECT studentId, AVG(submissionGrade) AS totalGrade " +
                "FROM TaskSubmission " +
                "WHERE submissionGrade IS NOT NULL AND status = 'SUBMITTED' " +
                "GROUP BY studentId";

        Map<Integer, Double> studentTotalGrades = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                double totalGrade = resultSet.getDouble("totalGrade");
                studentTotalGrades.put(studentId, totalGrade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentTotalGrades;
    }

    public Map<Integer, Integer> getGradeDistributionByRange() {
        Map<Integer, Integer> gradeDistribution = new HashMap<>();
        // Initialize all ranges to 0
        for (int i = 0; i < 20; i++) {
            gradeDistribution.put(i, 0);
        }

        String sql = "SELECT submissionGrade FROM TaskSubmission WHERE submissionGrade IS NOT NULL AND status = 'SUBMITTED'";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int grade = resultSet.getInt("submissionGrade");
                int rangeIndex = grade / 5; // Determine which range this grade belongs to
                if (rangeIndex >= 0 && rangeIndex < 20) {
                    gradeDistribution.put(rangeIndex, gradeDistribution.get(rangeIndex) + 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gradeDistribution;
    }

    public Map<Integer, Integer> getGradeDistributionByRange(int courseId) {
        Map<Integer, Integer> gradeDistribution = new HashMap<>();
        // Initialize all ranges to 0
        for (int i = 0; i < 20; i++) {
            gradeDistribution.put(i, 0);
        }

        String sql = "SELECT ts.submissionGrade FROM TaskSubmission ts JOIN Task t ON ts.taskId = t.taskId WHERE t.courseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int grade = resultSet.getInt("submissionGrade");
                    int rangeIndex = grade / 5; // Determine which range this grade belongs to
                    if (rangeIndex >= 0 && rangeIndex < 20) {
                        gradeDistribution.put(rangeIndex, gradeDistribution.get(rangeIndex) + 1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gradeDistribution;
    }

    // find course code via submission id
    public String findCourseCodeBySubmissionId(int submissionId) {
        String sql = "SELECT c.courseCode FROM TaskSubmission ts JOIN Task t ON ts.taskId = t.taskId JOIN Course c ON t.courseId = c.courseId WHERE ts.submissionId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, submissionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("courseCode");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // find course title via submission id
    public String findCourseTitleBySubmissionId(int submissionId) {
        String sql = "SELECT c.courseTitle FROM TaskSubmission ts JOIN Task t ON ts.taskId = t.taskId JOIN Course c ON t.courseId = c.courseId WHERE ts.submissionId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, submissionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("courseTitle");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // find task title via submission id
    public String findTaskTitleBySubmissionId(int submissionId) {
        String sql = "SELECT t.taskTitle FROM TaskSubmission ts JOIN Task t ON ts.taskId = t.taskId WHERE ts.submissionId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, submissionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("taskTitle");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // find by course id, return arr list of task submissions (all task submissions)
    public ArrayList<TaskSubmission> findByCourseId(int courseId) {
        ArrayList<TaskSubmission> submissions = new ArrayList<>();
        String sql = "SELECT ts.* FROM TaskSubmission ts JOIN Task t ON ts.taskId = t.taskId WHERE t.courseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TaskSubmission submission = createTaskSubmissionFromResultSet(resultSet);
                    submissions.add(submission);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return submissions;
    }
    


    // creates a task submission object from resultset, private
    private TaskSubmission createTaskSubmissionFromResultSet(ResultSet resultSet) throws SQLException {
        int submissionId = resultSet.getInt("submissionId");
        String submissionTitle = resultSet.getString("submissionTitle");
        String submissionContent = resultSet.getString("submissionContent");
        Integer submissionGrade = resultSet.getInt("submissionGrade");
        if (resultSet.wasNull()) {
            submissionGrade = null;
        }
        TaskStatus status = TaskStatus.valueOf(resultSet.getString("status"));
        int studentId = resultSet.getInt("studentId");
        int taskId = resultSet.getInt("taskId");
        return new TaskSubmission(submissionId, submissionTitle, submissionContent, submissionGrade, status, studentId, taskId);
    }    

    

}
