<%-- 
    Document   : index
    Created on : 16-Mar-2017, 1:38:47 AM
    Author     : seong
--%>

<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Info</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
        <link href="css/index.css" rel="stylesheet" type="text/css"/>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        
        <h1><img src="img/bankimage.png" alt="BankImage"> Seong Bank's Customer Information</h1>
        <form action="CheckServlet" method="post">
        <table class='buttonTable'>
            <td class='buttonTable'>
                <button type="submit" name="button" value="insert" class='btn btn-lg btn-primary'>Insert</button>
            </td>
            <td class='buttonTable'>
                <button type="submit" name="button" value="update" class='btn btn-lg btn-success'>Update</button>
            </td>
            <td class='buttonTable'>
                <button type="submit" name="button" value="delete" class='btn btn-lg btn-danger'>Delete</button>
            </td>
            <td class='buttonTable'>
                <button type="submit" name="button" value="select" class='btn btn-lg btn-info'>Select</button>
            </td>
            <td class='buttonTable'>
                <button type="submit" name="button" value="retrieve" class='btn btn-lg btn-info'>Retrieve</button>
            </td>  
        </table>
            <table border="1">
                <tr>
                    <td>No (Update & Delete)</td>
                    <td><input type="number" name="no" value=${retrieved.getNo()}></td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName" value=${retrieved.getFirstName()}></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName" value=${retrieved.getLastName()}></td>
                </tr>
                <tr>
                    <td>Account Number</td>
                    <td><input type="text" name="accountNumber" value=${retrieved.getAccountNumber()}></td>
                </tr>
                <tr>
                    <td>Date</td>
                    <td><input type="date" name="date" value=${retrieved.getDate()}></td>
                </tr>
                <tr>
                    <td>Total</td>
                    <td><input type="number" name="total" value=${retrieved.getTotal()}></td>
                </tr>
                <tr>
                    <td>Branch</td>
                    <td><input type="text" name="branch" value=${retrieved.getBranch()}></td>
                </tr>
                <tr>
                    <td>Manager</td>
                    <td><input type="text" name="manager" value=${retrieved.getManager()}></td>
                </tr>
            </table>
        </form>
        
        <h3>${result}</h3>
        <p style="color:red;text-align:center;">${error}</p>
        <% if(request.getAttribute("customers") != null){
        %>
        <h4>Current Data:</h4>
        <table class='infoTable'>
            <tr class='infoTable'>
                <td class='infoTable'>No.</td>
                <td class='infoTable'>First Name</td>
                <td class='infoTable'>Last Name</td>
                <td class='infoTable'>Date</td>
                <td class='infoTable'>Account Number</td>
                <td class='infoTable'>Total</td>
                <td class='infoTable'>Branch</td>
                <td class='infoTable'>Manager</td>
            </tr>
            <c:forEach var="customer" items="${customers}">
                <tr class='infoTable'>
                    <td class='infoTable'>
                        ${customer.getNo()}
                    </td>
                    <td class='infoTable'>
                        ${customer.getFirstName()}
                    </td>
                    <td class='infoTable'>
                        ${customer.getLastName()}
                    </td>
                    <td class='infoTable'>
                        ${customer.getDate()}
                    </td>
                    <td class='infoTable'>
                        ${customer.getAccountNumber()}
                    </td>
                    <td class='infoTable'>
                        ${customer.getTotal()}
                    </td>
                    <td class='infoTable'>
                        ${customer.getBranch()}
                    </td>
                    <td class='infoTable'>
                        ${customer.getManager()}
                    </td>
                </tr>
            </c:forEach>
        </table>
        <%
        }
        %>
    </body>
</html>
