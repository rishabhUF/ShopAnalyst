package com.rishabh.ShopAnalyst.service.impl;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rishabh.ShopAnalyst.constants.Constants;
import com.rishabh.ShopAnalyst.doa.AnalyzeMongoDoa;
import com.rishabh.ShopAnalyst.doa.CustomeMongoDao;
import com.rishabh.ShopAnalyst.domains.Member;
import com.rishabh.ShopAnalyst.service.AnalyzeService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyzeServiceImpl implements AnalyzeService {

    @Autowired
    AnalyzeMongoDoa mongoDao;

    @Override
    public String textSearch(String query, String pageNumber) {
        List<Member> memberData = mongoDao.getSearchResult(query,pageNumber);
        Gson gson = new Gson();
       if(memberData == null){
         final Member member = new Member();
           member.setErrorMessage("Error in finding the query. Check the parameter again.");
           List<Member> memberList = new ArrayList<>();
           memberList.add(member);
           return gson.toJson(memberList);
       }
        else if (memberData.size() == 0) {
            final Member member = new Member();
            member.setErrorMessage("No element found. Empty Collection");
            memberData.add(member);
        }
        return gson.toJson(memberData);
    }


    @Override
    public String count() {
        MongoClient client = null;
        int count = -1;
        try {
            client = new MongoClient("localhost", 27017);
            MongoDatabase db = client.getDatabase(Constants.DB_NAME);
            MongoCollection<Document> collection = db.getCollection(Constants.COLL_NAME);
            FindIterable<Document> cursorIterator = collection.find();
            for(Document item : cursorIterator){
                count+=1;
            }
        }finally {
            if (client != null)
                client.close();
        }
        return "Total number of Records = " + count + " \nFor full detail use the link /displayMongoData";
    }

    @Override
    public String calculateAverage(String param) {
        final int equivalentEthnicity = Constants.getEthinicityCode(param);
        String result = "";
        if (1 <= equivalentEthnicity && equivalentEthnicity <= 9) {

            MongoClient client = null;
            try {
                List<Member> memberData = new ArrayList<Member>();
                client = new MongoClient("localhost", 27017);
                MongoDatabase db = client.getDatabase(Constants.DB_NAME);
                MongoCollection<Document> collection = db.getCollection(Constants.COLL_NAME);
                Document equivalentQuery = new Document();
                equivalentQuery.put(Constants.ETHNICITY, new Document("$eq", param));
                FindIterable<Document> cursorIterator = collection.find(equivalentQuery);
                if (cursorIterator != null) {
                    for (Document item : cursorIterator) {
                        final Member member = Member.getMemberFromMongo(item);
                        memberData.add(member);
                    }
                }

                // Now get the average of the filtered data
                int listResultSize = memberData.size();
                if (listResultSize > 0) {
                    int heightSum = memberData.stream().mapToInt(o -> o.height).sum();
                    result = result + "Average height : " + heightSum / listResultSize;

                    int weightSum = memberData.stream().mapToInt(o -> o.weight).sum();
                    result += "\n Average weight : " + weightSum / listResultSize;
                }
                return result;
            } finally {
                if (client != null)
                    client.close();
            }
        }
        System.out.println(result);
        return result;
    }

    @Override
    public String getSocialHabits(String veg, String drink) {
        MongoClient client = null;
//        try {
//            List<Member> memberData = new ArrayList<Member>();
//            client = new MongoClient("localhost", 27017);
//            MongoDatabase db = client.getDatabase(Constants.DB_NAME);
//            MongoCollection<Document> collection = db.getCollection(Constants.COLL_NAME);
//            DBObject query1 = new BasicDBObject(Constants.isVEG, veg == 1);
//            DBObject query2 = new BasicDBObject(Constants.isDRINK, drink == 1);
//            BasicDBList condtionalOperator = new BasicDBList();
//            condtionalOperator.add(query1);
//            condtionalOperator.add(query2);
//            DBObject query = new BasicDBObject("$and", condtionalOperator);
//            FindIterable<Document> cursorIterator = collection.find(query);
            return null;
    }
}
