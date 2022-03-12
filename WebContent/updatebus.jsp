<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*,java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UPDATE bUS</title>
</head>
<body>
<%
String selvalue = request.getParameter("bupdate");
System.out.println(selvalue);
String id = request.getParameter("busid");
int bid = Integer.parseInt(id);
System.out.println(bid);
String value = request.getParameter("text");
System.out.println(value);
%>
<%!
Connection con;
%>
<%
try
   {
Class.forName("oracle.jdbc.driver.OracleDriver");
con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
System.out.println("Connected");

String query = "update buses set "+selvalue+"=? where busid=?";

PreparedStatement ps = con.prepareStatement(query);

ps.setString(1,value);
ps.setInt(2,bid);
int i=ps.executeUpdate();
	if(i>0){
		out.print("<html><head>");
		out.println("<link rel='stylesheet' type='text/css' href='addbus.css'>");
		out.print("</head><body>");
		out.print("<br></br>" );
		out.print("<br></br>");
		out.print("<br></br>");
		out.print("<br></br>");

		out.println("<center><form id='form1' ><center><div class='centered' ><h2>Updated Successfully....</h2></div>");
		
		out.println("<h2><a href='Updatebus.html' >Update Another One </a> </h2>");
		out.println("<h2><a href='Viewbus.html' >view Buses </a> </h2>");

		out.print("</center></form></center></bod></html>");
	}else{
		out.print("<html><head>");
		out.println("<link rel='stylesheet' type='text/css' href='addbus.css'>");
		out.print("</head><body>");
		out.print("<br></br>");		
		out.print("<br></br>");

		out.print("<br></br>");
		out.print("<br></br>");

		out.println("<form id='form1' ><center><div class='centered' > <h2>Not Updated</h2><div>");
		
		out.println("<h2><a href='Updatebus.html' >Update Another One </h2></a> ");
		out.println("<h2><a href='Viewbus.html' >view Buses </a> </h2>");

		out.print("</center></form></center></bod></html>");

	}

   }
catch(SQLException se)
{
System.out.println("SQL Exception has raised");
se.printStackTrace();
}
catch(ClassNotFoundException ce)
{
System.out.println("Driver not found");
ce.printStackTrace();
}


%>
</body>
</html>

