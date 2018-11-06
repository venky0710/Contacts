package com.agilecrm.service;

import java.sql.SQLException;

import com.agilecrm.model.contactEntity;

public interface ContactService {

	public int saveContact(contactEntity contact);

	public contactEntity fetchContact(int id);

	public int removeContact(int id) throws ClassNotFoundException, SQLException;

	public int updateContact(contactEntity contact);

}
