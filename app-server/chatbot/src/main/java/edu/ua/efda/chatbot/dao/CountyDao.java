package edu.ua.efda.chatbot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ua.efda.chatbot.dto.County;

@Repository
public interface CountyDao extends JpaRepository<County,String>{

}
