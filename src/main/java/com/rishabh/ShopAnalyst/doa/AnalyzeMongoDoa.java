package com.rishabh.ShopAnalyst.doa;

import com.rishabh.ShopAnalyst.domains.Member;

import java.util.List;

public interface AnalyzeMongoDoa {

    public List<Member> getSearchResult(String query, String pageNumber);
}
