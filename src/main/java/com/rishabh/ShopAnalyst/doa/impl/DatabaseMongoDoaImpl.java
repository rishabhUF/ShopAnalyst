package com.rishabh.ShopAnalyst.doa.impl;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.rishabh.ShopAnalyst.constants.Constants;
import com.rishabh.ShopAnalyst.doa.CustomeMongoDao;
import com.rishabh.ShopAnalyst.doa.DatabaseMangoDoa;
import com.rishabh.ShopAnalyst.domains.Member;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMongoDoaImpl implements DatabaseMangoDoa {

    @Autowired
    CustomeMongoDao customeMongoDao;
    @Override
    public String getDatabaseNames() {
        String result = "";
        MongoIterable<String> databases = customeMongoDao.getDatabasesFromServer();
        for(String db : databases)
        {
            result = result + db.toUpperCase() + "( "+db+" )";
            result = result + '\n';
        }
        return result;
    }

    @Override
    public String insertData() {
        MongoCollection<Document> collection = customeMongoDao.getCollectionFromDatabase(Constants.DB_NAME,Constants.COLL_NAME);
        collection.drop();
        int count = loadDataInMongo(collection);
        return "Inserted " + count + "records in mongo db" + "\nDB_NAME = " + Constants.DB_NAME + "\nCOLLECTION = " + Constants.COLL_NAME;
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

    int loadDataInMongo(MongoCollection<Document> collection)
    {
        int count = 0;
        try {
            JsonReader reader = new JsonReader(new FileReader(Constants.JSON_FILE_LOCATION));
            // Reading the array from the json file
            reader.beginArray();
            while(reader.hasNext())
            {
                Document document = readerMessage(reader);
                collection.insertOne(document);
                count+=1;
            }
            reader.endArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private Document readerMessage(JsonReader reader) throws IOException {
        Document obj = new Document();
        //BasicDBObject obj = new BasicDBObject();
        // reading the object from the json object.
        reader.beginObject();

        while(reader.hasNext())
        {
            String name = reader.nextName();
            if(name.equals(Constants.ID)){
                obj.append(Constants.ID, reader.nextString());
            }
            else if(name.equals(Constants.DOB)){
                obj.append(Constants.DOB, reader.nextString());
            }else if (name.equals(Constants.CAPTION)) {
                obj.append(Constants.CAPTION, reader.nextString());
            } else if (name.equals(Constants.ETHNICITY)) {
                String ethnicity = reader.nextString();
                if (ethnicity.length() > 1) {
                    obj.append(Constants.ETHNICITY, new Integer(-1));
                } else {
                    obj.append(Constants.ETHNICITY, new Integer(ethnicity));
                }

            } else if (name.equals(Constants.WEIGHT)) {
                obj.append(Constants.WEIGHT, new Integer(reader.nextString())/Constants.GRAMS_TO_KGS);
            } else if (name.equals(Constants.HEIGHT)) {
                obj.append(Constants.HEIGHT, new Integer(reader.nextString()));
            } else if (name.equals(Constants.isVEG)) {
                obj.append(Constants.isVEG, new Boolean(reader.nextString()));
            } else if (name.equals(Constants.isDRINK)) {
                obj.append(Constants.isDRINK,  new Boolean(reader.nextString()));
            }

        }
        //end of the object
        reader.endObject();
        return obj;
    }
}
