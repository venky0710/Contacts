package com.agilecrm.Dao;

import Domain.ContactDomain;

public interface ContactDao {

	public int addContact(ContactDomain contact);
	public ContactDomain getContact(String id);
	public int deleteContact(String id);
	public int updateContact(String id);
}
