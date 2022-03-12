<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bus Details</title>
<link rel='stylesheet' type='text/css' href='bustable.css' >
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>

</head>
<body>
<%
String id = request.getParameter("busid");
Connection con;

try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
System.out.println("connected");

String query = "select * from buses where busid=?";
PreparedStatement ps=con.prepareStatement(query);  

ps.setString(1,id);  


int i=ps.executeUpdate();
System.out.println(i);

ResultSet rs = ps.executeQuery();


 if(i>0) {
	//out.print("<html><body>");
	//out.print("<form action='seat.jsp' method='post'>");
	
	out.print("<center><h1> Bus Details..!&nbsp<i class='fa fa-bus'></i><h1></center><br/>");
	
	out.print("<table align='center' border='1'>");
	out.println("<tr> <th>Bus Id</th><th>BusName</th> <th>BusType</th> <th>Departure Time</th> <th>Arrival Time</th>"
			+ "<th>Fare</th></tr>") ;
	

	while(rs.next()) {
	
		out.print("<tr><td>"+rs.getString("busid")+"</td><td>"+rs.getString("bname")+"</td><td>"+rs.getString("btype")+"</td><td>"+rs.getInt("bstarttimehrs")+":"+rs.getInt("bstarttimemin")+"</td><td>"+rs.getInt("barrtimehrs")+":"+rs.getInt("barrtimemin")+"</td><td>"+rs.getInt("bfare")+"</td></tr>");
	
	}
	out.print("</table>");
	//out.print("</body></html>");
	
	
	

	



}else {
	out.print("<center><h1><div class='w3-container w3-mobile w3-display-middle'>NO Buses are Avialable</div><h1></center>");
}

}catch(java.lang.ClassNotFoundException e) {
	System.out.println("Oracle Driver not found");
}
catch(SQLException ex) {
	System.out.println("SQLException:" + ex.getMessage());
}
%>

</body>
</html>