package com.nhnbasecamp.guestbook.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhnbasecamp.guestbook.vo.GuestbookArticle;

@Component("guestbookArticleDao")
public class MysqlGuestbookArticleDao implements GuestbookArticleDao {
	
	SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	public MysqlGuestbookArticleDao(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public List<GuestbookArticle> getList(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("com.nhnbasecamp.guestbook.dao.GuestbookArticleDao.selectList", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	public int insertArticle(GuestbookArticle guestbookArticle) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("com.nhnbasecamp.guestbook.dao.GuestbookArticleDao.insert", guestbookArticle);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	public int updateArticle(GuestbookArticle guestbookArticle) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("com.nhnbasecamp.guestbook.dao.GuestbookArticleDao.update", guestbookArticle);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
}
