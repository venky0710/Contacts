package com.agilecrm.service;

import java.sql.SQLException;
import java.util.List;

import com.agilecrm.model.contactEntity;

public interface ContactService {

	public int saveContactService(contactEntity contact);

	public contactEntity fetchContactService(int id);

	public int removeContactService(int id) throws ClassNotFoundException, SQLException;

	public int updateContactService(contactEntity contact);
	
	public List<contactEntity> getAllService();

}
