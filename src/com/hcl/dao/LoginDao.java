package com.hcl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	
	public static boolean validate(String userName, String password) {
		// TODO Auto-generated method stud
		boolean status=false;  
		try{  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		System.out.println("Driver class found");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","dbuser","1234");  
		System.out.println("Connected successfully");
		PreparedStatement ps=con.prepareStatement( "select * from admin where name=? and password=?");  
		ps.setString(1,userName);  
		ps.setString(2,password);  
		     
		ResultSet rs=ps.executeQuery();  
		status=rs.next();
		         
		}
		catch(ClassNotFoundException e){
		e.printStackTrace();

		}
		catch(SQLException e){
		System.out.println("SQLException:"+e);
		e.printStackTrace();
		}  
		return status;  
		}

}
