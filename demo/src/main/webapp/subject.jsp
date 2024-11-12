<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Subjects Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <!-- Navigation -->
    <div class="d-flex justify-content-between align-items-center">
        <h1 class="text-center">Subjects Management</h1>
        <a href="index.jsp" class="btn btn-outline-secondary">Go to Home</a>
    </div>

    <!-- Form to Add New Subject -->
    <div class="card mt-3">
        <div class="card-body">
            <h5 class="card-title">Add New Subject</h5>
            <form action="subject" method="post">
                <input type="hidden" name="action" value="create"> <!-- action parameter -->
                <div class="mb-3">
                    <label for="name" class="form-label">Subject Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="hours" class="form-label">Hours</label>
                    <input type="number" class="form-control" id="hours" name="hours" required>
                </div>
                <button type="submit" class="btn btn-primary">Add Subject</button>
            </form>
        </div>
    </div>

    <!-- List of Subjects -->
    <div class="mt-4">
        <h2>Subjects List</h2>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Hours</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="subject" items="${subjects}">
                <tr>
                    <td>${subject.id}</td>
                    <td>${subject.name}</td>
                    <td>${subject.hours}</td>
                    <td>
                        <!-- Update Form -->
                        <form action="subject" method="post" style="display: inline-block;">
                            <input type="hidden" name="action" value="update"> <!-- action parameter -->
                            <input type="hidden" name="id" value="${subject.id}">
                            <input type="text" name="name" value="${subject.name}" required>
                            <input type="number" name="hours" value="${subject.hours}" required>
                            <button type="submit" class="btn btn-sm btn-warning">Update</button>
                        </form>

                        <!-- Delete Form -->
                        <form action="subject" method="post" style="display: inline-block;">
                            <input type="hidden" name="action" value="delete"> <!-- action parameter -->
                            <input type="hidden" name="id" value="${subject.id}">
                            <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
