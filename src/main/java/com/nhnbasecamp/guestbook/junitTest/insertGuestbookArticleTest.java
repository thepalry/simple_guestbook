package com.nhnbasecamp.guestbook.junitTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nhnbasecamp.guestbook.control.ListController;
import com.nhnbasecamp.guestbook.dao.GuestbookArticleDao;

// test를 위해 root-context가 아닌 test-context을 사용한다.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
     "file:src/main/webapp/WEB-INF/spring/test-context.xml"})
@WebAppConfiguration
public class insertGuestbookArticleTest {
	// Dao 객체 주입
    @Mock
    GuestbookArticleDao guestbookArticleDao;
    @InjectMocks
    private ListController listController;
    
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    // 테스트 준비
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
         mockMvc = MockMvcBuilders.standaloneSetup(listController).build();
    }

    // 이메일, 비밀번호, 본문을 입력 받아 디비에 추가 테스트
    @Test
    public void testInsertGuestbookArticle() throws Exception {
    	
    	// 입력할 내용
    	String orderCond = null;
    	String email = "tes1t@test.com";
    	String pwd = "1234";
    	String article = "test1234";
    	
    	// 입력할 내용 요청
    	mockMvc.perform(post("/list")
    			.param("orderCond", orderCond)
    			.param("email", email)
    			.param("pwd", pwd)
    			.param("article", article))
    	.andExpect(status().isOk())
    	.andExpect(model().attributeExists("guestbookArticle"))	// 일치 확인
    	.andExpect(model().attribute("guestbookArticle", hasProperty("email", is(email))))
    	.andExpect(model().attribute("guestbookArticle", hasProperty("pwd", is(pwd))))
    	.andExpect(model().attribute("guestbookArticle", hasProperty("article", is(article))))
    	.andExpect(model().attribute("QueryResult", 0));	// 쿼리 결과 확인
    }
    
    @Test
    public void testEmailErrorChecking() throws Exception {
    	
    	// 입력할 내용, 잘못된 이메일 형태
    	String orderCond = null;
    	String email = "tes1t";
    	String pwd = "1234";
    	String article = "test1234";
    	
    	mockMvc.perform(post("/list")
    			.param("orderCond", orderCond)
    			.param("email", email)
    			.param("pwd", pwd)
    			.param("article", article))
    	.andExpect(status().isOk())
    	.andExpect(model().attributeExists("error"))	// 이메일 검사 오류 확인
    	.andExpect(model().attribute("error", "이메일주소가 유효하지 않습니다."));
    }
}
