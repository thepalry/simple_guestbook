package listener;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.GuestbookArticleDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

	Connection connection;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();
			
			Class.forName(sc.getInitParameter("driver"));
			connection = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			
			GuestbookArticleDao guestbookArticleDao = new GuestbookArticleDao();
			guestbookArticleDao.setConnection(connection);

			sc.setAttribute("guestbookArticleDao", guestbookArticleDao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			if(connection != null && connection.isClosed() == false)
				connection.close();
		} catch (Exception e) {
		}
	}


}
