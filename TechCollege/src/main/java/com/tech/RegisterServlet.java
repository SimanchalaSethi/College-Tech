package com.tech;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name = req.getParameter("NAME");
	String gender = req.getParameter("GENDER");
	String branch = req.getParameter("BRANCH");
	long reg =Long.parseLong(req.getParameter("REG"));
	String password =req.getParameter("PASSWORD");
	Connection con= null;
	PreparedStatement pst = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tech","root","root");
		pst = con.prepareStatement("insert into techcollege values(?,?,?,?,?)");
		pst.setString(2,name);
		pst.setString(3,gender);
		pst.setString(4,branch);
		pst.setLong(1,reg);
		pst.setString(5,password);
		pst.executeUpdate();
		PrintWriter write=resp.getWriter();
		write.write("<html><style>h2{background-image:linear-gradient(yellow, pink); text-align: center;}</style></head><body><h2>THANK YOU FOR REGISTER THE TECH-COLLEGE</h2></body></html>");
	}catch (SQLException | ClassNotFoundException e)
	{
		e.printStackTrace();
	}finally {
		if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(pst!=null)
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	}
	

}
