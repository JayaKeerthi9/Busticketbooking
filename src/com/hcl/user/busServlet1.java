package com.hcl.user;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class busServlet1
 */
@WebServlet("/busServlet1")
public class busServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public busServlet1() {
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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Connection con;
		String from = request.getParameter("city1");
		String to = request.getParameter("city2");
		String bustype = request.getParameter("btype");
		String date = request.getParameter("date");
		System.out.println(date);
		System.out.println(from);
		System.out.println(to);
		System.out.println(bustype);

		session.setAttribute("date",date);
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
		System.out.println("connected");
		
		String query = "select * from buses where bstartloc=? and bendloc=? and btype=?";
		PreparedStatement ps=con.prepareStatement(query);  
		
		ps.setString(1,from);  
		ps.setString(2,to);
		ps.setString(3,bustype);
		
		int i=ps.executeUpdate();
		System.out.println(i);
		
		ResultSet rs = ps.executeQuery();
		
		out.print("<html><head>");
		out.print("<link rel='stylesheet' type='text/css' href='bustable.css' >");
		out.print("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
		out.print("</head>");
		
	     if(i>0) {
			//out.print("<html><body>");
			//out.print("<form action='seat.jsp' method='post'>");
			
			out.print("<center><h1>Please Check Bus Details..!<i class='fa fa-bus'></i><h1></center><br/>");
			
			out.print("<table align='center' border='1'>");
			out.println("<tr> <th>Bus Id</th><th>BusName</th> <th>BusType</th> <th>Departure Time</th> <th>Arrival Time</th>"
					+ "<th>Fare</th></tr>") ;
			

			while(rs.next()) {
			
				out.print("<tr><td>"+rs.getString("busid")+"</td><td>"+rs.getString("bname")+"</td><td>"+rs.getString("btype")+"</td><td>"+rs.getInt("bstarttimehrs")+":"+rs.getInt("bstarttimemin")+"</td><td>"+rs.getInt("barrtimehrs")+":"+rs.getInt("barrtimemin")+"</td><td>"+rs.getInt("bfare")+"</td></tr>");
			
			}
			out.print("</table>");
			//out.print("</body></html>");
			
			
			String query1 = "select * from buses where bstartloc=? and bendloc=? and btype=?";
			PreparedStatement ps1=con.prepareStatement(query1);  
			
			ps.setString(1,from);  
			ps.setString(2,to);
			ps.setString(3,bustype);
			
			ResultSet rs1 = ps.executeQuery();
			
			
			out.print("<body><center>");
			out.print("<form action='seat.jsp' method='post'>");
			
			out.print("<br></br>");
			out.print("<h2>Available Buses For That Route<h2>");
			//out.print("<br/>");

			out.print("<select name='buses'>");
			out.print("<option>Select Bus</option>");
		while(rs1.next())
		{
			out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString("bname")+"</option><br/>");
			
		}
		
		out.print("</select><br/>");
		out.print("<br/>");

		out.print("<tr><td> No of Seats to be Reserved:</td><td><input type='text' name='no' placeholder='Seats to be Reserved' reqiured size='30' height='50'></td></tr><br/><br/>");

		out.print("<input type='submit' value='Submit'/>");

		//RequestDispatcher dispatcher = request.getRequestDispatcher("busServlet2");
		//dispatcher.include(request, response);
        //response.sendRedirect("bus.jsp");

		
		}else {
			out.print("<center><h1><div class='w3-container w3-mobile w3-display-middle'>NO Buses are Avialable for that route</div><h1></center>");
		}
		out.print("</form></center></body></html>");

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
		doGet(request, response);
	}

}
