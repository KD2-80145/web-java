package com.sunbeam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.daos.MoviesDaoImpl;
import com.sunbeam.pojo.Movies;

@WebServlet("/newReview")
public class NewReviewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		
		resp.setContentType("text/html");
		out.println("<html>");
		out.println("<form action='addReview'>");
		out.println("<table>");
		out.println("<thead>");
		out.println("<tr>");
		
		out.println("</tr>");
		out.println("</thead>");

		out.println("<tbody>");
		
		out.println("<tr>");
		out.println("<td><label for='movieName'>MOVIE : </label></td>");
		try(MoviesDaoImpl MovDao = new MoviesDaoImpl())
		{
			List<Movies> movList=MovDao.displayAll();
			out.println("<td><select name='movieName'>");
			for(Movies movie:movList) {
			out.printf("<option value='%d'>%s</option>",movie.getId(),movie.getTitle());
			}
			out.println("</select></td>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>RATING : </td>");
		out.println("<td><input type='number' name='rating'></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>REVIEW : </td>");
		out.println("<td><textarea name='review'  rows='4' cols='50'></textarea></td>");
		out.println("</tr>");
		
		out.println("</tbody>");

		out.println("</table>");
		
		out.println("<input type='submit' value='SAVE'>");
		
		out.println("</form>");
		
		out.println("</html>");
	}

}
