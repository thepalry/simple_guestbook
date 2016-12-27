package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.GuestbookArticle;

@WebServlet("/list")
public class mainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/studydb",
					"study",
					"study");
			stmt = conn.prepareStatement("SELECT GNO, EMAIL, ARTICLE, CREATED_TIME, MODIFIED_TIME FROM GUESTBOOK");
			rs = stmt.executeQuery();
			
			response.setContentType("text/html; charset=UTF-8");
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
			request.setAttribute("articles" , articles);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/main.jsp");
			rd.include(request, response);
			
		} catch (Exception e){
			throw new ServletException(e);
		} finally {
			try { if ( rs != null ) rs.close(); } catch(Exception e) {}
			try { if ( stmt != null ) stmt.close(); } catch(Exception e) {}
			try { if ( conn != null ) conn.close(); } catch(Exception e) {}
		}
		
		
	}
}
