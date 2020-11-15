package edu.fiu.ffqr.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import edu.fiu.ffqr.models.Admin;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

  Admin getUserBy_id(ObjectId _id);

  Admin getByUserId(String userId);
	
  Admin findByUsername(String username);

 // List<User> getAdmins(boolean isAdmin);
  
    
}