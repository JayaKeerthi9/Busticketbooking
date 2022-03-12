<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"  import="com.hcl.register.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seat Booking</title>
<link rel='stylesheet' type='text/css' href='bustable.css' >
</head>
<body>
<form method="post" action="payment.jsp" text-align='center'>

<%

String id=request.getParameter("id");

String no=request.getParameter("no");

int n= Integer.parseInt(no);
session.setAttribute("n", no);
System.out.println(n);
String name[]=new String[n];
String email[]=new String[n];
String seat[] = new String[n];

for(int i=0;i<n;i++){
	name[i]=request.getParameter("name"+i);
	email[i]=request.getParameter("email"+i);
	
	seat[i]=request.getParameter("seat"+i);
	session.setAttribute("name"+i, name[i]);
}

for(int i=0;i<n;i++){
	email[i]=request.getParameter("email"+i);
	
	seat[i]=request.getParameter("seat"+i);
	
	System.out.println(seat[i]);
	
}

for( int i=0;i<n;i++){
	
	//seat[i]=request.getParameter("seat");

//String  no=request.getParameter("no");

	System.out.println(id);

	System.out.println(seat[i]);
	
	if(seat[i]!="booked"){
		try{
	
			char ch1=seat[i].charAt(0);
			char ch2=seat[i].charAt(1);
			System.out.println(ch1);
	
			try{
		

				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
				System.out.println("connected");

				boolean flag=false;
				Statement stmt2=con.createStatement();

				Statement stmt1=con.createStatement();
		      	int i1=stmt1.executeUpdate("update seatbooking"+id+" set seat"+Character.toString(ch1)+"='booked' where seatrow='"+Character.toString(ch2)+"'"); 
		        System.out.println(i1);

				String qry = "insert into seatinformation"+id+" values('"+id+"','"+name[i]+"','"+email[i]+"','"+seat[i]+"')";
				int j=stmt2.executeUpdate(qry);
				System.out.println(j);
				
				
		
			}catch(java.lang.ClassNotFoundException e) {
				System.out.println("Oracle Driver not found");
			}
			catch(SQLException ex) {
				System.out.println("SQLException:" + ex.getMessage());
			}

		}catch(NullPointerException e){
			out.println("Please enter a seat number");

		}
		}else{
			out.println("This Seat Already booked....please book ticket with another seat number");
		}

}

try{

	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
	System.out.println("connected");
	out.print("<center><h1>Please Check Details <h1></center>");

	Statement stmt2 = con.createStatement();
	for(int i=0;i<n;i++){

	ResultSet rs= stmt2.executeQuery("select * from seatinformation"+id+" where name='"+name[i]+"'");
	out.println("<html><body><table border='1' align='center'>");
	out.println("<tr><th>Bus Id</th><th>Name</th><th>Email</th><th> Seat No</th></tr>");


	while(rs.next()){
		out.println("<tr><td>"+id+"</td>");
		out.println("<td>"+rs.getString("name")+"</td>");
		out.println("<td>"+rs.getString("email")+"</td>");
		out.println("<td>"+rs.getString("seatNos")+"</td></tr><br/>");

	}
	out.print("</table><br/>");
	out.println("</body></html>");

	

}
}catch(java.lang.ClassNotFoundException e) {
	System.out.println("Oracle Driver not found");
}
catch(SQLException ex) {
	System.out.println("SQLException:" + ex.getMessage());
}



%>

<table align="center" border="1">
<tr><td>Bus Id:</td><td><input type="text" name="id" value=<%=id%>></td></tr>
<tr><td>No of Seats to be Reserved:</td><td><input type="text" name="no" value=<%=no%> reqiured></td></tr>
</table><br/>
<center><input type="submit" value="Submit"/></center>

</form>

</body>
</html>