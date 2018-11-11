package com.example.jda8301.spellarhyme;

import java.util.LinkedList;
import java.util.List;

public class School {

    // this is a hacky way to store all of the schools,
    // find a better way to store the schools
    // (could we implement a singleton?)
    // https://medium.com/@kevalpatel2106/how-to-make-the-perfect-singleton-de6b951dfdb0
    private static volatile List<School> schools = new LinkedList<>();

    // field/instance variables
    private String name;

    private List<Teacher> teachers; // do we need a list of teachers? I'm not sure, maybe the school
                                    // only needs the classes it has, but what information does it
                                    // need from teachers?
    private List<Student> students; // do we need a list of students? I don't think we need this
                                    // because the classrooms already have a list of students

    private List<Classroom> classrooms;

    // constructors
    public School(String name)  {
        this(name, new LinkedList<Teacher>(), new LinkedList<Student>(), new LinkedList<Classroom>()); // should we use an arraylist or a linkedlist?
    }                                                                       // what would be better for our application?

    public School(String name, List<Teacher> teachers, List<Student> students, List<Classroom> classrooms) {
        this.name = name;

        this.teachers = teachers;
        this.students = students;

        this.classrooms = classrooms;
    }

    // instance variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**********************************************************************/

    public List<Teacher> getTeachers() {
        return teachers;
    }

    // should we do this? (standard way)
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    // or should we do this? (I think this is a more intuitive way)
    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public boolean removeTeacher(Teacher teacher) {
        return this.teachers.remove(teacher);
    }

    /**********************************************************************/

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    // should we do this? (standard way)
    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    // or should we do this? (I think this is a more intuitive way)
    public void addClassroom(Classroom classroom) {
        this.classrooms.add(classroom);
    }

    public boolean removeClassroom(Classroom classroom) {
        return this.classrooms.remove(classroom);
    }

    /**********************************************************************/

    public List<Student> getStudents() {
        return students;
    }

    // should we do this? (standard way)
    public void setStudents(List<Student> students) { this.students = students; }

    // or should we do this? (I think this is a more intuitive way)
    public void addStudent(Student student) {
        this.students.add(student);
    }

    public boolean removeStudent(Classroom student) {
        return this.students.remove(student);
    }
}