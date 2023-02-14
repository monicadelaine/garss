package edu.ua.efda.chatbot.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.ua.efda.chatbot.dao.ProviderRepository;
import edu.ua.efda.chatbot.dto.Provider;
import edu.ua.efda.chatbot.spec.entity.utils.GenericCsv;
import edu.ua.efda.chatbot.spec.entity.utils.PagingHeaders;
import edu.ua.efda.chatbot.spec.entity.utils.PagingResponse;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ProviderService extends GenericCsv<Provider> {

    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderService(ProviderRepository providerRepository) {
        super(Provider.class);
        this.providerRepository = providerRepository;
    }

    /**
     * delete element
     *
     * @param id element ID
     * @throws EntityNotFoundException Exception when retrieve entity
     */
    public void delete(String id) {
    	Provider entity = providerRepository.findById(id)
                                  .orElseThrow(() -> new EntityNotFoundException(String.format("Can not find the entity Provider (%s = %s).", "id", id)));
    	providerRepository.delete(entity);
    }

    /**
     * @param id element ID
     * @return element
     * @throws EntityNotFoundException Exception when retrieve element
     */
    public  Provider get(String id) {
        return providerRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException(String.format("Can not find the entity Provider (%s = %s).", "id", id)));
    }

    /**
     * get element using Criteria.
     *
     * @param spec    *
     * @param headers pagination data
     * @param sort    sort criteria
     * @return retrieve elements with pagination
     */
    public PagingResponse get(Specification<Provider> spec, HttpHeaders headers, Sort sort) {
    	System.out.println(spec+ " "+sort);
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            List<Provider> entities = get(spec, sort);
            return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }

    /**
     * get element using Criteria.
     *
     * @param spec    *
     * @param headers pagination data
     * @param sort    sort criteria
     * @return retrieve elements with pagination
     */
    public List<Provider> getListing(Specification<Provider> spec) {
            List<Provider> entities = get(spec, Sort.unsorted());
        
            return entities;
    }

    
    private boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }

    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
        
    }

    /**
     * get elements using Criteria.
     *
     * @param spec     *
     * @param pageable pagination data
     * @return retrieve elements with pagination
     */
    public PagingResponse get(Specification<Provider> spec, Pageable pageable) {
        Page<Provider> page = providerRepository.findAll(spec, pageable);
        List<Provider> content = page.getContent();
        return new PagingResponse(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
    }

    /**
     * get elements using Criteria.
     *
     * @param spec *
     * @return elements
     */
    public List<Provider> get(Specification<Provider> spec, Sort sort) {
        return providerRepository.findAll(spec, sort);
    }

    /**
     * create element.
     *
     * @param item element to create
     * @return element after creation
     * //     * @throws CreateWithIdException   Exception lancée lors de la création d'un objet existant
     * @throws EntityNotFoundException Exception lors de récupération de l'entité en base
     */
    public Provider create(Provider item) {
        return save(item);
    }

    /**
     * update element
     *
     * @param id   element identifier
     * @param item element to update
     * @return element after update
     * @throws EntityNotFoundException Exception when retrieve entity
     */
    public Provider update(String id, Provider item) {
        if (item.getIdCmsOther() == null) {
            throw new RuntimeException("Can not update entity, entity without ID.");
        } else if (!id.equals(item.getIdCmsOther())) {
            throw new RuntimeException(String.format("Can not update entity, the resource ID (%d) not match the objet ID (%d).", id, item.getIdCmsOther()));
        }
        return save(item);
    }

    /**
     * create \ update elements
     *
     * @param item element to save
     * @return element after save
     */
    public Provider save(Provider item) {
        return providerRepository.save(item);
    }


    @Async
    public List<Provider> uploadFile(MultipartFile multipartFile) throws IOException {
        List<Provider> providerList = parseCsvFile(multipartFile);
        return (List<Provider>)providerRepository.saveAll(providerList);
    }

}
