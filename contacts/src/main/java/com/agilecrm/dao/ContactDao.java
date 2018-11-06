package com.agilecrm.dao;

import java.sql.SQLException;
import java.util.List;

import com.agilecrm.model.contactEntity;

public interface ContactDao {

	public int addContact(contactEntity contact);

	public contactEntity getContact(int id);

	public int deleteContact(int id) throws ClassNotFoundException, SQLException;

	public int updateContact(contactEntity contact);
	
	public List<contactEntity> getAll();
}