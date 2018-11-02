package com.agilecrm.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

import com.agilecrm.utility.ConnectionDao;

import Domain.ContactDomain;

public class ContactDaoImpl implements ContactDao{

	@Override
	public int addContact(ContactDomain contact) {
		 int status=0;  
	        try{  
	        	Connection connection = ConnectionDao.getConnection();
	        	/*Connection con=null;
	            con=new ConnectionDao().getConnection(); */
	            PreparedStatement ps=connection.prepareStatement(  
	                         "insert into user905(name,password,email,country) values (?,?,?,?)");  
	            ps.setString(1,contact.getFirstName());  
	            ps.setString(2,contact.getLastName());  
	            ps.setString(3,contact.getEmail());  
	              
	            status=ps.executeUpdate();  
	              
	            connection.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return status;  

	}

	@Override
	public ContactDomain getContact(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteContact(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateContact(String id) {
		// TODO Auto-generated method stub
		return 0;
	}


}
