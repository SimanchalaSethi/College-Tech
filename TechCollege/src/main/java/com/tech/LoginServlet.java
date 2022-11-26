package com.tech;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long reg = Long.parseLong(req.getParameter("REG"));
		String password = req.getParameter("PASSWORD");
		Connection con= null;
		PreparedStatement pst = null;
		ResultSet re = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tech","root","root");
			pst = con.prepareStatement("select * from techcollege where reg=? and password=?");
			pst.setLong(1, reg);
			pst.setString(2, password);
			re=pst.executeQuery();
			PrintWriter writer = resp.getWriter();
		    if(re.next())
		    {
		    	writer.write("<html><body><h1>Login Succesfull</h1>");
		    	writer.write("<h1>"+"your name is: "+re.getString(2)+"</h1>");
		    	writer.write("<h1>"+"your branch is: "+re.getString(4)+"</h1>");
		    	
		    	writer.write("</body></html>");
		    }else {
		    writer.write("<html><head><style>h1{background-image:linear-gradient(yellow, pink); text-align: center;} div{text-align: center;}</style></head><body><h1>INVALLID USER NAME & PASSWORD</h1>");
		    writer.write("<div><a href=\"home.html\">RETURN TO HOME</a></div></body></html>");
		    }
		}catch (SQLException | ClassNotFoundException e) {
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


