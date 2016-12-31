package com.nhnbasecamp.guestbook.control;

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
	
	// 데이터 처리 Dao 객체
	GuestbookArticleDao guestbookArticleDao;
	
	// Dao 객체 주입
	@Autowired
	public UpdateController(GuestbookArticleDao guestbookArticleDao) {
		this.guestbookArticleDao = guestbookArticleDao;
	}
	
	// update POST 요청시 목록 출력, gno, email, pwd, article은 list에서 전달 받은 수정 대상 객체 정보
	// pwd null 인 경우 업데이트 창 표현, null이 아닌 경우 수정 요청
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String executePOST(
			@RequestParam(value="gno", required=true) int gno,
			@RequestParam(value="email", required=true) String email,
			@RequestParam(value="pwd", required=false) String pwd,
			@RequestParam(value="article", required=true) String article,
			Model model) throws Exception {
		
		// 표기할 객체 정보 생성
		GuestbookArticle guestbookArticle = new GuestbookArticle();
		guestbookArticle.setGno(gno);
		guestbookArticle.setEmail(email);
		guestbookArticle.setArticle(article);
		
		// pwd가 null인 경우, 즉 수정 창 표현 요청인 경우 수정 창(update.jsp)
		if(pwd == null) {
			model.addAttribute("articles" , article);
			return "Update";
		}
		// 수정 요청 후 목록으로 리다이렉트
		else {
			guestbookArticle.setPwd(pwd);
			try {
				model.addAttribute("QueryResult", guestbookArticleDao.updateArticle(guestbookArticle));
			} catch (Exception e) {
				model.addAttribute("error", "데이터를 수정하는데 문제가 발생하였습니다.");
				return "Error";
			}
			return "redirect:list";
		}
	}
}
