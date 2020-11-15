package edu.fiu.ffqr.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;


@Document(collection="research_institution")
public class ResearchInstitution implements Serializable {

	@Id
	private ObjectId _id;
	@JsonProperty("researchInstitutionId")
	private String researchInstitutionId; 
	@JsonProperty("address")
	private String address;
	@JsonProperty("createdDate")
	private String createdDate;
	@JsonProperty("institutionName")
        private String institutionName;
	@JsonProperty("siteType")
	private String siteType;    

	public ResearchInstitution() {}
	
	public ResearchInstitution(String researchInstitutionId, String address, String createdDate, String institutionName, String siteType){
                this.researchInstitutionId = researchInstitutionId;
		this.address = address;
		this.createdDate = createdDate;
		this.institutionName = institutionName;
		this.siteType = siteType;		

        }
	

	public ObjectId getId() {
        return this._id;
        }
	
	public String getResearchInstitutionId() {
		return this.researchInstitutionId;
	}

	public void setResearchInstitutionId(String researchInstitutionId) {
		this.researchInstitutionId = researchInstitutionId;
	}
        
        public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCreatedDate(){
        return this.createdDate;
        }
        
        public void setCreatedDate(String createdDate){
        this.createdDate = createdDate;
        }
	
	public String GetInstitutionName() {
        return this.institutionName;
        }
        
        public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
	}
	
	public String getSiteType() {
        return this.siteType;
	}
	
        public void setSiteType(String siteType) {
        this.siteType = siteType;
	}
}
