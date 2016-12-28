package dao;

import java.util.List;

import vo.GuestbookArticle;

public interface GuestbookArticleDao {
	public List<GuestbookArticle> getList() throws Exception;
	public int insertArticle(GuestbookArticle guestbookArticle) throws Exception;
}
