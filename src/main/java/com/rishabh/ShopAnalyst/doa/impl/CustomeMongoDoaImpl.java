package com.rishabh.ShopAnalyst.doa.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoIterable;
import com.rishabh.ShopAnalyst.doa.CustomeMongoDao;

public class CustomeMongoDoaImpl implements CustomeMongoDao {

    @Override
    public String getDatabaseNames() {
        String result = "";
        MongoIterable<String> databases = getDatabasesFromServer();
        for(String db : databases)
        {
            result = result + db.toUpperCase() + "( "+db+" ) :";
            result = result + '\n';
            // MongoDatabase dbs = client.getDatabase(db);
        }
        return result;
    }

    private MongoIterable<String> getDatabasesFromServer() {
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
}
