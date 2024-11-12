package com.example.demo.dao;

import com.example.demo.database.Database;
import com.example.demo.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements DAOInterface<Subject> {

    // Retrieve all subjects
    @Override
    public List<Subject> getAll() {
        String sql = "SELECT * FROM Subjects";
        List<Subject> subjects = new ArrayList<>();
        Database database = Database.getInstance();
        try {
            var statement = database.getStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getInt("id"));
                subject.setName(resultSet.getString("name"));
                subject.setHours(resultSet.getInt("hours"));
                subjects.add(subject);
            }
        } catch (Exception e) {
            System.out.println("Error in getAll: " + e.getMessage());
        }
        return subjects;
    }

    // Create a new subject
    @Override
    public boolean create(Subject subject) {
        String sql = "INSERT INTO Subjects (name, hours) VALUES (?, ?)";
        Database database = Database.getInstance();
        try {
            var preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, subject.getHours());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error in create: " + e.getMessage());
        }
        return false;
    }

    // Update an existing subject
    @Override
    public boolean update(Subject subject) {
        if (subject == null || subject.getId() <= 0) {
            System.out.println("Invalid subject provided for update.");
            return false;
        }

        String sql = "UPDATE Subjects SET name = ?, hours = ? WHERE id = ?";
        Database database = Database.getInstance();
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, subject.getHours());
            preparedStatement.setInt(3, subject.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No subject found with ID: " + subject.getId());
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error in update: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    // Delete a subject
    @Override
    public boolean delete(Subject subject) {
        if (subject == null || subject.getId() <= 0) {
            System.out.println("Invalid subject provided for deletion.");
            return false;
        }

        String sql = "DELETE FROM Subjects WHERE id = ?";
        Database database = Database.getInstance();
        try (PreparedStatement preparedStatement = database.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, subject.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No subject found with ID: " + subject.getId());
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error in delete: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Find a subject by its ID
    @Override
    public <K> Subject find(K id) {
        String sql = "SELECT * FROM Subjects WHERE id = ?";
        Database database = Database.getInstance();
        try {
            var preparedStatement = database.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, (int) id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getInt("id"));
                subject.setName(resultSet.getString("name"));
                subject.setHours(resultSet.getInt("hours"));
                return subject;
            }
        } catch (Exception e) {
            System.out.println("Error in find: " + e.getMessage());
        }
        return null;
    }
}
