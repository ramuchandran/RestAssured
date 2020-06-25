package com.vcit.restassured;

import org.junit.BeforeClass;
import org.junit.Test;

import groovy.json.JsonSlurper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class TestFootballData {

	@BeforeClass
    public static void setupRestAssured() {
        RestAssured.baseURI = "http://api.football-data.org";
        RestAssured.basePath = "/v2/";
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                addHeader("X-Auth-Token", "94a18cc28ba64ab79f56bd258b4740fe").
                addHeader("X-Response-Control", "minified")
                .build();
        RestAssured.requestSpecification = requestSpecification;
    }
	
	@Test
	public void testTeamName() {
		given().
		when().
	    	get("/teams/57").
	    then().
	    	body("name", equalTo("Arsenal FC"));
	}
	
	@Test
	public void extractSingleValue_findSingleTeamName() {
	    Response response = get("teams/57");
	    String teamName = response.path("name");
	    System.out.println(teamName);
	}
	
	@Test
	public void extractSingleValue_findSingleTeamName_specifyJsonPath() {
	   Response response = get("teams/57");
	   JsonPath jsonPath = new JsonPath(response.asString());
	   String teamName = jsonPath.get("name");
	   System.out.println(teamName);
	   
	   //OR get everything with a single line of code
	   
	   teamName = get("teams/66").path("name");
	   System.out.println(teamName);
	}
	
//	@Test
//	public void extractListOfValues_findAllTeamNames() {
//	    Response response = get("competitions/426/teams");
//	    ArrayList<String> allTeamNames = response.path("teams.name");
//	    System.out.println(allTeamNames);
//	}
//	
//	@Test
//	public void extractListOfMapsOfElements_findAllTeamData() {
//	    Response response = get("competitions/426/teams");
//	    ArrayList<Map<String,?>> allTeamData = response.path("teams");
//	    System.out.println(allTeamData);
//	}
	
//	@Test
//	public void testTODOWIthGroovy() throws Exception {
//		JsonSlurper slurper = new JsonSlurper();
//		ClassLoader classLoader = getClass().getClassLoader();
//		File file = new File(classLoader.getResource("sample.json").getFile());
//		String content = new String(Files.readAllBytes(file.toPath()));
//		Object obj = slurper.parseText(content);
//		System.out.println(obj);
//	}
	
	@Test
	public void extractMapOfElementsWithFind_findAllTeamDataForSingleTeam() {
	    Response response = get("teams");
	    System.out.println(response.asString());
	    System.out.println();
	    Map<String,?> allTeamDataForSingleTeam = response.path("teams.find { it.name == 'Blackburn Rovers FC' }");
	    System.out.println(allTeamDataForSingleTeam);
	}
	
//	@Test
//	public void extractSingleValueWithFind_findAPlayerWithACertainJerseyNumber() {
//
//	    Response response = get("teams/66/players");
//	    System.out.println(response.asString());
//	    String certainPlayer = response.path("players.find { it.jerseyNumber == 20 }.name");
//	    System.out.println(certainPlayer);
//	}
	
	@Test
	public void extractSingleValueWithFind() {

	    Response response = get("/competitions/DED");
	    System.out.println(response.asString());
	    String startDate = response.path("seasons.find { it.id == 481 }.startDate");
	    Integer matchday = response.path("seasons.find { it.id == 481 }.currentMatchday");
	    System.out.println(startDate);
	    System.out.println(matchday);
	}
}
