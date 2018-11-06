package com.agilecrm.service;

import java.sql.SQLException;
import java.util.List;

import com.agilecrm.dao.ContactDao;
import com.agilecrm.dao.ContactDaoImpl;
import com.agilecrm.model.contactEntity;
import com.agilecrm.service.ContactService;

public class ContactServiceImpl implements ContactService {

	ContactDao dao = new ContactDaoImpl();

	@Override
	public int saveContactService(contactEntity contact) {
		return dao.addContactDao(contact);
	}

	@Override
	public contactEntity fetchContactService(int id) {
		return dao.getContactDao(id);
	}

	@Override
	public int removeContactService(int id) throws ClassNotFoundException, SQLException {
		return dao.deleteContactDao(id);
	}

	@Override
	public int updateContactService(contactEntity contact) {
		return dao.updateContactDao(contact);
	}

	@Override
	public List<contactEntity> getAllService() {
		// TODO Auto-generated method stub
		return dao.getAllDao();
	}

}
