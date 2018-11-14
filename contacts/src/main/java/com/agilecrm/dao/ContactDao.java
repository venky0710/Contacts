package com.agilecrm.dao;

import java.sql.SQLException;
import java.util.List;

import com.agilecrm.model.contactEntity;

public interface ContactDao {

	public int addContactDao(contactEntity contact);

	public contactEntity getContactDao(int id);

	public int deleteContactDao(int id) throws ClassNotFoundException, SQLException;

	public int updateContactDao(contactEntity contact);
	
	public List<contactEntity> getAllDao();
}