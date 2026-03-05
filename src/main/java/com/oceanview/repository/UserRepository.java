package com.oceanview.repository;

import com.oceanview.config.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.oceanview.model.User;
import org.bson.Document;

public class UserRepository {

    private final MongoCollection<Document> collection;

    public UserRepository() {
        collection = MongoDBConnection
                .getDatabase()
                .getCollection("users");
    }

    public void save(User user) {

        Document doc = new Document("username", user.getUsername())
                .append("password", user.getPassword());

        collection.insertOne(doc);
    }

    public User findByUsername(String username) {

        Document doc = collection
                .find(Filters.eq("username", username))
                .first();

        if (doc == null) return null;

        return new User(
                doc.getString("username"),
                doc.getString("password")
        );
    }
}
