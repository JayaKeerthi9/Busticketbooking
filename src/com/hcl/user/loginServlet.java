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
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
	          
	    String name=request.getParameter("username");  
	    String password=request.getParameter("password");  
	    
	   HttpSession session = request.getSession();
	   session.setAttribute("username", name);
	   session.setAttribute("password", password);
	    
	    System.out.println(name);
	    System.out.println(password);
	          
	    if(LoginDao.validate(name, password)){  
	    	
			
			 

	    	
	        RequestDispatcher rd=request.getRequestDispatcher("Welcome.jsp");  
	        
out.print("<html><head>");
	 		
	    	out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
	    	out.print("</head><body>");
	    	out.println("<script type='text/javascript'>");
	        out.println("swal('Successfully Login','Have a nice day!','success')");
	        out.println("</script>");
	        out.print("</html></body>");
	        
	    	
	        out.print("<center>Your are Successfully Login<center>");
	        session.setMaxInactiveInterval(30*60);
	        rd.forward(request,response); 
	        
	    	
	    	//response.sendRedirect("Travel Busticket.html");
	    }  
	    else{  
	    	out.print("<html><head>");
	 		
	    	out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
	    	out.print("</head><body>");
	    	out.println("<script type='text/javascript'>");
	        out.println("swal('Invalid username or password','Please Try again!','error')");
	        out.println("</script>");
	        out.print("</html></body>");
			
			 

	    	
	        //response.sendRedirect("Travel login.html");    

	      RequestDispatcher rd=request.getRequestDispatcher("Travel login.html"); 
	        rd.include(request,response); 
	        
	        out.print("<center>Sorry username or password is invalid</center>");  

	        
	       /* out.print("<html><body>");
	        out.print("<center>Sorry username or password is invalid</center>");  
	        out.print("</body></html>");*/
	        


	    }  
	          
	    out.close();  
	}
 static class LoginDao {  
		public static boolean validate(String name,String password){  
		boolean status=false;  
		try{  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");  
		  System.out.println("connected");    
		PreparedStatement ps=con.prepareStatement( "select * from register where name=? and password=?");  
		ps.setString(1,name);  
		ps.setString(2,password);  
		      
		ResultSet rs=ps.executeQuery();  
		status=rs.next();  
		          
		}catch(java.lang.ClassNotFoundException e) {
			System.out.println("Oracle Driver not found");
		}
		catch(SQLException ex) {
			System.out.println("SQLException:" + ex.getMessage());
		}

		return status;  
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
