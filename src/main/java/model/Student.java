package model;

import java.time.LocalDate;

public class Student {
    private int studentId;
    private String studentName;
    private LocalDate dateOfBirth;
    private String email;
    private String address;
    private String phoneNumber;
    private Classroom classroom;

    public Student(String studentName, LocalDate dateOfBirth, String email, String address, String phoneNumber, Classroom classroom) {
        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.classroom = classroom;
    }

    public Student(int studentId, String studentName, LocalDate dateOfBirth, String email, String address, String phoneNumber, Classroom classroom) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.classroom = classroom;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
