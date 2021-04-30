package com.github.sekkycodes.softwarequest.repositories;

import com.github.sekkycodes.softwarequest.domain.entities.Quest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Mongo Repository for Quest Aggregates
 */
@Repository
public interface QuestRepository extends MongoRepository<Quest, ObjectId> {

}
