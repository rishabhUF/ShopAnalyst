package com.rishabh.ShopAnalyst.service.impl;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.rishabh.ShopAnalyst.constants.Constants;
import com.rishabh.ShopAnalyst.doa.CustomeMongoDao;
import com.rishabh.ShopAnalyst.domains.Member;
import com.rishabh.ShopAnalyst.service.DatabaseService;
import com.rishabh.ShopAnalyst.util.Jsonreader;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    CustomeMongoDao mongoDao;
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
        MongoClient client = null;
        try {
            client = new MongoClient("localHost", 27017);
            MongoDatabase db = client.getDatabase(Constants.DB_NAME);

            // First always drop the collections.
            MongoCollection<Document> collection = db.getCollection(Constants.COLL_NAME);
            collection.drop();

            // Now insert the data from Json
            Jsonreader jsonreader = new Jsonreader(collection);
            int count = jsonreader.loadDataInMongo();
            return "Inserted " + count + "records in mongo db" + "\nDB_NAME = " + Constants.DB_NAME + "\nCOLLECTION = " + Constants.COLL_NAME;
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    @Override
    public String displayData() {

            MongoClient client = null;
            List<Member> memberData = new ArrayList<Member>();
            try {
                client = new MongoClient("localhost", 27017);
                MongoDatabase db = client.getDatabase(Constants.DB_NAME);
                FindIterable<Document> cursorIterator = db.getCollection(Constants.COLL_NAME).find();
                for (Document cursor : cursorIterator) {
                    final Member member = Member.getMemberFromMongo(cursor);
                    memberData.add(member);
                }
            }finally {
                if (client != null)
                    client.close();
            }
        if (memberData.size() == 0) {
            final Member member = new Member();
            member.setErrorMessage("No element found. Empty Collection");
            memberData.add(member);
        }
        Gson gson = new Gson();
        return gson.toJson(memberData);
    }
}
