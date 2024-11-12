package com.example.demo.dao;

import com.example.demo.database.Database;
import com.example.demo.entity.Classroom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDAO implements DAOInterface<Classroom> {

    // Retrieve all classrooms
    @Override
    public List<Classroom> getAll() {
        String sql = "SELECT * FROM Classrooms";
        List<Classroom> classrooms = new ArrayList<>();
        Database database = Database.getInstance();
        try {
            var statement = database.getStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Classroom classroom = new Classroom();
                classroom.setId(resultSet.getInt("id"));
                classroom.setName(resultSet.getString("name"));
                classroom.setSubjectId(resultSet.getInt("subject_id")); // Retrieve subject_id
                classrooms.add(classroom);
            }
        } catch (Exception e) {
            System.out.println("Error in getAll: " + e.getMessage());
        }
        return classrooms;
    }

    // Create a new classroom
    @Override
    public boolean create(Classroom classroom) {
        String sql = "INSERT INTO Classrooms (name, subject_id) VALUES (?, ?)";
        Database database = Database.getInstance();
        try {
            var preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, classroom.getName());
            preparedStatement.setInt(2, classroom.getSubjectId()); // Set subject_id
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error in create: " + e.getMessage());
        }
        return false;
    }

    // Update an existing classroom
    @Override
    public boolean update(Classroom classroom) {
        String sql = "UPDATE Classrooms SET name = ?, subject_id = ? WHERE id = ?";
        Database database = Database.getInstance();
        try {
            var preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, classroom.getName());
            preparedStatement.setInt(2, classroom.getSubjectId()); // Update subject_id
            preparedStatement.setInt(3, classroom.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error in update: " + e.getMessage());
        }
        return false;
    }

    // Delete a classroom
    @Override
    public boolean delete(Classroom classroom) {
        String sql = "DELETE FROM Classrooms WHERE id = ?";
        Database database = Database.getInstance();
        try {
            var preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, classroom.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error in delete: " + e.getMessage());
        }
        return false;
    }

    // Find a classroom by its ID
    @Override
    public <K> Classroom find(K id) {
        String sql = "SELECT * FROM Classrooms WHERE id = ?";
        Database database = Database.getInstance();
        try {
            var preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, (int) id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Classroom classroom = new Classroom();
                classroom.setId(resultSet.getInt("id"));
                classroom.setName(resultSet.getString("name"));
                classroom.setSubjectId(resultSet.getInt("subject_id")); // Retrieve subject_id
                return classroom;
            }
        } catch (Exception e) {
            System.out.println("Error in find: " + e.getMessage());
        }
        return null;
    }
}
