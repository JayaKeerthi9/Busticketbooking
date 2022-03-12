package com.hcl.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcl.dao.LoginDao;


/**
 * Servlet implementation class adminServlet
 */
@WebServlet("/adminServlet")
public class adminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminServlet() {
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
     
 String name=request.getParameter("userName");  
 String password=request.getParameter("password");  
     
 if(LoginDao.validate(name, password))
 {  
	 out.print("login Successfully");
     RequestDispatcher rd=request.getRequestDispatcher("Admin.html");  
     rd.forward(request,response);  
     out.print("<html><head>");
		
 	out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
 	out.print("</head><body>");
 	out.println("<script type='text/javascript'>");
     out.println("swal('Successfully Login','Have a nice day!','success')");
     out.println("</script>");
     out.print("</html></body>");
		
		 

 	
 }  
 else
 {  
 
 RequestDispatcher rd=request.getRequestDispatcher("adminlogin.html");  
              rd.include(request, response);
              out.print("<html><head>");
		 		
		    	out.print("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
		    	out.print("</head><body>");
		    	out.println("<script type='text/javascript'>");
		        out.println("swal('Invalid username or password','Please Try again!','error')");
		        out.println("</script>");
		        out.print("</html></body>");
				
				 

  	    	
     out.print(" Sorry username or password is invalid ");  

 }  
     
 out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
