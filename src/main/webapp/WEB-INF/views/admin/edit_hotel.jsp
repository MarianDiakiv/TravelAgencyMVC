<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Hotel</title>
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
        <a style="margin-left: 20px;   color: black;" href= "/adminBoard/admin-page">Country/RoomType</a>
    <a style="margin-left: 20px;   color: black;" href= "/adminBoard/showAllOrdering">Order</a>

    </nav>

    <div class ="conteiner-fluid" >
    <div style ="margin-top:3%" class="row" >
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div class="row">
                <form:form action="/adminBoard/edit-hotel/${hotelModel.id}"
                           method="POST" modelAttribute="hotelModel" >
                    <label style="width: 60px" >Country</label><label> ${hotelModel.country.country}</label><br>
                    <label style="width: 60px">Name</label> <form:input path="name"/><br>
                    <label style="width: 60px">City</label> <form:input path="city"/><br>
                    <label style="width: 60px">Street</label> <form:input path="street"/><br>
                    <input class="btn btn-dark" type="submit" value="Save"  >
                </form:form>
            </div>
            <div class="row" style="margin-top: 5%">
                <!--show room from hotel  -->
                <table class="table table-stripped" >
                    <tr>
                        <td> Номер </td>
                        <td> Тип кімнати </td>
                        <td> Ціна </td>
                    </tr>
                    <c:forEach items="${rooms}" var="hotelRooms">
                        <tr>
                            <td> ${hotelRooms.number } </td>
                            <td> ${hotelRooms.typeRoom.typeRoom } </td>
                            <td> ${hotelRooms.price } </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="row" style="margin-top: 5% ">
                <%-- Add room to hotel --%>
                    <form:form action="/adminBoard/create-room/${hotelModel.id}" method="POST" modelAttribute="roomRequest">
                        <label style="width: 80px;">Country</label>
                        <form:select path="typeRoom">
                            <form:options items="${typesModel}"/>
                        </form:select><br>

                        <%--<form:select path="country" items="${countries}"></form:select><br>--%>
                        <label style="width: 80px;">Number</label><form:input path="number"/><br>
                        <label style="width: 80px;" >Price</label><form:input path="price"/><br>
                        <input class="btn btn-dark" type="submit" value="Save" >
                    </form:form>
            </div>

        </div>
        </div>
    </div>
</sec:authorize>
</body>
</html>
