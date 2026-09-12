# Online Quiz & Examination System

## 📌 Project Overview

The **Online Quiz & Examination System** is a Java-based desktop application designed to conduct and manage online examinations efficiently.

The system provides separate functionalities for **Students** and **Administrators**. Students can log in, attempt multiple-choice examinations, view their scores, and receive results automatically. Administrators can manage questions and view examination results.

The project is developed using **Java Swing** and demonstrates important Java programming concepts such as **Object-Oriented Programming, Collections, Exception Handling, Multithreading, File Handling, Interfaces, and Abstract Classes**.

---

## 🎯 Objectives

- To provide a simple platform for conducting online examinations.
- To automate evaluation and result generation.
- To provide separate Student and Admin functionalities.
- To implement a countdown timer for examinations.
- To demonstrate core Java and OOP concepts.
- To reduce manual effort in conducting and evaluating quizzes.

---

## ✨ Features

### 👨‍🎓 Student Module

- Student Login
- Student Registration
- Start Online Examination
- Multiple Choice Questions (MCQs)
- Countdown Examination Timer
- Automatic Submission when time expires
- Automatic Answer Evaluation
- Score Calculation
- Percentage Calculation
- Grade Generation
- View Examination Results

### 👨‍💼 Admin Module

- Admin Login
- Add Questions
- Remove Questions
- View Question Bank
- View Student Results
- Manage Examination Questions

---

## 🛠️ Technologies Used

- **Programming Language:** Java
- **GUI:** Java Swing
- **IDE:** IntelliJ IDEA 2026.2
- **JDK:** JDK 17 or higher
- **Data Structures:** ArrayList, HashMap
- **File Handling:** FileWriter / File I/O
- **Multithreading:** Java Thread
- **Exception Handling:** Custom Exceptions
- **Database:** Currently uses in-memory data and file storage

---

## 🧠 Java Concepts Demonstrated

This project covers several concepts from the Java syllabus:

### 1. Object-Oriented Programming

The project uses:

- Classes and Objects
- Encapsulation
- Inheritance
- Polymorphism
- Abstraction
- Constructors
- Method Overriding

### 2. Abstract Classes

An abstract `User` class is used as a base class for:

- Student
- Admin

### 3. Interfaces

Interfaces are used to define operations related to:

- Examination
- Question Management

### 4. Exception Handling

Custom exceptions are implemented for handling situations such as:

- Invalid Login
- Invalid Questions
- Exam Not Found

### 5. Collections

The project uses:

- `ArrayList` for storing questions and results
- `HashMap` for storing users

### 6. Multithreading

A separate thread is used to implement the **examination countdown timer**.

When the timer reaches zero, the examination is automatically submitted.

### 7. Synchronization

Synchronization is used while storing examination results to avoid conflicts when multiple operations access shared data.

### 8. File Handling

Student examination results are stored using Java file handling.

---

## 🔄 System Workflow

```text
                ONLINE QUIZ & EXAMINATION SYSTEM
                              |
              +---------------+---------------+
              |                               |
           STUDENT                          ADMIN
              |                               |
           Login                           Login
              |                               |
       Student Dashboard              Admin Dashboard
              |                               |
         Start Exam                 Manage Questions
              |                     View Questions
        MCQ Questions               View Results
              |
        Countdown Timer
              |
         Submit Exam
              |
       Automatic Evaluation
              |
       Score & Percentage
              |
          Final Result
              |
       Save Result to File
