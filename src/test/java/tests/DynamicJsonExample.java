package tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import files.ReUsableMethods;
import files.payload;

public class DynamicJsonExample {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {

        RestAssured.baseURI = "http://216.10.245.166";
        Response resp = given().
                header("Content-Type", "application/json").
                body(payload.AddbookDynamic(isbn, aisle)).
                when().
                post("/Library/Addbook.php").
                then().assertThat().statusCode(200).
                extract().response();

        JsonPath js = ReUsableMethods.rawToJsonTwo(resp);
        String id = js.get("ID");
        System.out.println(id);

        // You can add the code to delete the book here if needed
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        return new Object[][] {{"ojfwty", "9363"}, {"cwetee", "4253"}, {"okmfet", "533"}};
    }
}
