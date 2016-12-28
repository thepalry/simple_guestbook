package com.nhnbasecamp.guestbook.dao;

import java.util.HashMap;
import java.util.List;

import com.nhnbasecamp.guestbook.vo.GuestbookArticle;

public interface GuestbookArticleDao {
	public List<GuestbookArticle> getList(HashMap<String, Object> paramMap) throws Exception;
	public int insertArticle(GuestbookArticle guestbookArticle) throws Exception;
	public int updateArticle(GuestbookArticle guestbookArticle) throws Exception;
}
