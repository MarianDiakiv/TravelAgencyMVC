<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
                        <div  class="row" style="margin-top: 1%" >
                            <div class = "col-sm-3" > Full Name</div>
                            <div class = "col-sm-9" >${userModel.fullName}</div>
                        </div>
                        <div class="row" style="margin-top: 1%">
                            <div class = "col-sm-3" >Age</div>
                            <div class = "col-sm-9" >${userModel.age}</div>
                        </div>
                        <div class="row" style="margin-top: 1%">
                            <div class = "col-sm-3" >Email</div>
                            <div class = "col-sm-9" >${userModel.email}</div>
                        </div>
                        <div class="row" style="margin-top: 1%">
                            <a class="btn btn-light" style="margin-left: 2%"  href="/edit-user-rofile/${userModel.id}">Edit</a>
                        </div>

                    </div>
                </div>
            <div class="row" style="margin-top: 5%">
                <P>Orders</P>
                <table class="table table-striped">
                    <tr>
                        <td>Hotel Name</td>
                        <td>Number</td>
                        <td>Type room</td>
                        <td> Date check in </td>
                        <td> Date check out </td>
                        <td>Сancel</td>
                    </tr>
                    <c:forEach items="${orderModel}" var="order">
                        <tr>
                            <td>${order.room.hotel.name}</td>
                            <td>${order.room.number}</td>
                            <td>${order.room.typeRoom.typeRoom}</td>
                            <td> ${order.dateOfSettlement}</td>
                            <td> ${order.departureDate}</td>
                            <td>
                                <form:form action="/cancel-order/${order.id}" method="post" modelAttribute="" >
                                    <input class="btn btn-light" type="submit" value="Cancel" >
                                </form:form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </div>
    </div>
</div>
</sec:authorize>
</body>
</html>
