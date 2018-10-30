package com.example.jda8301.spellarhyme;

import java.util.List;

public class Classroom {

    // field/instance variables
    private String name;
    private int code;
    private School school;
    private List<Student> students;

    // constructor
    public Classroom(String name, int code) {
        this.name = name;
        this.code = code;
    }

    // instance methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}