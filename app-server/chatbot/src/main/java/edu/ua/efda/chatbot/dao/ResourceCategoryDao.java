package edu.ua.efda.chatbot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ua.efda.chatbot.dto.ResourceCategory;
import edu.ua.efda.chatbot.dto.ResourceTypes;

public interface ResourceCategoryDao extends JpaRepository<ResourceCategory,ResourceTypes>{

	
}
