package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GuestbookArticleDao;
import vo.GuestbookArticle;

@WebServlet("/list")
public class mainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {	
			ServletContext sc = this.getServletContext();
			Connection conn = (Connection) sc.getAttribute("conn");
			
			GuestbookArticleDao guestbookArticleDao = new GuestbookArticleDao();
			guestbookArticleDao.setConnection(conn);
			
			ArrayList<GuestbookArticle> articles = guestbookArticleDao.getList();
			request.setAttribute("articles" , articles);
			
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/main.jsp");
			rd.include(request, response);
		} catch (Exception e){
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
			rd.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			stmt = conn.prepareStatement("INSERT INTO GUESTBOOK(EMAIL, PWD, ARTICLE, CREATED_TIME, MODIFIED_TIME)"
					+ " VALUES (?, ?, ?, now(), now()) ");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("pwd"));
			stmt.setString(3, request.getParameter("article"));
			stmt.executeUpdate();

			doGet(request, response);
		} catch (Exception e){
			throw new ServletException(e);
		} finally {
			try { if ( stmt != null ) stmt.close(); } catch(Exception e) {}
		}
	}
}
