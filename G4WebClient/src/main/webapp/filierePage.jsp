<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<title>Filiere</title>
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">School</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link btn btn-primary" href="StudentController">Students</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-success" href="FiliereController">Filieres</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container py-5">
        <form action="FiliereController" method="post" class="bg-white p-4 rounded shadow mb-5">
            <h2 class="mb-4">Filiere Form</h2>
            <div class="mb-3">
                <label for="code" class="form-label">Code:</label>
                <input type="text" id="code" name="code" value="${singleFiliere.code}" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" id="name" name="name" value="${singleFiliere.name }" class="form-control" required>
            </div>
			<input type="hidden" name="_method" value="${btnType}">
			<input type="hidden" name="idMod" value="${singleFiliere.id }">
            <button type="submit" class="btn btn-success">Submit</button>
        </form>

        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                   <c:forEach items="${filieres}" var="filiere">
                    <tr>
                        <td>${filiere.id}</td>
                        <td>${filiere.code}</td>
                        <td>${filiere.name}</td>
                        <td>
						    <div style="display: flex; gap: 10px;">
						        <form action="FiliereController" method="post">
						            <input type="hidden" name="_method" value="delete">
						            <input type="hidden" name="idDelete" value="${filiere.id}">
						            <button type="submit" class="btn btn-danger">Delete</button>
						        </form>
						        <form action="FiliereController" method="post">
						            <input type="hidden" name="_method" value="getFiliere">
						            <input type="hidden" name="idGet" value="${filiere.id}">
						            <button type="submit" class="btn btn-success">Update</button>
						        </form>
						    </div>
						</td>

                    </tr>
                   </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
<ul>
</ul>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>