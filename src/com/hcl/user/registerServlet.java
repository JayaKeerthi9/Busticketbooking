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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcl.dao.RegisterDao;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
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
		 String name=request.getParameter("name");
		 String email=request.getParameter("email");
		 String gender=request.getParameter("gender");
		 String phone=request.getParameter("mobile");
		 String password=request.getParameter("password");
		 String confirmpassword=request.getParameter("confirmpassword");
		         
		         
		 try{
			 
			 	//"Dyanamically" load the class
			 	Class.forName("oracle.jdbc.driver.OracleDriver");
			 	
			 	//create a connection object and connect to database
			 	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
		 
			 	
			 	
			    if(RegisterDao.validate(email)){  
			    	RequestDispatcher dispatcher = request.getRequestDispatcher("Travel Registration.html");
			 		dispatcher.include(request, response);
			 		out.print("<html><head>");
			 		
			    	out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
			    	out.print("</head><body>");
			    	out.println("<script type='text/javascript'>");
			        out.println("swal('Hey','This email already exists','error')");
			        out.println("</script>");
			        out.print("</html></body>");
			    }else {
			    	PreparedStatement ps=con.prepareStatement("insert into register values(?,?,?,?,?,?)");
					 
				 	ps.setString(1,name);
				 	ps.setString(2,email);
				 	ps.setString(3,phone);
				 	ps.setString(4,gender);
				 	ps.setString(5,password);
				 	ps.setString(6,confirmpassword);
			        int i=ps.executeUpdate();
				 	System.out.println(ps.executeUpdate());

			 		RequestDispatcher dispatcher = request.getRequestDispatcher("Travel login.html");
			 		dispatcher.include(request, response);
			 		out.print("<html><head>");
			 		
			    	out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
			    	out.print("</head><body>");
			    	out.println("<script type='text/javascript'>");
			        out.println("swal('Successfully Registered','Now its time to login','success')");
			        out.println("</script>");
			        out.print("</html></body>");
			 		out.print("<center>you are successfully registered...!</center>");


			 		
			 	
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
		doGet(request, response);
	}

}
