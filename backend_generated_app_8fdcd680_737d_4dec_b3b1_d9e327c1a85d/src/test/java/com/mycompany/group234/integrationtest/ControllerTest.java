package com.mycompany.group234.integrationtest;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.group234.SpringApp;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
class ControllerTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private WebApplicationContext context;
  @LocalServerPort
  private int port;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  
  
   private JsonNode getJSONFromFile(String filePath) throws IOException {
    try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)){
      JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
      return jsonNode;
    }
    catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  
  private String getPayload(String filePath) throws IOException {
	  String jsonString = mapper.writeValueAsString( getJSONFromFile(filePath) );
	  return jsonString;
  }

  @Test
  void testRetrieveServiceDocument() {
    final String xml = given()
        .accept(ContentType.XML)
        .when()
        .get("/generated_app/")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Collection<Node> n = ((Node) ((Node) path.get("service")).get("workspace")).get("collection");
    assertNotNull(n);
    assertFalse(n.isEmpty());
  }

  @Test
  void  testRetrieveMetadataDocument() {
    final String xml = given()
        .when()
        .get("/generated_app/$metadata")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Node n = ((Node) ((Node) path.get("edmx:Ed mx")).get("DataServices")).get("Schema");
    assertNotNull(n);
    assertEquals("generated_app", n.getAttribute("Namespace"));
    assertNotNull(n.get("EntityContainer"));
  }

	

	
  @Test
  void  testCreateUserInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("UserInstance.json"))
        .when()
        .post("/generated_app/Users")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsUser() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("UserInstance.json"))
        .when()
        .post("/generated_app/Users")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Users?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).UserName", equalTo("'<<replace_with_keyFieldValue>>'"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Users/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateCapabilityInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("CapabilityInstance.json"))
        .when()
        .post("/generated_app/Capabilities")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsCapability() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("CapabilityInstance.json"))
        .when()
        .post("/generated_app/Capabilities")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Capabilities?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CapabilityId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Capabilities/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateBETechnologyInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("BETechnologyInstance.json"))
        .when()
        .post("/generated_app/BETechnologies")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsBETechnology() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("BETechnologyInstance.json"))
        .when()
        .post("/generated_app/BETechnologies")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/BETechnologies?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).BeTechId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/BETechnologies/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateDocumentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("DocumentInstance.json"))
        .when()
        .post("/generated_app/Documents")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsDocument() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("DocumentInstance.json"))
        .when()
        .post("/generated_app/Documents")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Documents?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).DocId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Documents/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateBackendAppInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("BackendAppInstance.json"))
        .when()
        .post("/generated_app/BackendApps")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsBackendApp() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("BackendAppInstance.json"))
        .when()
        .post("/generated_app/BackendApps")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/BackendApps?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).AppId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/BackendApps/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateIndustryInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("IndustryInstance.json"))
        .when()
        .post("/generated_app/Industries")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsIndustry() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("IndustryInstance.json"))
        .when()
        .post("/generated_app/Industries")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Industries?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).IndustryId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Industries/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateScreenFlowInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ScreenFlowInstance.json"))
        .when()
        .post("/generated_app/ScreenFlows")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsScreenFlow() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ScreenFlowInstance.json"))
        .when()
        .post("/generated_app/ScreenFlows")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/ScreenFlows?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ScreenFlowId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/ScreenFlows/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateScreenFeatureInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ScreenFeatureInstance.json"))
        .when()
        .post("/generated_app/ScreenFeatures")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsScreenFeature() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ScreenFeatureInstance.json"))
        .when()
        .post("/generated_app/ScreenFeatures")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/ScreenFeatures?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ScreenFeatureId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/ScreenFeatures/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateProjectInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ProjectInstance.json"))
        .when()
        .post("/generated_app/Projects")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsProject() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ProjectInstance.json"))
        .when()
        .post("/generated_app/Projects")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Projects?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ProjectId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Projects/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateFrontendScreenInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("FrontendScreenInstance.json"))
        .when()
        .post("/generated_app/FrontendScreens")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsFrontendScreen() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("FrontendScreenInstance.json"))
        .when()
        .post("/generated_app/FrontendScreens")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/FrontendScreens?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).FeScreenId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/FrontendScreens/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateSiteMapInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("SiteMapInstance.json"))
        .when()
        .post("/generated_app/SiteMaps")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsSiteMap() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("SiteMapInstance.json"))
        .when()
        .post("/generated_app/SiteMaps")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/SiteMaps?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).SiteMapId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/SiteMaps/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateModelFileInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ModelFileInstance.json"))
        .when()
        .post("/generated_app/ModelFiles")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsModelFile() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ModelFileInstance.json"))
        .when()
        .post("/generated_app/ModelFiles")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/ModelFiles?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ModelId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/ModelFiles/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateDatabaseInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("DatabaseInstance.json"))
        .when()
        .post("/generated_app/Databases")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsDatabase() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("DatabaseInstance.json"))
        .when()
        .post("/generated_app/Databases")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Databases?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).DbId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Databases/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateThemeInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ThemeInstance.json"))
        .when()
        .post("/generated_app/Themes")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsTheme() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ThemeInstance.json"))
        .when()
        .post("/generated_app/Themes")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Themes?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ThemeId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Themes/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateFETechnologyInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("FETechnologyInstance.json"))
        .when()
        .post("/generated_app/FETechnologies")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsFETechnology() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("FETechnologyInstance.json"))
        .when()
        .post("/generated_app/FETechnologies")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/FETechnologies?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).FeTechId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/FETechnologies/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateFrontendAppInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("FrontendAppInstance.json"))
        .when()
        .post("/generated_app/FrontendApps")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsFrontendApp() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("FrontendAppInstance.json"))
        .when()
        .post("/generated_app/FrontendApps")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/FrontendApps?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).AppId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/FrontendApps/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateSettingsInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("SettingsInstance.json"))
        .when()
        .post("/generated_app/Settings")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsSettings() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("SettingsInstance.json"))
        .when()
        .post("/generated_app/Settings")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/Settings?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).SettingsId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/Settings/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateGitHubCredsInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("GitHubCredsInstance.json"))
        .when()
        .post("/generated_app/GitHubCreds")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsGitHubCreds() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("GitHubCredsInstance.json"))
        .when()
        .post("/generated_app/GitHubCreds")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/generated_app/GitHubCreds?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).GitHubCredId", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/generated_app/GitHubCreds/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
           
       
  
  
  
  
 
  @AfterEach
  void  teardown() {
    jdbcTemplate.execute("DELETE FROM generated_app.User");
    jdbcTemplate.execute("DELETE FROM generated_app.Capability");
    jdbcTemplate.execute("DELETE FROM generated_app.BETechnology");
    jdbcTemplate.execute("DELETE FROM generated_app.Document");
    jdbcTemplate.execute("DELETE FROM generated_app.BackendApp");
    jdbcTemplate.execute("DELETE FROM generated_app.Industry");
    jdbcTemplate.execute("DELETE FROM generated_app.ScreenFlow");
    jdbcTemplate.execute("DELETE FROM generated_app.ScreenFeature");
    jdbcTemplate.execute("DELETE FROM generated_app.Project");
    jdbcTemplate.execute("DELETE FROM generated_app.FrontendScreen");
    jdbcTemplate.execute("DELETE FROM generated_app.SiteMap");
    jdbcTemplate.execute("DELETE FROM generated_app.ModelFile");
    jdbcTemplate.execute("DELETE FROM generated_app.Database");
    jdbcTemplate.execute("DELETE FROM generated_app.Theme");
    jdbcTemplate.execute("DELETE FROM generated_app.FETechnology");
    jdbcTemplate.execute("DELETE FROM generated_app.FrontendApp");
    jdbcTemplate.execute("DELETE FROM generated_app.Settings");
    jdbcTemplate.execute("DELETE FROM generated_app.GitHubCreds");
     jdbcTemplate.execute("DELETE FROM generated_app.FrontendAppCapabilities");
     jdbcTemplate.execute("DELETE FROM generated_app.ProjectBackendApps");
     jdbcTemplate.execute("DELETE FROM generated_app.FrontendAppThemes");
     jdbcTemplate.execute("DELETE FROM generated_app.FrontendAppAllScreens");
     jdbcTemplate.execute("DELETE FROM generated_app.ProjectFrontendApps");
     jdbcTemplate.execute("DELETE FROM generated_app.FrontendAppMetaTags");
     jdbcTemplate.execute("DELETE FROM generated_app.BackendAppCapabilities");
     jdbcTemplate.execute("DELETE FROM generated_app.SettingsGitHubAccounts");
     jdbcTemplate.execute("DELETE FROM generated_app.BETechnologyFeatures");
     jdbcTemplate.execute("DELETE FROM generated_app.FETechnologyFeatures");
     jdbcTemplate.execute("DELETE FROM generated_app.ProjectCapabilities");
     jdbcTemplate.execute("DELETE FROM generated_app.ScreenFlowFlow");
     jdbcTemplate.execute("DELETE FROM generated_app.FrontendScreenFeatures");
     jdbcTemplate.execute("DELETE FROM generated_app.SiteMapFlows");

    RestAssuredMockMvc.reset();
  }
}
