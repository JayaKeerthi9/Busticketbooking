<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
<link rel="stylesheet" type="text/css" href = "PaymentStyle.css">

</head>

<body>
<center>
<% 
String no=request.getParameter("no");
String id=request.getParameter("id");

System.out.println(no);
System.out.println(id); %>




<div class="w3-container w3-mobile w3-padding-16 w3-display-middle" class="div2">
<form class="w3-container w3-mobile w3-border w3-border-black w3-white" id="form2" text-align='center' action="paymentServlet">
<h1>PAYMENT</h1>
<table>
<tr><td>Bus Id:</td><td><input type="text" name="id" value=<%=id%> ></td></tr><br/>
<tr><td>No of Seats to be Reserved:</td><td><input type="text" name="no" value=<%=no%> reqiured></td></tr><br/>
<div class="w3-container w3-mobile w3-padding-16 w3-bar w3-border w3-black" class="div1">
<label class="w3-bar-item">Payment : </label>

<input class="w3-radio w3-bar-item" type="radio" name="card" value="Credit/Debit">
<label class="w3-bar-item ">Credit/Debit</label>

<input class="w3-radio w3-bar-item" type="radio" name="card" value="Netbanking">
<label class="w3-bar-item ">Netbanking</label>
</div><br/>

</table><br/>






<div class="w3-container w3-mobile w3-bar w3-padding-32" id="div3">
<input type="submit"  value="Submit">
</div>

</form>
</div>
</center>
</body>
</html>