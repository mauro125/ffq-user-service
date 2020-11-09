package edu.fiu.ffqr.service;

import java.util.List;

import edu.fiu.ffqr.models.Admin;
import edu.fiu.ffqr.repositories.AdminRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.fiu.ffqr.models.Participants;
import edu.fiu.ffqr.repositories.ParticipantsRepository;

@Service
@Component
public class ParticipantsService extends UserService<Participants, ParticipantsRepository> {

	@Autowired
	public ParticipantsService(ParticipantsRepository repository) {
		this.repository = repository;
	}

	public List<Participants> getAll() {
		return repository.findAll();
	}

	public Participants getParticipantByUsername(String username) {
		return repository.findByUsername(username);
	}

	public Participants getParticipantBy_id(ObjectId _id) {
		return repository.getUserBy_id(_id);
	}

	public Participants getParticipantByUserId(String userId) {
		return repository.getByUserId(userId);
	}

	public List<Participants> getParticipantByAssignedResearcher(String assignedResearcherInst) {
		return repository.findByAssignedResearcherInst(assignedResearcherInst);
	}

	public void delete(String userName) {
		Participants fi = repository.findByUsername(userName);
		repository.delete(fi);
	}

	public void deleteById(String userId) {
		Participants fi = repository.getByUserId(userId);
		repository.delete(fi);
	}

}
