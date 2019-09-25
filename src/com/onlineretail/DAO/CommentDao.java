package com.onlineretail.DAO;

import com.onlineretail.model.*;

import java.util.*;

public interface CommentDao {
	 int Save(Comment comment);

	 List<Comment> findAll();

	 List<Comment> findComment(String categoryName);
	 
	 int findByComment(String categoryName);
	 
	 List<Category> findCategoryAll();

	 int findByCategoryId(int categoryId);
		
	 String getCategoryName(int categoryId);

}