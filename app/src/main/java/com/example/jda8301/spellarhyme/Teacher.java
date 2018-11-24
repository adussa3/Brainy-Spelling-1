package com.example.jda8301.spellarhyme;

import java.util.List;

public class Teacher extends User {

    // field/instance variables

    // teacher information
    private School school; // do we need to know what school the teacher works at?
    private List<Classroom> classrooms;

    // teacher login information
    private String username;
    private String email;
    private String password;

    // constructors
    public Teacher() {}

    public Teacher(String firstName, String lastName, School school, List<Classroom> classrooms, String username, String email, String password) {
        super(firstName, lastName);

        this.school = school;
        this.classrooms = classrooms;

        this.username = username;
        this.email = email;
        this.password = password;
    }

    // instance methods
    public School getSchool() { return school; }

    public void setSchool(School school) { this.school = school; }

    public List<Classroom> getClassrooms() { return classrooms; }

    public void setClassrooms(List<Classroom> classrooms) { this.classrooms = classrooms; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

}