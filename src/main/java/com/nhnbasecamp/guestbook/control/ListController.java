package com.nhnbasecamp.guestbook.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import com.nhnbasecamp.guestbook.dao.MysqlGuestbookArticleDao;
import com.nhnbasecamp.guestbook.vo.GuestbookArticle;

@Controller
public class ListController {
	MysqlGuestbookArticleDao guestbookArticleDao;
	
	@Autowired
	public ListController(MysqlGuestbookArticleDao guestbookArticleDao) {
		this.guestbookArticleDao = guestbookArticleDao;
	}
	
	public void setGuestbookArticleDao(MysqlGuestbookArticleDao guestbookArticleDao) {
		this.guestbookArticleDao = guestbookArticleDao;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String execute(Map<String, Object> model) throws Exception {
		
		if(model.get("email") != null) {
			GuestbookArticle guestbookArticle = new GuestbookArticle();
			guestbookArticle.setEmail((String) model.get("email"));
			guestbookArticle.setPwd((String) model.get("pwd"));
			guestbookArticle.setArticle((String) model.get("article"));
			guestbookArticleDao.insertArticle(guestbookArticle);
		}
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(model.get("orderCond") != null)
			paramMap.put("orderCond", (String) model.get("orderCond"));
		else
			paramMap.put("orderCond", "MODIFIED_TIME");
		
		List<GuestbookArticle> articles = guestbookArticleDao.getList(paramMap);
		model.put("articles" , articles);
		
		return "List";
	}

}
