package com.onlineretail.DAO.jdbc;

import com.onlineretail.DAO.*;

import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.onlineretail.DAO.jdbc.ConnectionHelper.*;

public class JdbcLoginDao implements LoginDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JdbcLoginDao.class);

	@Override
	public int loginValidate(String username,String password) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = con.prepareStatement("select count(*) from registration where username = ? and password = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			result = stmt.executeQuery();
			
			result.next();
			int cnt = result.getInt(1);
			con.close();
			return cnt;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, result);
		}
	}

}
