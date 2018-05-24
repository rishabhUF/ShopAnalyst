package com.rishabh.ShopAnalyst.service;

public interface AnalyzeService {
    String textSearch(String query, String pageNumber);

    String count();

    String calculateAverage(String param);

    String getSocialHabits(int veg, int drink);
}
