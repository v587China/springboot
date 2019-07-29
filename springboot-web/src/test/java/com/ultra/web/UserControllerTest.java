package com.ultra.web;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplication
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSelectByIdLong() {
        fail("Not yet implemented");
    }

    @Test
    public void testSelectList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(""));
    }

    @Test
    public void testSelectPageIntIntStringBooleanString() {
        fail("Not yet implemented");
    }

    @Test
    public void testInsertUserErrors() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateByIdUserErrors() {
        fail("Not yet implemented");
    }

    @Test
    public void testDeleteBatchIds() {
        fail("Not yet implemented");
    }

}
