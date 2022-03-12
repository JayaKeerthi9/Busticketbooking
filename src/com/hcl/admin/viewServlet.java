package com.hcl.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class viewServlet
 */
@WebServlet("/viewServlet")
public class viewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		Connection con;
		PreparedStatement ps;
		PrintWriter pw=response.getWriter();
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");

		//create a connection object and connect to database
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
				//pw.print("&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp &nbsp");
				pw.print("<html><head>");
				pw.print("<link rel='stylesheet' type='text/css' href='search.css'>");
				pw.print("		<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n" + 
						"");
				
				pw.print("</head><body>");
				pw.print("<form action='searchbus.jsp' method='get'>");
				pw.print("<center>");
				pw.print("<br></br>");

				pw.print(" <input type='text' name='busid' placeholder='Search..'>" ); 
				pw.print("   <input type=\"submit\" value=\"Search\">");
			    //pw.print("<input class='w3-input w3-border w3-bar-item w3-right w3-card' name='busid' type='text' placeholder='Enter Bus ID' required autofocus>&nbsp");
				//pw.print("<button class='w3-button w3-border w3-ripple w3-round-large w3-green w3-bar-item w3-right' onclick='#'>Search</button>");
				pw.print("</center></form></body></html>");
		

		ps=con.prepareStatement("select * from buses order by busid");
		ResultSet rs=ps.executeQuery();
		pw.println("<html><head> <link rel=\"stylesheet\" type=\"text/css\" href=\"addbus.css\"></head>\r\n" + 
				"<body><center><h1> BUSES </h1><form id='form1' ><table border='1' colspan=4><tr><td>BUS ID</td><td>BNAME</td><td>COACH</td><td>SOURCE</td><td>DESTINATION</td><td>ARRIVAL TIME</td><td>DEPARTURE TIME</td><td>FARE</td></tr>");
		while(rs.next())
		{
		pw.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+":"+rs.getString(7)+"</td><td>"+rs.getString(8)+":"+rs.getString(9)
		+"</td><td>"+rs.getString(10)+"</td></tr>");
		}
		pw.println("</table></form></center></body></html>");

		}
		catch(ClassNotFoundException e)
		{
		e.printStackTrace();

		}
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
