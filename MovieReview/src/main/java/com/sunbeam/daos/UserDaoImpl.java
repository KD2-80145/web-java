package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sunbeam.pojo.Dao;
import com.sunbeam.pojo.User;
import com.sunbeam.utils.DateTimeUtil;

public class UserDaoImpl extends Dao {
	public UserDaoImpl() throws Exception {
	}

	public int save(User u) throws Exception {
		String sql = "INSERT INTO users(id, first_name, last_name, email, mobile, birth, password) VALUES(default, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, u.getFirstName());
			stmt.setString(2, u.getLastName());
			stmt.setString(3, u.getEmail());
			stmt.setString(4, u.getMobile());
			stmt.setDate(5, DateTimeUtil.utilDateToSqlDate(u.getBirthDate()));
			stmt.setString(6, u.getPassword());
			int count = stmt.executeUpdate();
			return count;
		} //stmt.close();
	}
	
	public int update(User u) throws Exception {
		String sql = "UPDATE users SET first_name=?, last_name=?, mobile=?, birth=? WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, u.getFirstName());
			stmt.setString(2, u.getLastName());
			stmt.setString(3, u.getEmail());
			stmt.setDate(4, DateTimeUtil.utilDateToSqlDate(u.getBirthDate()));
			stmt.setInt(5, u.getId());
			int count = stmt.executeUpdate();
			return count;
		} //stmt.close();
	}

	public int updatePassword(int userId, String newPassword) throws Exception {
		String sql = "UPDATE users SET password=? WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, newPassword);
			stmt.setInt(2, userId);
			int count = stmt.executeUpdate();
			return count;
		} //stmt.close();
	}

	public User findByEmail(String email) throws Exception {
		String sql = "SELECT * FROM users WHERE email=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					String password = rs.getString("password");
					email = rs.getString("email");
					String mobile = rs.getString("mobile");
					Date uDate = DateTimeUtil.sqlDateToUtilDate(rs.getDate("birth"));
					return new User(id, fname, lname, email, password, uDate, mobile);
				}
			} // rs.close();
		} // stmt.close();
		return null;
	}

	public List<User> findAll() throws Exception {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT * FROM users";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					String password = rs.getString("password");
					String email = rs.getString("email");
					String mobile = rs.getString("mobile");
					Date uDate = DateTimeUtil.sqlDateToUtilDate(rs.getDate("birth"));
					User u = new User(id, fname, lname, email, password, uDate, mobile);
					list.add(u);
				}
			} // rs.close();
		} // stmt.close();
		return list;
	}
	

}
