package com.abhi.util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Schema {
	
	
	public static void createSchema() {
		Connection con = DBConnection.getConnection();
		
		try {
			Statement stmt = con.createStatement();
			
			String adminSql = "CREATE TABLE ADMIN ("
					+ "ID NUMBER PRIMARY KEY, "
					+ "USERNAME VARCHAR2(30), "
					+ "PASSWORD VARCHAR2(30) )";
			
			stmt.execute(adminSql);
			
			String StudentSql = "CREATE TABLE STUDENT3("
					+ "ID NUMBER PRIMARY KEY, "
					+ "NAME VARCHAR2(50), "
					+ "EMAIL VARCHAR2(50) UNIQUE , "
					+ "PASSWORD VARCHAR(30) , "
					+ "COURSE VARCHAR2(30) , "
					+ "MOBILE VARCHAR(15) )";
			stmt.execute(StudentSql);
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertAdmin() {
		Scanner sc = new Scanner(System.in);
		
		Connection con = DBConnection.getConnection();
		
		String sql = "INSERT INTO ADMIN(ID, USERNAME, PASSWORD) VALUES (ADMIN_SEQ.NEXTVAL, ?, ?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			System.out.print("Enter username : ");
			String userName = sc.nextLine();
			
			System.out.print("\nEnter Admin Password : ");
			String pass = sc.nextLine();
			pstmt.setString(1, userName);
			pstmt.setString(2, pass);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
//	public static void main(String [] args) {
//		Schema.createSchema();
//		Schema.insertAdmin();
//	}
	
}




