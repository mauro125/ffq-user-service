package edu.fiu.ffqr.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import edu.fiu.ffqr.models.Admin;
import edu.fiu.ffqr.models.Research;

@Repository
public interface ResearchRepository extends MongoRepository<Research, String> {

  Research getUserBy_id(ObjectId _id);

  Research getByUserId(String userId);
	
  Research findByUsername(String username);
  
    
}