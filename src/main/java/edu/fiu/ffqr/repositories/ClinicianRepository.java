package edu.fiu.ffqr.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import edu.fiu.ffqr.models.Clinician;

@Repository
public interface ClinicianRepository extends MongoRepository<Clinician, String> {


  Clinician getUserBy_id(ObjectId _id);

  Clinician getByUserId(String userId);
	
  Clinician findByUsername(String username);
  
  Clinician getByAbbreviation(String abbreviation);
    
}