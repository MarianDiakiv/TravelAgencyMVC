<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hotels</title>
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

<div class ="conteiner-fluid" >
    <div style ="margin-top:3%" class="row" >
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div class="row">
                <form:form action="/find-by-country" modelAttribute="country" method="post">
                    <label> Select country</label>
                    <form:select path="country">
                        <form:options items="${countries}"/>
                    </form:select>
                    <input  class="btn btn-dark" type="submit" value="Seach">
                </form:form>
            </div>
            <div class="row" >
                <table class="table table-stripped" >
                    <tr>
                        <td>Name</td>
                        <td> Country </td>
                        <td>City</td>
                        <td>Streed</td>

                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <td> Edit </td>
                        </sec:authorize>
                    </tr>
                    <c:forEach items="${hotels}" var="hotel">

                        <tr>
                            <td><a href="/hotel-info/${hotel.id}">${hotel.name}</a></td>
                            <td>${hotel.country.country}</td>
                            <td>${hotel.city}</td>
                            <td>${hotel.street}</td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td> <a href="/adminBoard/edit-hotel/${hotel.id}">Edit</a> </td>
                            </sec:authorize>

                        </tr>

                    </c:forEach>
                </table>
        </div>
        <div class="col-sm-2"></div>
    </div>
</div>
</body>
</html>
