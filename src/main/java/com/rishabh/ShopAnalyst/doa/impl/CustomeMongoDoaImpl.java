package com.rishabh.ShopAnalyst.doa.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.rishabh.ShopAnalyst.constants.Constants;
import com.rishabh.ShopAnalyst.doa.CustomeMongoDao;

import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public class CustomeMongoDoaImpl implements CustomeMongoDao {

   @Override
   public MongoIterable<String> getDatabasesFromServer() {
        MongoClient client = null;
        try {
            client = new MongoClient("localHost", 27017);
            MongoIterable<String> databases = client.listDatabaseNames();
            return databases;
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    @Override
    public  MongoCollection<Document> getCollectionFromDatabase(String databaseName, String columnName)
    {
        MongoClient client = null;
        client = new MongoClient("localHost", 27017);
        MongoDatabase db = client.getDatabase(Constants.DB_NAME);

        // First always drop the collections.
        MongoCollection<Document> collection = db.getCollection(Constants.COLL_NAME);
        return collection;
    }




}
