package com.rishabh.ShopAnalyst.doa;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

public interface CustomeMongoDao {
    MongoIterable<String> getDatabasesFromServer();
    MongoCollection<Document> getCollectionFromDatabase(String databaseName, String columnName);
}
