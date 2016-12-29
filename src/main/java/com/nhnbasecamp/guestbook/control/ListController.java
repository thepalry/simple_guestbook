package com.nhnbasecamp.guestbook.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.nhnbasecamp.guestbook.dao.GuestbookArticleDao;
import com.nhnbasecamp.guestbook.vo.GuestbookArticle;

@Controller
public class ListController {
	GuestbookArticleDao guestbookArticleDao;
	
	@Autowired
	public ListController(GuestbookArticleDao guestbookArticleDao) {
		this.guestbookArticleDao = guestbookArticleDao;
	}
	
	public void setGuestbookArticleDao(GuestbookArticleDao guestbookArticleDao) {
		this.guestbookArticleDao = guestbookArticleDao;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String executeGET(
			@RequestParam(value="orderCond", required=false) String orderCond,
			Model model) throws Exception {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(orderCond != null)
			paramMap.put("orderCond", orderCond);
		else
			paramMap.put("orderCond", "MODIFIED_TIME");
		
		try {
			List<GuestbookArticle> articles = guestbookArticleDao.getList(paramMap);
			model.addAttribute("articles" , articles);
		} catch (Exception e) {
			model.addAttribute("error", "�����͸� �޾ƿ��µ� ������ �߻��Ͽ����ϴ�.");
			return "Error";
		}
		
		return "List";
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public String executePOST(
			@RequestParam(value="orderCond", required=false) String orderCond,
			@RequestParam(value="email", required=true) String email,
			@RequestParam(value="pwd", required=true) String pwd,
			@RequestParam(value="article", required=true) String article,
			Model model) throws Exception {
		
		if(Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim()) == false) {
			model.addAttribute("error", "�̸����ּҰ� ��ȿ���� �ʽ��ϴ�.");
			return "Error";
		}

		GuestbookArticle guestbookArticle = new GuestbookArticle();
		guestbookArticle.setEmail(email);
		guestbookArticle.setPwd(pwd);
		guestbookArticle.setArticle(article);
		try {
			guestbookArticleDao.insertArticle(guestbookArticle);
		} catch (Exception e) {
			model.addAttribute("error", "�����͸� �Է��ϴµ� ������ �߻��Ͽ����ϴ�. �Է��� �ùٸ��� ������ �ּ���.");
			return "Error";
		}
		
		return executeGET(orderCond, model);
	}

}
