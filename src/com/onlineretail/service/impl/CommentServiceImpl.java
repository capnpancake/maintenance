package com.onlineretail.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.onlineretail.DAO.*;
import com.onlineretail.DAO.jdbc.*;
import com.onlineretail.model.*;
import com.onlineretail.service.*;

public class CommentServiceImpl implements CommentService {

	private CommentDao commentdao;

	public CommentServiceImpl() {
		commentdao = new JdbcCommentDao();
	}

	@Override
	public int AddComment(String Comment, String categoryname, Date CommentDate,
			int Rating) {

		Comment comment = new Comment();
		comment.setComment(Comment);
		comment.setCategoryname(categoryname);
		comment.setDate(CommentDate);
		comment.setRating(Rating);		

		return commentdao.Save(comment);
	}

	@Override
	public List<Comment> findAllComments() {
		return commentdao.findAll();
	}

	@Override
	public List<Comment> findComment(String categoryName) {
		return commentdao.findComment(categoryName);
	}

	public List<Category> findCategoryAll() {
		return commentdao.findCategoryAll();
	}

}
