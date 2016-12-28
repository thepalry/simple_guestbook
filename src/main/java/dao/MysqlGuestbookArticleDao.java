package dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.GuestbookArticle;

public class MysqlGuestbookArticleDao implements GuestbookArticleDao {
	
	SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public List<GuestbookArticle> getList(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("dao.GuestbookArticleDao.selectList", paramMap);
		} finally {
			sqlSession.close();
		}
	}
	
	public int insertArticle(GuestbookArticle guestbookArticle) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("dao.GuestbookArticleDao.insert");
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
}
