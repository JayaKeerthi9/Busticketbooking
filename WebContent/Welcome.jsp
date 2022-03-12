<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
 body{
 	background-image: url("busimage14.jpg");
font-family: rancho , gabirola;
 	
 }
 #wel{
 
font-family: rancho , gabirola;
font-size: 4vw; 
 }
 #name{
 float: right;
 position: absolute;
 right: 70px;
 }
</style>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<meta name="viewport" content="width=device-width , initial-scale=1">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lobster&effect=brick-sign">
		<link href='https://fonts.googleapis.com/css?family=Rancho' rel='stylesheet'>
		
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		
	<link rel="stylesheet" href="chatbotstyle.css" type="text/css">
	<meta name = "viewport" content="width=device-width , initial-scale=1.0">
</head>
<%String n=request.getParameter("username"); %>
<body>
<div class="div6" class="w3-container w3-mobile">
			<div class=" w3-container w3-bar w3-mobile w3-black w3-card-5 " style="height: 40px">
			<a href="Travel Home.html" class="w3-bar-item w3-button w3-mobile w3-green "><i class="fa fa-home" style="height: 100px , width: 100px"></i></a>
			<a href="Travel Aboutus.html" class="w3-bar-item w3-button w3-mobile ">About Us </a>
			<a href="Travel Contactus.html" class="w3-bar-item w3-button w3-mobile  ">Contact us </a>
			<a href="Travel Busticket.html" class="w3-bar-item w3-button w3-mobile ">Book Ticket </a>
						<a href="viewprofile.jsp" class="w3-bar-item w3-button w3-mobile">View Profile</a>
			
			
			<a href="logout.jsp" class="w3-bar-item w3-button w3-mobile ">Logout </a>
			
				
				<!--  <div class="w3-container w3-mobile w3-dropdown-hover" class="div7">-->
				<a id="name" href="viewprofile.jsp" ><%=n.toUpperCase()%><img src="avatar.jpg" class=" w3-circle" style="width:35px"></a>
				
				
		</div>		
		
		
		

<%
//String n=request.getParameter("username");
out.print("<html><head>");
	
out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
out.print("</head><body>");
out.println("<script type='text/javascript'>");
out.println("swal('SUCCESSFULLY LOGIN','Have a nice day!','success')");
out.println("</script>");
out.print("</html></body>");
out.print("<br></br>");
out.print("<br></br>");
out.print("<br></br>");
out.print("<br></br>");

out.print("<center><h2 id='wel'>WELCOME "+n.toUpperCase()+"!</h2></center>");  
      
%>

</body>
</html>