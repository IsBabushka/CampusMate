/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLibs;

/**
 *
 * @author allen
 */
import MyLibs.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private static final int NUM_COURSES = 16;
    private static final int NUM_TEACHERS = 8;
    private static final int NUM_STUDENTS = 100;

    public static void main(String[] args) {
        try {
            // Initialize DAOs
            UserDAO userDAO = new UserDAO();
            CourseDAO courseDAO = new CourseDAO();
            TaskDAO taskDAO = new TaskDAO();
            TaskSubmissionDAO taskSubmissionDAO = new TaskSubmissionDAO();
            SessionDAO sessionDAO = new SessionDAO();
            StudentSessionDAO studentSessionDAO = new StudentSessionDAO();

            // Create admin user
            User admin = createUser(userDAO, "Admin User", "admin@example.com", Role.ADMINISTRATOR, "admin");

            // Create teachers
            List<User> teachers = new ArrayList<>();
            for (int i = 1; i <= NUM_TEACHERS; i++) {
                teachers.add(createUser(userDAO, "Teacher " + i, "teacher" + i + "@example.com", Role.TEACHER, "admin"));
            }

            // Create students
            List<User> students = new ArrayList<>();
            for (int i = 1; i <= NUM_STUDENTS; i++) {
                students.add(createUser(userDAO, "Student " + i, "student" + i + "@example.com", Role.STUDENT, "admin"));
            }

            // Create courses
            List<Course> courses = new ArrayList<>();
            String[] courseTitles = {"Introduction to Programming", "Data Structures", "Algorithms", "Database Management", "Web Development", "Mobile App Development", "Operating Systems", "Computer Networks", "Calculus I", "Calculus II", "Linear Algebra", "Discrete Mathematics", "Physics I", "Physics II", "Chemistry", "Biology"};
            for (int i = 0; i < NUM_COURSES; i++) {
                courses.add(createCourse(courseDAO, "COURSE" + (i + 1), courseTitles[i]));
            }

            // Assign teachers to courses
            Random random = new Random();
            for (Course course : courses) {
                int numTeachersToAssign = random.nextInt(2) + 1; // Assign 1 or 2 teachers
                for (int i = 0; i < numTeachersToAssign; i++) {
                    User teacher = teachers.get(random.nextInt(teachers.size()));
                    courseDAO.assignTeacher(course.getCourseId(), teacher.getUserID());
                }
            }

            // Enroll students in courses
            for (User student : students) {
                int numCoursesToEnroll = random.nextInt(4) + 1; // Enroll in 1 to 4 courses
                for (int i = 0; i < numCoursesToEnroll; i++) {
                    Course course = courses.get(random.nextInt(courses.size()));
                    courseDAO.enrollStudent(course.getCourseId(), student.getUserID());
                }
            }

            // Create sessions for courses and student sessions
            for (Course course : courses) {
                for (int i = 1; i <= 3; i++) {
                    Session session = createSession(sessionDAO, "Session " + i, course.getCourseId());
                    List<User> enrolledStudents = courseDAO.findStudentsByCourseId(course.getCourseId());
                    for (User student : enrolledStudents) {
                        SessionStatus status = getRandomSessionStatus();
                        createStudentSession(studentSessionDAO, session.getSessionID(), student.getUserID(), status);
                    }
                }
            }

            // Create tasks and submissions
            for (Course course : courses) {
                for (int i = 1; i <= 2; i++) {
                    Task task = createTask(taskDAO, "Task " + i, "Description for Task " + i, course.getCourseId());
                    List<User> enrolledStudents = courseDAO.findStudentsByCourseId(course.getCourseId());
                    for (User student : enrolledStudents) {
                        createSubmission(taskSubmissionDAO, "Submission for Task " + i, "Content for Task " + i, null, TaskStatus.NONE, student.getUserID(), task.getTaskId());
                    }
                }
            }

            // Grade submissions with a bell curve distribution
            List<TaskSubmission> allSubmissions = new ArrayList<>();
            for (Course course : courses) {
                for (Task task : taskDAO.findByCourseId(course.getCourseId())) {
                    allSubmissions.addAll(taskSubmissionDAO.findByTaskId(task.getTaskId()));
                }
            }

            for (TaskSubmission submission : allSubmissions) {
                int grade = generateBellCurveGrade();
                gradeSubmission(taskSubmissionDAO, submission.getStudentId(), submission.getTaskId(), grade);
            }

            System.out.println("Data generation completed successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static User createUser(UserDAO userDAO, String name, String email, Role role, String password) throws SQLException {
        Random random = new Random();
        int year = random.nextInt(2005 - 1980) + 1980;
        int month = random.nextInt(12) + 1;
        int day = random.nextInt(28) + 1;
        Date birthdate = new Date(year - 1900, month - 1, day);
        String gender = random.nextBoolean() ? "Male" : "Female";
        User user = new User(name, birthdate, gender, email, password, role, null);
        userDAO.save(user);
        return userDAO.findByEmail(email);
    }

    private static Course createCourse(CourseDAO courseDAO, String courseCode, String courseTitle) throws SQLException {
        courseDAO.save(courseCode, courseTitle);
        return courseDAO.findAll().stream().filter(c -> c.getCourseCode().equals(courseCode)).findFirst().orElse(null);
    }

    private static Task createTask(TaskDAO taskDAO, String taskTitle, String taskPrompt, int courseId) throws SQLException {
        taskDAO.save(taskTitle, taskPrompt, courseId);
        return taskDAO.findByCourseId(courseId).stream().filter(t -> t.getTaskTitle().equals(taskTitle)).findFirst().orElse(null);
    }

    private static void createSubmission(TaskSubmissionDAO taskSubmissionDAO, String submissionTitle, String submissionContent, Integer submissionGrade, TaskStatus status, int studentId, int taskId) throws SQLException {
        taskSubmissionDAO.save(submissionTitle, submissionContent, submissionGrade, status, studentId, taskId);
    }

    private static void gradeSubmission(TaskSubmissionDAO taskSubmissionDAO, int studentId, int taskId, int grade) throws SQLException {
        List<TaskSubmission> submissions = taskSubmissionDAO.filterSubmissions(taskId, studentId, null, null);
        if (!submissions.isEmpty()) {
            TaskSubmission submission = submissions.get(0);
            taskSubmissionDAO.update(submission.getSubmissionId(), submission.getSubmissionTitle(), submission.getSubmissionContent(), grade, TaskStatus.SUBMITTED, submission.getStudentId(), submission.getTaskId());
        }
    }

    private static Session createSession(SessionDAO sessionDAO, String sessionTitle, int courseId) throws SQLException {
        sessionDAO.save(courseId, sessionTitle);
        return sessionDAO.findByCourseId(courseId).stream().filter(s -> s.getSessionTitle().equals(sessionTitle)).findFirst().orElse(null);
    }

    private static void createStudentSession(StudentSessionDAO studentSessionDAO, int sessionId, int studentId, SessionStatus status) throws SQLException {
        studentSessionDAO.save(sessionId, studentId, status);
    }

    private static SessionStatus getRandomSessionStatus() {
        Random random = new Random();
        int choice = random.nextInt(3);
        switch (choice) {
            case 0:
                return SessionStatus.PRESENT;
            case 1:
                return SessionStatus.LATE;
            default:
                return SessionStatus.ABSENT;
        }
    }

    private static int generateBellCurveGrade() {
        Random random = new Random();
        double u = random.nextDouble();
        double v = random.nextDouble();
        double num = Math.sqrt(-2.0 * Math.log(u)) * Math.cos(2.0 * Math.PI * v);
        double mean = 75;
        double sd = 15;
        double grade = mean + num * sd;
        return Math.max(0, Math.min(100, (int) Math.round(grade)));
    }
}
