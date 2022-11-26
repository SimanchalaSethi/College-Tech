package com.tech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	public static void main(String[] args) throws SQLException {
		Connection con=null;
		Statement st=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/tech","root","root");
			st=con.createStatement();
			st.execute("create table TechCollege(reg BIGINT(20) NOT NULL, name VARCHAR(45) NOT NULL, gender VARCHAR(45) NOT NULL, branch VARCHAR(45) NOT NULL, password VARCHAR(45) NOT NULL, PRIMARY KEY(REG))");
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null)con.close();
			if(st!=null)st.close();
		}
	}
}
