package edu.fiu.ffqr.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.fiu.ffqr.FFQUserApplication;
import edu.fiu.ffqr.models.Authenticate;
import edu.fiu.ffqr.models.SysUser;
import edu.fiu.ffqr.repositories.AdminRepository;
import edu.fiu.ffqr.models.Admin;
import edu.fiu.ffqr.models.ResearchInstitution;
import edu.fiu.ffqr.repositories.ResearchInstitutionRepository;
import edu.fiu.ffqr.service.SysUserService;
import edu.fiu.ffqr.service.AdminService;
import edu.fiu.ffqr.service.ResearchInstitutionService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/research_institution")
public class ResearchInstitutionController {

    @Autowired
    private ResearchInstitutionService researchService;
    @Autowired
    private ResearchInstitutionRepository researchRepository;

    public ResearchInstitutionController() {
    }

    @GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public Authenticate validateLogin() {
		return new Authenticate("User successfully authenticated");
	}
    
    
    @GetMapping("/all")
    public List<ResearchInstitution> allUsers() throws JsonProcessingException {
        
        List<ResearchInstitution> users = researchService.getAll();
        return users;
    }  

    @GetMapping("/{institutionID}")
	public ResearchInstitution gUserApplication(@PathVariable("institutionID") String userID) {
		return researchService.getResearchInstitutionById(userID);
	}
    
    @PostMapping("/createInstitution")
    public ResearchInstitution createUser(@RequestBody ResearchInstitution user) throws JsonProcessingException {

      if (researchService.getUserByInstitutionName(user.GetInstitutionName()) != null) {
            throw new IllegalArgumentException("A research Institution with name " + user.GetInstitutionName() + " already exists");
      }  
	  return researchService.create(user);
	  
  }

  @PostMapping("/updateinstitution")
    public void updateUser(@RequestBody ResearchInstitution user) throws JsonProcessingException {
        
        if (researchService.getResearchInstitutionById(user.getResearchInstitutionId()) == null) {
            throw new IllegalArgumentException("A research Institution with name " + user.GetInstitutionName()+ " doesn't exist");
        }

        ResearchInstitution currentUser = researchService.getResearchInstitutionById(user.getResearchInstitutionId());

        currentUser.setAddress(user.getAddress());
        currentUser.setCreatedDate(user.getCreatedDate());
        currentUser.setInstitutionName(user.GetInstitutionName());
        currentUser.setSiteType(user.getSiteType());

        researchRepository.save(currentUser);
       
    }


    @PostMapping("/create")
    public ResearchInstitution create(@RequestBody ResearchInstitution item) throws JsonProcessingException {
        
        if (researchService.getUserByInstitutionName(item.GetInstitutionName()) != null) {
            throw new IllegalArgumentException("A research Institution with name " + item.GetInstitutionName()+ " already exists");
        }

        return researchService.create(item);
    }

    
    
   
	
	@PostMapping("/createMany")
	public ArrayList<ResearchInstitution> create(@RequestBody ArrayList<ResearchInstitution> users) {
		ResearchInstitution user = null;
		
		for(ResearchInstitution s : users)
		{
			user = researchService.create(s);
		}
		
		return users;
	}
	
	  
	  
	  @DeleteMapping("/delete")
	  public String delete(@RequestParam String userId) {
        researchService.deleteById(userId);
	  	  return "Deleted " + userId;
      }
	
}
