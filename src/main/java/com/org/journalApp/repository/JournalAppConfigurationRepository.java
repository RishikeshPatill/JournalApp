package com.org.journalApp.repository;

import com.org.journalApp.entity.JournalAppConfigurationEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface JournalAppConfigurationRepository extends MongoRepository<JournalAppConfigurationEntity, ObjectId> {

}
