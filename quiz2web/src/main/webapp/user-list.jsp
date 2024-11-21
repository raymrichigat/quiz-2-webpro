<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>College Student Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
          crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa; /* Light gray background */
            font-family: Arial, sans-serif;
        }

        .navbar {
            background-color: #343a40; /* Dark navbar */
        }

        .navbar a {
            color: #fff !important;
            font-weight: bold;
        }

        h3.text-center {
            margin-top: 20px;
            color: #007bff; /* Blue heading */
        }

        .btn-success {
            background-color: #28a745;
            border: none;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .btn-success:hover {
            background-color: #218838;
        }

        table.table {
            margin-top: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        table.table th {
            background-color: #007bff;
            color: #fff;
            font-weight: bold;
            text-align: center;
        }

        table.table td {
            text-align: center;
            vertical-align: middle;
        }

        a {
            color: #007bff;
            font-weight: bold;
        }

        a:hover {
            color: #0056b3;
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md">
            <div>
                <a href="#" class="navbar-brand">User Management App</a>
            </div>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
            </ul>
        </nav>
    </header>
    <br>

    <div class="row">
        <div class="container">
            <h3 class="text-center">List of Users</h3>
            <hr>
            <div class="container text-left">
                <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
                <a href="<%=request.getContextPath()%>/deleteAll" class="btn btn-danger" 
       onclick="return confirm('Are you sure you want to delete all users?');">Delete All Users</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>NRP</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Alamat</th>
                        <th>Jurusan</th>
                        <th>Gender</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${listUser}">
                        <tr>
                            <td><c:out value="${user.id}" /></td>
                            <td><c:out value="${user.nrp}" /></td>
                            <td><c:out value="${user.name}" /></td>
                            <td><c:out value="${user.email}" /></td>
                            <td><c:out value="${user.alamat}" /></td>
                            <td><c:out value="${user.jurusan}" /></td>
                            <td><c:out value="${user.gender}" /></td>
                            <td>
                                <a href="edit?id=<c:out value='${user.id}' />" class="btn btn-primary btn-sm">Edit</a>
                                &nbsp;&nbsp;
                                <a href="delete?id=<c:out value='${user.id}' />" class="btn btn-danger btn-sm">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
