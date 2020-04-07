package edu.fiu.ffqr.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
public class User implements Serializable {
	private String status;

	public User(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
*/

@Document(collection="admins")
public class Admin implements Serializable {

	@JsonProperty("userId")
	private String userId; 
	@JsonProperty("username")
	private String username;
	@JsonProperty("userpassword")
	private String userpassword;
	@JsonProperty("firstname")
	private String firstname;
	@JsonProperty("lastname")
	private String lastname;
	


	public Admin() {}
	
	public Admin(String userId, String username, String userpassword, String firstname, String lastname){
        this.userId = userId;
		this.username = username;
		this.userpassword = userpassword;
		this.firstname = firstname;
		this.lastname = lastname;

    }
	
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword(){
		return this.userpassword;
	}

	public void setUserpassword(String userpassword){
		this.userpassword = userpassword;
	}

	
/*
	public String getFullname() {
        return (this.firstname + " " + this.lastname);
	}*/
	
	public String getFirstname() {
        return this.firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
	}
	
	public String setLastname() {
        return this.lastname;
	}
	
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
