package edu.fiu.ffqr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.fiu.ffqr.models.SysUser;
import edu.fiu.ffqr.models.Admin;
import edu.fiu.ffqr.models.Research;
import edu.fiu.ffqr.repositories.AdminRepository;
import edu.fiu.ffqr.repositories.ResearchRepository;

@Service
@Component
public class ResearchService {

	@Autowired
	private ResearchRepository researchRepository;
	
	public List<Research> getAll()	{
		return researchRepository.findAll();
	}
	
	public Research getUserByUsername(String username) {
		return researchRepository.findByUsername(username);
	}
	
	public Research getUserByUserId(String userId) {
		return researchRepository.getByUserId(userId);
	}
	
	public Research create(Research user) {
		return researchRepository.save(user);
	}
	
	public void delete(String userName) {
		Research fi = researchRepository.findByUsername(userName);
		researchRepository.delete(fi);
	}

	public Research getAdminBy_id(ObjectId _id) {
		return researchRepository.getUserBy_id(_id);
	}

	public void deleteById(String userId) {
		Research fi = researchRepository.getByUserId(userId);
		researchRepository.delete(fi);
	}
	/*public List<User> getAllAdmins(boolean admin)	{
		return usersRepository.getAdmins(admin);
	}*/
	
}
