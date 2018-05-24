package com.rishabh.ShopAnalyst.controller;

import com.rishabh.ShopAnalyst.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value="/database")
public class DatabaseController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value ="/names", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getMongoDBNames(){
        return databaseService.getDatabaseNames();
    }

    @RequestMapping(value = "/insertData" , method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String insertData(){
        return databaseService.insertData();
    }

    @RequestMapping(value = "/displayData", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String displayData(){
        return databaseService.displayData();
    }
}
