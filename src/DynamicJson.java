import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	private static final String ResuableMethods = null;

	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		String resp=given().header("Content-Type", "application/json")
				.body(Payload.addBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
	JsonPath js=ReusableMethods.rawToJson(resp);
	String id=js.get("ID");
	System.out.println(id);
		
		
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		return new Object[][] {{"aastkdd","12602"},{"zsdstkcd","1280676"}};
	}
		
	@Test
	public void getBookDetails() {
	RestAssured.baseURI="http://216.10.245.166";
	String repsponse=given().log().all().queryParam("AuthorName","somename").header("Content-Type","application/json")
	.when().get("/Library/GetBook.php")
	.then().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js=ReusableMethods.rawToJson(repsponse);
	//System.out.println(js);
	
	}
	}

