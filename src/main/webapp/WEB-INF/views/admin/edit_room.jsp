<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit Room</title>
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
    <div class = "row" style="margin-top: 5%" >
        <div class="col-sm-2" ></div>
        <div class="col-sm-8" >
            <form:form action="/adminBoard/edit-room/${hotelId}/${roomId}" method="post" modelAttribute="roomRequestModel">
                <label style="width: 80px;">Id</label><label>${roomRequestModel.id}</label><br>
                <label style="width: 80px;">Type room</label>
                <form:select path="typeRoom">
                    <form:options items="${typesModel}"/>
                </form:select><br>

                <label style="width: 80px;">Number</label><form:input path="number" /><br>
                <label style="width: 80px;" >Price</label><form:input path="price"/><br>
                <input class="btn btn-dark" type="submit" value="Save" >
                <%--<form:button model action="  " ></form:button>--%>
            </form:form>


        </div>
    </div>
</div>


</sec:authorize>
</body>
</html>
