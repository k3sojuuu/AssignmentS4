package com.example.demo.dao;

import com.example.demo.database.Database;
import com.example.demo.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAOInterface<Student> {

    private final Database database;

    public StudentDAO() {
        this.database = Database.getInstance();
    }

    // Retrieve all students
    @Override
    public List<Student> getAll() {
        String sql = "SELECT students.*, classrooms.name AS classroom_name " +
                "FROM students " +
                "LEFT JOIN classrooms ON students.classroom_id = classrooms.id";
        List<Student> students = new ArrayList<>();

        try (Statement statement = database.getStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setPhone(resultSet.getString("phone"));
                student.setClassroomId(resultSet.getObject("classroom_id", Integer.class));
                students.add(student);
            }
        } catch (Exception e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    @Override
    public boolean create(Student student) {
        String sql = "INSERT INTO students (name, email, phone, classroom_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = database.getPreparedStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPhone());
            if (student.getClassroomId() != null) {
                preparedStatement.setInt(4, student.getClassroomId());
            } else {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            }
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error creating student: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, phone = ?, classroom_id = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = database.getPreparedStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPhone());
            if (student.getClassroomId() != null) {
                preparedStatement.setInt(4, student.getClassroomId());
            } else {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            }
            preparedStatement.setInt(5, student.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error updating student: " + e.getMessage());
        }
        return false;
    }

    // Delete a student
    @Override
    public boolean delete(Student student) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (PreparedStatement preparedStatement = database.getPreparedStatement(sql)) {
            preparedStatement.setInt(1, student.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error deleting student: " + e.getMessage());
        }
        return false;
    }

    // Find a student by ID
    @Override
    public <K> Student find(K id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        int studentId = (int) id;

        try (PreparedStatement preparedStatement = database.getPreparedStatement(sql)) {
            preparedStatement.setInt(1, studentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setName(resultSet.getString("name"));
                    student.setEmail(resultSet.getString("email"));
                    student.setPhone(resultSet.getString("phone"));
                    return student;
                }
            }
        } catch (Exception e) {
            System.err.println("Error finding student: " + e.getMessage());
        }
        return null;
    }
}
