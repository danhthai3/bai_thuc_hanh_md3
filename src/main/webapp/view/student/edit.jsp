<%--
  Created by IntelliJ IDEA.
  User: quang
  Date: 8/6/2023
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous"></script>
    <title>Edit</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="student?action=getAll">Home</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="student?action=add">Add Student <span
                                    class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                    <form action="student?action=search" method="post" class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"
                               name="search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
            </nav>
        </div>
    </div>
    <div class="row mt-5">
        <div class="col">
            <form action="student?action=edit&studentId=${student.studentId}" method="post">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputName">Name</label>
                        <input type="text" class="form-control" id="inputName" name="studentName" placeholder="Nguyễn Văn A"
                               value="${student.studentName}" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputDateOfBirth">Date Of Birth</label>
                        <input type="date" class="form-control" id="inputDateOfBirth" name="dateOfBirth" value="${student.dateOfBirth}" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputEmail">Email</label>
                        <input type="text" class="form-control" id="inputEmail" name="email"
                               placeholder="ABC@gmail.com" value="${student.email}" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputAddress">Address</label>
                        <input type="text" class="form-control" id="inputAddress" name="address" placeholder="Hà Nội"
                               value="${student.address}" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputPhone">Phone</label>
                        <input type="text" class="form-control" id="inputPhone" name="phoneNumber"
                               placeholder="0123456789" value="${student.phoneNumber}" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputClassroom">Classroom</label>
                        <select id="inputClassroom" class="form-control" name="classId">
                            <c:forEach items="${classroomList}" var="classroom">
                                <option value="${classroom.classId}">${classroom.className}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Edit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script>
    document.getElementById("inputClassroom").value =${student.classroom.classId};
</script>
