package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testUtils {
	
	RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		//RestAssured.baseURI = getGlobalProperties("baseURI");
		if (req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("frame.log"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseURI")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
		} return req;
	}

	public String getGlobalProperties(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Pratik\\eclipse-workspace\\AutoApiFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		String outProperty = prop.getProperty(key);
		return outProperty;
	}
	
	public String getJsonItem(Response response, String keyValue) {
		String responseS = response.asString();
	    JsonPath js = new JsonPath(responseS);
	    return js.getString(keyValue).toString();
	}
}

