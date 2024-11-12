package com.example.demo.controller;

import com.example.demo.dao.ClassroomDAO;
import com.example.demo.dao.StudentDAO;
import com.example.demo.entity.Classroom;
import com.example.demo.entity.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "studentController", value = "/student")
public class StudentController extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAO();
    private final ClassroomDAO classroomDAO = new ClassroomDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDAO.getAll();
        List<Classroom> classrooms = classroomDAO.getAll();

        request.setAttribute("students", students);
        request.setAttribute("classrooms", classrooms);

        RequestDispatcher dispatcher = request.getRequestDispatcher("student.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");

        if (method == null || method.equalsIgnoreCase("POST")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            Integer classroomId = request.getParameter("classroomId") == null ? null
                    : Integer.parseInt(request.getParameter("classroomId"));

            Student student = new Student();
            student.setName(name);
            student.setEmail(email);
            student.setPhone(phone);
            student.setClassroomId(classroomId);

            studentDAO.create(student);
            response.sendRedirect("student");
        } else if (method.equalsIgnoreCase("PUT")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            Integer classroomId = request.getParameter("classroomId") == null ? null
                    : Integer.parseInt(request.getParameter("classroomId"));

            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setEmail(email);
            student.setPhone(phone);
            student.setClassroomId(classroomId);

            studentDAO.update(student);
            response.sendRedirect("student");
        } else if (method.equalsIgnoreCase("DELETE")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Student student = new Student();
            student.setId(id);

            studentDAO.delete(student);
            response.sendRedirect("student");
        }
    }
}
