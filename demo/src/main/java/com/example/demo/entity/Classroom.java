package com.example.demo.entity;

public class Classroom {
    private int id;
    private String name;
    private int subjectId;

    public Classroom(int id, String name, int subjectId) {
        this.id = id;
        this.name = name;
        this.subjectId = subjectId;
    }

    public Classroom() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
