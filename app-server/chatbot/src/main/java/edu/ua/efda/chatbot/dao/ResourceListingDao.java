package edu.ua.efda.chatbot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import edu.ua.efda.chatbot.dto.ResourceListing;
import edu.ua.efda.chatbot.dto.ResourceListingKey;


public interface ResourceListingDao extends JpaRepository<ResourceListing,ResourceListingKey>{
	
	List<ResourceListing> findAllByProviderIdCmsOtherOrderByResourceTypeAsc(String idCmsOther);
	
//	UPDATE resource_listing SET service_area = ST_Union(
//			(SELECT wkb_geometry FROM county WHERE name = 'Cleburne'),
//			(SELECT wkb_geometry FROM county WHERE name = 'Clarke')
//			)
//			WHERE id_cms_other = '17013' and resource_type='activity_enrichment';
	@Modifying
	@Query(value="UPDATE resource_listing SET service_area = ST_Multi(ST_Union((SELECT wkb_geometry FROM county WHERE name = :county1),(SELECT wkb_geometry FROM county WHERE name = :county2))) WHERE id_cms_other = :providerId and resource_type = :resourceType", nativeQuery=true)
    void updateServiceAreaMultCounties(String providerId,String resourceType,String county1,String county2);
	
//	@Modifying
//	//take multipolygon from county to here
//	@Query(value="UPDATE resource_listing SET service_area = (SELECT wkb_geometry FROM county WHERE name = '$3') WHERE id_cms_other = '?1' and resource_type='?2'", nativeQuery=true)
//    void updateServiceAreaSingleCounty(String providerId,String resourceType,String county1);

	//UPDATE resource_listing SET service_area = ST_Multi(ST_Union((SELECT service_area FROM resource_listing WHERE id_cms_other = 'M0717' and resource_type = 'activity_enrichment'),(SELECT wkb_geometry FROM county WHERE name = 'Greene'))) WHERE id_cms_other = 'M0717' and resource_type = 'activity_enrichment';
	@Modifying
	@Query(value="UPDATE resource_listing SET service_area = ST_Multi(ST_Union((SELECT service_area FROM resource_listing WHERE id_cms_other = :providerId and resource_type = :resourceType),(SELECT wkb_geometry FROM county WHERE name = :county))) WHERE id_cms_other = :providerId and resource_type = :resourceType", nativeQuery=true)
    void addCountyToServiceArea(String providerId,String resourceType,String county);

	
}
