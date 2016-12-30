package com.nhnbasecamp.guestbook.vo;

import java.sql.Date;

public class GuestbookArticle {
	protected int gno;
	protected String email;
	protected String pwd;
	protected String article;
	protected Date createdTime;
	protected Date modifiedTime;

	public int getGno() {
		return gno;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public String getArticle() {
		return article;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	
	public Date getModifiedTime() {
		return modifiedTime;
	}
	
	public void setGno(int gno) {
		this.gno = gno;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void setArticle(String article) {
		this.article = article;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
}
