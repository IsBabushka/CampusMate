CREATE TABLE `course` (
  `courseID` int NOT NULL AUTO_INCREMENT,
  `courseCode` varchar(20) NOT NULL,
  `courseTitle` varchar(255) NOT NULL,
  PRIMARY KEY (`courseID`),
  UNIQUE KEY `courseCode` (`courseCode`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `courseroom` (
  `courseID` int NOT NULL,
  `roomCode` varchar(20) NOT NULL,
  PRIMARY KEY (`courseID`,`roomCode`),
  CONSTRAINT `courseroom_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `coursestudent` (
  `courseID` int NOT NULL,
  `studentID` int NOT NULL,
  PRIMARY KEY (`courseID`,`studentID`),
  KEY `studentID` (`studentID`),
  CONSTRAINT `coursestudent_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`) ON DELETE CASCADE,
  CONSTRAINT `coursestudent_ibfk_2` FOREIGN KEY (`studentID`) REFERENCES `user` (`userID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `courseteacher` (
  `courseID` int NOT NULL,
  `teacherID` int NOT NULL,
  PRIMARY KEY (`courseID`,`teacherID`),
  KEY `teacherID` (`teacherID`),
  CONSTRAINT `courseteacher_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`) ON DELETE CASCADE,
  CONSTRAINT `courseteacher_ibfk_2` FOREIGN KEY (`teacherID`) REFERENCES `user` (`userID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `session` (
  `sessionID` int NOT NULL AUTO_INCREMENT,
  `sessionTitle` varchar(255) NOT NULL,
  `courseID` int NOT NULL,
  PRIMARY KEY (`sessionID`),
  KEY `courseID` (`courseID`),
  CONSTRAINT `session_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `studentsession` (
  `studentID` int NOT NULL,
  `sessionID` int NOT NULL,
  `status` enum('PRESENT','LATE','ABSENT') NOT NULL,
  PRIMARY KEY (`studentID`,`sessionID`),
  KEY `sessionID` (`sessionID`),
  CONSTRAINT `studentsession_ibfk_1` FOREIGN KEY (`studentID`) REFERENCES `user` (`userID`) ON DELETE CASCADE,
  CONSTRAINT `studentsession_ibfk_2` FOREIGN KEY (`sessionID`) REFERENCES `session` (`sessionID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `task` (
  `taskID` int NOT NULL AUTO_INCREMENT,
  `taskTitle` varchar(255) NOT NULL,
  `taskPrompt` text,
  `courseID` int NOT NULL,
  PRIMARY KEY (`taskID`),
  KEY `courseID` (`courseID`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tasksubmission` (
  `submissionID` int NOT NULL AUTO_INCREMENT,
  `submissionTitle` varchar(255) DEFAULT NULL,
  `submissionContent` text,
  `submissionGrade` int DEFAULT NULL,
  `status` enum('SUBMITTED','NONE') NOT NULL,
  `studentID` int DEFAULT NULL,
  `taskID` int DEFAULT NULL,
  PRIMARY KEY (`submissionID`),
  KEY `studentID` (`studentID`),
  KEY `taskID` (`taskID`),
  CONSTRAINT `tasksubmission_ibfk_1` FOREIGN KEY (`studentID`) REFERENCES `user` (`userID`) ON DELETE CASCADE,
  CONSTRAINT `tasksubmission_ibfk_2` FOREIGN KEY (`taskID`) REFERENCES `task` (`taskID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=472 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `birthdate` date NOT NULL,
  `gender` varchar(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMINISTRATOR','STUDENT','TEACHER') NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `email` (`email`),
  CONSTRAINT `CHK_Gender` CHECK ((`gender` in (_utf8mb4'Male',_utf8mb4'Female',_utf8mb4'Other')))
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
