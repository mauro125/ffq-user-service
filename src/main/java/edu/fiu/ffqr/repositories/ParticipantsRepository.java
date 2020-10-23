package edu.fiu.ffqr.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import edu.fiu.ffqr.models.Participants;
import edu.fiu.ffqr.models.Clinician;

@Repository
public interface ParticipantsRepository extends MongoRepository<Participants, String> {

    Participants getUserBy_id(ObjectId _id);

    Participants getByUserId(String userId);
	
    Participants findByUsername(String username);

    Participants findByAssignedResearcherInst(String assignedResearcherInst);   
   
    
}