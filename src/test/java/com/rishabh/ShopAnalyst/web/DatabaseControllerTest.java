package com.rishabh.ShopAnalyst.web;

import com.rishabh.ShopAnalyst.service.DatabaseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseControllerTest {

    private MockMvc mockMvc;

    @Mock
    DatabaseService databaseService;
    @InjectMocks
    DatabaseController databaseController;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(databaseController).build();
    }

    @Test
    public void testGetDatabasesName() throws Exception {
        when(databaseService.getDatabaseNames()).thenReturn("Admin");
        mockMvc.perform(get("/database/names"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(content().string("Admin"));

    }

    @Test
    public void testInsertData() throws Exception {
        when(databaseService.insertData()).thenReturn("inserted");
        mockMvc.perform(post("/database/insertData"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDisplayData() throws Exception {
        when(databaseService.displayData()).thenReturn("string is returned");
        mockMvc.perform(get("/database/displayData"))
                .andExpect(status().isOk());

    }
}
