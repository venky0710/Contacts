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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String method = request.getParameter("action");
		int id= Integer.parseInt(request.getParameter("id"));
		
		if(method.equals("save"))
			out.write((ContactServlet.saveData(request,response)));
		else if(method.equals("getAll"))
			out.write(ContactServlet.getAll(request,response)+"");
		else if(method.equals("getById"))
			ContactServlet.getById(request,response);
		
	}

	private static void getById(HttpServletRequest request, HttpServletResponse response) {
		
		ContactService service = new ContactServiceImpl();
		
		contactEntity entity = service.fetchContactService(Integer.parseInt(request.getParameter("id")));
		ObjectMapper mapper = new ObjectMapper();
	

		//Object to JSON in file
		try {
			mapper.writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Object to JSON in String
		try {
			String jsonInString = mapper.writeValueAsString(entity);
			System.out.println(jsonInString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}

	protected static String saveData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("application/json");

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
			return "Record saved successfully!";
			//request.getRequestDispatcher("index.html").include(request, response);
		} else {
			return "Sorry! unable to save record";
		}

	}
	protected static Object getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//String id = request.getParameter("id")
		//int id = Integer.parseInt(request.getParameter("id"));
		ContactService service = new ContactServiceImpl();

			List<contactEntity> list = service.getAllService();
		PrintWriter out = response.getWriter();
		if(list.isEmpty())
			return ("Sorry! unable to update record");
		else
			return list;
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

		int id = Integer.parseInt(request.getParameter("id"));
		ContactService service = new ContactServiceImpl();

		try {
			service.removeContactService(id);
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
