package edu.ua.efda.chatbot.controller;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import edu.ua.efda.chatbot.dto.Provider;
import edu.ua.efda.chatbot.dto.ResourceListing;
import edu.ua.efda.chatbot.dto.ResourceListingKey;
import edu.ua.efda.chatbot.dto.ResourceTypes;
import edu.ua.efda.chatbot.dto.ResourceCategory;
import edu.ua.efda.chatbot.service.ProviderService;
import edu.ua.efda.chatbot.service.ResourceListingService;
import edu.ua.efda.chatbot.spec.entity.utils.PagingHeaders;
import edu.ua.efda.chatbot.spec.entity.utils.PagingResponse;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import edu.ua.efda.chatbot.service.ResourceCategoryService;

@RestController
@RequestMapping("/api")
public class EfdaApiResourceTrackerController {

	// private ProviderService providerService;
	private ResourceCategoryService resourceCategoryService;
	private ResourceListingService resourceListingService;
	private final ProviderService providerService;

	@Autowired
	public EfdaApiResourceTrackerController(ProviderService theProviderService,
			ResourceCategoryService theResourceCategoryService, ResourceListingService theResourceListingService) {
		providerService = theProviderService;
		resourceCategoryService = theResourceCategoryService;
		resourceListingService = theResourceListingService;

	}

	/******************************/
	/*
	 * providers /
	 ******************************/
	// expose "/providers" and return list of providers

	@Transactional
	@PostMapping(value = "/providers", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Provider create(@RequestBody Provider item) {
		return providerService.create(item);
	}

	@Transactional
	@PatchMapping(value = "/providers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Provider update(@PathVariable(name = "id") String id, @RequestBody Provider item) {
		return providerService.update(id, item);
	}

	@Transactional
	@DeleteMapping(value = "/providers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id") String id) {
		providerService.delete(id);
	}

	@Transactional
	@GetMapping(value = "/providers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Provider get(@PathVariable(name = "id") String id) {
		return providerService.get(id);
	}

	@Transactional
	@GetMapping(value = "/providers", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Provider>> get(@And({ @Spec(path = "city", params = "city", spec = Like.class),
			@Spec(path = "zip", params = "zip", spec = Like.class),
			@Spec(path = "county", params = "county", spec = Equal.class),
			@Spec(path = "agencyName", params = "agencyName", spec = Like.class),
			// @Spec(path = "createDate", params = "createDate", spec = Equal.class),
			// @Spec(path = "createDate", params = {"createDateGt", "createDateLt"}, spec =
			// Between.class)
	}) Specification<Provider> spec, Sort sort, @RequestHeader HttpHeaders headers) {
		final PagingResponse response = providerService.get(spec, headers, sort);
		return new ResponseEntity<>(response.getElements(), returnHttpHeaders(response), HttpStatus.OK);
	}

	public HttpHeaders returnHttpHeaders(PagingResponse response) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
		headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
		headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
		headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
		headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
		return headers;
	}

//
//    @ResponseBody
//    @PostMapping(value = "/providers/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<List<ProviderTest>> uploadFile(@RequestParam(value = "files") List<MultipartFile> files) {
//        List<ProviderTest> providers = files.stream()
//                              .parallel()
//                              .map(unchecked(providerService::uploadFile))
//                              .flatMap(Collection::stream)
//                              .collect(Collectors.toList());
//        return ResponseEntity.status(HttpStatus.CREATED)
//                             .body(providers);
//    }
//
//    @GetMapping(value = "/providers/extract", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Resource> extractFile(
//            @And({
//                    @Spec(path = "manufacturer", params = "manufacturer", spec = Like.class),
//                    @Spec(path = "model", params = "model", spec = Like.class),
//                    @Spec(path = "country", params = "country", spec = In.class),
//                    @Spec(path = "type", params = "type", spec = Like.class),
//                    @Spec(path = "createDate", params = "createDate", spec = Equal.class),
//                    @Spec(path = "createDate", params = {"createDateGt", "createDateLt"}, spec = Between.class)
//            }) Specification<ProviderTest> spec,
//            Sort sort) throws IOException {
//        List<ProviderTest> providers = providerService.get(spec, sort);
//        Resource resource = providerService.generateCsvFile(providers);
//
//        LocalDateTime now = LocalDateTime.now();
//        return ResponseEntity.ok()
//                             .contentType(MediaType.parseMediaType("application/octet-stream"))
//                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Search_Extraction_" + now + ".csv\"")
//                             .body(resource);
//    }
//	
	/******************************/
	/*
	 * resource classification /
	 ******************************/

	// expose "/resource_categories" and return list of resourceCategoryId
	@GetMapping("/resource_categories")
	public List<ResourceCategory> findAllResourceCategory() {
		return resourceCategoryService.findAll();
	}

	// add mapping for GET /resource_category/{resourceCategoryId}

	@GetMapping("/resource_category/{resourceCategoryId}")
	public ResourceCategory getResourceCategoryId(@PathVariable String resourceCategoryId) {
		
		

		ResourceCategory theResourceCategoryId = resourceCategoryService.findById(ResourceTypes.valueOf(resourceCategoryId)).orElse(null);

		if (theResourceCategoryId == null) {
			throw new RuntimeException("Resource category not found - " + resourceCategoryId);
		}

		return theResourceCategoryId;
	}

	// add mapping for POST /resource_category - add new provider

	@PostMapping("/resource_category")
	public ResourceCategory addResourceCategoryId(@RequestBody ResourceCategory theProvider) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		// theProvider.setCmsId(null);

		resourceCategoryService.save(theProvider);

		return theProvider;
	}

	// add mapping for PUT /resource_category - update existing provider

	@PutMapping("/resource_category")
	public ResourceCategory updateResourceCategoryId(@RequestBody ResourceCategory theProvider) {

		resourceCategoryService.save(theProvider);

		return theProvider;
	}

	// add mapping for DELETE /resource_category/{resourceCategoryId} - delete
	// provider
//
//	@DeleteMapping("/resource_category/{resourceCategoryId}")
//	public String deleteResourceCategoryId(@PathVariable String resourceCategoryId) {
//
//		ResourceCategory tempResourceCategoryId = resourceCategoryService.findById(resourceCategoryId).orElse(null);
//
//		// throw exception if null
//
//		if (tempResourceCategoryId == null) {
//			throw new RuntimeException("Provider id not found - " + resourceCategoryId);
//		}
//
//		resourceCategoryService.deleteById(resourceCategoryId);
//
//		return "Deleted provider id - " + resourceCategoryId;
//	}

	/******************************/
	/*
	 * resource listing /
	 ******************************/

	// expose "/resources" and return list of available resources
	@GetMapping("/resources")
	public List<ResourceListing> findAllResourceListing() {
		return resourceListingService.findAll();
	}

	// add mapping for GET /resources/{providerId}

	@GetMapping("/resources/{providerId}/{resourceClass}")
	public ResourceListing getResourceListing(@PathVariable("providerId") String providerId,
			@PathVariable("resourceClass") String code) {

		ResourceListing theResourceListing = resourceListingService.findById(new ResourceListingKey(providerId, code))
				.orElse(null);

		if (theResourceListing == null) {
			throw new RuntimeException("Provider id not found - " + providerId + " " + code);
		}

		return theResourceListing;
	}

	// add mapping for POST /resources - add new provider

	@PostMapping("/resources")
	public ResourceListing addProvider(@RequestBody ResourceListing theResourceListing) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		// Don't need to set cuz externally set
		// theProvider.setIdCmsOther(null);

		resourceListingService.save(theResourceListing);

		return theResourceListing;
	}

	// add mapping for PUT /resources - update existing provider

	@PutMapping("/resources")
	public ResourceListing updateResourceListing(@RequestBody ResourceListing theResourceListing) {

		resourceListingService.save(theResourceListing);

		return theResourceListing;
	}

	// add mapping for DELETE /resources/{providerId} - delete provider

	@DeleteMapping("/resources/{providerId}/{resourceClass}")
	public String deleteProvider(@PathVariable("providerId") String providerId,
			@PathVariable("resourceClass") String code) {

		ResourceListingKey key = new ResourceListingKey(providerId, code);
		ResourceListing tempResourceListing = resourceListingService.findById(key).orElse(null);

		// throw exception if null

		if (tempResourceListing == null) {
			throw new RuntimeException("Resource listing not found - " + providerId + " " + code);
		}

		resourceListingService.deleteById(key);

		return "Deleted provider id - " + providerId + " " + code;
	}

}
