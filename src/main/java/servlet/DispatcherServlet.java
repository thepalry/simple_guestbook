package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	
	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		
		try {
			String pageControllerPath = null;
			if("guestbook/list.do".equals(servletPath)) {
				pageControllerPath = "guestbook/list";
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
			rd.include(request, response);
			
			String viewUrl = (String) request.getAttribute("viewUrl");
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/error.jsp");
			rd.forward(request, response);
		}
	}
}
