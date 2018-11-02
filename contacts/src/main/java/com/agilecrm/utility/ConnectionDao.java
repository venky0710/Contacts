package com.agilecrm.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {

	public static Connection getConnection() throws ClassNotFoundException, SQLException{  
		 Class.forName("com.mysql.jdbc.Driver");  
         return DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts","root","root123");  
	}  
}
