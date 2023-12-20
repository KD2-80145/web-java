package com.sunbeam.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojo.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pass = req.getParameter("password");

		User userFound = null;
		String userFoundPass = "";
		
		try {
			UserDaoImpl userDao = new UserDaoImpl();

			userFound = userDao.findByEmail(email);
			userFoundPass = userFound.getPassword();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userFound != null && pass.equals(userFoundPass)) {
			
			HttpSession session=req.getSession();
			session.setAttribute("currUser", userFound);
			
			RequestDispatcher rd= req.getRequestDispatcher("reviewslist");
			rd.forward(req, resp);
		} else {
			resp.sendRedirect("notfound.html");
		}
	}
}
