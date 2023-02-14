package edu.ua.efda.chatbot.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.ua.efda.chatbot.dto.ResourceCategory;
import edu.ua.efda.chatbot.dto.ResourceTypes;
import edu.ua.efda.chatbot.service.ProviderService;
import edu.ua.efda.chatbot.service.ResourceCategoryService;
import edu.ua.efda.chatbot.service.ResourceListingService;


@Component
public class VerifyDatabaseAtRunTime {

	ResourceCategoryService resourceCategoryService;	

	public VerifyDatabaseAtRunTime(ResourceCategoryService theResourceCategoryService) {
		resourceCategoryService = theResourceCategoryService;

	}

	public void verifyResourceCategoryEnum() {
		String code=null;
		
	    try {

			for (ResourceTypes s : ResourceTypes.values()) {
				System.err.println("checking enum of "+s.toString());
				
				ResourceCategory theResourceCategoryId=resourceCategoryService.findById(s).orElse(null);
				if (theResourceCategoryId==null) {
					System.err.println("Missing enum category in database: "+s.toString());
					return;
				}
			    
			}

			List<ResourceCategory> categories =resourceCategoryService.findAll();
   
			for (ResourceCategory s:categories) {
				   
				code = s.getResourceType().getCode();
				   
				if (ResourceTypes.valueOf(code)==null) {
						System.err.println("Database category missing in enum: "+code);	
						return;
				}
			}
			
		} catch (Exception e) {
			System.err.println("VerifyResourceCategoryEnum ("+code+") failed: "+e.getMessage());
			//e.printStackTrace();
		}
	    
	
	}

}
