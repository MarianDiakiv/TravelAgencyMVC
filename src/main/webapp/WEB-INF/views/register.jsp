<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<style>
    .error{
        color: red;
    }
</style>
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

<div class="row" >
    <div class="col-sm-2" ></div>
    <div class =col-sm-8 >
        <div style="margin-left:25%;
						margin-top:5%">
            <h1 style="margin-left: 70px">Реєстрація</h1>
            <c:if test="${param.fail}">
                <p style="color: red;">Fail to autorize</p>
            </c:if>
            <form:form action="/register" method="POST" modelAttribute="registerModel">
                <form:errors path="*" cssClass="error"/><br>
                <label style="width: 140px;">Email</label>
                <form:input path="email"/>
                <br>
                <label style="width: 140px;">Пароль</label>
                <form:password path="password"/>
                <br>
                <label style="width: 140px;">Повторіть пароль</label>
                <form:password path="passwordConfirmation"/>
                <br>
                <input style="margin-top: 3%;  width: 185px; margin-left: 70px;" class="btn btn-dark" type="submit" value="Реєстрація">
            </form:form>


        </div>

    </div>
    <div class="col-sm-2" ></div>
</div>

</body>
</html>
