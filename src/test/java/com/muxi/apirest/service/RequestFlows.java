package com.muxi.apirest.service;

import static io.restassured.RestAssured.given;

public class RequestFlows {
	
	public void okFlow(String corpo, String path) {
		given().
			contentType("text/html").
			body(corpo).
		when().
			post("http://localhost:8080" + path).
		then().
			assertThat().statusCode(200);
	}
	
	
	public void badRequestFlow(String corpo, String path) {
		given().
			contentType("text/html").
			body(corpo).
		when().
			post("http://localhost:8080" + path).
		then().
			assertThat().statusCode(400);
	}
}
