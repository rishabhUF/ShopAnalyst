package com.rishabh.ShopAnalyst.controller;

import com.rishabh.ShopAnalyst.service.AnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/service")
public class AnalyzeController {

    @Autowired
    AnalyzeService analyzeService;
    @RequestMapping(value ="/search_caption" ,method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String fullTextSearch(@RequestParam(value ="query", required = true)String query,
                                 @RequestParam(value ="page" , required = false, defaultValue = "0") String pageNumber)
    {
        return analyzeService.textSearch(query,pageNumber);
    }


    @RequestMapping(value ="/count", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String count()
    {
        return analyzeService.count();
    }

    @RequestMapping(value ="/{param}/averages", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String average(@PathVariable(value ="param") String param)
    {
        return analyzeService.calculateAverage(param);
    }

    @RequestMapping(value ="/social_habits", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String socialHabits(@RequestParam(value ="veg") int veg, @RequestParam(value ="drink") int drink)
    {
        return analyzeService.getSocialHabits(veg,drink);
    }

}
