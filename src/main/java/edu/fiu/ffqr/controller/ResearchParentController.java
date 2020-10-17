package edu.fiu.ffqr.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
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
import edu.fiu.ffqr.models.SysUser;
import edu.fiu.ffqr.repositories.ResearchParentRepository;
import edu.fiu.ffqr.service.SysUserService;
//import edu.fiu.ffqr.service.UserService;
import edu.fiu.ffqr.models.ResearchParent;
import edu.fiu.ffqr.service.ResearchService;
import edu.fiu.ffqr.service.ResearchParentService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/researchparent")
public class ResearchParentController{

    @Autowired
    private ResearchParentService researchParentService;
    @Autowired
    private ResearchParentRepository ResearchParentRepository;

    public ResearchParentController() {
    }
    

    
    @GetMapping("/all")
    public List<ResearchParent> allParents() throws JsonProcessingException {
        
        List<ResearchParent> users = researchParentService.getAll();
        return users;
    }  

    @GetMapping("/{userID}")
	public ResearchParent getParent(@PathVariable("userID") String userId) {
		return researchParentService.getParentByUserId(userId);
	}
    
    @PostMapping("/createparent")
    public ResearchParent createUser(@RequestBody ResearchParent user) throws JsonProcessingException {

      if (researchParentService.getParentByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("A user with Username " + user.getUsername() + " already exists");
      }  
	  return researchParentService.create(user);
	  
  }

    @PutMapping("/updateparent")
    public void updateUser(@RequestBody ResearchParent user) throws JsonProcessingException {
        
        if (researchParentService.getParentByUserId(user.getUserId()) == null) {
            throw new IllegalArgumentException("A user with Username " + user.getUsername() + " doesn't exist");
        }

        ResearchParent currentUser = researchParentService.getParentByUserId(user.getUserId());
        
        currentUser.setUsername(user.getUsername());
        currentUser.setUserpassword(user.getUserpassword());
        currentUser.setFirstname(user.getFirstname());
        currentUser.setLastname(user.getLastname());
        currentUser.setUsertype(user.getUsertype());

        currentUser.setAssignedclinic(user.getAssignedclinic());
        currentUser.setAssignedclinician(user.getAssignedclinician()); 
        currentUser.setChildrennames(user.getChildrennames());            

        researchParentRepository.save(currentUser);    
    }


    @PostMapping("/create")
    public ResearchParent create(@RequestBody ResearchParent item) throws JsonProcessingException {
        
        if (researchParentService.getParentByUsername(item.getUsername()) != null) {
            throw new IllegalArgumentException("A parent with Username " + item.getUsername() + " already exists");
        }

        return researchParentService.create(item);
    }

    
    
   
	
	@PostMapping("/createMany")
	public ArrayList<ResearchParent> create(@RequestBody ArrayList<ResearchParent> users) {
		ResearchParent user = null;
		
		for(ResearchParent s : users)
		{
			user = researchParentService.create(s);
		}
		
		return users;
	}
	
	  
	  
	  @DeleteMapping("/delete")
	  public String delete(@RequestParam String userId) {
        researchParentService.deleteById(userId);
	  	  return "Deleted " + userId;
	  }
	
}
