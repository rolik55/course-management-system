package com.coursemngsys.coursemanagementsystem.Model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student extends User{
    private String studentNumber;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Course> enrolledCourses;

    public Student() {
    }

    public Student(String login, String password, String name, String surname, String email, String studentNumber, List<Course> enrolledCourses) {
        super(login, password, name, surname, email);
        this.studentNumber = studentNumber;
        this.enrolledCourses = enrolledCourses;
    }

    public Student(String login, String password, String name, String surname, String email, List<File> myFiles, String studentNumber, List<Course> enrolledCourses) {
        super(login, password, name, surname, email, myFiles);
        this.studentNumber = studentNumber;
        this.enrolledCourses = enrolledCourses;
    }

    public Student(String login, String password, String name, String surname, String email, String studentNumber) {
        super(login, password, name, surname, email);
        this.studentNumber = studentNumber;
    }

    public Student(String login, String password, String name, String surname, String email, List<File> myFiles, String studentNumber) {
        super(login, password, name, surname, email, myFiles);
        this.studentNumber = studentNumber;
    }

    public Student(int id, String login, String password, String name, String surname, String email, String studentNumber) {
        super(id, login, password, name, surname, email);
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
