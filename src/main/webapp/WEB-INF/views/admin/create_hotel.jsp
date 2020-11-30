<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Hotel</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">DMMTravel</a>
    <sec:authorize access="!isAuthenticated()">
        <a style="margin-left: 20px;   color: white;" href="/login" > Login</a>
        <br>
        <a style="margin-left: 20px;   color: white;" href="/register" > Register</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a style="margin-left: 20px;   color: white;" href="/hotels" > Hotels</a>
        <br>
        <a style="margin-left: 20px;   color: white;" href="/profile/${user.id}" >${user.email}</a>

    </sec:authorize>

</nav>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <nav style="margin-top: 2%;" class="navbar navbar-expand-sm  bg-light">
        <a style="margin-left: 20px;   color: black;" href="/adminBoard/create-hotel" > Create Hotel</a>
        <a style="margin-left: 20px;   color: black;" href= "/adminBoard/addRoomType">Create room type</a>
        <a style="margin-left: 20px;   color: black;" href= "/adminBoard/showAllOrdering">Order</a>

    </nav>

    <div class ="conteiner-fluid" >
        <div style ="margin-top:3%" class="row" >
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <form:form action="/adminBoard/create-hotel" method="POST" modelAttribute="hotel">
                    <label style="width: 80px;">Country</label>
                    <form:select path="country">
                        <form:options items="${countries}"/>
                    </form:select><br>

                    <%--<form:select path="country" items="${countries}"></form:select><br>--%>
                    <label style="width: 80px;">Name Hotel</label><form:input path="name"/><br>
                    <label style="width: 80px;" >City</label><form:input path="city"/><br>
                    <label style="width: 80px;">Street</label><form:input path="street"/><br>
                    <input class="btn btn-dark" type="submit" value="Save" >
                </form:form>

            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>
</sec:authorize>
</body>
</html>
