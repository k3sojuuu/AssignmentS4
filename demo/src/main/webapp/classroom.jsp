<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Classrooms Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Classrooms Management</h1>
    <a href="index.jsp" class="btn btn-secondary mb-3">Back to Home</a>

    <!-- Form to Add New Classroom -->
    <div class="card mt-3">
        <div class="card-body">
            <h5 class="card-title">Add New Classroom</h5>
            <form action="classroom" method="post">
                <input type="hidden" name="_method" value="POST">
                <div class="mb-3">
                    <label for="name" class="form-label">Classroom Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="subjectId" class="form-label">Select Subject</label>
                    <select class="form-select" id="subjectId" name="subjectId" required>
                        <c:forEach var="subject" items="${subjects}">
                            <option value="${subject.id}">${subject.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Add Classroom</button>
            </form>
        </div>
    </div>

    <!-- List of Classrooms -->
    <div class="mt-4">
        <h2>Classrooms List</h2>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Subject</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="classroom" items="${classrooms}">
                <tr>
                    <td>${classroom.id}</td>
                    <td>${classroom.name}</td>
                    <td>
                        <c:forEach var="subject" items="${subjects}">
                            <c:if test="${subject.id == classroom.subjectId}">
                                ${subject.name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <!-- Update Form -->
                        <form action="classroom" method="post" style="display: inline-block;">
                            <input type="hidden" name="_method" value="PUT">
                            <input type="hidden" name="id" value="${classroom.id}">
                            <input type="text" name="name" value="${classroom.name}" required>
                            <select name="subjectId" required>
                                <c:forEach var="subject" items="${subjects}">
                                    <option value="${subject.id}" ${subject.id == classroom.subjectId ? 'selected' : ''}>
                                            ${subject.name}
                                    </option>
                                </c:forEach>
                            </select>
                            <button type="submit" class="btn btn-sm btn-warning">Update</button>
                        </form>

                        <!-- Delete Form -->
                        <form action="classroom" method="post" style="display: inline-block;">
                            <input type="hidden" name="_method" value="DELETE">
                            <input type="hidden" name="id" value="${classroom.id}">
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
