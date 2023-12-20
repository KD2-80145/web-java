package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sunbeam.pojo.Dao;

public class ShareDaoImpl extends Dao {

	public ShareDaoImpl() throws Exception {
	}

	public int share(int rid, int user_id) throws SQLException {
		String sql = "INSERT INTO shares VALUES(?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, rid);
			stmt.setInt(2, user_id);

			int cnt = stmt.executeUpdate();
			return cnt;
		}
	}

	public void add(int rid, int uid) throws Exception {
		String sql = "insert into shares values(?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, rid);
			stmt.setInt(2, uid);
			stmt.executeUpdate();
		}
	}

	// deleting shared reviews
	public void delete(int rid) throws Exception {
		String sql = "delete from shares where review_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, rid);

			stmt.executeUpdate();
		}
	}


}
