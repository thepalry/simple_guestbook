package servlet;

import java.io.IOException;
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
			GuestbookArticleDao guestbookArticleDao = (GuestbookArticleDao) sc.getAttribute("guestbookArticleDao");

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
		try {
			ServletContext sc = this.getServletContext();
			GuestbookArticleDao guestbookArticleDao = (GuestbookArticleDao) sc.getAttribute("guestbookArticleDao");
			guestbookArticleDao.insertArticle(
					request.getParameter("email"),
					request.getParameter("pwd"),
					request.getParameter("article"));

			doGet(request, response);
		} catch (Exception e){
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
			rd.forward(request, response);
		}
	}
}
