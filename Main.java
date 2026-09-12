import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }

    interface QuestionOperations {
        void addQuestion(Question question);
        void removeQuestion(int questionId);
    }

    interface ExamOperations {
        void startExam();
        void submitExam();
    }

    // =========================================================
    // ABSTRACT USER CLASS
    // =========================================================

    static abstract class User {

        private String username;
        private String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public abstract void showDashboard();
    }


    // =========================================================
    // STUDENT CLASS
    // =========================================================

    static class Student extends User {

        private String name;

        public Student(String username, String password, String name) {
            super(username, password);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void showDashboard() {
            new StudentDashboard(this);
        }
    }
    // =========================================================
    // ADMIN CLASS
    // =========================================================

    static class Admin extends User {

        public Admin(String username, String password) {
            super(username, password);
        }

        @Override
        public void showDashboard() {
            new AdminDashboard();
        }
    }


    // =========================================================
    // QUESTION CLASS
    // =========================================================

    static class Question {

        private int id;
        private String question;
        private String optionA;
        private String optionB;
        private String optionC;
        private String optionD;
        private String correctAnswer;

        public Question(
                int id,
                String question,
                String optionA,
                String optionB,
                String optionC,
                String optionD,
                String correctAnswer) {

            this.id = id;
            this.question = question;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.optionD = optionD;
            this.correctAnswer = correctAnswer;
        }

        public int getId() {
            return id;
        }

        public String getQuestion() {
            return question;
        }

        public String getOptionA() {
            return optionA;
        }

        public String getOptionB() {
            return optionB;
        }

        public String getOptionC() {
            return optionC;
        }

        public String getOptionD() {
            return optionD;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        @Override
        public String toString() {
            return id + ". " + question;
        }
    }

    // =========================================================
    // EXAM CLASS
    // =========================================================

    static class Exam {

        private String title;
        private int duration;
        private List<Question> questions;

        public Exam(String title, int duration, List<Question> questions) {
            this.title = title;
            this.duration = duration;
            this.questions = questions;
        }

        public String getTitle() {
            return title;
        }

        public int getDuration() {
            return duration;
        }

        public List<Question> getQuestions() {
            return questions;
        }
    }

    // =========================================================
    // RESULT CLASS
    // =========================================================

    static class Result {

        private String studentName;
        private int score;
        private int total;

        public Result(String studentName, int score, int total) {
            this.studentName = studentName;
            this.score = score;
            this.total = total;
        }

        public double getPercentage() {
            return ((double) score / total) * 100;
        }

        @Override
        public String toString() {
            return "Student: " + studentName +
                    "\nScore: " + score + "/" + total +
                    "\nPercentage: " +
                    String.format("%.2f", getPercentage()) + "%";
        }
    }


    // =========================================================
    // CUSTOM EXCEPTIONS
    // =========================================================

    static class InvalidLoginException extends Exception {

        public InvalidLoginException(String message) {
            super(message);
        }
    }

    static class ExamNotFoundException extends Exception {

        public ExamNotFoundException(String message) {
            super(message);
        }
    }

    static class InvalidQuestionException extends Exception {

        public InvalidQuestionException(String message) {
            super(message);
        }
    }


    // =========================================================
    // DATABASE-LIKE DATA STORAGE
    // =========================================================

    static class DataStore {

        // HashMap demonstrates Collections
        static HashMap<String, User> users = new HashMap<>();

        // ArrayList demonstrates Collections
        static ArrayList<Question> questions = new ArrayList<>();

        static ArrayList<Result> results = new ArrayList<>();

        static {

            // Default users

            users.put(
                    "admin",
                    new Admin("admin", "admin123")
            );

            users.put(
                    "student",
                    new Student(
                            "student",
                            "student123",
                            "Student"
                    )
            );


            // Default questions

            questions.add(
                    new Question(
                            1,
                            "Which language is used for Android development?",
                            "Java",
                            "HTML",
                            "CSS",
                            "SQL",
                            "A"
                    )
            );

            questions.add(
                    new Question(
                            2,
                            "Which method is the entry point of a Java program?",
                            "start()",
                            "run()",
                            "main()",
                            "execute()",
                            "C"
                    )
            );

            questions.add(
                    new Question(
                            3,
                            "Which class is the parent class of all Java classes?",
                            "Main",
                            "Class",
                            "Object",
                            "Parent",
                            "C"
                    )
            );

            questions.add(
                    new Question(
                            4,
                            "Which keyword is used to handle exceptions?",
                            "try",
                            "check",
                            "error",
                            "exception",
                            "A"
                    )
            );

            questions.add(
                    new Question(
                            5,
                            "Which concept allows the same method to behave differently?",
                            "Encapsulation",
                            "Polymorphism",
                            "Abstraction",
                            "Compilation",
                            "B"
                    )
            );
        }


        public static void registerStudent(
                String username,
                String password,
                String name)
                throws InvalidQuestionException {

            if (username.isEmpty() ||
                    password.isEmpty() ||
                    name.isEmpty()) {

                throw new InvalidQuestionException(
                        "All fields are required."
                );
            }

            if (users.containsKey(username)) {

                throw new InvalidQuestionException(
                        "Username already exists."
                );
            }

            users.put(
                    username,
                    new Student(username, password, name)
            );
        }


        public static User login(
                String username,
                String password)
                throws InvalidLoginException {

            User user = users.get(username);

            if (user == null ||
                    !user.getPassword().equals(password)) {

                throw new InvalidLoginException(
                        "Invalid username or password."
                );
            }

            return user;
        }


        // synchronized method demonstrates synchronization
        public static synchronized void saveResult(Result result) {

            results.add(result);

            FileManager.saveResult(result);
        }
    }


    // =========================================================
    // FILE MANAGER
    // =========================================================

    static class FileManager {

        public static void saveResult(Result result) {

            try {

                FileWriter writer =
                        new FileWriter("quiz_results.txt", true);

                writer.write(
                        "====================================\n"
                );

                writer.write(
                        result.toString() + "\n"
                );

                writer.write(
                        "====================================\n"
                );

                writer.close();

            } catch (IOException e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Could not save result file."
                );
            }
        }
    }


    // =========================================================
    // LOGIN FRAME
    // =========================================================

    static class LoginFrame extends JFrame {

        JTextField usernameField;
        JPasswordField passwordField;

        public LoginFrame() {

            setTitle("Online Quiz & Examination System");

            setSize(500, 400);

            setLocationRelativeTo(null);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setLayout(new BorderLayout());


            JLabel heading =
                    new JLabel(
                            "ONLINE QUIZ & EXAMINATION SYSTEM",
                            SwingConstants.CENTER
                    );

            heading.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            22
                    )
            );

            add(heading, BorderLayout.NORTH);


            JPanel panel = new JPanel();

            panel.setLayout(
                    new GridLayout(
                            5,
                            2,
                            10,
                            10
                    )
            );

            panel.setBorder(
                    BorderFactory.createEmptyBorder(
                            40,
                            50,
                            40,
                            50
                    )
            );


            panel.add(
                    new JLabel("Username:")
            );

            usernameField =
                    new JTextField();

            panel.add(usernameField);


            panel.add(
                    new JLabel("Password:")
            );

            passwordField =
                    new JPasswordField();

            panel.add(passwordField);


            JButton loginButton =
                    new JButton("LOGIN");

            JButton registerButton =
                    new JButton("REGISTER");


            panel.add(loginButton);
            panel.add(registerButton);


            JLabel demo =
                    new JLabel(
                            "<html>Admin: admin / admin123<br>" +
                                    "Student: student / student123</html>"
                    );

            panel.add(demo);


            add(panel, BorderLayout.CENTER);


            loginButton.addActionListener(
                    e -> login()
            );


            registerButton.addActionListener(
                    e -> {

                        dispose();

                        new RegisterFrame();
                    }
            );


            setVisible(true);
        }


        private void login() {

            String username =
                    usernameField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );


            try {

                User user =
                        DataStore.login(
                                username,
                                password
                        );

                dispose();

                user.showDashboard();

            } catch (InvalidLoginException e) {

                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }


    // =========================================================
    // REGISTER FRAME
    // =========================================================

    static class RegisterFrame extends JFrame {

        JTextField nameField;
        JTextField usernameField;
        JPasswordField passwordField;

        public RegisterFrame() {

            setTitle("Student Registration");

            setSize(450, 350);

            setLocationRelativeTo(null);

            setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );


            JPanel panel = new JPanel(
                    new GridLayout(
                            5,
                            2,
                            10,
                            10
                    )
            );

            panel.setBorder(
                    BorderFactory.createEmptyBorder(
                            30,
                            40,
                            30,
                            40
                    )
            );


            panel.add(
                    new JLabel("Full Name:")
            );

            nameField = new JTextField();

            panel.add(nameField);


            panel.add(
                    new JLabel("Username:")
            );

            usernameField =
                    new JTextField();

            panel.add(usernameField);


            panel.add(
                    new JLabel("Password:")
            );

            passwordField =
                    new JPasswordField();

            panel.add(passwordField);


            JButton register =
                    new JButton("REGISTER");

            JButton back =
                    new JButton("BACK");


            panel.add(register);
            panel.add(back);


            register.addActionListener(
                    e -> registerStudent()
            );


            back.addActionListener(
                    e -> {

                        dispose();

                        new LoginFrame();
                    }
            );


            add(panel);

            setVisible(true);
        }


        private void registerStudent() {

            try {

                DataStore.registerStudent(
                        nameField.getText().trim(),
                        new String(
                                passwordField.getPassword()
                        ),
                        nameField.getText().trim()
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Registration successful!"
                );

                dispose();

                new LoginFrame();

            } catch (InvalidQuestionException e) {

                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage()
                );
            }
        }
    }


    // =========================================================
    // STUDENT DASHBOARD
    // =========================================================

    static class StudentDashboard extends JFrame {

        private Student student;

        public StudentDashboard(Student student) {

            this.student = student;

            setTitle(
                    "Student Dashboard - " +
                            student.getName()
            );

            setSize(600, 450);

            setLocationRelativeTo(null);

            setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );


            JPanel panel =
                    new JPanel(
                            new GridLayout(
                                    5,
                                    1,
                                    20,
                                    20
                            )
                    );

            panel.setBorder(
                    BorderFactory.createEmptyBorder(
                            50,
                            100,
                            50,
                            100
                    )
            );


            JLabel welcome =
                    new JLabel(
                            "Welcome, " +
                                    student.getName(),
                            SwingConstants.CENTER
                    );

            welcome.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            22
                    )
            );


            JButton start =
                    new JButton(
                            "START EXAM"
                    );

            JButton results =
                    new JButton(
                            "VIEW MY RESULTS"
                    );

            JButton logout =
                    new JButton(
                            "LOGOUT"
                    );


            panel.add(welcome);
            panel.add(start);
            panel.add(results);
            panel.add(logout);


            start.addActionListener(
                    e -> {

                        dispose();

                        new ExamFrame(student);
                    }
            );


            results.addActionListener(
                    e -> showResults()
            );


            logout.addActionListener(
                    e -> {

                        dispose();

                        new LoginFrame();
                    }
            );


            add(panel);

            setVisible(true);
        }


        private void showResults() {

            StringBuilder text =
                    new StringBuilder();

            text.append(
                    "YOUR RESULTS\n\n"
            );


            if (DataStore.results.isEmpty()) {

                text.append(
                        "No results available."
                );

            } else {

                for (Result r :
                        DataStore.results) {

                    if (r.studentName.equals(
                            student.getName()
                    )) {

                        text.append(
                                r
                        );

                        text.append(
                                "\n\n"
                        );
                    }
                }
            }


            JOptionPane.showMessageDialog(
                    this,
                    text.toString()
            );
        }
    }


    // =========================================================
    // ADMIN DASHBOARD
    // =========================================================

    static class AdminDashboard extends JFrame
            implements QuestionOperations {

        DefaultListModel<String> listModel =
                new DefaultListModel<>();

        JList<String> questionList =
                new JList<>(listModel);


        public AdminDashboard() {

            setTitle(
                    "Admin Dashboard"
            );

            setSize(700, 500);

            setLocationRelativeTo(null);

            setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );


            refreshList();


            JButton add =
                    new JButton(
                            "ADD QUESTION"
                    );

            JButton remove =
                    new JButton(
                            "REMOVE QUESTION"
                    );

            JButton results =
                    new JButton(
                            "VIEW ALL RESULTS"
                    );

            JButton logout =
                    new JButton(
                            "LOGOUT"
                    );


            JPanel buttons =
                    new JPanel();

            buttons.add(add);
            buttons.add(remove);
            buttons.add(results);
            buttons.add(logout);


            add(
                    new JScrollPane(questionList),
                    BorderLayout.CENTER
            );

            add(
                    buttons,
                    BorderLayout.SOUTH
            );


            add.addActionListener(
                    e -> addQuestionDialog()
            );


            remove.addActionListener(
                    e -> removeSelectedQuestion()
            );


            results.addActionListener(
                    e -> showAllResults()
            );


            logout.addActionListener(
                    e -> {

                        dispose();

                        new LoginFrame();
                    }
            );


            setVisible(true);
        }


        private void refreshList() {

            listModel.clear();

            for (Question q :
                    DataStore.questions) {

                listModel.addElement(
                        q.toString()
                );
            }
        }


        @Override
        public void addQuestion(
                Question question) {

            DataStore.questions.add(
                    question
            );

            refreshList();
        }


        @Override
        public void removeQuestion(
                int questionId) {

            DataStore.questions.removeIf(
                    q -> q.getId() == questionId
            );

            refreshList();
        }


        private void addQuestionDialog() {

            JTextField question =
                    new JTextField();

            JTextField a =
                    new JTextField();

            JTextField b =
                    new JTextField();

            JTextField c =
                    new JTextField();

            JTextField d =
                    new JTextField();

            JComboBox<String> correct =
                    new JComboBox<>(
                            new String[]{
                                    "A",
                                    "B",
                                    "C",
                                    "D"
                            }
                    );


            JPanel panel =
                    new JPanel(
                            new GridLayout(
                                    7,
                                    2,
                                    5,
                                    5
                            )
                    );


            panel.add(
                    new JLabel("Question:")
            );

            panel.add(question);


            panel.add(
                    new JLabel("Option A:")
            );

            panel.add(a);


            panel.add(
                    new JLabel("Option B:")
            );

            panel.add(b);


            panel.add(
                    new JLabel("Option C:")
            );

            panel.add(c);


            panel.add(
                    new JLabel("Option D:")
            );

            panel.add(d);


            panel.add(
                    new JLabel("Correct Answer:")
            );

            panel.add(correct);


            int id =
                    DataStore.questions.size() + 1;


            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            panel,
                            "Add Question",
                            JOptionPane.OK_CANCEL_OPTION
                    );


            if (choice ==
                    JOptionPane.OK_OPTION) {

                Question q =
                        new Question(
                                id,
                                question.getText(),
                                a.getText(),
                                b.getText(),
                                c.getText(),
                                d.getText(),
                                (String) correct.getSelectedItem()
                        );

                addQuestion(q);
            }
        }


        private void removeSelectedQuestion() {

            int index =
                    questionList.getSelectedIndex();

            if (index == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Select a question first."
                );

                return;
            }


            Question q =
                    DataStore.questions.get(index);

            removeQuestion(q.getId());
        }


        private void showAllResults() {

            StringBuilder text =
                    new StringBuilder(
                            "ALL RESULTS\n\n"
                    );


            if (DataStore.results.isEmpty()) {

                text.append(
                        "No results available."
                );

            } else {

                for (Result r :
                        DataStore.results) {

                    text.append(
                            r
                    );

                    text.append(
                            "\n------------------\n"
                    );
                }
            }


            JTextArea area =
                    new JTextArea(
                            text.toString()
                    );

            area.setEditable(false);


            JOptionPane.showMessageDialog(
                    this,
                    new JScrollPane(area),
                    "Results",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }


    // =========================================================
    // EXAM FRAME
    // =========================================================

    static class ExamFrame extends JFrame
            implements ExamOperations {

        private Student student;

        private Exam exam;

        private int currentQuestion = 0;

        private int score = 0;

        private int timeLeft;

        private JLabel questionLabel;

        private JRadioButton optionA;
        private JRadioButton optionB;
        private JRadioButton optionC;
        private JRadioButton optionD;

        private ButtonGroup options;

        private JLabel timerLabel;

        private JButton nextButton;

        private TimerThread timerThread;


        public ExamFrame(Student student) {

            this.student = student;


            exam =
                    new Exam(
                            "Java Programming Exam",
                            60,
                            DataStore.questions
                    );


            timeLeft =
                    exam.getDuration();


            createGUI();

            startExam();
        }


        private void createGUI() {

            setTitle(
                    "Online Examination"
            );

            setSize(750, 550);

            setLocationRelativeTo(null);

            setDefaultCloseOperation(
                    JFrame.DO_NOTHING_ON_CLOSE
            );


            // Timer label

            timerLabel =
                    new JLabel(
                            "Time: " +
                                    timeLeft +
                                    " seconds",
                            SwingConstants.CENTER
                    );

            timerLabel.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            20
                    )
            );


            add(
                    timerLabel,
                    BorderLayout.NORTH
            );


            JPanel questionPanel =
                    new JPanel();

            questionPanel.setLayout(
                    new BoxLayout(
                            questionPanel,
                            BoxLayout.Y_AXIS
                    )
            );


            questionLabel =
                    new JLabel();

            questionLabel.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            18
                    )
            );


            optionA =
                    new JRadioButton();

            optionB =
                    new JRadioButton();

            optionC =
                    new JRadioButton();

            optionD =
                    new JRadioButton();


            options =
                    new ButtonGroup();

            options.add(optionA);
            options.add(optionB);
            options.add(optionC);
            options.add(optionD);


            questionPanel.add(
                    questionLabel
            );

            questionPanel.add(
                    Box.createVerticalStrut(30)
            );

            questionPanel.add(optionA);
            questionPanel.add(optionB);
            questionPanel.add(optionC);
            questionPanel.add(optionD);


            add(
                    questionPanel,
                    BorderLayout.CENTER
            );


            nextButton =
                    new JButton(
                            "NEXT"
                    );


            add(
                    nextButton,
                    BorderLayout.SOUTH
            );


            nextButton.addActionListener(
                    e -> nextQuestion()
            );


            setVisible(true);
        }


        @Override
        public void startExam() {

            if (exam.getQuestions().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No questions available."
                );

                dispose();

                new LoginFrame();

                return;
            }


            showQuestion();


            timerThread =
                    new TimerThread();

            timerThread.start();
        }


        private void showQuestion() {

            Question q =
                    exam.getQuestions()
                            .get(currentQuestion);


            questionLabel.setText(
                    "<html>Question " +
                            (currentQuestion + 1) +
                            ": " +
                            q.getQuestion() +
                            "</html>"
            );


            optionA.setText(
                    "A. " + q.getOptionA()
            );

            optionB.setText(
                    "B. " + q.getOptionB()
            );

            optionC.setText(
                    "C. " + q.getOptionC()
            );

            optionD.setText(
                    "D. " + q.getOptionD()
            );


            options.clearSelection();


            if (currentQuestion ==
                    exam.getQuestions().size() - 1) {

                nextButton.setText(
                        "SUBMIT EXAM"
                );

            } else {

                nextButton.setText(
                        "NEXT"
                );
            }
        }


        private String getSelectedAnswer() {

            if (optionA.isSelected())
                return "A";

            if (optionB.isSelected())
                return "B";

            if (optionC.isSelected())
                return "C";

            if (optionD.isSelected())
                return "D";

            return "";
        }


        private void nextQuestion() {

            String answer =
                    getSelectedAnswer();


            Question q =
                    exam.getQuestions()
                            .get(currentQuestion);


            if (answer.equals(
                    q.getCorrectAnswer()
            )) {

                score++;
            }


            if (currentQuestion <
                    exam.getQuestions().size() - 1) {

                currentQuestion++;

                showQuestion();

            } else {

                submitExam();
            }
        }


        @Override
        public void submitExam() {

            if (timerThread != null) {

                timerThread.stopTimer();
            }


            Result result =
                    new Result(
                            student.getName(),
                            score,
                            exam.getQuestions().size()
                    );


            DataStore.saveResult(result);


            dispose();


            new ResultFrame(
                    student,
                    result
            );
        }


        // =====================================================
        // TIMER THREAD
        // =====================================================

        class TimerThread extends Thread {

            private volatile boolean running =
                    true;


            public void stopTimer() {

                running = false;
            }


            @Override
            public void run() {

                while (
                        running &&
                                timeLeft > 0
                ) {

                    try {

                        Thread.sleep(1000);

                        timeLeft--;


                        SwingUtilities.invokeLater(
                                () -> timerLabel.setText(
                                        "Time: " +
                                                timeLeft +
                                                " seconds"
                                )
                        );


                    } catch (
                            InterruptedException e) {

                        Thread.currentThread()
                                .interrupt();

                        break;
                    }
                }


                if (running &&
                        timeLeft <= 0) {

                    SwingUtilities.invokeLater(
                            () -> {

                                JOptionPane.showMessageDialog(
                                        ExamFrame.this,
                                        "Time is over!\n" +
                                                "Your exam will be submitted."
                                );

                                submitExam();
                            }
                    );
                }
            }
        }
    }


    // =========================================================
    // RESULT FRAME
    // =========================================================

    static class ResultFrame extends JFrame {

        public ResultFrame(
                Student student,
                Result result) {

            setTitle(
                    "Examination Result"
            );

            setSize(500, 400);

            setLocationRelativeTo(null);

            setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );


            JPanel panel =
                    new JPanel(
                            new BorderLayout()
                    );


            JLabel title =
                    new JLabel(
                            "EXAMINATION RESULT",
                            SwingConstants.CENTER
                    );

            title.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            25
                    )
            );


            JTextArea resultArea =
                    new JTextArea();


            resultArea.setEditable(false);

            resultArea.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            18
                    )
            );


            resultArea.setText(
                    "Student: " +
                            student.getName() +
                            "\n\n" +

                            "Score: " +
                            result.score +
                            "/" +
                            result.total +
                            "\n\n" +

                            "Percentage: " +
                            String.format(
                                    "%.2f",
                                    result.getPercentage()
                            ) +
                            "%\n\n" +

                            getGrade(
                                    result.getPercentage()
                            )
            );


            JButton home =
                    new JButton(
                            "BACK TO DASHBOARD"
                    );


            home.addActionListener(
                    e -> {

                        dispose();

                        new StudentDashboard(
                                student
                        );
                    }
            );


            panel.add(
                    title,
                    BorderLayout.NORTH
            );

            panel.add(
                    resultArea,
                    BorderLayout.CENTER
            );

            panel.add(
                    home,
                    BorderLayout.SOUTH
            );


            add(panel);

            setVisible(true);
        }


        private String getGrade(
                double percentage) {

            if (percentage >= 90)
                return "Grade: A+";

            if (percentage >= 80)
                return "Grade: A";

            if (percentage >= 70)
                return "Grade: B";

            if (percentage >= 60)
                return "Grade: C";

            if (percentage >= 50)
                return "Grade: D";

            return "Grade: F";
        }
    }
}