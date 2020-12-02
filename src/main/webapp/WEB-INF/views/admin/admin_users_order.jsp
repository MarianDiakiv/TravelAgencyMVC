<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Orders</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/home">DMMTravel</a>
    <sec:authorize access="!isAuthenticated()">
        <a style="margin-left: 20px;   color: white;" href="/login" > Login</a>
        <br>
        <a style="margin-left: 20px;   color: white;" href="/register" > Register</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a style="margin-left: 20px;   color: white;" href="/hotels" > Hotels</a>
        <br>
        <a style="margin-left: 20px;   color: white;" href="/profile" >Profile</a>

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
            <div class="row" style="margin-top: 1%" >
                <div class="col-sm-12">
                    <div class="row" style="margin-top: 1%" >
                        <p> User Profile</p>
                    </div>
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

                </div>
            </div>
            <div class="row" style="margin-top: 5%">
                <div class="col-sm-12">


            <table class="table table-striped">
                <tr>
                    <td> Hotel</td>
                    <td> Date check in</td>
                    <td> Date check out</td>
                </tr>
                <c:forEach items="${ordersModel}" var="order">
                    <tr>
                        <td> ${order.room.hotel.name}</td>
                        <td> ${order.dateOfSettlement}</td>
                        <td> ${order.departureDate}</td>
                    </tr>
                </c:forEach>
            </table>
            </div>
            </div>
        </div>
    </div>
    </div>
</sec:authorize>
</body>
</html>
