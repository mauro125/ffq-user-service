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
import edu.fiu.ffqr.service.SysUserService;
//import edu.fiu.ffqr.service.UserService;
import edu.fiu.ffqr.models.Participants;
import edu.fiu.ffqr.service.ClinicianService;
import edu.fiu.ffqr.service.ParticipantsService;
import edu.fiu.ffqr.repositories.ParticipantsRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/participant")
public class ParticipantsController {

    @Autowired
    private ParticipantsService participantsService;
    @Autowired
    private ParticipantsRepository participantRepository;

    public ParticipantsController() {
    }

    @GetMapping("/all")
    public List<Participants> allResearcherParents() throws JsonProcessingException {
        List<Participants> users = participantsService.getAll();
        return users;
    }

    @GetMapping("/all/{instID}")
    public List<Participants> allResearcherParticipants(@PathVariable("instID") String instID) {
        List<Participants> users = participantsService.getParticipantByAssignedResearcher(instID);
        return users;
    }

    @GetMapping("/{userID}")
    public Participants getParent(@PathVariable("userID") String userId) {
        return participantsService.getParticipantByUserId(userId);
    }

    @PostMapping("/createparticipant")
    public Participants createUser(@RequestBody Participants user) throws JsonProcessingException {

        if (participantsService.getParticipantByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("A user with Username " + user.getUsername() + " already exists");
        }
        return participantsService.create(user);

    }

    @PutMapping("/updateparticipant")
    public void updateUser(@RequestBody Participants user) throws JsonProcessingException {

        if (participantsService.getParticipantByUserId(user.getUserId()) == null) {
            throw new IllegalArgumentException("A user with Username " + user.getUsername() + " doesn't exist");
        }

        Participants currentUser = participantsService.getParticipantByUserId(user.getUserId());

        currentUser.setUsername(user.getUsername());
        currentUser.setUserpassword(user.getUserpassword());
        currentUser.setUsertype(user.getUsertype());
        currentUser.setUserCount(user.getUserCount());
        currentUser.setAssignedResearcherInst(user.getAssignedResearcherInst());
        currentUser.setAssignedResearcherUser(user.getAssignedResearcherUser());
        currentUser.setChildrennames(user.getChildrennames());

        participantRepository.save(currentUser);
    }

    @PostMapping("/create")
    public Participants create(@RequestBody Participants item) throws JsonProcessingException {

        if (participantsService.getParticipantByUsername(item.getUsername()) != null) {
            throw new IllegalArgumentException("A participant with Username " + item.getUsername() + " already exists");
        }

        return participantsService.create(item);
    }

    // @GetMapping("/createMany")
    // public ArrayList<Participants> create(@PathVariable("numberOfParents") String
    // numberOfParents,
    // @PathVariable("parentsPrefix") String parentsPrefix) {
    // Participants user = null;

    // for (Participants s : users) {
    // user = participantsService.create(s);
    // }

    // return users;
    // }

    @DeleteMapping("/delete")
    public String delete(@RequestParam String userId) {
        participantsService.deleteById(userId);
        return "Deleted " + userId;
    }

}
