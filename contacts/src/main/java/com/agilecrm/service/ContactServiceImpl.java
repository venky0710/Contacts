package com.agilecrm.service;

import java.sql.SQLException;

import com.agilecrm.dao.ContactDao;
import com.agilecrm.dao.ContactDaoImpl;
import com.agilecrm.model.contactEntity;
import com.agilecrm.service.ContactService;

public class ContactServiceImpl implements ContactService {

	ContactDao dao = new ContactDaoImpl();

	@Override
	public int saveContact(contactEntity contact) {
		return dao.addContact(contact);
	}

	@Override
	public contactEntity fetchContact(int id) {
		return dao.getContact(id);
	}

	@Override
	public int removeContact(int id) throws ClassNotFoundException, SQLException {
		return dao.deleteContact(id);
	}

	@Override
	public int updateContact(contactEntity contact) {
		return dao.updateContact(contact);
	}

}
