<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Student Management</h1>
    <a href="index.jsp" class="btn btn-secondary mb-3">Back to Home</a>

    <!-- Add Student Form -->
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Add New Student</h5>
            <form action="student" method="post">
                <input type="hidden" name="_method" value="POST">
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone</label>
                    <input type="text" class="form-control" id="phone" name="phone" required>
                </div>
                <div class="mb-3">
                    <label for="classroomId" class="form-label">Classroom</label>
                    <select class="form-select" id="classroomId" name="classroomId">
                        <option value="" selected>No Classroom</option>
                        <c:forEach var="classroom" items="${classrooms}">
                            <option value="${classroom.id}">${classroom.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Add Student</button>
            </form>
        </div>
    </div>

    <!-- Students List -->
    <div class="mt-4">
        <h2>Students List</h2>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Classroom</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.email}</td>
                    <td>${student.phone}</td>
                    <td>
                        <c:forEach var="classroom" items="${classrooms}">
                            <c:if test="${classroom.id == student.classroomId}">
                                ${classroom.name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <!-- Update Form -->
                        <form action="student" method="post" style="display: inline-block;">
                            <input type="hidden" name="_method" value="PUT">
                            <input type="hidden" name="id" value="${student.id}">
                            <input type="text" name="name" value="${student.name}">
                            <input type="email" name="email" value="${student.email}">
                            <input type="text" name="phone" value="${student.phone}">
                            <select name="classroomId" required>
                                <c:forEach var="classroom" items="${classrooms}">
                                    <option value="${classroom.id}" ${classroom.id == student.classroomId ? 'selected' : ''}>
                                            ${classroom.name}
                                    </option>
                                </c:forEach>
                            </select>
                            <button type="submit" class="btn btn-sm btn-warning">Edit</button>
                        </form>

                        <!-- Delete -->
                        <form action="student" method="post" style="display: inline-block;">
                            <input type="hidden" name="_method" value="DELETE">
                            <input type="hidden" name="id" value="${student.id}">
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
