package control;

import java.util.ArrayList;
import java.util.Map;

import dao.GuestbookArticleDao;
import vo.GuestbookArticle;

public class mainController implements Controller {
	GuestbookArticleDao guestbookArticleDao;
	
	public mainController(GuestbookArticleDao guestbookArticleDao) {
		this.guestbookArticleDao = guestbookArticleDao;
	}
	
	public void setGuestbookArticleDao(GuestbookArticleDao guestbookArticleDao) {
		this.guestbookArticleDao = guestbookArticleDao;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		if(model.get("email") != null)
			guestbookArticleDao.insertArticle(
				(String) model.get("email"),
				(String) model.get("pwd"),
				(String) model.get("article"));

		ArrayList<GuestbookArticle> articles = guestbookArticleDao.getList();
		model.put("articles" , articles);
		
		return "/WEB-INF/views/main.jsp";
	}

}
