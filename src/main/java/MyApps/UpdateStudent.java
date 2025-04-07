/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MyApps;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import MyLibs.Course;
import MyLibs.CourseDAO;
import MyLibs.Role;
import MyLibs.User;
import MyLibs.UserDAO;

/**
 *
 * @author allen
 */
public class UpdateStudent extends javax.swing.JFrame {

    private ArrayList<Integer> coursesToAdd = new ArrayList<>();
    private ArrayList<Integer> coursesToRemove = new ArrayList<>();
    private User studentToUpdate;
    
    /**
     * Creates new form UpdateTeacher
     */
    public UpdateStudent() {
        initComponents();
        updateCoursesTable();
        this.studentToUpdate = null; // Indicate that we are creating a new student
        jLabel1.setText("Create Student"); // Change the header text
    }

    public UpdateStudent(User student) {
        initComponents();
        updateCoursesTable();
        this.studentToUpdate = student; // Store the student being updated
        jLabel1.setText("Update Student"); // Change the header text
        populateFields(student); // Fill the fields with the student's data
        populateAssignedCoursesTable(student);
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
        jLabel2 = new javax.swing.JLabel();
        birthdateField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        genderField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        assignedCoursesTable = new javax.swing.JTable();
        addACourseButton = new javax.swing.JPanel();
        addACourseText = new javax.swing.JLabel();
        removeACourseButton = new javax.swing.JPanel();
        removeACourseText = new javax.swing.JLabel();
        saveButton = new javax.swing.JPanel();
        saveText = new javax.swing.JLabel();
        cancelButton = new javax.swing.JPanel();
        cancelText = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        allCoursesTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 900));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(249, 249, 249));

        jLabel1.setFont(new java.awt.Font("Helvetica", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(67, 67, 67));
        jLabel1.setText("Student Editor");

        jLabel2.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(67, 67, 67));
        jLabel2.setText("Full Name");

        birthdateField.setBackground(new java.awt.Color(255, 255, 255));
        birthdateField.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        birthdateField.setForeground(new java.awt.Color(67, 67, 67));
        birthdateField.setBorder(null);
        birthdateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                birthdateFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(67, 67, 67));
        jLabel3.setText("Date of Birth");

        nameField.setBackground(new java.awt.Color(255, 255, 255));
        nameField.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        nameField.setForeground(new java.awt.Color(67, 67, 67));
        nameField.setBorder(null);
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Helvetica", 0, 8)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(67, 67, 67));
        jLabel4.setText("YYYY-MM-DD");

        jLabel5.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(67, 67, 67));
        jLabel5.setText("Gender");

        genderField.setBackground(new java.awt.Color(255, 255, 255));
        genderField.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        genderField.setForeground(new java.awt.Color(67, 67, 67));
        genderField.setBorder(null);
        genderField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderFieldActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(67, 67, 67));
        jLabel6.setText("Email Address");

        emailField.setBackground(new java.awt.Color(255, 255, 255));
        emailField.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        emailField.setForeground(new java.awt.Color(67, 67, 67));
        emailField.setBorder(null);
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(67, 67, 67));
        jLabel8.setText("Assigned Courses");

        assignedCoursesTable.setBackground(new java.awt.Color(255, 255, 255));
        assignedCoursesTable.setForeground(new java.awt.Color(0, 0, 0));
        assignedCoursesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Course ID", "Course Code", "Course Title"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(assignedCoursesTable);

        addACourseButton.setBackground(new java.awt.Color(49, 53, 110));
        addACourseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addACourseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addACourseButtonMouseReleased(evt);
            }
        });

        addACourseText.setFont(new java.awt.Font("Helvetica", 1, 18)); // NOI18N
        addACourseText.setForeground(new java.awt.Color(255, 255, 255));
        addACourseText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addACourseText.setText("Add a Course");

        javax.swing.GroupLayout addACourseButtonLayout = new javax.swing.GroupLayout(addACourseButton);
        addACourseButton.setLayout(addACourseButtonLayout);
        addACourseButtonLayout.setHorizontalGroup(
            addACourseButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addACourseButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addACourseText, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        addACourseButtonLayout.setVerticalGroup(
            addACourseButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addACourseText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        removeACourseButton.setBackground(new java.awt.Color(194, 130, 130));
        removeACourseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeACourseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                removeACourseButtonMouseReleased(evt);
            }
        });

        removeACourseText.setFont(new java.awt.Font("Helvetica", 1, 18)); // NOI18N
        removeACourseText.setForeground(new java.awt.Color(255, 255, 255));
        removeACourseText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        removeACourseText.setText("Remove a Course");

        javax.swing.GroupLayout removeACourseButtonLayout = new javax.swing.GroupLayout(removeACourseButton);
        removeACourseButton.setLayout(removeACourseButtonLayout);
        removeACourseButtonLayout.setHorizontalGroup(
            removeACourseButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(removeACourseButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(removeACourseText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        removeACourseButtonLayout.setVerticalGroup(
            removeACourseButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(removeACourseText, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        saveButton.setBackground(new java.awt.Color(101, 139, 112));
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                saveButtonMouseReleased(evt);
            }
        });

        saveText.setFont(new java.awt.Font("Helvetica", 1, 18)); // NOI18N
        saveText.setForeground(new java.awt.Color(255, 255, 255));
        saveText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveText.setText("Save");

        javax.swing.GroupLayout saveButtonLayout = new javax.swing.GroupLayout(saveButton);
        saveButton.setLayout(saveButtonLayout);
        saveButtonLayout.setHorizontalGroup(
            saveButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saveButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saveText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        saveButtonLayout.setVerticalGroup(
            saveButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveText, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        cancelButton.setBackground(new java.awt.Color(194, 130, 130));
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelButtonMouseReleased(evt);
            }
        });

        cancelText.setFont(new java.awt.Font("Helvetica", 1, 18)); // NOI18N
        cancelText.setForeground(new java.awt.Color(255, 255, 255));
        cancelText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cancelText.setText("Cancel");

        javax.swing.GroupLayout cancelButtonLayout = new javax.swing.GroupLayout(cancelButton);
        cancelButton.setLayout(cancelButtonLayout);
        cancelButtonLayout.setHorizontalGroup(
            cancelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancelButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cancelText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cancelButtonLayout.setVerticalGroup(
            cancelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cancelText, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(67, 67, 67));
        jLabel9.setText("All Courses");

        allCoursesTable.setBackground(new java.awt.Color(255, 255, 255));
        allCoursesTable.setForeground(new java.awt.Color(0, 0, 0));
        allCoursesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Course ID", "Course Code", "Course Title", "Student Count", "Teacher Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(allCoursesTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(genderField)
                                .addGap(19, 19, 19))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeACourseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addACourseButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel4))
                                    .addComponent(birthdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(emailField)))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genderField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(birthdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(addACourseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeACourseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
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

    private void birthdateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_birthdateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_birthdateFieldActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void genderFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderFieldActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void addACourseButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addACourseButtonMouseReleased
        // TODO add your handling code here:
        // creates a prompt box, has the prompt "Course to add (Enter the code)" with a textfield below. once submitted, course code will be searched in the SQL, if there is a match, keep "assign teacher to course" in memory, update table to add the course
        String courseCode = JOptionPane.showInputDialog(this, "Course to add (Enter the code):", "Add Course", JOptionPane.QUESTION_MESSAGE);
        if (courseCode != null && !courseCode.trim().isEmpty()) {
            // Search for the course in the SQL database
            CourseDAO courseDAO;
            try {
                courseDAO = new CourseDAO();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            Course course = courseDAO.getCourseByCode(courseCode);
            if (course != null) {
                // Check if the course is already in the assigned courses table
                boolean alreadyAssigned = false;
                for (int i = 0; i < assignedCoursesTable.getRowCount(); i++) {
                    if (assignedCoursesTable.getValueAt(i, 1).equals(course.getCourseCode())) {
                        alreadyAssigned = true;
                        break;
                    }
                }
                if (!alreadyAssigned) {
                    // Add the course to the table
                    DefaultTableModel model = (DefaultTableModel) assignedCoursesTable.getModel();
                    model.addRow(new Object[]{course.getCourseId(), course.getCourseCode(), course.getCourseTitle()});

                    // Keep "assign teacher to course" in memory
                    coursesToAdd.add(course.getCourseId());
                    
                    if (coursesToRemove.contains(course.getCourseId())) {
                        coursesToRemove.remove(Integer.valueOf(course.getCourseId()));
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Course is already assigned.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Course not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        
    }//GEN-LAST:event_addACourseButtonMouseReleased

    private void removeACourseButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeACourseButtonMouseReleased
        // TODO add your handling code here:
        // creates a prompt box, has the prompt "Course to remove (Enter the code)" with a textfield below. once submitted, course code will be searched in the SQL, if there is a match, keep "unassign teacher to course" in memory, update table to remove the course
        int selectedRow = assignedCoursesTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Get the course ID from the selected row
            int courseId = (int) assignedCoursesTable.getValueAt(selectedRow, 0);

            // Remove the course from the table
            DefaultTableModel model = (DefaultTableModel) assignedCoursesTable.getModel();
            model.removeRow(selectedRow);

            // Keep "unassign teacher to course" in memory
            coursesToRemove.add(courseId);
            
            if (coursesToAdd.contains(courseId)) {
                coursesToAdd.remove(Integer.valueOf(courseId));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course to remove.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_removeACourseButtonMouseReleased

    private void saveButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseReleased
        // TODO add your handling code here:
        // using the ID of the row, update the teacher. if there is no id, then create. also do the "assign" or "unassign" here in this area
        UserDAO userDAO;
        CourseDAO courseDAO;
        try {
            userDAO = new UserDAO();
            courseDAO = new CourseDAO();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String name = nameField.getText();
        String birthdate = birthdateField.getText();
        String gender = genderField.getText();
        String email = emailField.getText();

        if (name.trim().isEmpty() || birthdate.trim().isEmpty() || gender.trim().isEmpty() || email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Date.valueOf(birthdate);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (studentToUpdate == null) {
            // Create a new student
            User newStudent = new User(name, Date.valueOf(birthdate), gender, email, "password", Role.STUDENT);
            userDAO.save(newStudent);

            // Retrieve the new student from the database to get the generated ID
            newStudent = userDAO.findByEmail(email);

            // Enroll courses to the new student
            for (int courseId : coursesToAdd) {
                courseDAO.enrollStudent(courseId, newStudent.getUserID());
            }
        } else {
            // Update the existing student
            studentToUpdate.setName(name);
            studentToUpdate.setBirthdate(Date.valueOf(birthdate));
            studentToUpdate.setGender(gender);
            studentToUpdate.setEmail(email);
            userDAO.update(studentToUpdate);

            // Enroll and unenroll courses
            for (int courseId : coursesToAdd) {
                courseDAO.enrollStudent(courseId, studentToUpdate.getUserID());
            }
            for (int courseId : coursesToRemove) {
                courseDAO.unenrollStudent(courseId, studentToUpdate.getUserID());
            }
        }

        this.dispose();

    }//GEN-LAST:event_saveButtonMouseReleased

    private void cancelButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseReleased
        // TODO add your handling code here:
        // just exit out
        this.dispose();
    }//GEN-LAST:event_cancelButtonMouseReleased

    private void updateCoursesTable() {
        CourseDAO courseDAO;
        try {
            courseDAO = new CourseDAO();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        DefaultTableModel allCoursesModel = (DefaultTableModel) allCoursesTable.getModel();
        allCoursesModel.setRowCount(0);

        ArrayList<Course> allCourses = courseDAO.getAllCourses();
        for (Course course : allCourses) {
            allCoursesModel.addRow(new Object[]{course.getCourseId(), course.getCourseCode(), course.getCourseTitle(), courseDAO.countStudentsByCourseId(course.getCourseId()), courseDAO.countTeachersByCourseId(course.getCourseId())});
        }

    }

    private void populateAssignedCoursesTable(User student) {
        if (student == null) return; // Exit if student is null

        DefaultTableModel model = (DefaultTableModel) assignedCoursesTable.getModel();
        model.setRowCount(0); // Clear existing rows

        CourseDAO courseDAO;
        try {
            courseDAO = new CourseDAO();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        ArrayList<Course> assignedCourses = courseDAO.findCoursesByStudentId(student.getUserID());
        for (Course course : assignedCourses) {
            model.addRow(new Object[]{course.getCourseId(), course.getCourseCode(), course.getCourseTitle()});
        }
    }   

    private void populateFields(User student) {
        nameField.setText(student.getName());
        birthdateField.setText(student.getBirthdate().toString());
        genderField.setText(student.getGender());
        emailField.setText(student.getEmail());
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
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addACourseButton;
    private javax.swing.JLabel addACourseText;
    private javax.swing.JTable allCoursesTable;
    private javax.swing.JTable assignedCoursesTable;
    private javax.swing.JTextField birthdateField;
    private javax.swing.JPanel cancelButton;
    private javax.swing.JLabel cancelText;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField genderField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField nameField;
    private javax.swing.JPanel removeACourseButton;
    private javax.swing.JLabel removeACourseText;
    private javax.swing.JPanel saveButton;
    private javax.swing.JLabel saveText;
    // End of variables declaration//GEN-END:variables
}
