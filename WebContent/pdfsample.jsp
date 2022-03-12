<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TICKET</title>
<style>
@media print {
  #pbtn {
    display: none;
  }
}
</style>
<style type="text/css">
 body{
 	background-image: url("busimage18.jpg");
 	margin-top: 0px;
	padding-top: 0px;
	margin-bottom: 0px;
	min-height: 620px;
	height: 100%; 
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
	max-width: 100%;
	margin-top: 0;
	padding-top: 0;
	position: relative;
 }
 table,tr,td
{
font-family: "Trebuchet MS",gabirola , serif;
border: 1px groove ;
border-collapse: collapse;
font-size: 1.8vw;
padding: 8px;
}

tr:nth-child(even)
{
	background-color : #f2f2f2;
	
}
 
</style>
</head>
<center>
<body onload="javascript:window.print()">
<%
String id = (String)session.getAttribute("id");
String name = (String)session.getAttribute("username");
String  n = (String) session.getAttribute("n");
int n1 = Integer.parseInt(n);
for(int i=0; i<n1 ;i++){
	
	String names = (String) session.getAttribute("name"+i);
	

String url = "jdbc:oracle:thin:@localhost:1521:xe";
String username = "dbuser";
String password = "1234";
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con = DriverManager.getConnection(url, username, password);
System.out.println("Connected...");
PreparedStatement stmt = con.prepareStatement("select * from buses where busid=?");
stmt.setString(1, id);
ResultSet rs1 = stmt.executeQuery();
out.print("<br/>");
out.print("<br/>");

out.println("<h1><u> BUS INFORMATION</u></h1>");
out.println("<table border=1>");
out.println("<tr><td>Bus NAME</td><td>COACH</td><td>SOURCE</td><td>DESTINATION</td><td>ARRIVAL TIME</td><td>DEPARTURE TIME</td><td>FARE</td></tr>");
while(rs1.next()){
	out.println("</td><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td><td>"+rs1.getString(6)+":"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+":"+rs1.getString(9)
	+"</td><td>"+rs1.getString(10)+"</td></tr>");

	
}
out.println("</table><br/><br/>");
PreparedStatement pstmt = con.prepareStatement("select * from seatinformation"+id+" where name='"+names+"'");
ResultSet rs = pstmt.executeQuery();
out.print("<h1><u>PASSENGERS INFORMATION</u></h1>");
out.println("<table>");
out.println("<tr><td>BUS ID</td><td>NAME </td><td>EMAIL</td><td>SEAT NO</td></tr>");

while(rs.next()){
	out.println("<tr><td> "+id+"</td>");
	out.println("<td> "+rs.getString("name")+"</td>");
	out.println("<td>"+rs.getString("email")+"</td>");
	out.println("<td>"+rs.getString("seatNos")+"</td></tr>");

}
out.println("</table>");
out.print("<br/>");
out.print("<br/>");
out.print("<br/>");
out.print("<br/>");

out.print("<h2>HAPPY AND SAFE JOURNEY!<h2>");




rs.close();
pstmt.close();
con.close();
%>

<%}
catch(SQLException se)
{
System.out.println("SQL Exception has raised...");
se.printStackTrace();
}
catch(ClassNotFoundException ce)
{
System.out.println("Driver not found...");
ce.printStackTrace();
}
}
%>

</body>
</center>