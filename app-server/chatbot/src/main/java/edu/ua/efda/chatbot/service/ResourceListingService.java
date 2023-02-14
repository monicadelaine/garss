package edu.ua.efda.chatbot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ua.efda.chatbot.dao.ResourceCategoryDao;
import edu.ua.efda.chatbot.dao.ResourceListingDao;
import edu.ua.efda.chatbot.dto.ResourceCategory;
import edu.ua.efda.chatbot.dto.ResourceListing;
import edu.ua.efda.chatbot.dto.ResourceListingKey;

@Service
public class ResourceListingService {

	private ResourceListingDao resourceListingDao;
	
	@Autowired
	public ResourceListingService( ResourceListingDao theResourceListingDao) {
		resourceListingDao = theResourceListingDao;
	}
	
	@Transactional
	public List<ResourceListing> findAll() {
		return resourceListingDao.findAll();
	}
	
	@Transactional
	public Optional<ResourceListing> findById(ResourceListingKey theKey) {
		return resourceListingDao.findById(theKey);
	}
	
	@Transactional
	public void save(ResourceListing theResourceListing) {
		resourceListingDao.save(theResourceListing);
	}
	
	@Transactional
	public void deleteById(ResourceListingKey theKey) {
		resourceListingDao.deleteById(theKey);
		
	}
	public List<ResourceListing> findAllByProviderIdCmsOtherOrderByResourceTypeAsc(String id){
	return resourceListingDao.findAllByProviderIdCmsOtherOrderByResourceTypeAsc(id);
	}

	@Transactional
	public void updateServiceAreaMultCounties(String providerid, String resourceType, String county1, String county2) {
		resourceListingDao.updateServiceAreaMultCounties( providerid,  resourceType,  county1,  county2);
	}
	
	@Transactional
	public void addCountyToServiceArea(String providerid, String resourceType, String county) {
		resourceListingDao.addCountyToServiceArea( providerid,  resourceType,  county);
	}
	
//  addCountyToServiceArea(String providerId,String resourceType,String county);
//	public void updateServiceAreaSingleCounty(String providerid, String resourceType, String county1) {
//		resourceListingDao.updateServiceAreaSingleCounty( providerid,  resourceType,  county1);
//	}
}
