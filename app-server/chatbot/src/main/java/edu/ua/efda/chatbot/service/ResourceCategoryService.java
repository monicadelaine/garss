package edu.ua.efda.chatbot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ua.efda.chatbot.dao.ResourceCategoryDao;
import edu.ua.efda.chatbot.dto.ResourceCategory;
import edu.ua.efda.chatbot.dto.ResourceTypes;

@Service
public class ResourceCategoryService {

	private ResourceCategoryDao resourceCategoryDao;
	
	@Autowired
	public ResourceCategoryService(ResourceCategoryDao theResourceCategoryInfoRepository) {
		resourceCategoryDao = theResourceCategoryInfoRepository;
	}
	
	@Transactional
	public List<ResourceCategory> findAll() {
		return resourceCategoryDao.findAll();
	}
	
	@Transactional
	public Optional<ResourceCategory> findById(ResourceTypes theId) {
		return resourceCategoryDao.findById(theId);
	}
	
	@Transactional
	public void save(ResourceCategory theProvider) {
		resourceCategoryDao.save(theProvider);
	}
	
	@Transactional
	public void deleteById(ResourceTypes theId) {
		resourceCategoryDao.deleteById(theId);
	}

}
