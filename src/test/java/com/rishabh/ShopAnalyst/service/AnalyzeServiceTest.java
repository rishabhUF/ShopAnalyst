package com.rishabh.ShopAnalyst.service;

import com.google.gson.Gson;
import com.rishabh.ShopAnalyst.doa.AnalyzeMongoDoa;
import com.rishabh.ShopAnalyst.doa.CustomeMongoDao;
import com.rishabh.ShopAnalyst.domains.Member;
import com.rishabh.ShopAnalyst.service.impl.AnalyzeServiceImpl;
import com.rishabh.ShopAnalyst.service.impl.DatabaseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class AnalyzeServiceTest {
    @Mock
    AnalyzeMongoDoa mongoDao;

    @InjectMocks
    private AnalyzeService analyzeService = new AnalyzeServiceImpl();

    @Test
    public void testTextSearch(){
        String query = "to";
        String page = "10";
        List<Member> memberList = getMemberList();
        String expected = getStringFromList(memberList);
        when(mongoDao.getSearchResult(query,page)).thenReturn(memberList);
        assertThat(analyzeService.textSearch("to","10"),is(expected));
        verify(mongoDao,times(1)).getSearchResult(query,page);
    }

    @Test
    public void testTextSeacrhEmptyList(){
        String query ="to";
        String page = "10";
        List<Member> memberList = null;
        when(mongoDao.getSearchResult(query,page)).thenReturn(memberList);
        String expected = getStringWhenNull();
        assertThat(analyzeService.textSearch("to","10"),is(expected));
        verify(mongoDao,times(1)).getSearchResult(query,page);
    }

    @Test
    public void testTextSearchSizeZero(){
        String query ="to";
        String page = "10";
        List<Member> memberList = new ArrayList<>();
        when(mongoDao.getSearchResult(query,page)).thenReturn(memberList);
        String expected = getStringWhenNull();
        assertThat(analyzeService.textSearch("to","10"),is(expected));
        verify(mongoDao,times(1)).getSearchResult(query,page);
    }


    private String getStringWhenNull(){
        final Member member = new Member();
        List<Member> memberList = new ArrayList<>();
        member.setErrorMessage("Error in finding the query. Check the parameter again.");
        memberList.add(member);
        return getStringFromList(memberList);

    }

    private String getStringWhenNull(){
        final Member member = new Member();
        List<Member> memberList = new ArrayList<>();
        member.setErrorMessage("Error in finding the query. Check the parameter again.");
        memberList.add(member);
        return getStringFromList(memberList);

    }
    private String getStringFromList(List<Member> memberList) {
        Gson gson = new Gson();
        return gson.toJson(memberList);
    }

    private List<Member> getMemberList() {
        List<Member> memberList = new ArrayList<>();

        Member a = new Member();
        a.setWeight(100);
        a.setCaption("This is A");
        a.setDob("10/12/1993");
        a.setEthnicity(2);
        a.setHeight(161);
        a.setId(121212);
        a.setIs_drink(true);
        a.setIs_veg(false);
        memberList.add(a);

        Member x = new Member();
        x.setWeight(100);
        x.setCaption("This is A");
        x.setDob("10/12/1993");
        x.setEthnicity(2);
        x.setHeight(161);
        x.setId(121212);
        x.setIs_drink(true);
        x.setIs_veg(false);
        memberList.add(x);

        Member b = new Member();
        b.setWeight(100);
        b.setCaption("This is B");
        b.setDob("10/12/1995");
        b.setEthnicity(2);
        b.setHeight(161);
        b.setId(121212);
        b.setIs_drink(true);
        b.setIs_veg(false);
        memberList.add(b);

        Member c = new Member();
        c.setWeight(100);
        c.setCaption("This is A");
        c.setDob("10/12/1993");
        c.setEthnicity(2);
        c.setHeight(161);
        c.setId(121212);
        c.setIs_drink(true);
        c.setIs_veg(false);
        memberList.add(c);

        Member d = new Member();
        d.setWeight(100);
        d.setCaption("This is A");
        d.setDob("10/12/1993");
        d.setEthnicity(2);
        d.setHeight(161);
        d.setId(121212);
        d.setIs_drink(true);
        d.setIs_veg(false);
        memberList.add(d);

        Member e = new Member();
        e.setWeight(100);
        e.setCaption("This is A");
        e.setDob("10/12/1993");
        e.setEthnicity(2);
        e.setHeight(161);
        e.setId(121212);
        e.setIs_drink(true);
        e.setIs_veg(false);
        memberList.add(e);

        Member f = new Member();
        f.setWeight(100);
        f.setCaption("This is A");
        f.setDob("10/12/1993");
        f.setEthnicity(2);
        f.setHeight(161);
        f.setId(121212);
        f.setIs_drink(true);
        f.setIs_veg(false);
        memberList.add(f);

        return memberList;
    }
}
