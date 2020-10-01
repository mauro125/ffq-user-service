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
import edu.fiu.ffqr.models.Research;
import edu.fiu.ffqr.repositories.ResearchRepository;
import edu.fiu.ffqr.service.SysUserService;
import edu.fiu.ffqr.service.AdminService;
import edu.fiu.ffqr.service.ResearchService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/research")
public class ResearchController {

    @Autowired
    private ResearchService researchService;
    @Autowired
    private ResearchRepository researchRepository;

    public ResearchController() {
    }

    @GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public Authenticate validateLogin() {
		return new Authenticate("User successfully authenticated");
	}
    
    
    @GetMapping("/all")
    public List<Research> allUsers() throws JsonProcessingException {
        
        List<Research> users = researchService.getAll();
        return users;
    }  

    @GetMapping("/{userID}")
	public Research gUserApplication(@PathVariable("userID") String userID) {
		return researchService.getUserByUserId(userID);
	}
    
    @PostMapping("/createuser")
    public Research createUser(@RequestBody Research user) throws JsonProcessingException {

      if (researchService.getUserByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("A user with Username " + user.getUsername() + " already exists");
      }  
	  return researchService.create(user);
	  
  }

  @PostMapping("/updateuser")
    public void updateUser(@RequestBody Research user) throws JsonProcessingException {
        
        if (researchService.getUserByUserId(user.getUserId()) == null) {
            throw new IllegalArgumentException("A user with Username " + user.getUsername() + " doesn't exist");
        }

        Research currentUser = researchService.getUserByUserId(user.getUserId());

        currentUser.setUsername(user.getUsername());
        currentUser.setUserpassword(user.getUserpassword());
        currentUser.setFirstname(user.getFirstname());
        currentUser.setLastname(user.getLastname());
        currentUser.setUsertype(user.getUsertype());

        researchRepository.save(currentUser);

        //return adminService.create(user);
    }


    @PostMapping("/create")
    public Research create(@RequestBody Research item) throws JsonProcessingException {
        
        if (researchService.getUserByUsername(item.getUsername()) != null) {
            throw new IllegalArgumentException("A user with Username " + item.getUsername() + " already exists");
        }

        return researchService.create(item);
    }

    
    
   
	
	@PostMapping("/createMany")
	public ArrayList<Research> create(@RequestBody ArrayList<Research> users) {
		Research user = null;
		
		for(Research s : users)
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
      
    @RequestMapping("/login")
    public boolean login(@RequestBody Research user) {
        return
          user.getUsername().equals("user") && user.getUserpassword().equals("password");
    }
     
    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
          .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
          .decode(authToken)).split(":")[0];
    }
	
}
