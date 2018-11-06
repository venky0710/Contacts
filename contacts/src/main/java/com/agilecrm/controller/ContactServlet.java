package com.agilecrm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.agilecrm.model.contactEntity;
import com.agilecrm.service.ContactService;
import com.agilecrm.service.ContactServiceImpl;




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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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

		int status = service.saveContact(contact);
		if (status > 0) {
			out.print("Record saved successfully!");
			//request.getRequestDispatcher("index.html").include(request, response);
		} else {
			out.println("Sorry! unable to save record");
		}

		out.close();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//String id = request.getParameter("id")
		//int id = Integer.parseInt(request.getParameter("id"));
		ContactService service = new ContactServiceImpl();

		try {
			//int id = Integer.parseInt(request.getParameter("id"));
			service.removeContact(10);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.println("Sorry! unable to update record");
		//response.sendRedirect("ViewServlet");
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		ContactService service = new ContactServiceImpl();

		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("name");
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
		contact.setId(id);
		contact.setDob(dob);

		int status = service.updateContact(contact);
		if (status > 0) {
			out.print("<p>Record Updated successfully!</p>");
		} else {
			out.println("Sorry! unable to update record");
		}

		out.close();

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		ContactService service = new ContactServiceImpl();

		try {
			service.removeContact(id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.sendRedirect("ViewServlet");
	}

}
