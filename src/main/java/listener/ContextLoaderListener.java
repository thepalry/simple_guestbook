package listener;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import control.mainController;
import dao.GuestbookArticleDao;
import dao.MysqlGuestbookArticleDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext sc = event.getServletContext();
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/studydb");
			
			MysqlGuestbookArticleDao guestbookArticleDao = new MysqlGuestbookArticleDao();
			guestbookArticleDao.setDataSource(ds);
			
			sc.setAttribute("/list.do", new mainController(guestbookArticleDao));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {}
}
