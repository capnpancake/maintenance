package com.onlineretail.service;

import java.util.Date;
import java.util.List;

import com.onlineretail.model.*;

public interface CommentService {

	int AddComment(String Comments, String CategoryName, Date CommentDate, int Rating);

	 List<Comment> findAllComments();

	 List<Comment> findComment(String categoryName);

	 List<Category> findCategoryAll();

}
