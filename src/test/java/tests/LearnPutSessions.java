package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import files.payload;


public class LearnPutSessions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// given - all input details
		//when - Submit the API
		//Then - validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asPrettyString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);// for parsing json
		String placeId = js.getString("place_id");
		
		System.out.println(placeId);
	
		// Update Place
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\"825c26de8280a6160290b23a4ace9255\",\r\n"
				+ "\"address\":\"15 Summer walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

	}

}
