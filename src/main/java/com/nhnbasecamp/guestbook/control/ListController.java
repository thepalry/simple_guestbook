package com.nhnbasecamp.guestbook.control;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.nhnbasecamp.guestbook.dao.GuestbookArticleDao;
import com.nhnbasecamp.guestbook.vo.GuestbookArticle;

@Controller
public class ListController {
	
	// 데이터 처리 Dao 객체
	GuestbookArticleDao guestbookArticleDao;
	
	// Dao 객체 주입
	@Autowired
	public ListController(GuestbookArticleDao guestbookArticleDao) {
		this.guestbookArticleDao = guestbookArticleDao;
	}
	
	// list GET 요청시 목록 출력, orderCond로 목록 정렬 순서 지정
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String executeGET(
			@RequestParam(value="orderCond", required=false) String orderCond,
			Model model) throws Exception {
		
		// orderCond가 없는 경우 수정 시간 순으로 정렬
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(orderCond != null)
			paramMap.put("orderCond", orderCond);
		else
			paramMap.put("orderCond", "MODIFIED_TIME");
		
		// 목록 요청
		try {
			List<GuestbookArticle> articles = guestbookArticleDao.getList(paramMap);
			model.addAttribute("articles" , articles);
		} catch (Exception e) {
			model.addAttribute("error", "");
			return "Error";
		}
		
		return "List";
	}
	
	// list POST 요청시 새 정보 입력, orderCond로 목록 정렬 순서 지정, email, pwd, article은 입력 될 새 정보
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public String executePOST(
			@RequestParam(value="orderCond", required=false) String orderCond,
			@RequestParam(value="email", required=true) String email,
			@RequestParam(value="pwd", required=true) String pwd,
			@RequestParam(value="article", required=true) String article,
			Model model) throws Exception {
		
		// 이메일 유효성 확인
		if(Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim()) == false) {
			model.addAttribute("error", "이메일주소가 유효하지 않습니다.");
			return "Error";
		}

		// 입력할 새 객체 작성
		GuestbookArticle guestbookArticle = new GuestbookArticle();
		guestbookArticle.setEmail(email);
		guestbookArticle.setPwd(pwd);
		guestbookArticle.setArticle(article);
		model.addAttribute("guestbookArticle", guestbookArticle);
		
		// 입력 요청 
		try {
			model.addAttribute("QueryResult", guestbookArticleDao.insertArticle(guestbookArticle));
		} catch (Exception e) {
			model.addAttribute("error", "데이터를 입력하는데 문제가 발생하였습니다. 입력이 올바른지 학인해 주세요.");
			return "Error";
		}
		
		// 목록 출력을 위해 GET 요청 처리
		return executeGET(orderCond, model);
	}

}
