package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;

import files.payload;


public class Practices {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// given - all input details
		//when - Submit the API
		//Then - validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)");

	}

}
