package com.onlineretail.DAO.jdbc;

import com.onlineretail.DAO.RegisterDao;
import com.onlineretail.model.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.onlineretail.DAO.jdbc.ConnectionHelper.cleanup;
import static com.onlineretail.DAO.jdbc.ConnectionHelper.getMySqlConnection;

public class JdbcRegisterDao implements RegisterDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JdbcRegisterDao.class);

	@Override
	public List<Registration> findAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = (Statement) con.createStatement();
			result = stmt.executeQuery("select * from registration where status = 'y'");
			List<Registration> registrations = new ArrayList<Registration>();
			Registration registration;
			while (result.next()) {
				registration = new Registration();
				registration.setId(result.getInt("id"));
				registration.setUsername(result.getString("username"));
				registration.setPassword(result.getString("password"));
				registration.setPhnnumber(result.getString("phno"));
				registration.setGender(result.getString("gender"));
				registration.setCity(result.getString("city"));
				registration.setCountry(result.getString("country"));
				registration.setRegistereddate(result.getDate("regdate"));
				registration.setStatus(result.getString("status"));
				registrations.add(registration);
			}
			con.close();
			return registrations;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, result);
		}
	}

	@Override
	public int Save(Registration registration) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt;
		try {
			con = getMySqlConnection();
			String query = "insert into registration(id,username,password,phno,gender,city,country,regdate)"
					+ " values(Registration_Seq.nextval,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, registration.getUsername());
			stmt.setString(2, registration.getPassword());
			stmt.setString(3, registration.getPhnnumber());
			stmt.setString(4, registration.getGender());
			stmt.setString(5, registration.getCity());
			stmt.setString(6, registration.getCountry());
			stmt.setDate(7, registration.getRegistereddate());
			cnt = stmt.executeUpdate();
			con.close();
			return cnt;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, null);
		}
	}

	@Override
	public void updateRegistration(Registration registration) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "update registration set phno = ? where id = ?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, registration.getPhnnumber());
			stmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, null);
		}
	}

	@Override
	public void deleteRegistration(int Id) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getMySqlConnection();
			String query = "update registration set status = 'n' where id = ?";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, Id);
			con.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException("Registration Details Not Deleted");
		} finally {
			cleanup(con, stmt, null);
		}
	}
	
	public boolean isDuplicateUserName(String userName) {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from registration where username = '"
							+ userName + "'");
			result.next();
			int count = result.getInt(1);
			con.close();
			if (count == 0)
				return false;
			else
				return true;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, result);
		}
	}

	public int findByUserId(int userId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		int status = 0;
		try {
			con = getMySqlConnection();
			stmt = con.prepareStatement("select count(*) from registration where id = ?");
			stmt.setInt(1, userId);
			result = stmt.executeQuery();
			result.next();
			status = result.getInt(1);
			con.close();
			return status;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, result);
		}
	}

}
