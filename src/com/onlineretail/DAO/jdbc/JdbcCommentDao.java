package com.onlineretail.DAO.jdbc;

import com.onlineretail.DAO.CommentDao;
import com.onlineretail.model.Category;
import com.onlineretail.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.onlineretail.DAO.jdbc.ConnectionHelper.cleanup;
import static com.onlineretail.DAO.jdbc.ConnectionHelper.getMySqlConnection;

public class JdbcCommentDao implements CommentDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JdbcCommentDao.class);

	@Override
	public int Save(Comment comment) {
		Connection con = null;
		PreparedStatement stmt = null;
		int cnt = 0;
		try {
			con = getMySqlConnection();
			String query = "insert into comments(id,categoryname,comments,commentdate,rating)"
					+ " values(Comment_seq.nextval,?,?,?,?)";
			stmt = con.prepareStatement(query);
			stmt.setString(1, comment.getCategoryname());
			stmt.setString(2, comment.getComment());
			stmt.setDate(3, (java.sql.Date) comment.getDate());
			stmt.setInt(4, comment.getRating());
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
	public List<Comment> findAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = con.createStatement();
			result = stmt.executeQuery("select * from comments where status = 'y'");
			List<Comment> comments = new ArrayList<>();
			Comment comment;
			while (result.next()) {
				comment = new Comment();
				comment.setId(result.getInt("id"));
				comment.setCategoryname(result.getString("categoryname"));
				comment.setComment(result.getString("comments"));
				comment.setDate(result.getDate("commentdate"));
				comment.setRating(result.getInt("rating"));
				comment.setStatus(result.getString("status"));
				comments.add(comment);
			}
			con.close();
			Collections.sort(comments);
			return comments;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, result);
		}
	}

	@Override
	public List<Category> findCategoryAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = con.createStatement();
			result = stmt
					.executeQuery("select * from category where status = 'y'");
			List<Category> categories = new ArrayList<>();
			Category category;
			while (result.next()) {
				category = new Category();
				category.setCid(result.getInt("cid"));
				category.setCname(result.getString("cname"));
				category.setDescription(result.getString("description"));
				category.setStatus(result.getString("status"));
				categories.add(category);
			}
			con.close();
			return categories;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, result);
		}
	}


	@Override
	public List<Comment> findComment(String categoryName) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = con
					.prepareStatement("select * from comments where CategoryName = ?");
			stmt.setString(1, categoryName);
			result = stmt.executeQuery();
			List<Comment> comments = new ArrayList<>();
			Comment comment;
			while (result.next()) {
				comment = new Comment();
				comment.setId(result.getInt("id"));
				comment.setCategoryname(result.getString("categoryname"));
				comment.setComment(result.getString("comments"));
				comment.setDate(result.getDate("commentdate"));
				comment.setRating(result.getInt("rating"));
				comment.setStatus(result.getString("status"));
				comments.add(comment);
			}
			con.close();
			return comments;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, result);
		}
	}


	@Override
	public int findByComment(String categoryName) {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		String query = "select count(*) from comments where CategoryName = '"
						+ categoryName + "'";
		int count;
		try {
			con = getMySqlConnection();
			stmt = con.createStatement();
			result = stmt.executeQuery(query);
			result.next();
			count = result.getInt(1);
			con.close();
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, result);
		}
		return count;
	}


	public int findByCategoryId(int categoryId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		int status = 0;
		try {
			con = getMySqlConnection();
			stmt = (Statement) con.createStatement();
			result = stmt
					.executeQuery("select count(*) from category where cid = "
							+ categoryId);
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


	@Override
	public String getCategoryName(int categoryId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			con = getMySqlConnection();
			stmt = con.prepareStatement("select * from category where cid = ?");
			stmt.setInt(1, categoryId);
			result = stmt.executeQuery();
			result.next();
			String categoryName = result.getString("cname");
			con.close();
			return categoryName;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			cleanup(con, stmt, result);
		}
	}

}
