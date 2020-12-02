<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
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
<sec:authorize access="isAuthenticated()">
    <div class ="conteiner-fluid" >
        <div style ="margin-top:3%" class="row" >
            <div class="col-sm-2"></div>
            <div class="col-sm-8">

                <div class="row" style="margin-top:5%">
                    <div class="col-sm-12">
                        <c:if test="${param.fail}">
                            <p>Fail to edit</p>
                        </c:if>
                        <form:form action="/edit-user-rofile/${userId}" modelAttribute="userEditModel" method="post">
                            <form:errors path="*" cssClass="error"/><br>
                            <label style="width: 80px;">Full Name</label><form:input path="fullName"/><br>
                            <label style="width: 80px;" >Email</label><form:input path="email"/><br>
                            <label style="width: 80px;">Age</label><form:input path="age"/><br>
                            <input class="btn btn-dark" type="submit" value="Save" >
                        </form:form>

                    </div>
                </div>


            </div>
        </div>
    </div>
</sec:authorize>
</body>
</html>
