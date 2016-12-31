package com.nhnbasecamp.guestbook.dao;

import java.util.HashMap;
import java.util.List;

import com.nhnbasecamp.guestbook.vo.GuestbookArticle;

// 데이터 처리 Dao 객체
public interface GuestbookArticleDao {
	public List<GuestbookArticle> getList(HashMap<String, Object> paramMap) throws Exception;	// 전체 목록 받아오기
	public int insertArticle(GuestbookArticle guestbookArticle) throws Exception;				// 새 글 입력
	public int updateArticle(GuestbookArticle guestbookArticle) throws Exception;				// 기존 글 수정
}
