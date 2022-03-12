package com.hcl.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SeatInformationServlet
 */
@WebServlet("/SeatInformationServlet")
public class SeatInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeatInformationServlet() {
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
		String id=request.getParameter("busid");
		System.out.println(id);
		try{

			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
			System.out.println("connected");

			Statement stmt2 = con.createStatement();
			
			int i = stmt2.executeUpdate("select * from seatinformation"+id);
			System.out.println(i);
			
			ResultSet rs= stmt2.executeQuery("select * from seatinformation"+id);
			
			out.println("<html><head>");
			out.print("<link rel='stylesheet' href='https://fonts.googleeapis.com/css?family=Lobster'>");
			out.print("<center><h1 style=' font-family: lobster;'>PASSENGER LIST</h1></center>");
			out.print("<link rel='stylesheet' type='text/css' href='addbus.css'></head>");
			
			if(i>0) {
				out.print("<body><table border='1' align='center' bgcolor='#FFFAFA'>");
				out.println("<tr><th>Bus Id</th><th>Name</th><th>Email</th><th> Seat No</th></tr>");

			while(rs.next()){
				
				out.println("<tr><td>"+id+"</td>");
				out.println("<td>"+rs.getString("name")+"</td>");
				out.println("<td>"+rs.getString("email")+"</td>");
				out.println("<td>"+rs.getString("seatNos")+"</td></tr>");

			}
			out.print("</table>");
			}else {
				out.print("<center><h2>No passengers</h2></center>");
			}
			out.println("</body></html>");

			

		
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
