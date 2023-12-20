package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.daos.ReviewsDaoImpl;
import com.sunbeam.pojo.Reviews;
import com.sunbeam.pojo.User;

@WebServlet("/reviewslist")
public class ReviewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		HttpSession session=req.getSession();
		User currUser = (User)session.getAttribute("currUser");
		out.printf("<h1>HELLO  %s !</h1>", currUser.getFirstName()+" "+currUser.getLastName());
		out.printf("<a href='logout'><h3 style='text-align:right;'>SIGN OUT</h3></a>");
		out.println("<hr>");
		out.println("<table border='1' width='700px' height='300px'>");
		out.println("<thead>");
		out.println("<th>ID</th>");
		out.println("<th>MOVIE ID</th>");
		out.println("<th>RATING</th>");
		out.println("<th>REVIEW</th>");
		out.println("<th>ACTION</th>");
		out.println("</thead>");

		out.println("<tbody>");
		
		try (ReviewsDaoImpl reviewDao = new ReviewsDaoImpl()) {
			List<Reviews> reviewList = reviewDao.displayAll();
			
			for (Reviews review : reviewList) {

				out.println("<tr>");
				out.printf("<td>%d</td>", review.getId());
				out.printf("<td>%s</td>", review.getMovie_id());
				out.printf("<td>%s</td>", review.getRating());
				out.printf("<td>%s</td>", review.getReview());
				out.println("<td>DELETE,EDIT</td>");
				out.println("</tr>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		out.println("</tbody>");
		out.println("</table>");

		out.println("<br>");
		out.println("<br>");
		out.println("<br>");

		out.println("<a href='newReview'>ADD REVIEW</a>");

	}

}
