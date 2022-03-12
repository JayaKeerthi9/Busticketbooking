<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*,java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
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
 input[type=submit]
{
background: linear-gradient(to left, #0066ff 0%, #00ffff 100% ) ;
	color: white;
	cursor: pointer;
	border-radius: 20px;
	width: 155spx;
	height: 40px;
	font-size: 1.5vw;
	
	 

}
</style>
<% String n1=request.getParameter("username"); %>
<meta name="viewport" content="width=device-width , initial-scale=1">
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<!--  <link rel="stylesheet" type="text/css" href="PaymentStyle.css">-->
		<link rel="stylesheet" href="https://fonts.googleeapis.com/css?family=rancho">
		<link rel="stylesheet" type="text/css" href="loader.css">
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		
		
	</head>
	<body onload="myTime()" style="margin:0;">
	<div class="div6" class="w3-container w3-mobile">
			<div class=" w3-container w3-bar w3-mobile w3-black w3-card-5">
			<a href="Travel Home.html" class="w3-bar-item w3-button w3-mobile w3-green"><i class="fa fa-home"></i></a>
			<a href="Travel Aboutus.html" class="w3-bar-item w3-button w3-mobile">About Us </a>
			<a href="Travel Contactus.html" class="w3-bar-item w3-button w3-mobile">Contact us </a>
			<a href="Travel Busticket.html" class="w3-bar-item w3-button w3-mobile">Book Ticket </a>
						<a href="viewprofile.jsp" class="w3-bar-item w3-button w3-mobile">View Profile</a>
			
			<div class="w3-container w3-mobile w3-dropdown-hover">
				<button class="w3-button">Login</button>
				<div class="w3-dropdown-content w3-bar-block w3-card-2">
					<a href="Travel login.html" class="w3-bar-item w3-button w3-mobile">Sign in</a>
				</div>
			</div>
			<a href="logout.jsp" class="w3-bar-item w3-button w3-mobile">Logout </a>
			
				
				
		</div>
<center>
<div id="loader" class="w3-container w3-mobile w3-middle">
</div>
<p id="p1"> Payment is processing...!</p>

</center>

<div class="animate-bottom" style="display:none;" id="Div1">
<center>
<form text-align='center' action='pdfsample.jsp' method="get">
<%
String no=request.getParameter("no");
String id=request.getParameter("id");

session.setAttribute("id", id);

int n=Integer.parseInt(no);

System.out.println(n);
System.out.println(no);
System.out.println(id); 

String selpayment = request.getParameter("card");
System.out.println(selpayment);




Connection con;
PreparedStatement ps;
if(selpayment.equals("Credit/Debit"))
{
	String cardnum = request.getParameter("cardnumber");
	System.out.println(cardnum);
	String expirynum = request.getParameter("exyear");
	String cvvnum = request.getParameter("cvvnumber");

	try
	{
	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
		System.out.println("connected1");

	
		String query = "select * from creditpayment where cardno=? and expiry=? and cvvno=?";
		ps = con.prepareStatement(query);
		
		ps.setString(1,cardnum);
		ps.setString(2,expirynum);
		ps.setString(3,cvvnum);
		
		int i1=ps.executeUpdate();
		
		System.out.println(i1);
		ResultSet rs = ps.executeQuery();
		
		int amt;
		int id1;
		
		out.print("<html><head>");
		out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'> ");
		out.print("</head>");
		
		if(rs.next()){
		
			
			out.print("<h3>Your amount in account : "+rs.getInt("amount")+" </h3><br/>");
		}else{
			
			out.print("<html><head>");
	 		
	    	out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
	    	out.print("</head><body>");
	    	out.println("<script type='text/javascript'>");
	        out.println("swal('You have no account','Please try again!','error')");
	        out.println("</script>");
	        out.print("</html></body>");
			out.println("<h3>You Have No Account</h3>");
		}
		

		String qry = "select * from buses where busid=?";
		
		PreparedStatement pst = con.prepareStatement(qry);
		
		pst.setString(1,id);
		
		int i2= pst.executeUpdate();
		System.out.print(i2);

			ResultSet rset = pst.executeQuery();
			if(rset.next()){
			
					//int fare = rset.getInt("bfare");
					//System.out.println(fare);
					out.print( "<h3>Ticket Price : "+rset.getInt("bfare")+"</h3><br/>");
			}
					
					int totalamt =  rset.getInt("bfare")*n;
					
					out.print("<h3>Your Total Amount : "+totalamt+"</h3><br/>");

			
			if(totalamt>rs.getInt("amount"))
			{
				out.println("<h3>Insuffecient Balance to book a ticket</h3>");
			}
			else
			{
				out.println("<h3>Your Amount : "+totalamt +" had been charged for booking ticket</h3><br/>");
				amt = rs.getInt("amount")-totalamt;
				out.println("<h3>Balance amount : "+amt+"</h3><br/>");
				
				out.print("<html><head>");
		 		
		    	out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
		    	out.print("</head><body>");
		    	out.println("<script type='text/javascript'>");
		        out.println("swal('Successfully booked the ticket','Have a nice day!','success')");
		        out.println("</script>");
		        out.print("</html></body>");
				
				 

				out.print("<h2>Successfully booked the ticket &nbsp &#128578</h2><br/>");
				out.print("<h2>Thank You For Booking....&#128578</h2><br/>");
				
				
				}
			out.print("</html>");

		}catch(java.lang.ClassNotFoundException e) {
				System.out.println("Oracle Driver not found");
		}
		catch(SQLException ex) {
				System.out.println("SQLException:" + ex.getMessage());
		}

}

if(selpayment.equals("Netbanking"))
{
	String custid = request.getParameter("customerid");
	System.out.println(custid);

	String pin = request.getParameter("pinvalue");
	System.out.println(pin);
	
	int amt;
	int id2 ;

	try
	{
	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
		System.out.println("connected2");

		String query = "select * from netbanking where cid=? and mpin=?";

		ps = con.prepareStatement(query);
		ps.setString(1,custid);
		ps.setString(2,pin);
		
		//Statement stmt=con.createStatement();
		
		int i1 = ps.executeUpdate();
		System.out.println(i1);

		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			out.print("<html><head>");
			out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'> ");
			out.print("</head>");
			out.print("<h3>Your amount in account : "+rs.getInt("amount")+" </h3><br/>");
			//out.print(rs.getString("cid"));
		}else{
			
			out.print("<html><head>");
	 		
	    	out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
	    	out.print("</head><body>");
	    	out.println("<script type='text/javascript'>");
	        out.println("swal('You have no account','Please try again!','error')");
	        out.println("</script>");
	        out.print("</html></body>");
			
			 

			out.println("<h3>You Have No Account</h3><br/> ");
		}
			
			//amt = rs.getInt("amount");
			//System.out.print(amt);

			//id2 = rs.getInt("cid");
		
		
		//System.out.print(id2);


		String qry = "select * from buses where busid=?";
		
		PreparedStatement pst = con.prepareStatement(qry);
		
		pst.setString(1,id);
		
		int i2= pst.executeUpdate();
		System.out.print(i2);

		
		ResultSet rset = pst.executeQuery();
		if(rset.next()){
		
				//int fare = rset.getInt("bfare");
				//System.out.println(fare);
				out.print( "<h3>Ticket Price : "+rset.getInt("bfare")+"</h3><br/>");
		}
				
				int totalamt =  rset.getInt("bfare")*n;
				
				out.print("<h3>Your Total Amount : "+totalamt+"</h3><br/>");

		
		if(totalamt>rs.getInt("amount"))
		{
			out.println("<h3>Insuffecient Balance to book a ticket</h3>");
		}
		else
		{
			
			out.print("<html><head>");
	 		
	    	out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
	    	out.print("</head><body>");
	    	out.println("<script type='text/javascript'>");
	        out.println("swal('Successfully booked the ticket','Have a nice day!','success')");
	        out.println("</script>");
	        out.print("</html></body>");
			
			 

			out.println("<h3>Your Amount : "+totalamt +" had been charged for booking ticket</h3><br/>");
			amt = rs.getInt("amount")-totalamt;
			out.println("<h3>Balance amount : "+amt+"</h3><br/>");
			
			out.print("<h2>Successfully booked the ticket &nbsp &#128578</h2><br/>");
			out.print("<h2><style color='white' text-shadow= '1px 1px 2px black, 0 0 5px darkblue' font-family='rancho'>Thank You For Booking....&#128578</style></h2><br/>");
			
			String qry1 = "update netbanking set amount=? where cid=?";
			ps = con.prepareStatement(qry);
			ps.setInt(1,amt);
			ps.setString(2,custid);
			//ResultSet rset;
			
			int i = ps.executeUpdate();
			System.out.println(i+"Row updated");
		}
		
		out.print("</html>");
	}catch(java.lang.ClassNotFoundException e) {
		System.out.println("Oracle Driver not found");
	}
	catch(SQLException ex) {
		System.out.println("SQLException:" + ex.getMessage());
	}
}



%>
</div>
<script>
function myTime()
 {
    var time = setTimeout(showPage, 3000);
	var time1 = setTimeout(Timeout , 3000);
}
function showPage() 
{
  document.getElementById("loader").style.display = "none";
  document.getElementById("Div1").style.display = "block";
}
function Timeout()
{
	document.getElementById("p1").style.display = "none";
}

</script>
<center>
<input type='submit' value="Save As PDF" >
</center>
</center>
</body>
</html>

		

