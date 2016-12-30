package com.nhnbasecamp.guestbook.junitTest;

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
    	mockMvc.perform(post("/list")
    			.param("orderCond", (String) null)
    			.param("email", "test34@test.com")
    			.param("pwd", "1234")
    			.param("article", "test1234"))
    	.andExpect(status().isOk())
    	.andExpect(model().attributeExists("insertResult"));
    }
}
