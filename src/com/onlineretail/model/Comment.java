package com.onlineretail.model;

import java.util.Date;

public class Comment implements Comparable {
	private int id;
	private String categoryname;
	private String comment;
	private Date date;
	private int rating;
	private String status;

	public Comment() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comments) {
		this.comment = comments;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comment == null) ? 0 : comment.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return getId() + "\t" + getCategoryname() + "\t\t" + getComment() + "\t\t" + getDate() + "\t\t" + getRating();
	}

	@Override
	public int compareTo(Object o) {
		Comment c = (Comment) o;
		if (c.getCategoryname().equals(getCategoryname())) {
			return 0;
		} else if (Character.getNumericValue(c.getCategoryname().charAt(0)) < Character.getNumericValue(getCategoryname().charAt(0))){
			return 1;
		} else if (Character.getNumericValue(c.getCategoryname().charAt(0)) > Character.getNumericValue(getCategoryname().charAt(0))){
			return -1;
		}
		return 0;
	}
}
