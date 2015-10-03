/*
 * Copyright (c) 2015. Copyright (c) 2015 Jonah Seguin (Shawckz).  All rights reserved.  You may not modify, decompile, distribute or use any code/text contained in this document(plugin) without explicit signed permission from Jonah Seguin.
 */

package com.shawckz.icheat.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.shawckz.icheat.configuration.Configuration;
import com.shawckz.icheat.configuration.annotations.ConfigData;

import java.util.Arrays;

import org.bukkit.plugin.Plugin;


/**
 * The DBManager class
 * Used to handle the connection the MongoClient.
 * The setup method should not be touched except in the PureCore onEnable
 */
public class DBManager extends Configuration {

    private static MongoClient mongoClient;
    private static MongoDatabase db;


    @ConfigData("database.name")
    private static String databaseName = "xxx";
    @ConfigData("database.authName")
    private static String authDatabaseName = "xxx";
    @ConfigData("database.host")
    private static String host = "xxx";
    @ConfigData("database.port")
    private static int port = 3309;
    @ConfigData("database.credentials.username")
    private static String username = "xxx";
    @ConfigData("database.credentials.password")
    private static String password = "xxx";
    @ConfigData("database.useAuth")
    private boolean auth = false;


    public DBManager(Plugin plugin) {
        super(plugin, "database.yml");
        load();
        save();
        setup();
    }

    private void setup() {

        if (auth) {
            MongoCredential credential = MongoCredential.createCredential(username, authDatabaseName, password.toCharArray());
            MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(50).build();
            mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential), options);
            db = mongoClient.getDatabase(databaseName);
        } else {
            mongoClient = new MongoClient(new ServerAddress(host, port));
            db = mongoClient.getDatabase(databaseName);
        }

    }

    public static MongoDatabase getDb() {
        return db;
    }

    public static void setDb(MongoDatabase db) {
        DBManager.db = db;
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static void setMongoClient(MongoClient mongoClient) {
        DBManager.mongoClient = mongoClient;
    }
}
