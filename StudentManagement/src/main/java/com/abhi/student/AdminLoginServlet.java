package com.abhi.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.abhi.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		PrintWriter pw = resp.getWriter();
		
		Connection con = DBConnection.getConnection();
		
		String username = req.getParameter("username");
		String pass = req.getParameter("pass");
		
		
		String sql = "SELECT USERNAME , PASSWORD, ISLOGIN FROM ADMIN WHERE USERNAME = ? ";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet res = pstmt.executeQuery();
			
			if(res.next() && username.equals(res.getString(1)) && pass.equals(res.getString(2))) {
				if(res.getInt(3) == 1) {
					pw.println("You are already login!");
					RequestDispatcher rd = req.getRequestDispatcher("/adminDashboard.html");
					rd.forward(req, resp);
					
				}else{
					String alterLogin = "UPDATE ADMIN SET ISLOGIN = 1 ";
					pstmt = con.prepareStatement(alterLogin);
					pstmt.executeUpdate();
					
					pw.println("Login success!");
					RequestDispatcher rd = req.getRequestDispatcher("/adminDashboard.html");
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
