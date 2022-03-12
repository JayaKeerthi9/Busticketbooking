package com.hcl.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addbusServlet
 */
@WebServlet("/addbusServlet")
public class addbusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addbusServlet() {
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
		PrintWriter pw=response.getWriter();
		
		String BusID=request.getParameter("BusID");
		String name=request.getParameter("BusNAME");
		String type=request.getParameter("BusTYPE");
		String startLoc=request.getParameter("BSTARTLOC");
		String EndLoc=request.getParameter("BENDLOC");
		String Starttimehrs=request.getParameter("BSTARTTIMEHRS");
		String Starttimemins=request.getParameter("BSTARTTIMEMINS");
		String Arrivaltimehrs=request.getParameter("BARRTIMEHRS");
		String Arrivaltimemins=request.getParameter("BARRTIMEMINS");
		String Bus_fare=request.getParameter("BFARE");
		try{

		//"Dynamically" load the class
		Class.forName("oracle.jdbc.driver.OracleDriver");

		//create a connection object and connect to database
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");

		System.out.println("connected");
		
		String query = "select * from buses where busid=?";
		PreparedStatement ps1=con.prepareStatement(query);  

		ps1.setString(1,BusID);  


		int i1=ps1.executeUpdate();
		System.out.println(i1);
		if(i1>0) {
			pw.print("<html><head>");
			pw.println("<link rel='stylesheet' type='text/css' href='addbus.css'>");
			pw.print("</head><body>");
			pw.print("<br></br>");
			pw.print("<br></br>");
			pw.print("<br></br>");
			pw.print("<br></br>");

			pw.println("<center><form id='form1' ><center><div class='centered' ><h1>This Bus Id Already Exists</h1><div>");
			
			pw.print("<h1><a href='ADDBUS.html' >Add Another Bus</a></h1>");
			pw.print("<h1><a href='Viewbus.html' >View Buses</a></h1>");

			pw.print("</center></form></center></bod></html>");
			
		}else {

		PreparedStatement ps=con.prepareStatement("Insert into buses values(?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, BusID);
		ps.setString(2, name);
		ps.setString(3, type);
		ps.setString(4,startLoc);
		ps.setString(5, EndLoc);
		ps.setString(6, Starttimehrs);
		ps.setString(7, Starttimemins);
		ps.setString(8, Arrivaltimehrs);
		ps.setString(9, Arrivaltimemins);
		ps.setString(10, Bus_fare);

		int i=ps.executeUpdate();

		if(i>0){
			pw.print("<html><head>");
			pw.println("<link rel='stylesheet' type='text/css' href='addbus.css'>");
			pw.print("</head><body>");
			pw.print("<br></br>");
			pw.print("<br></br>");
			pw.print("<br></br>");
			pw.print("<br></br>");

			pw.println("<center><form id='form1' ><center><div class='centered' ><h1>Bus Added Successfully</h1></div>");
			
			pw.print("<h1><a href='ADDBUS.html' >Add Another Bus</a></h1>");
			pw.print("<h1><a href='Viewbus.html' >View Buses</a></h1>");

			pw.print("</center></form></center></bod></html>");
		/*RequestDispatcher rd=request.getRequestDispatcher("ADDBUS.html");
		rd.include(request, response);*/

		}else {
			pw.print("<html><head>");
			pw.println("<link rel='stylesheet' type='text/css' href='addbus.css'>");
			pw.print("</head><body>");
			pw.println("<center><form id='form1' ><center><div class='centered' ><h1>No Bus Added..please Try Again</h1><div>");
			
			pw.print("<h1><a href='ADDBUS.html' >Add Another Bus</a></h1>");
			pw.print("<h1><a href='Viewbus.html' >View Buses</a></h1>");

			pw.print("</center></form></center></bod></html>");
			
		}
		}

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
