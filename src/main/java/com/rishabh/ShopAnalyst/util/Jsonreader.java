package com.rishabh.ShopAnalyst.util;

import com.google.gson.stream.JsonReader;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import com.rishabh.ShopAnalyst.constants.Constants;
import org.bson.Document;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Jsonreader {
    MongoCollection<Document> collection;

    public Jsonreader(MongoCollection<Document> collection)
    {
        this.collection = collection;
    }

    public int loadDataInMongo() {
        int count = 0;

        try {
            JsonReader reader = new JsonReader(new FileReader(Constants.JSON_FILE_LOCATION));
            // Reading the array from the json file
            reader.beginArray();
            while(reader.hasNext())
            {
                Document document = readerMessage(reader);
                this.collection.insertOne(document);
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

    private Document readerMessage(JsonReader reader) throws IOException{
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
