# CampusMate

#### Video Demo: [Campus Mate | CS50x Final Project](https://youtu.be/xNiaF0I_n5w)

## Table of Contents
- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
  - [Admin Dashboard](#admin-dashboard)
  - [Teacher Dashboard](#teacher-dashboard)
  - [Student Dashboard](#student-dashboard)
- [Data Management](#data-management)
- [Security](#security)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

## Introduction

CampusMate is a robust Java-based application designed to streamline campus management for administrators, teachers, and students alike. It offers a seamless experience across multiple user roles, providing tailored dashboards and tools to meet the specific needs of each user group. CampusMate aims to simplify complex processes and provide actionable insights, empowering educational institutions to focus on learning and growth.

## Technologies Used

CampusMate leverages a variety of technologies to ensure robustness, scalability, and user-friendliness:

-   **Java:** Core programming language for backend logic and database interactions through Data Access Objects (DAOs).
-   **JFreeChart:** For generating dynamic charts and visualizations in the admin dashboard.
-   **Java Swing:** For creating intuitive and responsive user interfaces.
-   **BCrypt:** For secure password hashing.
-   **MySQL:** For reliable data storage and management.
-   **Object-Oriented Design:** Ensuring modular and loosely coupled code structure.

## Project Structure

The project is structured to maintain clarity and ease of navigation:

-   `src/MyApps`: Contains the main application classes and forms.
-   `src/MyLibs`: Houses utility classes and data access objects.
-   `src/Icons`: Stores icons used in the application.
-   `db`: Contains SQL script for schema creation.

## Installation

To install and run CampusMate, follow these steps:

### Prerequisites

-   Ensure you have Java Development Kit (JDK) installed.
-   Install MySQL and set up a database.
-   Install an IDE like IntelliJ IDEA, Eclipse, or Apache NetBeans.

### Clone the Repository

```bash
git clone https://github.com/IsBabushka/CampusMate.git  
cd CampusMate
```

### Database Setup

1.  Create a MySQL database named `campusmate`.
2.  Run the SQL script located in `db/schema.sql` to set up the database schema.

### Build and Run

1.  Open the project in your IDE.
2.  Build the project and run the `CampusMate` class.

## Usage

CampusMate is designed to be user-friendly, with intuitive navigation and clear instructions. Here’s a quick guide to get you started:

-   **Login Screen:** Access the application through the login screen using your credentials.
-   **Admin Dashboard:** Manage students, teachers, and courses. Generate reports and visualize data.
-   **Teacher Dashboard:** Manage course-specific attendance, tasks, and student performance.
-   **Student Dashboard:** View enrolled courses, submit tasks, and manage your profile.

## Features

CampusMate offers a wide range of features to cater to the needs of administrators, teachers, and students:

### Admin Dashboard

-   **View Key Metrics:** Quickly access total student count, total teacher count, and overall attendance rates.
-   **Generate Reports:** Export data as CSV files or generate detailed individual reports for students and teachers.
-   **Manage Entities:** Add, edit, or remove students, teachers, and courses with ease.
-   **Visualize Data:** Use JFreeChart to create dynamic charts for attendance rates per class and grade distribution.

### Teacher Dashboard

-   **Manage Attendance:** Mark students as present, late, or absent and create or modify attendance sessions.
-   **Task Management:** Create new tasks, update existing ones, or remove them entirely. Grade student submissions and filter by various criteria.
-   **View Performance Metrics:** Access detailed performance metrics for each student, including average grades and attendance rates.
-   **Profile Management:** Update personal information and change passwords securely.

### Student Dashboard

-   **View Courses:** Access enrolled courses with average grades and attendance rates.
-   **Submit Assignments:** Submit tasks and review previous submissions.
-   **Edit Profile:** Update personal information and change passwords.

## Data Management

CampusMate ensures efficient data management through:

-   **Relational Database:** Uses MySQL for structured data storage.
-   **Data Access Objects (DAOs):** Facilitate interaction with the database, ensuring data integrity and security.
-   **Export Options:** Allows administrators to export data as CSV files for further analysis.

## Security

CampusMate prioritizes security through:

-   **BCrypt:** Ensures secure password hashing.
-   **Role-Based Access Control:** Restricts access based on user roles (admin, teacher, student).
-   **Data Encryption:** Protects sensitive data during transmission and storage.

## Contributing

Contributions to CampusMate are welcome. To contribute, follow these steps:

1.  Fork the repository.
2.  Create a new branch for your feature or fix (`git checkout -b feature/AmazingFeature`).
3.  Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4.  Push to the branch (`git push origin feature/AmazingFeature`).
5.  Open a Pull Request.

## License

CampusMate is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgements

Special thanks to my friend Kirby, whose encouragement throughout the course have been incredibly valuable (goodluck with Tideman). This project also benefited from the feedback of previous classmates. Your support made CampusMate possible. 
