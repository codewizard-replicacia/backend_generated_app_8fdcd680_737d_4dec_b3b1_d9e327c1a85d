package com.mycompany.group234.model.jointable;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import lombok.Data;
import javax.persistence.*;

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

@Entity(name = "SiteMapFlows")
@Table(schema = "\"generated_app\"", name = "\"SiteMapFlows\"")
@Data
public class SiteMapFlows{

 	@Id
    @Column(name = "\"Id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "\"SiteMapId\"")
	private Long siteMapId;

    
    @Column(name = "\"ScreenFlowId\"")
    private Integer screenFlowId;
 
}