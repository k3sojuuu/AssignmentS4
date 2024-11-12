package com.example.demo.entity;

import java.time.LocalDateTime;

public class Student {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Integer classroomId;

    public Student() {
    }

    public Student(int id, String name, String email, String phone, Integer classroomId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.classroomId = classroomId;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
