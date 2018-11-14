package com.agilecrm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agilecrm.model.contactEntity;
import com.agilecrm.service.ContactService;
import com.agilecrm.service.ContactServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/servlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();
		ContactService service = new ContactServiceImpl();

		String name = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Date dob = null;
		try {
			dob = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dob"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		contactEntity contact = new contactEntity();
		contact.setFirstName(name);
		contact.setLastName(lastName);
		contact.setEmail(email);
		contact.setDob(dob);
		contact.setCreatedDate(new Date());

		int status = service.saveContactService(contact);
		if (status > 0) {
			out.write("Record saved successfully!");
		} else {
			out.write("Sorry! unable to save record");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");

		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		ContactService service = new ContactServiceImpl();

		if(request.getParameter("id")!=null) {
			int id = Integer.parseInt(request.getParameter("id"));
			contactEntity entity = service.fetchContactService(id);
			
		

			//Object to JSON in file
			try {
				mapper.writeValueAsString(entity);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			//Object to JSON in String
			try {
				String jsonInString = mapper.writeValueAsString(entity);
				out.write(jsonInString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			

		}
		else {
			List<contactEntity> list = service.getAllService();
			if(list.isEmpty())
				out.write("Sorry! unable to fetch record");
			else {
				mapper.writeValueAsString(list);
				String jsonInString = mapper.writeValueAsString(list);
				out.write(jsonInString);
			}

		}
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		ContactService service = new ContactServiceImpl();

		int ide = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		Date dob = null;
		try {
			dob = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dob"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		contactEntity contact = new contactEntity();
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setEmail(email);
		contact.setId(ide);
		contact.setDob(dob);

		int status = service.updateContactService(contact);
		if (status > 0) {
			out.print("<p>Record Updated successfully!</p>");
		} else {
			out.println("Sorry! unable to update record");
		}

		out.close();

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int status = 0;
		int id = Integer.parseInt(request.getParameter("id"));
		ContactService service = new ContactServiceImpl();

		PrintWriter out = response.getWriter();
			try {
				status = service.removeContactService(id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (status > 0) {
				out.print("<p>Record Updated successfully!</p>");
			} else {
				out.println("Sorry! unable to update record");
			}

			out.close();
	}

}
