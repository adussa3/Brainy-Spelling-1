package com.example.jda8301.spellarhyme;

import java.util.List;

public class Teacher extends User {

    // field/instance variables

    // teacher information
    private School school;
    private List<Classroom> classrooms;

    // teacher login information
    private String username;
    private String email;
    private String password;

    // constructor
    public Teacher(String firstName, String lastName, School school, String username, String email, String password) {
        super(firstName, lastName);

        this.username = username;
        this.email = email;
        this.password = password;
    }
}