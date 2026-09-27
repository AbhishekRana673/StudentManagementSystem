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

public class LoginServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		PrintWriter pw = resp.getWriter();
		
		Connection con = DBConnection.getConnection();
		
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		
		
		String sql = "SELECT EMAIL , PASSWORD, ISLOGIN FROM STUDENT3 WHERE EMAIL = ? ";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet res = pstmt.executeQuery();
			
			if(res.next() && email.equals(res.getString(1)) && pass.equals(res.getString(2))) {
				if(res.getInt(3) == 1) {
					pw.println("You are already login!");
					RequestDispatcher rd = req.getRequestDispatcher("/StudentDashboard.html");
					rd.forward(req, resp);
					
				}else{
					String alterLogin = "UPDATE STUDENT3 SET ISLOGIN = 1 ";
					pstmt = con.prepareStatement(alterLogin);
					pstmt.executeUpdate();
					
					pw.println("Login success!");
					RequestDispatcher rd = req.getRequestDispatcher("/StudentDashboard.html");
					rd.forward(req, resp);
				}
				
			}else {
				pw.println("Please Enter correct Credentials");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
