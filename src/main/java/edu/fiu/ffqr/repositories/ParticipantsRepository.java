package edu.fiu.ffqr.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import edu.fiu.ffqr.models.Participants;
import edu.fiu.ffqr.models.ResearchInstitution;

@Repository
public interface ParticipantsRepository extends UserRepository<Participants> {

    Participants getUserBy_id(ObjectId _id);

    Participants getByUserId(String userId);

    Participants findByUsername(String username);

    List<Participants> findByAssignedResearcherInst(String assignedResearcherInst);
}