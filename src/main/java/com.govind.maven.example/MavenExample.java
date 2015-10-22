package com.govind.maven.example;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by govindp on 8/14/2015.
 */
public class MavenExample {
    public static void main(String[] args) {
        String example = "moto g3";
        System.out.printf(example.replaceAll("\\s+","+"));
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        MongoDatabase mongoDatabase = mongoClient.getDatabase("school").withReadPreference(ReadPreference.secondary());
        MongoCollection<Document> collection  = mongoDatabase.getCollection("students");
        System.out.println(collection.find(eq("_id", 0)).first());

     /*   Document govind= new Document().append("name", "Govind")
                .append("age",22)
                .append("profession", "engineer");
        Document krishna = new Document().append("name", "Krishna")
                .append("age",22)
                .append("profession","engineer");

        govind.remove("_id");
        collection.insertMany(Arrays.asList(govind, krishna));
     */
        ArrayList<Document> students =collection.find().into(new ArrayList<Document>());
        for(int i = 0; i < students.size(); i++) {
            Document newDocument = students.get(i);
            ArrayList<Document> arrayList= (ArrayList) newDocument.get("scores");
            double min = Double.MAX_VALUE;
            int minIndex = -1;
            double score = 0;
            for(Document document :arrayList){
                if(document.get("type").equals("homework")){
                    //System.out.println(document);
                    score  = (double) document.get("score");

                    if(min > score){
                        min = score;
                        minIndex = arrayList.indexOf(document);
                    }
                }

            }
            arrayList.remove(minIndex);
            newDocument.put("scores", arrayList);
            collection.updateOne(eq("_id",i),new Document("$set",new Document("scores",arrayList)));
            //System.out.println(newDocument.get("scores"));
            //System.out.println(collection.updateOne(){"_id":String.valueOf(i)}));
            //collection.deleteOne(eq("_id", i));
        }

    }
}
