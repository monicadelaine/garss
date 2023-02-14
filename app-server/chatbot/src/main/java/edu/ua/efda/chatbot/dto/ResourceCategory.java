package edu.ua.efda.chatbot.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "resource_categories", schema="public")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class ResourceCategory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2979031482598364354L;
	
	@Id
	@Column(name="code",nullable=false)
    @Enumerated(EnumType.STRING)
    private ResourceTypes resourceType;
    //ResourceTypes code;  // public.resource_categories NOT NULL,
	
	@Column(nullable=false)
    String description;  // text,
    
    @Column(length = 4000,name="long_description")
    String longDescription;  // text,
    
    String keywords[];  // character varying[],
    String apply;  // character varying,
    String process;  // character varying,
    String kinds;  // character varying,
    String payment;  // character varying,
    String evaluate;  // character varying
    String link;
	

}
