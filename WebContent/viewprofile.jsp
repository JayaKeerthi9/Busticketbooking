<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*" import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="profile.css" type="text/css">
			<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href='https://fonts.googleapis.com/css?family=Rancho' rel='stylesheet'>
	

<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
body , html
{
	height: 100%;
	margin: 0;
	background-image: url("busimage21.jpg");
	min-height: 500px;
	height: 100%; 
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
}




</style>

</head>
<body>

<center>
<%
response.setContentType("text/html");

String name=(String) session.getAttribute("username");

System.out.println(name);

try{
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
       System.out.println("connected");
      PreparedStatement p;
     p=con.prepareStatement("select * from register WHERE name=?");
      p.setString(1,name);
          
     int i= p.executeUpdate();
     
     ResultSet rs =p.executeQuery();

     if(i>0) {
    	 
    	
	 		out.print("<div class='divbox'>");
	 		
	 		if(rs.next()) {
	 			
	 			%>
	 			<div class="left">
				<img src="avatar.jpg"class="w3-circle" width='50%'></img><br>
				
				<h3> <%=rs.getString(1).toUpperCase()%> </h3>
			</div>
			<div class="right">
				<div class="profile">
					<h2> PROFILE </h2>
				</div>
				<div class="info">
					<h3> <i class="fa fa-envelope"></i></h3>
					<p> <%=rs.getString(2) %></p>
					
					<h3> <i class="fa fa-mobile fa-2x"></i> </h3>
					<p> <%=rs.getString(3) %> </p>
				</div>
			</div>
	 		<% 
				
			
			}
	 		
			 


%></div>
<%



	 		
	 	}

}catch(java.lang.ClassNotFoundException e) {
System.out.println("Oracle Driver not found");
}
catch(SQLException ex) {
System.out.println("SQLException:" + ex.getMessage());
}
%>
</center>

</body>
</html>