package com.integrationtesting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class AddRequestControllerIntegrationTest {

    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testResourceDetails() throws Exception {
        mockMvc.perform(get("/ResourceDetails"))
                .andExpect(status().isOk())
                .andExpect(view().name("resource-details")).andExpect(model().attributeExists("resourceDetailsView"));
    }

    @Test
    public void testProjectDetails() throws Exception {
        // ResourceDetailsView resourceDetailsView=mockResourceDetailsView();
        mockMvc.perform(get("/ProjectDetails").param("obtid", "555"))
                .andExpect(status().isOk())
                .andExpect(view().name("project-details")).andExpect(model().attributeExists("projectDetailsView"));
    }

    /*  private ResourceDetailsView mockResourceDetailsView(ResourceDetailsView resourceDetailsView) {

          resourceDetailsView.setObtid("55699");
          resourceDetailsView.setType("aaa");
          resourceDetailsView.setTechnology("ffff");
          resourceDetailsView.setRole("yyy");
          resourceDetailsView.setLevel("gggg");
          resourceDetailsView.setRdate("rrrr");
          resourceDetailsView.setResponsibility("llll");
          resourceDetailsView.setRmonth("xxxx");
          return resourceDetailsView;  //To change body of created methods use File | Settings | File Templates.
      }
  **/
    @Test
    public void testResourceDetailsBack() throws Exception {
        mockMvc.perform(get("/ResourceDetails"))
                .andExpect(status().isOk())
                .andExpect(view().name("resource-details")).andExpect(model().attributeExists("resourceDetailsView"));
    }

    @Test
    public void testAddRequestSuccess() throws Exception {
        mockMvc.perform(post("/Success").param("obtid", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-request-success"));
    }
}
