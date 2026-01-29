// src/main/StudentManagementApp.java
package main;

import dao.StudentDAO;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import model.Student;

public class StudentManagementApp {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentDAO studentDAO = new StudentDAO();

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("   STUDENT MANAGEMENT SYSTEM     ");
        System.out.println("================================");

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice from the menu: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    viewStudentById();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    searchStudents();
                    break;
                case 7:
                    getDepartmentAverage();
                    break;
                case 8:
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. View Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Search Students by Name");
        System.out.println("7. Get Department Average CGPA");
        System.out.println("8. Exit");
        System.out.println("=====================");
    }

    private static void addStudent() {
        System.out.println("\n----- ADD NEW STUDENT -----");

        System.out.print("Enter Roll Number: ");
        String rollNumber = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        int semester = getIntInput("Enter Semester: ");
        double cgpa = getDoubleInput("Enter CGPA: ");

        System.out.print("Enter Enrollment Date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        Date enrollmentDate = Date.valueOf(dateStr);

        Student student = new Student(rollNumber, name, email, department,
                semester, cgpa, enrollmentDate);

        if (studentDAO.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student!");
        }
    }

    private static void viewAllStudents() {
        System.out.println("\n----- ALL STUDENTS -----");
        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("There are No students in the database now!");
        } else {
            System.out.println("Total Students: " + students.size());
            System.out.println("-----------------------------------");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void viewStudentById() {
        int id = getIntInput("\nEnter Student ID: ");
        Student student = studentDAO.getStudentById(id);

        if (student != null) {
            System.out.println("\n----- STUDENT DETAILS -----");
            System.out.println(student);
        } else {
            System.out.println("Student not found with ID: " + id);
        }
    }

    private static void updateStudent() {
        int id = getIntInput("\nEnter Student ID to update: ");
        Student student = studentDAO.getStudentById(id);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("Current Details: " + student);
        System.out.println("\nEnter new details (press enter to keep current):");

        System.out.print("Roll Number [" + student.getRollNumber() + "]: ");
        String rollNumber = scanner.nextLine();
        if (!rollNumber.isEmpty()) student.setRollNumber(rollNumber);

        System.out.print("Name [" + student.getName() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) student.setName(name);

        System.out.print("Email [" + student.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) student.setEmail(email);

        System.out.print("Department [" + student.getDepartment() + "]: ");
        String department = scanner.nextLine();
        if (!department.isEmpty()) student.setDepartment(department);

        System.out.print("Semester [" + student.getSemester() + "]: ");
        String semesterStr = scanner.nextLine();
        if (!semesterStr.isEmpty()) student.setSemester(Integer.parseInt(semesterStr));

        System.out.print("CGPA [" + student.getCgpa() + "]: ");
        String cgpaStr = scanner.nextLine();
        if (!cgpaStr.isEmpty()) student.setCgpa(Double.parseDouble(cgpaStr));

        if (studentDAO.updateStudent(student)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Failed to update student!");
        }
    }

    private static void deleteStudent() {
        int id = getIntInput("\nEnter Student ID to delete: ");

        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            if (studentDAO.deleteStudent(id)) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Failed to delete student!");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static void searchStudents() {
        System.out.print("\nEnter name to search: ");
        String name = scanner.nextLine();

        List<Student> students = studentDAO.searchStudentsByName(name);

        if (students.isEmpty()) {
            System.out.println("No students found with name containing: " + name);
        } else {
            System.out.println("\n----- SEARCH RESULTS -----");
            System.out.println("Found " + students.size() + " student(s)");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void getDepartmentAverage() {
        System.out.print("\nEnter department: ");
        String department = scanner.nextLine();

        double average = studentDAO.getAverageCgpaByDepartment(department);
        System.out.printf("Average CGPA for %s department: %.2f\n", department, average);
    }

    private static int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input! Please enter a number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return value;
    }

    private static double getDoubleInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input! Please enter a decimal number: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        return value;
    }
}


