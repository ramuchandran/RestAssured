package com.vcit.restassured;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;





public class TestCountryInfoWithRestAssured {
	
	@Before
	public void before() {
	    RestAssured.baseURI = "https://restcountries.eu";
	    RestAssured.basePath="/rest/v2/";
	}
	
	@Test
	public void testValidateIndiaLanguage() throws Exception {
		given().
		//already set as setup
		when().
			get("/name/India").
		then().
			statusCode(200).
			body("name", contains("British Indian Ocean Territory", "India")).and().
			body("languages[1].iso639_2[0]", equalTo("hin")).and().
			body("languages[1].iso639_2[1]", equalTo("eng"));
		
	}

	@Test
	public void getAllCountriesLogResonseAndStatusOK() {
		when().
			get("/all").
		then()
//			.log().all().and()
			.statusCode(200);
	}
	
	@Test
	public void whenMeasureResponseTime_thenOK() {
	    Response response = RestAssured.get("/rest/v2/name/Taiwan");
	    long timeInMS = response.time();
	    long timeInSeconds = response.timeIn(TimeUnit.SECONDS);
	    assertEquals(timeInSeconds, timeInMS/1000);
	}
	
	@Test
	public void testValidResonseTime() throws Exception {
		when().
			get("/name/India").
		then().
			log().all().and().
			time(lessThan(1L),TimeUnit.SECONDS);
	}
	
	@Test
	public void testValidateData() throws Exception {
		RestAssured.get("/name/India")
		.then()
//		.statusCode(200)
		.body("name", contains("British Indian Ocean Territory", "India")).and()
		.body("name[1]", equalTo("India"));
	}
	
	
	
}
