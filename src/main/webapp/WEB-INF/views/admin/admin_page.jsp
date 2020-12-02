<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/home">DMMTravel</a>
    <br>
    <a style="margin-left: 20px;   color: white;" href="/hotels" > Hotels</a>
    <sec:authorize access="!isAuthenticated()">
        <a style="margin-left: 20px;   color: white;" href="/login" > Login</a>
        <br>
        <a style="margin-left: 20px;   color: white;" href="/register" > Register</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">

        <a style="margin-left: 20px;   color: white;" href="/profile" >Profile</a>
        <div style="margin-left: 70%">
            <form:form action="/logout" >
                <input class="btn btn-dark" type="submit" value="Logout" >
            </form:form>
        </div>
    </sec:authorize>

</nav>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <nav style="margin-top: 2%;" class="navbar navbar-expand-sm  bg-light">
        <a style="margin-left: 20px;   color: black;" href="/adminBoard/create-hotel" > Create Hotel</a>
        <a style="margin-left: 20px;   color: black;" href= "/adminBoard/admin-page">Country/RoomType</a>
        <a style="margin-left: 20px;   color: black;" href= "/adminBoard/all-user">User and Order</a>

    </nav>

    <div class ="conteiner-fluid" >
    <div style ="margin-top:3%" class="row" >
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <div class="row" >
            <form:form action="/adminBoard/addCountry" method="post" modelAttribute="country">
                <label style="width: 80px;"> Country</label><form:input path="country"/>
                <input class="btn btn-dark" type="submit" value="create" >
            </form:form>
        </div>
        <div class="row" style="margin-top: 5%"  >
            <form:form action="/adminBoard/addTypeRoom" method="post" modelAttribute="roomType">
                <label style="width: 80px;"> Room Type </label><form:input path="typeRoom"/>
                <input class="btn btn-dark" type="submit" value="create" >
            </form:form>
        </div>
        <%--<form:form action="/adminBoard/createHotel" method="POST" modelAttribute="hotel">--%>
        <%--<label style="width: 80px;">Назва</label><form:input path="name"/><br>--%>
        <%--<label style="width: 80px;" >Місто</label><form:input path="city"/><br>--%>
        <%--<label style="width: 80px;">Адреса</label><form:input path="street"/><br>--%>
        <%--<input class="btn btn-dark" type="submit" value="Зберегти" >--%>
    <%--</form:form>--%>
    </div>
    <div class="col-sm-2"></div>
    </div>
    </div>
</sec:authorize>
</body>
</html>
