package com.hyl.core.service;

import cn.hutool.db.nosql.mongo.MongoDS;
import cn.hutool.db.nosql.mongo.MongoFactory;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public class MongoDBService {
    private static final Logger logger = LoggerFactory.getLogger(MongoDBService.class);

    @PostConstruct
    public void init() {
        try {
            MongoDS mongoDS = MongoFactory.getDS("127.0.0.1", 27017);
            DB db = mongoClient.getDB(dataBase);
            gridFS = new GridFS(db);
        } catch (Exception e) {
            logger.error("mongoClient:UnknownHostException", e);
        }
    }
}
