package edu.ua.efda.chatbot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ua.efda.chatbot.dao.CountyDao;
import edu.ua.efda.chatbot.dto.County;


@Service
public class CountyService {

	private CountyDao countyDao;
	
	@Autowired
	public CountyService(CountyDao theCountyDao) {
		countyDao = theCountyDao;
	}
	
	@Transactional
	public List<County> findAll() {
		return countyDao.findAll();
	}
	
	@Transactional
	public Optional<County> findById(String theId) {
		return countyDao.findById(theId);
	}
	
	@Transactional
	public void save(County theProvider) {
		countyDao.save(theProvider);
	}
	
	@Transactional
	public void deleteById(String theId) {
		countyDao.deleteById(theId);
	}
	
}
