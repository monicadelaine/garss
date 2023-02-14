package edu.ua.efda.chatbot.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "county", schema="public")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class County {
	
	@Column(name="ogc_fid")
	int ogcFid;
	
	@Column(name="statefp",length = 2)
	String stateCode; 

	@Column(name="countyfp",length = 3)
	String countyCode; 

	@Column(name="countyns",length = 8)
	String countyns;

	@Column(name="affgeoid",length = 14)
	String affgeoid;

	@Column(name="geoid",length = 5)
	String geoid;

	@Id
	@Column(name="name",length = 100)
	String name;

	@Column(name="lsad",length = 2)
	String lsad;

	String aland;

	String awater;	

    @Column(columnDefinition = "geometry",name="wkb_geometry")
    @Type(type = "jts_geometry")
    Geometry wkGeometry; //  polygon[],
    
    



}
