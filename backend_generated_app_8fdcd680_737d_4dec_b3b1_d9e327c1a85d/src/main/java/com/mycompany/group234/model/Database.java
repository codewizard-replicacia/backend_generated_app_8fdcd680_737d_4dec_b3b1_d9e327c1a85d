package com.mycompany.group234.model;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


 
import com.mycompany.group234.model.Document;
import com.mycompany.group234.model.SiteMap;
import com.mycompany.group234.model.FETechnology;
import com.mycompany.group234.model.Capability;
import com.mycompany.group234.model.Industry;
import com.mycompany.group234.model.GitHubCreds;
import com.mycompany.group234.model.User;
import com.mycompany.group234.model.Settings;
import com.mycompany.group234.model.FrontendApp;
import com.mycompany.group234.model.BackendApp;
import com.mycompany.group234.model.ScreenFlow;
import com.mycompany.group234.model.ScreenFeature;
import com.mycompany.group234.model.Database;
import com.mycompany.group234.model.Project;
import com.mycompany.group234.model.ModelFile;
import com.mycompany.group234.model.FrontendScreen;
import com.mycompany.group234.model.Theme;
import com.mycompany.group234.model.BETechnology;
import com.mycompany.group234.model.complex.BasicDetails;
import com.mycompany.group234.enums.IndustryType;
import com.mycompany.group234.enums.ModelType;
import com.mycompany.group234.enums.ModelCreator;
import com.mycompany.group234.converter.IndustryTypeConverter;
import com.mycompany.group234.converter.ModelTypeConverter;
import com.mycompany.group234.converter.ModelCreatorConverter;
import com.mycompany.group234.converter.DurationConverter;
import com.mycompany.group234.converter.UUIDToByteConverter;
import com.mycompany.group234.converter.UUIDToStringConverter;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmMediaStream;

@Entity(name = "Database")
@Table(name = "\"Database\"", schema =  "\"generated_app\"")
@Data
                        
public class Database {
	public Database () {   
  }
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"DbId\"", nullable = true )
  private Integer dbId;
	  
  @Column(name = "\"Dbname\"", nullable = true )
  private String dbname;
  
	  
  @Column(name = "\"Dbversion\"", nullable = true )
  private String dbversion;
  
	  
  @Column(name = "\"Description\"", nullable = true )
  private String description;
  
	  
  @Column(name = "\"Icon\"", nullable = true )
  private String icon;
  
  
  
  
   
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "Database [" 
  + "DbId= " + dbId  + ", " 
  + "Dbname= " + dbname  + ", " 
  + "Dbversion= " + dbversion  + ", " 
  + "Description= " + description  + ", " 
  + "Icon= " + icon 
 + "]";
	}
	
}