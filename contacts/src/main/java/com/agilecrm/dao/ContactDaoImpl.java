package com.agilecrm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.agilecrm.model.contactEntity;
import com.agilecrm.utility.ConnectionDao;

public class ContactDaoImpl implements ContactDao {

	@Override
	public int addContactDao(contactEntity contact) {
		int status = 0;
		try {
			Connection connection = ConnectionDao.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(
					"insert into contacts(firstName,lastName,email,dob,isActive,createdDate) values (?,?,?,?,1,?)");
			ps.setString(1, contact.getFirstName());
			ps.setString(2, contact.getLastName());
			ps.setString(3, contact.getEmail());
			ps.setDate(4, new java.sql.Date(contact.getDob().getTime()));
			ps.setDate(5, new java.sql.Date(new Date().getTime()));

			status = ps.executeUpdate();

			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;

	}

	@Override
	public contactEntity getContactDao(int id) {
		contactEntity contact = new contactEntity();
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from contacts where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				contact.setId(rs.getInt(1));
				contact.setFirstName(rs.getString(2));
				contact.setLastName(rs.getString(3));
				contact.setEmail(rs.getString(4));
				contact.setDob(rs.getDate(5));
				contact.setCreatedBy(rs.getInt(6));
				contact.setUpdatedBy(rs.getInt(7));
				contact.setCreatedDate(rs.getDate(8));
				contact.setUpdatedDate(rs.getDate(9));
				contact.setIsActive(rs.getInt(10));

			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return contact;
	}

	@Override
	public int deleteContactDao(int id) throws ClassNotFoundException, SQLException {
		int status = 0;
		contactEntity contact = new contactEntity();
		Connection con = ConnectionDao.getConnection();
		try {

			PreparedStatement pst = con.prepareStatement("update contacts set isActive=0 where id=?");
			pst.setInt(1, id);
			
			status = pst.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public int updateContactDao(contactEntity contact) {
		int status = 0;
		try {
			
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"UPDATE contacts SET firstName=?,lastName=?,email=?,dob=?,updatedDate=? where id=? and isActive=1");
			ps.setString(1, contact.getFirstName());
			ps.setString(2, contact.getLastName());
			ps.setString(3, contact.getEmail());
			ps.setDate(4, new java.sql.Date(contact.getDob().getTime()));
			ps.setDate(5, new java.sql.Date(new Date().getTime()));
			ps.setInt(6, contact.getId());
			
			status = ps.executeUpdate();


			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	@Override
	public List<contactEntity> getAllDao() {
		List<contactEntity> list = new ArrayList<contactEntity>();
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from contacts where isActive=1");

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				contactEntity contact = new contactEntity();
				contact.setEmail(resultSet.getString("email"));
				contact.setFirstName(resultSet.getString("firstName"));
				contact.setLastName(resultSet.getString("lastName"));
				contact.setId(resultSet.getInt("id"));
				list.add(contact);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

}
