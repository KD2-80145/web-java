package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.ReviewsDaoImpl;
import com.sunbeam.pojo.Reviews;
import com.sunbeam.pojo.User;

@WebServlet("/addReview")
public class AddReviewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int movieId = Integer.parseInt(req.getParameter("movieName"));
		int rating = Integer.parseInt(req.getParameter("rating"));
		String review = req.getParameter("review");

		HttpSession session = req.getSession();
		User currUser = (User) session.getAttribute("currUser");

		Date uDate = new Date();
		Reviews newReview = new Reviews(0, movieId, review, rating, currUser.getId(), uDate);

		try (ReviewsDaoImpl revDao = new ReviewsDaoImpl()) {
			revDao.insReview(newReview);
		} catch (Exception e) {
			e.printStackTrace();
		}


		resp.sendRedirect("reviewslist");
//		RequestDispatcher rd = req.getRequestDispatcher("reviewslist");
//		rd.forward(req, resp);

	}
}
