package com.rishabh.ShopAnalyst.service.impl;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rishabh.ShopAnalyst.constants.Constants;
import com.rishabh.ShopAnalyst.service.DatabaseService;
import com.rishabh.ShopAnalyst.util.Jsonreader;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;


@Service
public class DatabaseServiceImpl implements DatabaseService {
    @Override
    public String getDatabaseNames() {
        MongoClient client = null;
        try{
            client = new MongoClient("localHost", 27017);
            List<String> databases = (List<String>) client.listDatabaseNames();
            String result = "";
            for(String db : databases)
            {
                result = result + db.toUpperCase() + "( "+db+" ) :";
                result = result + '\n';
            }
            return result;
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }


    @Override
    public String insertData() {
        MongoClient client = null;
        try{
            client = new MongoClient("localHost", 27017);
            MongoDatabase db = client.getDatabase(Constants.DB_NAME);

            // First always drop the collections.
            MongoCollection<Document> collection = db.getCollection(Constants.COLL_NAME);
            collection.drop();

            // Now insert the data from Json
            Jsonreader jsonreader = new Jsonreader(collection);
            int count = jsonreader.loadDataInMongo();
            return "Inserted " + count + "records in mongo db" + "\nDB_NAME = " + Constants.DB_NAME + "\nCOLLECTION = " + Constants.COLL_NAME ;
        } finally {
            if (client != null) {
                client.close();
            }
    }

    @Override
    public String displayData() {

            MongoClient client = null;
            List<Member> memberData = new ArrayList<Member>();
            try {
                client = new MongoClient("localhost", 27017);

        }
        return null;
    }
}
