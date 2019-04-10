package com.muxi.apirest;

import static io.restassured.RestAssured.given; 
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class TerminalPostTest {

	@Test
	public void PostValidation() {
		String corpo = "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN";
		given().
			contentType("text/html").
			body(corpo).
		when().
			post("http://localhost:8080/v1/terminal").
		then().
			statusCode(200)
			.body("body", equalTo(corpo));
	}

}
