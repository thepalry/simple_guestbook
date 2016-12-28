package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.GuestbookArticle;

public class GuestbookArticleDao {
	
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public ArrayList<GuestbookArticle> getList() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT GNO, EMAIL, ARTICLE, CREATED_TIME, MODIFIED_TIME FROM GUESTBOOK");
			
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
			try { if ( conn != null ) conn.close(); } catch(Exception e) {}
			try { if ( stmt != null ) stmt.close(); } catch(Exception e) {}
			try { if ( rs != null ) rs.close(); } catch(Exception e) {}
		}
	}
	
	public void insertArticle(String email, String pwd, String article) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("INSERT INTO GUESTBOOK(EMAIL, PWD, ARTICLE, CREATED_TIME, MODIFIED_TIME)"
					+ " VALUES (?, ?, ?, now(), now()) ");
			stmt.setString(1, email);
			stmt.setString(2, pwd);
			stmt.setString(3, article);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			try { if ( conn != null ) conn.close(); } catch(Exception e) {}
			try { if ( stmt != null ) stmt.close(); } catch(Exception e) {}
			try { if ( rs != null ) rs.close(); } catch(Exception e) {}
		}
	}
}
