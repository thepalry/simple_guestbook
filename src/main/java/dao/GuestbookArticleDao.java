package dao;

import java.util.ArrayList;

import vo.GuestbookArticle;

public interface GuestbookArticleDao {
	public ArrayList<GuestbookArticle> getList() throws Exception;
	public void insertArticle(String email, String pwd, String article) throws Exception;
}
