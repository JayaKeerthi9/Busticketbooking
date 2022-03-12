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
 * Servlet implementation class adminforgotServlet
 */
@WebServlet("/adminforgotServlet")
public class adminforgotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminforgotServlet() {
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
		PrintWriter out=response.getWriter();
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		String confirmpassword=request.getParameter("confirmPassword");
		
		
		
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");
		       System.out.println("connected");
		      PreparedStatement p;
		     p=con.prepareStatement("Update admin set password=?,confirmpassword=? WHERE name=?");
		      p.setString(1,password);
		      p.setString(2, confirmpassword);
		      p.setString(3, name);        
		      p.executeUpdate();
		      int i= p.executeUpdate();
		      System.out.println(i);
			     if(i>0) {
			    	 
			    	 RequestDispatcher dispatcher = request.getRequestDispatcher("adminlogin.html");
				 		dispatcher.include(request, response);
				 		out.print("<center>your password successfully updated...!</center>");
				 		//response.sendRedirect("forgot password.html");
				 		out.print("<html><head><style>");
				    	out.print("body{ background-image:'url('homepage4.jpg')}");
				    	out.print("</style></head><body>");
				    	out.println("<script type=\"text/javascript\">");
				        out.println("alert('your password successfully updated...!');");
				        out.println("</script>");
				        out.print("</html></body>");



				 		
				 	}else {
					 	
				 		//response.sendRedirect("forgot password.html");
				 		out.print("<center>your password is not updated....Please Try Again....!</center>");

				 		RequestDispatcher rd=request.getRequestDispatcher("adminforgetpassword.html");  
					    rd.include(request,response); 
					    out.print("<html><head><style>");
				    	out.print("body{ background-image:'url('homepage4.jpg')}");
				    	out.print("</style></head><body>");
				    	out.println("<script type=\"text/javascript\">");
				        out.println("alert('You Are Not Registered ....Please Register First....!');");
				        out.println("</script>");
				        out.print("</html></body>");
					    


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
