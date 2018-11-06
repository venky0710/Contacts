package com.agilecrm.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {

	public static Connection getConnection() throws ClassNotFoundException, SQLException{  
		 Class.forName("com.mysql.jdbc.Driver");  
         return DriverManager.getConnection("jdbc:mysql://localhost:3306/training","root","root123");  
	}  
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println(ConnectionDao.getConnection().getClass().getName()+"-----------"+"done");
	}
}
