package com.example.demo.entity;

public class Subject {
    private int id;
    private String name;
    private int hours;

    public Subject(int id, String name, int hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
    }

    public Subject() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
