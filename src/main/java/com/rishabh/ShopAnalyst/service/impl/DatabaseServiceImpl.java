package com.rishabh.ShopAnalyst.service.impl;

import com.rishabh.ShopAnalyst.doa.DatabaseMangoDoa;
import com.rishabh.ShopAnalyst.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    DatabaseMangoDoa mongoDao;

    @Override
    public String getDatabaseNames() {
        String result = "";
        result = mongoDao.getDatabaseNames();
        if(result.isEmpty())
            result = "OOPS !! sorry, There are no databases present to show";
        return result;
    }


    @Override
    public String insertData() {

        String result = "";
        result = mongoDao.insertData();
        if(result.isEmpty())
            return "Data is not inserted property";
        return result;
    }

    @Override
    public String displayData() {

        String result = "";
        result = mongoDao.displayData();
        if(result.isEmpty())
            return "There is no data store in the DB";
        return result;
    }
}
