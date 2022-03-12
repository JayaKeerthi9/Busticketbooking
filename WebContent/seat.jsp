<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
 body{
 	background-image: url("busimage11.jpg");
 }
</style>

<meta charset="ISO-8859-1">
<title>Seat Booking</title>
<link rel='stylesheet' type='text/css' href='bustable.css' >
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
		
</head>
<body>
<form method="post" action="place.jsp">
<center><h1>Available Bus Seats </h1></center>
<table border="1" align='center'>

<tr><th>Sno</th><th>SEAT A</th><th>SEAT B</th><th>SEAT C</th><th>SEAT D</th></tr>
<%

String id=request.getParameter("buses");
String no=request.getParameter("no");
int n= Integer.parseInt(no);
System.out.println(id);
//out.print(id);
try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
	System.out.println("connected");
	Statement st=con.createStatement();
	ResultSet rs= st.executeQuery("select * from seatbooking"+id);
	while(rs.next()){
%>
	<tr><td><%=rs.getString(1)%></td><td><%=rs.getString(2)%></td><td><%=rs.getString(3)%></td><td><%=rs.getString(4)%></td><td><%=rs.getString(5)%></td></tr>

<%
	}
	}catch(java.lang.ClassNotFoundException e) {
		System.out.println("Oracle Driver not found");
	}
	catch(SQLException ex) {
		System.out.println("SQLException:" + ex.getMessage());
	}
%>
</table><br/>
<center><h1>Please Enter Below Details<i class="fa fa-hand-o-down" ></i></h1></center>
<table align='center' bgcolor='white'>
<tr><td>Bus Id:</td><td><input type="text" name="id" value=<%=id%>></td></tr>
<tr><td>Enter No of Seats to be Reserved:</td><td><input type="text" name="no" value=<%=no%> reqiured></td></tr>
<% for(int i=0;i<n;i++) { 
out.print("<tr><td>Enter Name:</td><td><input type='text' name='name"+i+"' placeholder='Enter name' reqiured autofocus></td></tr>");
out.print("<tr><td>Enter Email:</td><td><input type='email' name='email"+i+"' placeholder='Enter Email' reqiured style='height: 3vw'></td></tr>");
out.print("<tr><td>Enter Seat to Reserve:</td><td><input type='text' name='seat"+i+"' ></td></tr>");
} %>
</table><br>
<center><input type="submit" value="Submit"></center>

</form>
</html>