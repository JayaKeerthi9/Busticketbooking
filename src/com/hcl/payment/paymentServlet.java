package com.hcl.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class PaymentDisplay
 */
@WebServlet("/paymentServlet")
public class paymentServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public paymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
response.setContentType("text/html");
PrintWriter out = response.getWriter();

String card = request.getParameter("card");
String no=request.getParameter("no");
String id=request.getParameter("id");

System.out.println(card);
System.out.println(no);
System.out.println(id);

try{
	

	Class.forName("oracle.jdbc.driver.OracleDriver");
	java.sql.Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
	System.out.println("connected");
	
	if(card.equals("Credit/Debit"))
	{

	out.println("<html>");
	out.print("<head>");
	out.print("<link rel='stylesheet' type='text/css' href = 'PaymentStyle.css\'> ");
	out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'> ");
	out.print("</head><body><center>");
	out.print("<center><p><h1>Please Enter Below Details <i class='fa fa-hand-o-down'></i></h1></p></center>");
	out.println("<form method='get' action='paymentdisplay.jsp'>");


	out.print("<table>");
	out.print("<tr><td>Card :</td><td> <input type='text' name='card' value='"+card+"' </td></tr><br/>");

	out.print("<tr><td>Bus Id:</td><td><input type='text' name='id' value='"+id+"' ></td></tr><br/>");
	out.print("<tr><td>No of Seats to be Reserved:</td><td><input type='text' name='no' value='"+no+"' reqiured></td></tr><br/>");
	out.print("<div class='w3-container w3-mobile w3-padding-16' class='div1'>");
	out.print("<label style='text-align:center;'>Accepted Cards</label>");
	out.print("<div class='icon-container' class='w3-container w3-mobile w3-padding-16'>");
	out.print("<h1><i class='fa fa-cc-visa w3-xxxlarge w3-jumbo' style='color:navy;'></i> &nbsp");
	out.print("<i class='fa fa-cc-amex w3-xxxlarge w3-jumbo' style='color:blue;'></i> &nbsp");
	out.print("<i class='fa fa-cc-mastercard w3-xxxlarge w3-jumbo' style='color:red;'></i> &nbsp");
	out.print("<i class='fa fa-cc-discover w3-xxxlarge w3-jumbo' style='color:orange;'></i></h1>");
	out.print("</div>");
	out.println("<tr><td>Card no : </td>");
	out.println("<td><input type='text' name='cardnumber'></td></tr><br/>");

	out.println("<tr><td> Expiry year : </td>");
	out.println("<td><input type='text' name='exyear'></td></tr><br/>");

	out.println("<tr><td>CVV :</td>");
	out.println("<td><input type='text' name='cvvnumber'></td></tr><br/>");
	out.print("</table><br/>");

	

	out.println("<center><input type='submit' value='Submit'></center>");
	out.println("</form><center></body></html>");

	}
	else
	{
		out.println("<html>");
		out.print("<head>");
		out.print("<link rel='stylesheet' type='text/css' href = 'PaymentStyle.css\'> ");
		out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'> ");

		
		out.print("</head><body><center>");	
		out.print("<center><p><h1>Please Enter Below Details <i class='fa fa-hand-o-down'></i></h1></p></center><br/>");
		out.print("<center><h1>NET BANKING &nbsp <i class='fa fa-money' style='color:blue;'></i></h1></center><br/>");
		out.print("<h1><i class='fa fa-bank' style='color:navy;'>kotak</i>&nbsp <i class='fa fa-bank' style='color:red;'>HDFC</i> &nbsp<i class='fa fa-bank' style='color:brown;'>Axis</i> &nbsp <i class='fa fa-bank' style='color:green;'>SBI</i></h1>");
		out.println("<form method='get' action='paymentdisplay.jsp'>");


	out.print("<table background-color='white'>");
	out.print("<tr><td>Card: </h2></td><td><input type='text' name='card' value='"+card+"'</td></tr><br/>");

	out.print("<tr><td>Bus Id:</h2></td><td><input type='text' name='id' value='"+id+"' ></td></tr><br/>");
	out.print("<tr><td>No of Seats to be Reserved :</h2> </td><td><input type='text' name='no' value='"+no+"' reqiured></td></tr><br/>");
	out.println("<tr><td> Customer Id :</td>");
	out.println("<td><input type='text' name='customerid'></td></tr><br/>");

	out.println("<tr><td>Mpin : </td>");
	out.println("<td><input type='text' name='pinvalue'></td></tr><br/>");

	out.print("</table><br/>");

	

	out.println("<br/><center><input type='submit' value='Submit'></center>");
	out.println("</form><center></body></html>");
	}


	

}catch(java.lang.ClassNotFoundException e) {
	System.out.println("Oracle Driver not found");
}
catch(SQLException ex) {
	System.out.println("SQLException:" + ex.getMessage());
}


}

/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
}

}

