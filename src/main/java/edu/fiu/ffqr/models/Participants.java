package edu.fiu.ffqr.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.fiu.ffqr.models.Clinician;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "participants")
public class Participants extends User implements Serializable {

    @JsonProperty("usercount")
    private String usercount;
    @JsonProperty("assignedResearcherInst")
    private String assignedResearcherInst;
    @JsonProperty("assignedResearcherUsers")
    private List<String> assignedResearcherUsers;
    @JsonProperty("childrennames")
    private ArrayList<String> childrennames = new ArrayList<String>();

    public Participants() {
    }

    public Participants(String userId, String username, String userpassword, String usertype, String usercount,
            String assignedResearcherInst, List<String> assignedResearcherUsers, ArrayList<String> childrennames,
            boolean isactive) {
        this.userId = userId;
        this.username = username;
        this.userpassword = userpassword;
        this.usertype = usertype;
        this.usercount = usercount;
        this.assignedResearcherInst = assignedResearcherInst;
        this.assignedResearcherUsers = assignedResearcherUsers;
        this.childrennames = childrennames;
        this.isactive = isactive;

    }

    public String getUserCount() {
        return this.usercount;
    }

    public void setUserCount(String usercount) {
        this.usercount = usercount;
    }

    public String getAssignedResearcherInst() {
        return this.assignedResearcherInst;
    }

    public void setAssignedResearcherInst(String assignedResearcherOrg) {
        this.assignedResearcherInst = assignedResearcherOrg;

    }

    public List<String> getAssignedResearcherUser() {
        return this.assignedResearcherUsers;
    }

    public void setAssignedResearcherUser(List<String> assignedResearcherUser) {
        this.assignedResearcherUsers = assignedResearcherUser;

    }

    public ArrayList<String> getChildrennames() {
        return this.childrennames;
    }

    public void setChildrennames(ArrayList<String> childrennames) {
        this.childrennames = childrennames;
    }

}
