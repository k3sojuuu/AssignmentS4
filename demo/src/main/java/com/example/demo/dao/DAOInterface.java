package com.example.demo.dao;

import com.example.demo.entity.Student;

import java.util.List;

public interface DAOInterface<S> {
    List<S> getAll();
    boolean create(S s);
    boolean update(S s);
    boolean delete(S s);
    <K>S find(K id);
}
