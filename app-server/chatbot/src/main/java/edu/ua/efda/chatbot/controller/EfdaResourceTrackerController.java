package edu.ua.efda.chatbot.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ua.efda.chatbot.dto.ResourceCategory;
import edu.ua.efda.chatbot.dto.AlabamaLocations;
import edu.ua.efda.chatbot.dto.County;
import edu.ua.efda.chatbot.dto.Provider;
import edu.ua.efda.chatbot.dto.ResourceListing;
import edu.ua.efda.chatbot.dto.ResourceListingKey;
import edu.ua.efda.chatbot.dto.ResourceTypes;
import edu.ua.efda.chatbot.dto.ServiceAreaType;
import edu.ua.efda.chatbot.service.CountyService;
import edu.ua.efda.chatbot.service.ProviderService;
import edu.ua.efda.chatbot.service.ResourceListingService;
import edu.ua.efda.chatbot.spec.entity.utils.PagingResponse;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@Controller
@RequestMapping("/rms")
public class EfdaResourceTrackerController {

	private ResourceListingService resourceListingService;
	private ProviderService providerService;
	private CountyService countyService;

	GeometryFactory geometryFactory;

	@Autowired
	public EfdaResourceTrackerController(ResourceListingService resourceListingService,
			ProviderService theProviderService, CountyService theCountyService) {
		geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
		this.resourceListingService = resourceListingService;
		providerService = theProviderService;
		countyService=theCountyService;
	}

	@GetMapping(value = "/providers")
	public String get(@And({ @Spec(path = "city", params = "city", spec = LikeIgnoreCase.class),
			@Spec(path = "zip", params = "zip", spec = Equal.class),
			@Spec(path = "county", params = "county", spec = LikeIgnoreCase.class),
			@Spec(path = "agencyName", params = "agencyName", spec = LikeIgnoreCase.class),
			// @Spec(path = "createDate", params = "createDate", spec = Equal.class),
			// @Spec(path = "createDate", params = {"createDateGt", "createDateLt"}, spec =
			// Between.class)
	}) Specification<Provider> spec, Sort sort, @RequestHeader HttpHeaders headers, Model theModel) {

		PagingResponse response = providerService.get(spec, headers, sort);

		if (response == null || response.getCount() == 0) {
			System.out.println("id em[ty");
		}
		Point pt = geometryFactory.createPoint(new Coordinate(-86.496774, 32.344437));
		System.out.println("id" + response.getCount() + " " + response.getPageTotal());

		response.getElements().get(0).setCoordinates(pt);
		theModel.addAttribute("providers", response.getElements());
		theModel.addAttribute("count", response.getCount());
		this.providerService.save(response.getElements().get(0));

		return "rms/list-providers";
	}

	@GetMapping("/resources")
	public String listResources(Model theModel) {

		List<ResourceListing> theResourceListing = this.resourceListingService.findAll();

		theModel.addAttribute("rls", theResourceListing);

		return "rms/list-rl";
	}

	@GetMapping("/resources/{idCmsOther}")
	public String listResourcesByProvider(@PathVariable("idCmsOther") String providerId, Model theModel) {

		List<ResourceListing> theResourceListing = resourceListingService
				.findAllByProviderIdCmsOtherOrderByResourceTypeAsc(providerId);

		theModel.addAttribute("rls", theResourceListing);
		theModel.addAttribute("idCmsOther", providerId);
		int resourcesIdx = 0;
		int typesIdx = 0;
		ResourceTypes[] typeList = ResourceTypes.values();
		int typesTotal = typeList.length;
		List<ResourceTypes> noResourceListed = new ArrayList<ResourceTypes>();
		while (typesIdx < typesTotal && resourcesIdx < theResourceListing.size()) {
			if (theResourceListing.get(resourcesIdx).getResourceType().name()
					.compareToIgnoreCase(typeList[typesIdx].name()) == 0) {
				// this type exists
				resourcesIdx++;
				typesIdx++;
			} else if (theResourceListing.get(resourcesIdx).getResourceType().name()
					.compareToIgnoreCase(typeList[typesIdx].name()) > 0) {
				// skipped..add enum
				noResourceListed.add(typeList[typesIdx]);
				// increment enum
				typesIdx++;

			} else {
				// increment resource list
				resourcesIdx++;
			}

		}
		while (typesIdx < typesTotal) {
			noResourceListed.add(typeList[typesIdx]);
			// increment enum
			typesIdx++;
		}
		theModel.addAttribute("resourceTypesToAdd", noResourceListed);

		return "rms/list-provider-rl";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(@RequestParam("idCmsOther") String providerId,
			@RequestParam("resourceType") String resourceType, Model theModel) {

		// First find Provider, then select resource type
		// go to add screen

		ResourceListing theResourceListing = new ResourceListing();
		ArrayList<String> selected=new ArrayList<String>();
		theResourceListing.setIdCmsOther(providerId);
		theResourceListing.setResourceType(ResourceTypes.valueOf(resourceType));
		theResourceListing.setServiceAreaType(ServiceAreaType.COUNTY);
		theModel.addAttribute("rl", theResourceListing);
		theModel.addAttribute("counties",AlabamaLocations.countyList);
		theModel.addAttribute("selected",selected);

		return "rms/rl-form";

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("idCmsOther") String providerId,
			@RequestParam("resourceType") String resourceType, Model theModel) {

		ResourceListing rl = this.resourceListingService.findById(new ResourceListingKey(providerId, resourceType))
				.orElse(null);

		theModel.addAttribute("rl", rl);
		ArrayList<String> selected=new ArrayList<String>();
		if (rl.getServiceAreaType()==ServiceAreaType.COUNTY && rl.getServiceAreaDescription()!=null) {
			//break apart string to show selected
			String[] countyList=rl.getServiceAreaDescription().split(",");
//			for (String item: list) {
//				System.out.println("item "+item);
//				selected.add(item);
//			}
			Collections.addAll(selected, countyList);
			System.out.println("all items "+selected);
		}

		theModel.addAttribute("selected",selected);
		theModel.addAttribute("counties",AlabamaLocations.countyList);

		return "rms/rl-form";

	}

	@PostMapping("/save")
	public String save(@ModelAttribute("rl") ResourceListing rl) {

		if (rl.getDateAdded() == null)
			rl.setDateAdded(LocalDate.now());
//		theResourceListing.setDateLastVerified(new Date("2022-05-15"));
		
		if (rl.getServiceAreaDescription()== null || rl.getServiceAreaDescription().length()==0) {
			Provider provider=this.providerService.get(rl.getIdCmsOther());
			rl.setServiceAreaDescription(provider.getCounty());
		}
		System.out.println("object to save:"+rl.getServiceAreaDescription());
		
		//Set service area based on county polygon
		String[] countyList=rl.getServiceAreaDescription().split(",");

		
		if (countyList.length==0) {
			rl.setServiceArea(null);
			this.resourceListingService.save(rl);
			
		} else if (countyList.length==1) {
			System.out.println("adding county "+countyList[0]);

			System.out.println("id "+rl.getIdCmsOther()+" rt "+rl.getResourceType().toString()+" county "+countyList[0]);
			try {
				County county = countyService.findById(countyList[0]).orElse(null);
				rl.setServiceArea((MultiPolygon)county.getWkGeometry());
				this.resourceListingService.save(rl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("error: "+e.getMessage());
			}
			
		} else {
			this.resourceListingService.save(rl);
			System.out.println("id "+rl.getIdCmsOther()+" rt "+rl.getResourceType().toString()+" county "+countyList[0]);

			System.out.println("adding counties "+countyList[0]+" "+countyList[1]);
			
			try {
				resourceListingService.updateServiceAreaMultCounties( rl.getIdCmsOther(), rl.getResourceType().toString(), countyList[0], countyList[1]);
			} catch (Exception e) {
				System.out.println("error: "+e.getMessage());
			}

			for (int i = 2; i< countyList.length; i++) {
				resourceListingService.addCountyToServiceArea( rl.getIdCmsOther(), rl.getResourceType().toString(), countyList[i]);
				
			}
			
		}

		return "redirect:/rms/resources/" + rl.getIdCmsOther();
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("idCmsOther") String providerId, @RequestParam("resourceType") String code) {

		this.resourceListingService.deleteById(new ResourceListingKey(providerId, code));
		return "redirect:/rms/resources/" + providerId;
	}

}
