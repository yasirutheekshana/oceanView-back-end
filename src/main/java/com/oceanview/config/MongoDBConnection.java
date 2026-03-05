package com.oceanview.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static final String CONNECTION_STRING =
            "mongodb+srv://yasirutheekshanadev_db_user:MkAB7Lmj1vOTN5lg@cluster0.dqhzccl.mongodb.net/?appName=Cluster0";

    private static final String DATABASE_NAME = "oceanView";

    private static final MongoClient mongoClient;

    static {

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                .serverApi(serverApi)
                .build();

        mongoClient = MongoClients.create(settings);

        System.out.println("MongoDB Connected Successfully ✅");
    }

    public static MongoDatabase getDatabase() {
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}
