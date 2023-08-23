import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }
}

public class StudentManagementSystemGUI extends JFrame implements ActionListener {
    private JTextField nameField, rollNumberField, gradeField;
    private JTextArea outputArea;
    private JButton addButton, searchButton, displayButton, removeButton, exitButton;
    private StudentManagementSystem system;

    public StudentManagementSystemGUI() {
        system = new StudentManagementSystem();

        setTitle("Student Management System");
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name: "));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Roll Number: "));
        rollNumberField = new JTextField();
        inputPanel.add(rollNumberField);
        inputPanel.add(new JLabel("Grade: "));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        addButton = new JButton("Add Student");
        addButton.addActionListener(this);
        searchButton = new JButton("Search Student");
        searchButton.addActionListener(this);
        displayButton = new JButton("Display All Students");
        displayButton.addActionListener(this);
        removeButton = new JButton("Remove Student");
        removeButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(exitButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(outputArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addStudent();
        } else if (e.getSource() == searchButton) {
            searchStudent();
        } else if (e.getSource() == displayButton) {
            displayAllStudents();
        } else if (e.getSource() == removeButton) {
            removeStudent();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void addStudent() {
        String name = nameField.getText();
        int rollNumber = Integer.parseInt(rollNumberField.getText());
        String grade = gradeField.getText();

        if (name.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Grade cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student = new Student(name, rollNumber, grade);
        system.addStudent(student);

        nameField.setText("");
        rollNumberField.setText("");
        gradeField.setText("");
    }

    private void searchStudent() {
        int rollNumber = Integer.parseInt(rollNumberField.getText());
        Student student = system.searchStudent(rollNumber);
        if (student != null) {
            outputArea.setText(student.toString());
        } else {
            outputArea.setText("Student not found.");
        }
    }

    private void displayAllStudents() {
        ArrayList<Student> students = system.getAllStudents();
        if (students.isEmpty()) {
            outputArea.setText("No students added yet.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Student student : students) {
                sb.append(student.toString()).append("\n");
            }
            outputArea.setText(sb.toString());
        }
    }

    private void removeStudent() {
        int rollNumber = Integer.parseInt(rollNumberField.getText());
        Student student = system.searchStudent(rollNumber);
        if (student != null) {
            system.removeStudent(student);
            outputArea.setText("Student removed successfully.");
        } else {
            outputArea.setText("Student not found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementSystemGUI());
    }
}
