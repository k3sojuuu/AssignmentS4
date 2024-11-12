package com.example.demo.controller;

import com.example.demo.dao.ClassroomDAO;
import com.example.demo.dao.SubjectDAO;
import com.example.demo.entity.Classroom;
import com.example.demo.entity.Subject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "classroomController", value = "/classroom")
public class ClassroomController extends HttpServlet {
    private final ClassroomDAO classroomDAO = new ClassroomDAO();
    private final SubjectDAO subjectDAO = new SubjectDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all classrooms and subjects
        List<Classroom> classrooms = classroomDAO.getAll();
        List<Subject> subjects = subjectDAO.getAll();
        request.setAttribute("classrooms", classrooms);
        request.setAttribute("subjects", subjects);

        RequestDispatcher dispatcher = request.getRequestDispatcher("classroom.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");

        if (method == null || method.equalsIgnoreCase("POST")) {
            // Create a new classroom
            String name = request.getParameter("name");
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));

            Classroom classroom = new Classroom();
            classroom.setName(name);
            classroom.setSubjectId(subjectId);

            classroomDAO.create(classroom);
            response.sendRedirect("classroom");
        } else if (method.equalsIgnoreCase("PUT")) {
            // Update an existing classroom
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int subjectId = Integer.parseInt(request.getParameter("subjectId"));

            Classroom classroom = new Classroom();
            classroom.setId(id);
            classroom.setName(name);
            classroom.setSubjectId(subjectId);

            classroomDAO.update(classroom);
            response.sendRedirect("classroom");
        } else if (method.equalsIgnoreCase("DELETE")) {
            // Delete a classroom
            int id = Integer.parseInt(request.getParameter("id"));

            Classroom classroom = new Classroom();
            classroom.setId(id);

            classroomDAO.delete(classroom);
            response.sendRedirect("classroom");
        }
    }
}
