package com.rishabh.ShopAnalyst.doa.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.rishabh.ShopAnalyst.constants.Constants;
import com.rishabh.ShopAnalyst.doa.AnalyzeMongoDoa;
import com.rishabh.ShopAnalyst.doa.CustomeMongoDao;
import com.rishabh.ShopAnalyst.domains.Member;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class AnalzeMongoDoaImpl implements AnalyzeMongoDoa {

    @Autowired
    CustomeMongoDao customeMongoDao;


    @Override
    public List<Member> getSearchResult(String query, String pageNumber) {
        final List<Member> memberData = new ArrayList<Member>();
        MongoCollection<Document> collection = customeMongoDao.getCollectionFromDatabase(Constants.DB_NAME,Constants.COLL_NAME);
        Document regexQuery = new Document();
        regexQuery.put(Constants.CAPTION, new Document("$regex",query).append("$options","i"));
        FindIterable<Document> cursorIterator = null;
        if(new Integer(pageNumber) > 0){
            final int endIndex = new Integer(pageNumber) * Constants.PAGE_SIZE;
            final int startIndex = endIndex - Constants.PAGE_SIZE;
            cursorIterator = collection.find(regexQuery).limit(Constants.PAGE_SIZE).skip(startIndex);
        }
        else{
            cursorIterator = collection.find(regexQuery);
        }

        if(cursorIterator != null){
            for(Document item : cursorIterator){
                final Member member = Member.getMemberFromMongo(item);
                memberData.add(member);
            }
        }
        return memberData;
    }
}
