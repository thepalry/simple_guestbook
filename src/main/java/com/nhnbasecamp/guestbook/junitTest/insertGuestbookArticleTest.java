package com.nhnbasecamp.guestbook.junitTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.nhnbasecamp.guestbook.vo.GuestbookArticle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
     "file:src/main/webapp/WEB-INF/spring/test-context.xml"})
@WebAppConfiguration
public class insertGuestbookArticleTest {
    @Mock
    GuestbookArticleDao guestbookArticleDao;
    @InjectMocks
    private ListController listController;
    
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
         mockMvc = MockMvcBuilders.standaloneSetup(listController).build();
    }

    @Test
    public void testInsertGuestbookArticle() throws Exception {
    	String orderCond = null;
    	String email = "test34@test.com";
    	String pwd = "1234";
    	String article = "test1234";
    	GuestbookArticle guestbookArticle = new GuestbookArticle();
    	guestbookArticle.setEmail(email);
    	guestbookArticle.setPwd(pwd);
    	guestbookArticle.setArticle(article);
    	
    	when(guestbookArticleDao.insertArticle(guestbookArticle)).thenReturn(-1);
    	mockMvc.perform(post("/list")
    			.param("orderCond", orderCond)
    			.param("email", email)
    			.param("pwd", pwd)
    			.param("article", article))
    	.andExpect(status().isOk());
    }
}
