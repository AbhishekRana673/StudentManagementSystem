package com.abhi.util;
import java.sql.*;

public class DBConnection {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "system";
	private static final String PASS = "kali";
	
	public static Connection getConnection(){
		Connection con = null;
		try {
			//1. loading 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(URL, USER, PASS);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
}
