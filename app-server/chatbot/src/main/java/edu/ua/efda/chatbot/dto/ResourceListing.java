package edu.ua.efda.chatbot.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
//import org.springframework.data.geo.Point;
//import org.springframework.data.geo.Polygon;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resource_listing", schema="public")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@IdClass(ResourceListingKey.class)
public class ResourceListing {

	@Id
    @Column(name="id_cms_other",nullable=false)
    String idCmsOther; // character varying NOT NULL,
    
	@Id
	@Column(name="resource_type",nullable=false)
    @Enumerated(EnumType.STRING)
    private ResourceTypes resourceType;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date_added")
    LocalDate dateAdded; // date,
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date_last_verified")
    LocalDate dateLastVerified; //  date,
    
    @Enumerated(EnumType.STRING)
    @Column(name="service_area_type")
    ServiceAreaType serviceAreaType; //  text[],
    
    @Column(length = 4000,name="service_area_description")
    String serviceAreaDescription; //  text[],
    
    @Column(columnDefinition = "geometry",name="service_area")
    @Type(type = "jts_geometry")
    //@Type(type = "org.hibernate.spatial.JTSGeometryType")
    MultiPolygon serviceArea; //  polygon[],
    
    @Column(length = 100,name="contact_name")
    String contactName; //  character varying,
    
    @Column(length = 100,name="contact_email")
    String contactEmail; //  character varying,
    
    @Column(length = 40,name="contact_phone")
    String contactPhone; //  numeric,
    
    @Column(length = 100,name="contact_messaging")
    String contactMessaging; //  character varying[]
    
    @Column(length = 4000,name="source")
    String source;
    
    @Column(length = 4000,name="notes")
    String notes;
    
    @Column(length = 40,name="verify_method")
    @Enumerated(EnumType.STRING)
    VerifyMethod verifyMethod;
    
    @MapsId("idCmsOther")
    @JoinColumn(name = "id_cms_other")
    @ManyToOne
    private Provider provider;
	
}
