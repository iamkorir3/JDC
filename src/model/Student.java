package model;

import java.sql.Date;

public class Student {
    private int id;
    private String rollNumber;
    private String name;
    private String email;
    private String department;
    private int semester;
    private double cgpa;
    private Date enrollmentDate;
    
    // Constructors
    public Student() {}
    
    public Student(String rollNumber, String name, String email, 
                   String department, int semester, double cgpa, Date enrollmentDate) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.email = email;
        this.department = department;
        this.semester = semester;
        this.cgpa = cgpa;
        this.enrollmentDate = enrollmentDate;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
    
    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }
    
    public Date getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(Date enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    
    @Override
    public String toString() {
        return String.format("ID: %d, Roll: %s, Name: %s, Email: %s, Dept: %s, " +
                           "Semester: %d, CGPA: %.2f, Enrolled: %s",
                           id, rollNumber, name, email, department, 
                           semester, cgpa, enrollmentDate);
    }
}