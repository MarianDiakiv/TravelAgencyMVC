<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hotel </title>
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
<div class ="conteiner-fluid" >
    <div class = "row" >
        <div class="col-sm-2" ></div>
        <div class="col-sm-8" >
            <div class="row" style="margin-top:5%">
                <div class="col-sm-12">
                <div  class="row" style="margin-top: 1%" >
                    <div class = "col-sm-3" >Name of Hotel</div>
                    <div class = "col-sm-9" >${hotelModel.name}</div>
                </div>
                <div class="row" style="margin-top: 1%">
                    <div class = "col-sm-3" >Country</div>
                    <div class = "col-sm-9" >${hotelModel.country.country}</div>
                </div>
                <div class="row" style="margin-top: 1%">
                    <div class = "col-sm-3" >City</div>
                    <div class = "col-sm-9" >${hotelModel.city}</div>
                </div>
                <div class="row" style="margin-top: 1%">
                    <div class = "col-sm-3" >Вулиця</div>
                    <div class = "col-sm-9" >${hotelModel.street}</div>
                </div>
                </div>
            </div>
            <div style="margin-top:2%" class="row" >
                <form:form action="/free-rooms/${hotelModel.id}"
                           method="POST" modelAttribute="dateModel">
                    <label style="margin-left:20px;">Select date of check-in</label><form:input style="margin-left:20px;" path="date1" type="Date"/>
                    <label style="margin-left:20px;">Select date of check-out</label><form:input style="margin-left:20px;" path="date2" type="Date"/>
                    <input class="btn btn-light" type="Submit" value="Сonfirm"  >
                </form:form>
            </div>
            <div class="row" >
                <p style="font-size: 17px; ">Rooms</p>
                <table class="table table-stripped" >
                    <tr>
                        <td>Number</td>
                        <td>Type of room</td>
                        <td>Price</td>
                        <td>Booking</td>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <td> Edit </td>
                        </sec:authorize>
                    </tr>
                    <c:forEach items="${roomsModel}" var="hotelRooms">
                        <tr>
                            <td> ${hotelRooms.number } </td>
                            <td> ${hotelRooms.typeRoom.typeRoom } </td>
                            <td> ${hotelRooms.price } </td>
                                <%-- <td><a href="${pageContext.request.contextPath}/order/${hotelRooms.id}/${dateAtrr}" >Забронювати</a></td> --%>
                            <td>
                                <sec:authorize access="isAuthenticated()">
                                <form:form action="/order/${hotelRooms.id}"
                                            method="POST" modelAttribute="dateModel" >
                                    <div style="margin-top: 5px;" ><label>Select date</label><form:input style="margin-left:20px;" path="date1" type="Date"/><br></div>
                                    <div style="margin-top: 5px;" ><label>Select date</label><form:input style="margin-left:20px;" path="date2" type="Date"/><br> </div>
                                    <input class="btn btn-dark" type="submit" value="Book a room" >
                                </form:form>
                                </sec:authorize>
                                <sec:authorize access="!isAuthenticated()">
                                    <a href="/register"> Register </a>
                                </sec:authorize>
                            </td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <td> <a href="/adminBoard/edit-room/${hotelRooms.id}">Edit</a> </td>
                            </sec:authorize>

                        </tr>
                    </c:forEach>
                </table>
            </div>
            <%--<sec:authorize access="isAuthenticated()">--%>
                <%--<div class="row"   >--%>

                <%--</div>--%>
            <%--</sec:authorize>--%>
        </div>
    </div>
</div>
</body>
</html>
