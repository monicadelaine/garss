package edu.ua.efda.chatbot.dao;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.ua.efda.chatbot.dto.Provider;

@Repository
public interface ProviderRepository extends PagingAndSortingRepository<Provider, String>, JpaSpecificationExecutor<Provider> {
}
