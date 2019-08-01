package com.hyl.core.service;

import cn.hutool.db.nosql.mongo.MongoDS;
import cn.hutool.db.nosql.mongo.MongoFactory;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public class MongoDBService {
    private static final Logger logger = LoggerFactory.getLogger(MongoDBService.class);

    @PostConstruct
    public void init() {
        try {
            MongoDS mongoDS = MongoFactory.getDS("127.0.0.1", 27017);
            MongoClient mongoClient = mongoDS.getMongo();

        } catch (Exception e) {
            logger.error("mongoClient:UnknownHostException", e);
        }
    }
}
