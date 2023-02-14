package edu.ua.efda.chatbot.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;



public class ResourceListingKey implements Serializable{
	
	
    String idCmsOther;

    @Enumerated(EnumType.STRING)
    private ResourceTypes resourceType;
    
    
	ResourceListingKey() {

	}

	public ResourceListingKey(String idCmsOther, String resourceType) {
		//this.idCmsOther = new Provider();
		//this.idCmsOther.setIdCmsOther(id_cms_other);
		this.idCmsOther=idCmsOther;
		this.resourceType = ResourceTypes.valueOf(resourceType);
	}

	public void setIdCmsOther(String idCmsOther) {
		this.idCmsOther=idCmsOther;
	}

	public String getIdCmsOther() {
		return idCmsOther;
	}



	public ResourceTypes getResourceType() {
		return resourceType;
	}


	public void setResourceType(ResourceTypes resourceType) {
		this.resourceType = resourceType;
	}


}
