package com.rishabh.ShopAnalyst.web;

import com.rishabh.ShopAnalyst.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/database")
public class DatabaseController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value ="/names", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getMongoDBNames(){
        return databaseService.getDatabaseNames();
    }

    @RequestMapping(value = "/insertData" , method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String insertData(){
        return databaseService.insertData();
    }

    @RequestMapping(value = "/displayData", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String displayData(){
        return databaseService.displayData();
    }
}
