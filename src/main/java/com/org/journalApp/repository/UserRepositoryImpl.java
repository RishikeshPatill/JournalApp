package com.org.journalApp.repository;

import com.org.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.schema.JsonSchemaObject;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

//  query.addCriteria(Criteria.where("username").is("hardik"));
//  query.addCriteria(Criteria.where("anyField").ne("value"));
//  query.addCriteria(Criteria.where("anyField").lt("value"));

//  query.addCriteria(criteria.orOperator(
//  Criteria.where("email").exists(true),
//  Criteria.where("sentimentAnalysis").is(true)));

//  query.addCriteria(Criteria.where("username").nin("Rajat","Msd")); //just an example that we can use with array as well
//  query.addCriteria(Criteria.where("sentimentAnalysis").type(JsonSchemaObject.Type.BsonType.BOOLEAN); // we can play with the return type as well

    public List<User> getUserForSentimentAnalysis(){
        Query query=new Query();
        Criteria criteria=new Criteria();
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;


    }
}
