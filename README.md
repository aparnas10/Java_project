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


--

## 🛠️ Technologies Used

* **Language:** Java
* **GUI:** Java Swing
* **IDE:** IntelliJ IDEA
* **Collections:** HashMap, ArrayList
* **File Handling:** FileWriter
* **Multithreading:** Java Thread
* **Exception Handling:** Custom Exceptions
* **OOP Concepts:** Inheritance, Abstraction, Encapsulation, Polymorphism
* **Interfaces:** QuestionOperations, ExamOperations

## 📂 Project Structure

The project is implemented in a single `Main.java` file with multiple classes:

```text
Main.java
│
├── QuestionOperations
├── ExamOperations
├── User
├── Student
├── Admin
├── Question
├── Exam
├── Result
├── InvalidLoginException
├── ExamNotFoundException
├── InvalidQuestionException
├── DataStore
├── FileManager
├── LoginFrame
├── RegisterFrame
├── StudentDashboard
├── AdminDashboard
├── ExamFrame
├── TimerThread
└── ResultFrame
```

## 🔐 Default Login Credentials

### Admin

```text
Username: admin
Password: admin123
```

### Student

```text
Username: student
Password: student123
```

The application also allows new students to register through the registration interface.

## 🎯 Application Workflow

```text
             ┌───────────────┐
             │    Login      │
             └───────┬───────┘
                     │
          ┌──────────┴──────────┐
          │                     │
     ┌────▼─────┐         ┌─────▼────┐
     │  Student │         │   Admin  │
     └────┬─────┘         └─────┬────┘
          │                     │
    ┌─────▼──────┐        ┌─────▼────────┐
    │ Start Exam │        │Manage Questions│
    └─────┬──────┘        └─────┬────────┘
          │                     │
    ┌─────▼──────┐        ┌─────▼────────┐
    │  Timer +   │        │ View Results  │
    │ Questions  │        └───────────────┘
    └─────┬──────┘
          │
    ┌─────▼──────┐
    │  Submit    │
    │   Exam     │
    └─────┬──────┘
          │
    ┌─────▼──────┐
    │  Result &  │
    │   Grade    │
    └────────────┘
```

## 🧠 OOP Concepts Demonstrated

### 1. Encapsulation

Private variables are used inside classes with public getter methods.

Example:

```java
private String username;
private String password;
```

### 2. Inheritance

`Student` and `Admin` inherit from the abstract `User` class.

```java
static class Student extends User
static class Admin extends User
```

### 3. Abstraction

The `User` class is declared abstract and contains an abstract method:

```java
public abstract void showDashboard();
```

### 4. Polymorphism

Different classes provide their own implementation of `showDashboard()`.

### 5. Interfaces

The project uses interfaces to define operations for questions and examinations:

```java
interface QuestionOperations
interface ExamOperations
```

## 📚 Java Concepts Used

* Classes and Objects
* Constructors
* Access Modifiers
* Abstract Classes
* Interfaces
* Method Overriding
* Collections Framework
* ArrayList
* HashMap
* Exception Handling
* Custom Exceptions
* File Handling
* Multithreading
* Lambda Expressions
* Java Swing GUI
* Event Handling

## ⏱️ Examination Timer

The examination includes a countdown timer implemented using a separate `TimerThread`. The timer updates every second and automatically submits the examination when the time reaches zero.

## 💾 Result Storage

After an examination is submitted, the result is stored in memory and also written to:

```text
quiz_results.txt
```

The project uses `FileWriter` to append examination results to the file.

## 📊 Grading System

| Percentage    | Grade |
| ------------- | ----- |
| 90% and above | A+    |
| 80% – 89%     | A     |
| 70% – 79%     | B     |
| 60% – 69%     | C     |
| 50% – 59%     | D     |
| Below 50%     | F     |

## ▶️ How to Run

### Using IntelliJ IDEA

1. Open **IntelliJ IDEA**.
2. Create or open the Java project.
3. Place `Main.java` inside the `src` folder.
4. Make sure Java SDK is configured.
5. Open `Main.java`.
6. Run the `main()` method.
7. The Login window will appear.

### Using Command Line

Compile:

```bash
javac Main.java
```

Run:

```bash
java Main
```

## 🖥️ Main Modules

### Login Module

Authenticates users and redirects them to the appropriate dashboard.

### Registration Module

Allows new students to create an account.

### Student Module

Allows students to start exams, answer questions, submit exams and view results.

### Admin Module

Allows administrators to add/remove questions and view student results.

### Examination Module

Displays MCQ questions, tracks answers and calculates the final score.

### Result Module

Displays the student's score, percentage and grade.

## 🔮 Future Enhancements

The project can be further improved by adding:

* MySQL/SQLite database integration
* Multiple subjects and examinations
* Admin authentication with encrypted passwords
* Randomized questions
* Question categories
* Negative marking
* Exam history
* Student leaderboard
* More advanced GUI design
* Export results to PDF
* Online/cloud-based examination support
* User profile management

**Aparna Singh**
--

**25BAI10270**
--

B.Tech – Computer Science & Engineering (AI & ML)



  
