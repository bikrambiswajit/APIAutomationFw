package StepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.testUtils;

public class StepDef extends testUtils {

  RequestSpecification res;
  ResponseSpecification resSpec;
  TestDataBuild data = new TestDataBuild();
  Response response;
 static String placeId;

  @Given("Add place payload {string} {string} {string}")
  public void add_place_payload(String name, String language, String address) throws IOException {

    
    res = given().spec(requestSpecification())
    	      .body(data.addPlacePayload(name, language, address));

  }

  @When("User calls {string} using {string} method")
  public void user_calls_using_method(String resource, String httpMethod) {
	  
	  APIResources resApi =  APIResources.valueOf(resource);
	  resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
   
	  if(httpMethod.equalsIgnoreCase("POST")) {
	  response = res.when().post(resApi.getResource())
      .then().spec(resSpec).extract().response();
	  }
	  else if(httpMethod.equalsIgnoreCase("GET")) {
		  response = res.when().get(resApi.getResource())
			      .then().spec(resSpec).extract().response();
	  }
	  else if(httpMethod.equalsIgnoreCase("Delete")) {
		  response = res.when().delete(resApi.getResource())
			      .then().spec(resSpec).extract().response();
	  }
  }

  @Then("API call got success with status code {int}")
  public void api_call_got_success_with_status_code(Integer int1) {

    assertEquals(response.getStatusCode(), 200);
  }

  @Then("{string} in response body is {string}")
  public void in_response_body_is(String keyValue, String expectedValue) {
    
    assertEquals(getJsonItem(response,keyValue), expectedValue);
  }
  
  @Then("Verify place_id created maps to {string} using {string}")
  public void verify_place_id_created_maps_to_using(String expName, String resource) throws IOException {
     placeId = getJsonItem(response,"place_id");
	 res = given().spec(requestSpecification()).queryParam("place_id", placeId);
	user_calls_using_method(resource, "GET");
	String actualname = getJsonItem(response,"name");
	assertEquals(actualname,expName);
  }

  @Given("DeletePlace payload")
  public void delete_place_payload() throws IOException {
     res =  given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
  }
}