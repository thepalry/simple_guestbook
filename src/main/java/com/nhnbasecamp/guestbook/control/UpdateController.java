package com.nhnbasecamp.guestbook.control;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhnbasecamp.guestbook.dao.GuestbookArticleDao;
import com.nhnbasecamp.guestbook.vo.GuestbookArticle;

@Controller
public class UpdateController {
	GuestbookArticleDao guestbookArticleDao;
	
	@Autowired
	public UpdateController(GuestbookArticleDao guestbookArticleDao) {
		this.guestbookArticleDao = guestbookArticleDao;
	}
	
	public void setUpdateControllerDao(GuestbookArticleDao guestbookArticleDao) {
		this.guestbookArticleDao = guestbookArticleDao;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String executePOST(
			@RequestParam(value="gno", required=true) int gno,
			@RequestParam(value="email", required=true) String email,
			@RequestParam(value="pwd", required=false) String pwd,
			@RequestParam(value="article", required=true) String article,
			Model model) throws Exception {
		
		GuestbookArticle guestbookArticle = new GuestbookArticle();
		guestbookArticle.setGno(gno);
		guestbookArticle.setEmail(email);
		guestbookArticle.setArticle(article);
		
		if(pwd == null) { // 업데이트 창 요청 
			model.addAttribute("articles" , article);
			return "Update";
		}
		else { // 업데이트 요청
			guestbookArticle.setPwd(pwd);
			guestbookArticleDao.updateArticle(guestbookArticle);
			return "redirect:list";
		}
		
	}
}
