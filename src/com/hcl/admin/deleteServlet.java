package com.hcl.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteServlet
 */
@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteServlet() {
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
		PrintWriter out= response.getWriter();
		String busid=request.getParameter("busid");
		System.out.println(busid);
		
		Connection con;
		PreparedStatement ps;
		try
		{
		//"Dynamically" load the class
		Class.forName("oracle.jdbc.driver.OracleDriver");

		//create a connection object and connect to database
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
		System.out.print("connected");
		Statement stmt2 = con.createStatement();
		
		int i1 = stmt2.executeUpdate("select * from seatinformation"+busid);
		System.out.println(i1);
		out.print("<html><head>");
		out.println("<link rel='stylesheet' type='text/css' href='addbus.css'>");
		//out.print("</head><body>");
		//out.print("<html><head>");
		//out.println("<link rel='stylesheet' type='text/css' href='addbus.css'>");
		out.print("</head><body><center>");
		out.print("<br></br>");
		out.print("<br></br>");
		out.print("<br></br>");
		out.print("<br></br>");
		out.print("<br></br>");
		
		if(i1>0) {
			
			 out.print("<form id='form1'><h2>Passengers Are Available</h2>");
			out.print("<h2>You can not Delete Bus</h2></form>");
		}else {
		String query="delete from buses where busid=?";
		ps=con.prepareStatement(query);
		ps.setString(1,busid);
		int i=ps.executeUpdate();
		System.out.println(i);
		
		
		if(i>0) {
			
			

			out.println("<form id='form1'><center><div class='centered' ><h2>Bus Deleted Successfully</h2></div>");
			
			out.print("<h2><a href='Deletebus.html' >Delete Another Bus</a></h2></center>");
			out.print("<h2><a href='Viewbus.html' >View  Buses</a></h2></center>");

		}else {
			
			
			out.print("<center><form id='form1'><h2>Bus Id Is Not Available</h2></form></center>");
		}
		
		}
		out.print("</center></form></body></html>");	

		}
		catch(java.lang.ClassNotFoundException e) {
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
		doGet(request, response);
	}

}
