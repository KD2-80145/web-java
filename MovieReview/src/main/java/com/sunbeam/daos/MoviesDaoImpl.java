package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.pojo.Movies;
import com.sunbeam.pojo.Dao;

public class MoviesDaoImpl extends Dao{
	public MoviesDaoImpl() throws Exception {
	}

	public List<Movies> displayAll() throws SQLException{
		List<Movies> list = new ArrayList<>();
		String sql = "SELECT * FROM movies";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("title");
					java.sql.Date sdate = rs.getDate("rel_date");
					java.util.Date jdate = new java.util.Date(sdate.getTime());
					Movies movie = new Movies(id,name,jdate);
					list.add(movie);
				}
			}
		}
		return list;
	}
}
