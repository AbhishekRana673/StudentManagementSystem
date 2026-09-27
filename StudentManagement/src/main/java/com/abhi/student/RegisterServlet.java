package com.abhi.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abhi.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		Connection con = DBConnection.getConnection();
		
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		String course = req.getParameter("course");
		String mobileNumber = req.getParameter("mobileNumber");
		
		
		if(isUserExist(email)) {
			pw.println("User already Exist! Please enter different email");
			return;
		}
		//otp validation 
		
//		int generateOtp = otpGenerator();
//		
//		System.out.println("OTP : " + generateOtp);
//		System.out.println("Enter 4 digit OTP : ");
//		int enteredOtp = sc.nextInt();
		
//		if(enteredOtp == generateOtp) {
			String sql = "INSERT INTO STUDENT3(ID, NAME, EMAIL, PASSWORD, COURSE, MOBILE ,ISLOGIN) VALUES (STUDENT_SEQ.NEXTVAL,?,?,?,?,?,0)";
			
			try {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				pstmt.setString(3, pass);
				pstmt.setString(4, course);
				pstmt.setString(5, mobileNumber);
				
				int confirm = pstmt.executeUpdate();
				if(confirm > 0) {
					pw.println("Registered Successfully!");
					RequestDispatcher rd = req.getRequestDispatcher("/registrationDone.html");
					rd.forward(req, resp);
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
//		}else {
//			System.out.println("Wrong OTP");
//			return;
//		}
	}
	
	public boolean isUserExist(String email) {
		Connection con = DBConnection.getConnection();
		String sql = "SELECT EMAIL FROM STUDENT3 WHERE email = ? ";
		boolean flag = false;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				flag = true;
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
