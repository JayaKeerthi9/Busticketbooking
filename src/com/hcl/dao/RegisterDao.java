package com.hcl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDao {
	public static boolean validate(String email){  
		boolean status=false;  
		try{  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");  
		  System.out.println("connected");    
		PreparedStatement ps=con.prepareStatement( "select * from register where email=?");  
		ps.setString(1,email);  
		      
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
