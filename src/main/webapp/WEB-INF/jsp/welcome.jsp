

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="test.json.*" %>
<jsp:useBean id="order" class="test.json.WebController" scope="request"/>
<jsp:setProperty name="person" property="*" />


<%--
  Created by IntelliJ IDEA.
  User: annaleis
  Date: 7/27/14
  Time: 5:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>ZMS</title>
</head>
<body>
<section>
    <div class="jumbotron">
    <h1>ZWMS</h1>
    <p>Please enter your order:</p>
    </div>
    <form name="myform" action="order.jsp" method="post">
        <% Request newreq = new Request(); %>
        Accessories : <input type="int" name="acc" value = <%= newreq.getAccessories() %>><br>
        Bags : <input type="int" name="bags" value = <%= newreq.getBags() %>><br>
        Beauty : <input type="int" name="beaut" value = <%= newreq.getBeauty() %>><br>
        Clothes : <input type="int" name="cloth" value = <%= newreq.getClothes() %>><br>
        Housewares : <input type="int" name="house" value = <%= newreq.getHousewares() %>><br>
        Shoes : <input type="int" name="shoes" value = <%= newreq.getShoes() %>><br>
        <input type="SUBMIT" value="OK">
    </form>
</section>
</body>
</html>