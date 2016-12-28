package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import vo.GuestbookArticle;

public class GuestbookArticleDao {
	
	Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<GuestbookArticle> getList() throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.prepareStatement("SELECT GNO, EMAIL, ARTICLE, CREATED_TIME, MODIFIED_TIME FROM GUESTBOOK");
			rs = stmt.executeQuery();
			
			ArrayList<GuestbookArticle> articles = new ArrayList<GuestbookArticle>();
		
			while(rs.next()) {
				GuestbookArticle article = new GuestbookArticle();
				article.setGno(rs.getInt("GNO"));
				article.setEmail(rs.getString("EMAIL"));
				article.setArticle(rs.getString("ARTICLE"));
				article.setCreatedTime(rs.getDate("CREATED_TIME"));
				article.setModifiedTime(rs.getDate("MODIFIED_TIME"));
				articles.add(article);
			}
			return articles;
		} catch (Exception e) {
			throw e;
		} finally {
			try { if ( rs != null ) rs.close(); } catch(Exception e) {}
			try { if ( stmt != null ) stmt.close(); } catch(Exception e) {}
		}
	}
}
