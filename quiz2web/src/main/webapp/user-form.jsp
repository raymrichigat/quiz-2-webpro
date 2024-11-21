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
            background-color: #f8f9fa; /* Light gray background for better contrast */
            font-family: Arial, sans-serif;
        }

        .navbar {
            background-color: #007bff; /* Blue color for navbar */
        }

        .navbar a.navbar-brand {
            font-weight: bold;
            font-size: 1.5em;
            color: #fff !important;
        }

        .card {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-top: 30px;
        }

        .card-body {
            padding: 20px;
        }

        h2 {
            font-weight: bold;
            color: #007bff; /* Match navbar color */
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            color: #555;
        }

        .form-control {
            border-radius: 5px;
        }

        button.btn-success {
            background-color: #28a745;
            border: none;
            padding: 10px 20px;
            font-size: 1em;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        button.btn-success:hover {
            background-color: #218838;
        }

        footer {
            margin-top: 30px;
            padding: 10px 0;
            background-color: #007bff;
            color: #fff;
            text-align: center;
        }
    </style>
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark">
            <div>
                <a>College Student Management App</a>
            </div>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <!-- Tentukan aksi form -->
                <form action="${user != null ? 'update' : 'insert'}" method="post">

                <!-- Form Title -->
                <div>
                    <h2>
                        <c:choose>
                            <c:when test="${user != null}">Edit User</c:when>
                            <c:otherwise>Add New User</c:otherwise>
                        </c:choose>
                    </h2>
                    <p>"${user.id}"</p>
                </div>

                <!-- Hidden Field for User ID -->
                <c:if test="${user != null}">
	    			<fieldset class="form-group">
	        			<label>User ID</label>
	        			<input type="text" value="${user.id}" class="form-control" readonly/>
	    			</fieldset>
				</c:if>

                <!-- User NRP -->
                <fieldset class="form-group">
                    <label>User NRP</label>
                    <input type="text" value="${user.nrp != null ? user.nrp : ''}" 
                           class="form-control" name="nrp" required>
                </fieldset>

                <!-- User Name -->
                <fieldset class="form-group">
                    <label>User Name</label>
                    <input type="text" value="${user.name != null ? user.name : ''}" 
                           class="form-control" name="name" required>
                </fieldset>

                <!-- User Email -->
                <fieldset class="form-group">
                    <label>User Email</label>
                    <input type="email" value="${user.email != null ? user.email : ''}" 
                           class="form-control" name="email" required>
                </fieldset>

                <!-- User Address -->
                <fieldset class="form-group">
                    <label>User Address</label>
                    <input type="text" value="${user.alamat != null ? user.alamat : ''}" 
                           class="form-control" name="alamat">
                </fieldset>

                <!-- User Major -->
                <fieldset class="form-group">
                    <label>User Major</label>
                    <input type="text" value="${user.jurusan != null ? user.jurusan : ''}" 
                           class="form-control" name="jurusan">
                </fieldset>

                <!-- User Gender -->
                <fieldset class="form-group">
   					<label>User Gender</label>
    				<select class="form-control" name="gender" required>
        				<option value="M" <c:if test="${user.gender == 'M'}">selected</c:if>>Male</option>
        				<option value="F" <c:if test="${user.gender == 'F'}">selected</c:if>>Female</option>
    				</select>
				</fieldset>

                
                <!-- Submit Button -->
                <button type="submit" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; 2024 College Student Management Application</p>
    </footer>
</body>
</html>
