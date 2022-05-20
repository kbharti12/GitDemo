import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import Files.Payload;

public class AddPlace {

	public static void main(String[] args) throws IOException {
		// Validate Add place API is working as expected
		
		//given: inpiut all details
		//when: submit the API - resource and http method
		//Then: Validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\bhathite\\Desktop\\StudyMaterial\\AddAPI.json")))).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
	
		System.out.println(response);
		
		JsonPath js= new JsonPath(response);//for parsing JSON
		String placeID=js.getString("place_id");
		
		System.out.println(placeID);
		
		String newaddress="70 Summer walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200);
		
		String getPlaceResponse= given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.header("Content-Type","application/json")
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1=new JsonPath(getPlaceResponse);
		
		String actualAddress=js1.getString("address");
		System.out.println(actualAddress);
		System.out.println(actualAddress);
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newaddress);
		
	}

}
