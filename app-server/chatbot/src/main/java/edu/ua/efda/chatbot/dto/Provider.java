package edu.ua.efda.chatbot.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.mapping.Set;
import org.locationtech.jts.geom.Point;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

@Getter
@Setter
@Entity
@Table(name = "providers", schema="public")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Provider {
	
	@Id
	@Column(name="id_cms_other")
	String idCmsOther; // character varying(6) NOT NULL,

	@Column(name="agency_name")
	//@Column(nullable=false,name="agency_name")
	String agencyName; //; // character varying NOT NULL,
	
	//@Column(nullable=false) 
	String addr1; // character varying NOT NULL,
	String addr2; // character varying,
	
	//@Column(nullable=false) 
	String city; //y character varying NOT NULL,
	
	//@Column(nullable=false) 
	String state; // character(2) NOT NULL,
	
	//@Column(nullable=false) 
	String zip; // integer NOT NULL,
	String county; // character varying(30),

	//@Column(nullable=false,name="phone_number")
	@Column(name="phone_number")
	String phoneNumber; // character varying(15) NOT NULL,

	String website; // character varying,

	//@Column(nullable=false,name="ownership_type")
	@Column(name="ownership_type")
	OwnershipType ownershipType; // public.ownership_types

    @Column(columnDefinition = "geometry")
    @JsonSerialize(using=GeometrySerializer.class)
    @JsonDeserialize(contentUsing=GeometryDeserializer.class)
    @Type(type = "jts_geometry")
	Point coordinates; //point

	//ToDo: Make next 2 cols enums 
	@Column(name="default_service_area_type")
	String defaultServiceAreaType; // public.service_area_types,

	@Column(name="service_area_entities")
	String serviceAreaEntities; // character varying[],

	@Column(name="service_area_polygon")
	String serviceAreaPolygon; // polygon,

	@Column(name="date_last_updated")
	Date dateLastUpdated; // date,
	
	@Column(name="data_source")
	String dataSource; // character varying[],

	String notes; // text,
	
	@OneToMany
	private List<ResourceListing> listings;

}
