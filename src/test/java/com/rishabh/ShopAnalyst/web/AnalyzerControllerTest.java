package com.rishabh.ShopAnalyst.web;

import com.rishabh.ShopAnalyst.service.AnalyzeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class AnalyzerControllerTest {

    private MockMvc mockMvc;

    @Mock
    AnalyzeService analyzeService;

    @InjectMocks
    AnalyzeController analyzeController;

    @Before
    public void init()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(analyzeController).build();
    }

    @Test
    public void testFullTextSearch() throws Exception
    {
        String query = "to";
        String page = "10";
        when(analyzeService.textSearch(query,page)).thenReturn("this is the string to returned");
        mockMvc.perform(get("/service/search_caption")
                .param("query","to")
                .param("page","10")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                .andDo(print());
    }

    @Test
    public void testFullTextSearchNoValuePassedForQuery() throws Exception
    {
        String page = "10";
        when(analyzeService.textSearch("",page)).thenReturn("");
        mockMvc.perform(get("/service/search_caption")
        .param("page", "10"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testTextFullSearchNoValuePassedForPage() throws Exception{
        String query = "to";
        when(analyzeService.textSearch(query,"")).thenReturn("this is the string to be returned");
        mockMvc.perform(get("/service/search_caption")
        .param("query", "to"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testCount() throws Exception{
        when(analyzeService.count()).thenReturn("100");
        mockMvc.perform(get("/service/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("100"));
    }

    @Test
    public void testAverage() throws Exception{
        String param = "British";
        when(analyzeService.calculateAverage(param)).thenReturn("the average is calculated");
        mockMvc.perform(get("/service/{param}/averages", param))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                .andDo(print());
    }

    @Test
    public void testAverageNoParamValue() throws Exception{
        String param ="";
        when(analyzeService.calculateAverage(param)).thenReturn("");
        mockMvc.perform(get("/service/{param}/averages", param))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSocialHabits() throws Exception {
        String veg = "0";
        String drink = "1";
        when(analyzeService.getSocialHabits(veg,drink)).thenReturn("");
        mockMvc.perform(get("/service/social_habits")
                .param("veg", "0")
                .param("drink","1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testSocialHabitsNoValueForVeg() throws Exception {
        String drink = "1";
        String veg = "1";
        when(analyzeService.getSocialHabits(veg,drink)).thenReturn("");
        mockMvc.perform(get("/service/social_habits")
//
                .param("drink","1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testSocialHabitsNoValueForDrink() throws Exception {
        String veg = "0";
        String drink = "1";
        when(analyzeService.getSocialHabits(veg,drink)).thenReturn("");
        mockMvc.perform(get("/service/social_habits")
                .param("veg", "0")
                .param("drink","1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
 }


