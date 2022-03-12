<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://fonts.googleeapis.com/css?family=Lobster">

<meta charset="ISO-8859-1">
<meta http-equiv="Refresh" content="5;url=Travel Home.html">
<title>LOGOUT</title>

	
<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>

<style type="text/css">
 body{
 	background-image: url("busimage11.jpg");
 	height: 100%;
	margin: 0;
	min-height: 500px;
	height: 100%; 
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
	
	 font-family: Lobster;
    font-size: 22px;
 }
 <!-- h1{
 
 background: linear-gradient(to top, #ea8d8d 0%, #a890fe 100%);
-webkit-background-clip: text;
-webkit-text-fill-color: transparent;
font-family: lobster , gabirola;
line-height: 33px;
 
 }-->
 
 
</style>
	
</head>
<body>

<center>
<br></br>
<br></br>
<br></br>
<script type="text/javascript">
 swal('THANK YOU','Have a nice day!','success');
 </script>

	
	<h1>LOGOUT SUCCESSFULLY</h1>
</center>
<%

	session.invalidate();
	//String name=(String) session.getAttribute("username");
	
//RequestDispatcher rd=request.getRequestDispatcher("Travel Home.html"); 
	       // rd.include(request,response); 	
%>

</body>
</html>