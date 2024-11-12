package com.example.demo.controller;

import com.example.demo.dao.SubjectDAO;
import com.example.demo.entity.Subject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "subjectController", value = "/subject")
public class SubjectController extends HttpServlet {
    private final SubjectDAO subjectDAO = new SubjectDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all subjects and forward to JSP
        List<Subject> subjects = subjectDAO.getAll();
        request.setAttribute("subjects", subjects);
        RequestDispatcher dispatcher = request.getRequestDispatcher("subject.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equalsIgnoreCase(action)) {
            handleCreate(request, response);
        } else if ("update".equalsIgnoreCase(action)) {
            handleUpdate(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            handleDelete(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
        }
    }

    private void handleCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String hoursStr = request.getParameter("hours");

        if (name == null || hoursStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Name and hours are required.");
            return;
        }

        try {
            int hours = Integer.parseInt(hoursStr);
            Subject subject = new Subject();
            subject.setName(name);
            subject.setHours(hours);
            subjectDAO.create(subject);
            response.sendRedirect("subject");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid hours format.");
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String hoursStr = request.getParameter("hours");

        if (idStr == null || name == null || hoursStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID, name, and hours are required.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            int hours = Integer.parseInt(hoursStr);

            Subject subject = new Subject();
            subject.setId(id);
            subject.setName(name);
            subject.setHours(hours);

            if (!subjectDAO.update(subject)) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Subject not found.");
            } else {
                response.sendRedirect("subject");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID or hours format.");
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");

        if (idStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is required.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Subject subject = new Subject();
            subject.setId(id);

            if (!subjectDAO.delete(subject)) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Subject not found.");
            } else {
                response.sendRedirect("subject");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID format.");
        }
    }
}
