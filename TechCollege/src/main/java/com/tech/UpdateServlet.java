package com.tech;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="forgot", value="/fog")
public class UpdateServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long reg = Long.parseLong(req.getParameter("reg"));
		String pw = req.getParameter("pw");
		Connection con = null;
		PreparedStatement pst = null;
		String query="update techcollege set password=? where reg=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tech","root","root");
			pst = con.prepareStatement(query);
			pst.setLong(2,reg);
			pst.setString(1,pw);
			pst.executeUpdate();
			PrintWriter writer = resp.getWriter();
			 writer.write("<html><head><style>h1{background-image:linear-gradient(yellow, pink); text-align: center;} div{text-align: center;}</style></head><body><h1>PASSWORD UPDATE SUCESSFULLY</h1>");
		}catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally {
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
