package com.sunbeam.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojo.User;

@WebServlet("/registerUser")
public class AddUserServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String FirstName = req.getParameter("fname");
		String LastName = req.getParameter("lname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String mobile = req.getParameter("mobile");
		String birthDate = req.getParameter("birthdate");
//		System.out.println(req.getParameter("birthdate"));

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date udate = null;
		try {
			udate = formatter.parse(birthDate);
		} catch (ParseException e1) {
			e1.printStackTrace();
			throw new ServletException(e1);
		}

		User newUser = new User(0, FirstName, LastName, email, password, udate, mobile);

		try (UserDaoImpl userDao = new UserDaoImpl()) {
			int cnt = userDao.save(newUser);

			if (cnt == 1) {
				resp.sendRedirect("useradded.html");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
