/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MyApps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import MyLibs.Course;
import MyLibs.CourseDAO;
import MyLibs.Role;
import MyLibs.StudentSessionDAO;
import MyLibs.TaskSubmissionDAO;
import MyLibs.User;

/**
 *
 * @author allen
 */
public class ExportAsTXT extends javax.swing.JFrame {

    private User user;
    private Course course;

    /**
     * Creates new form ExportAsCSVForm
     */
    // Constructor for generating student report
    public ExportAsTXT(User user) {
        initComponents();
        this.user = user;
        try {
            generateReportPreview(); // Changed method name
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Constructor for generating course report
    public ExportAsTXT(Course course) {
        initComponents();
        this.course = course;
        try {
            generateReportPreview(); // Changed method name
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTextArea = new javax.swing.JTextArea();
        downloadTxtFileButton = new javax.swing.JPanel();
        downloadTxtFileText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 700));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(67, 67, 67));
        jLabel1.setText("Generate Student Report");

        txtTextArea.setEditable(false);
        txtTextArea.setBackground(new java.awt.Color(255, 255, 255));
        txtTextArea.setColumns(20);
        txtTextArea.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        txtTextArea.setForeground(new java.awt.Color(67, 67, 67));
        txtTextArea.setRows(5);
        txtTextArea.setBorder(null);
        jScrollPane1.setViewportView(txtTextArea);

        downloadTxtFileButton.setBackground(new java.awt.Color(49, 53, 110));
        downloadTxtFileButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        downloadTxtFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                downloadTxtFileButtonMouseReleased(evt);
            }
        });

        downloadTxtFileText.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        downloadTxtFileText.setForeground(new java.awt.Color(255, 255, 255));
        downloadTxtFileText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        downloadTxtFileText.setText("Download as .txt");

        javax.swing.GroupLayout downloadTxtFileButtonLayout = new javax.swing.GroupLayout(downloadTxtFileButton);
        downloadTxtFileButton.setLayout(downloadTxtFileButtonLayout);
        downloadTxtFileButtonLayout.setHorizontalGroup(
            downloadTxtFileButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(downloadTxtFileButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(downloadTxtFileText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        downloadTxtFileButtonLayout.setVerticalGroup(
            downloadTxtFileButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(downloadTxtFileText, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(downloadTxtFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(downloadTxtFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void downloadTxtFileButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downloadTxtFileButtonMouseReleased
        String txtData = txtTextArea.getText();
        if (txtData.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No data to export.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        String defaultFileName = null;
        if (user != null) {
            defaultFileName = "report_" + user.getUserID() + ".txt"; // Use student ID
        } else if (course != null) {
            defaultFileName = "course_report_" + course.getCourseCode() + ".txt"; // Use course code
        }
        
        fileChooser.setSelectedFile(new File(defaultFileName)); 
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Ensure the file has the .txt extension (this part remains the same)
            if (!filePath.toLowerCase().endsWith(".txt")) {
                filePath += ".txt";
                fileToSave = new File(filePath);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(txtData);
                JOptionPane.showMessageDialog(this, "Data exported successfully to: " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_downloadTxtFileButtonMouseReleased

    private void generateReportPreview() throws SQLException {
        StringBuilder reportData = new StringBuilder();

        if (user != null) {
            // if user is student, generateStudentReport, if teacher then generateTeacherReport
            jLabel1.setText(user.getName() + "'s Report");
            if (user.getRole() == Role.STUDENT) {
                generateStudentReport(reportData, user);
            } else if (user.getRole() == Role.TEACHER) {
                generateTeacherReport(reportData, user);
            }
        } else if (course != null) {
            // Generate report for a specific course
            jLabel1.setText(course.getCourseCode() + " Report");
            generateCourseReport(reportData, course);
        } else {
            // Handle the case where neither user nor course is specified (shouldn't happen with current design)
            reportData.append("Error: No user or course specified.");
        }

        txtTextArea.setText(reportData.toString());
    }


    private void generateCourseReport(StringBuilder reportData, Course course) throws SQLException {
        CourseDAO courseDAO = new CourseDAO();
        TaskSubmissionDAO taskSubmissionDAO = new TaskSubmissionDAO();
        StudentSessionDAO studentSessionDAO = new StudentSessionDAO();
        ArrayList<User> teachers = courseDAO.findTeachersByCourseId(course.getCourseId());
        ArrayList<User> students = courseDAO.findStudentsByCourseId(course.getCourseId());

        reportData.append("Course Code: ").append(course.getCourseCode()).append("\n");
        reportData.append("Course Title: ").append(course.getCourseTitle()).append("\n");
        reportData.append("Teachers Assigned:\n");
        if (teachers != null && !teachers.isEmpty()) {
            for (User t : teachers) {
                reportData.append("- ").append(t.getName()).append("\n");
            }
        } else {
            reportData.append("- None\n");
        }
        reportData.append("\n");


        reportData.append("Enrollment Summary:\n");
        reportData.append("Total Students: ").append(students.size()).append("\n");
        reportData.append("\n");

        reportData.append("Attendance Summary:\n");
        double attendanceRate = studentSessionDAO.getAttendanceRateForCourse(course.getCourseId()) * 100;
        reportData.append("- Attendance Rate: ").append(String.format("%.0f%%", attendanceRate)).append("\n");
        reportData.append("\n");

        reportData.append("Grade Summary:\n");
        double averageGrade = taskSubmissionDAO.getAverageGradeForCourse(course.getCourseId());
        reportData.append("- Average Grade: ").append(String.format("%.0f%%", averageGrade)).append("\n");
        reportData.append("\n");

        reportData.append("Detailed Grades:\n");
        for (User student : students) {
            double studentGrade = taskSubmissionDAO.getAverageGradeForStudentInCourse(student.getUserID(), course.getCourseId());
            reportData.append("- ").append(student.getName()).append(" - ").append(String.format("%.2f%%", studentGrade)).append("\n");
        }
    }

    private void generateStudentReport(StringBuilder reportData, User user) throws SQLException {
        StudentSessionDAO studentSessionDAO = new StudentSessionDAO();
        TaskSubmissionDAO taskSubmissionDAO = new TaskSubmissionDAO();
        CourseDAO courseDAO = new CourseDAO();

        reportData.append("Student Name: ").append(user.getName()).append("\n");
        reportData.append("Email: ").append(user.getEmail()).append("\n");
        reportData.append("Gender: ").append(user.getGender()).append("\n");
        reportData.append("Date of Birth: ").append(user.getBirthdate()).append("\n\n");

        // Attendance Summary
        int daysPresent = studentSessionDAO.countPresentDays(user.getUserID());
        int daysAbsent = studentSessionDAO.countAbsentDays(user.getUserID());
        int daysLate = studentSessionDAO.countLateDays(user.getUserID());
        double attendanceRate = studentSessionDAO.getOverallAttendanceRateForUser(user.getUserID()) * 100;

        reportData.append("Attendance Summary:\n");
        reportData.append("- Days Present: ").append(daysPresent).append("\n");
        reportData.append("- Days Absent: ").append(daysAbsent).append("\n");
        reportData.append("- Days Late: ").append(daysLate).append("\n");
        reportData.append("- Attendance Rate: ").append(String.format("%.0f%%", attendanceRate)).append("\n\n");

        // Grade Summary
        double averageGrade = taskSubmissionDAO.getOverallAverageGrade(user.getUserID());

        reportData.append("Grade Summary:\n");
        reportData.append("- Average Grade: ").append(String.format("%.0f%%", averageGrade)).append("\n\n");

        // Enrolled Courses
        reportData.append("Enrolled Courses: \n");
        ArrayList<Course> courses = courseDAO.findCoursesByStudentId(user.getUserID());
        for (Course course : courses) {
            reportData.append(course.getCourseCode()).append(": \n");
            double courseAverageGrade = taskSubmissionDAO.getAverageGradeForStudentInCourse(user.getUserID(), course.getCourseId());
            double courseAttendanceRate = studentSessionDAO.getAttendanceRateForStudentInCourse(user.getUserID(), course.getCourseId()) * 100;
            reportData.append("- Grade - ").append(String.format("%.2f", courseAverageGrade)).append("\n");
            reportData.append("- Attendance Rate - ").append(String.format("%.0f%%", courseAttendanceRate)).append("\n");
            reportData.append("\n");
        }

        // Detailed Grades (Example - replace with your actual detailed grade retrieval logic)
        reportData.append("Detailed Grades:\n");
        ArrayList<String> detailedGrades = taskSubmissionDAO.getDetailedGrades(user.getUserID());
        for (String grade : detailedGrades) {
            reportData.append(grade).append("\n");
        }
    }

    private void generateTeacherReport(StringBuilder reportData, User user) throws SQLException {
        StudentSessionDAO studentSessionDAO = new StudentSessionDAO();
        TaskSubmissionDAO taskSubmissionDAO = new TaskSubmissionDAO();
        CourseDAO courseDAO = new CourseDAO();

        reportData.append("Teacher Name: ").append(user.getName()).append("\n");
        reportData.append("Email: ").append(user.getEmail()).append("\n");
        reportData.append("Date of Birth: ").append(user.getBirthdate()).append("\n");
        reportData.append("Department: ").append(user.getDepartment()).append("\n\n");

        // Workload Summary
        ArrayList<Course> courses = courseDAO.getCoursesByTeacherId(user.getUserID());
        int numCourses = courses.size();
        int totalStudents = 0;
        for (Course c : courses) {
            totalStudents += courseDAO.countStudentsByCourseId(c.getCourseId());
        }

        reportData.append("Workload Summary:\n");
        reportData.append("- Courses Assigned: ").append(numCourses).append("\n");
        reportData.append("- Students Taught: ").append(totalStudents).append("\n\n");

        // Attendance Monitoring
        reportData.append("Attendance Monitoring:\n");
        for (Course course : courses) {
            double attendanceRate = studentSessionDAO.getAttendanceRateForCourse(course.getCourseId()) * 100;
            reportData.append(course.getCourseCode()).append(": ").append(String.format("%.0f%%", attendanceRate)).append(" attendance rate\n");
        }
        reportData.append("\n");

        // Grade Distribution per Course
        for (Course course : courses) {
            reportData.append("Grade Distribution per Course Assigned: ").append(course.getCourseCode()).append("\n");
            Map<Integer, Integer> gradeDistribution = taskSubmissionDAO.getGradeDistributionByRange(course.getCourseId()); // Modified method call

            for (int i = 0; i < 20; i++) {
                int lowerBound = i * 5;
                int upperBound = lowerBound + 4;
                String rangeLabel = String.format("%d-%d", lowerBound, upperBound);
                int count = gradeDistribution.getOrDefault(i, 0);
                double percentage = totalStudents > 0 ? (double) count / totalStudents * 100 : 0;
                reportData.append(rangeLabel).append(" - ").append(count).append(" (").append(String.format("%.0f%%", percentage)).append(")\n");
            }
            reportData.append("\n");
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ExportAsTXT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExportAsTXT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExportAsTXT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExportAsTXT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new ExportAsCSV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel downloadTxtFileButton;
    private javax.swing.JLabel downloadTxtFileText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtTextArea;
    // End of variables declaration//GEN-END:variables
}
