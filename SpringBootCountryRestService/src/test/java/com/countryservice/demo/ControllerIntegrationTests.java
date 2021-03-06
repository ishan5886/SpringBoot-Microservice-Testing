package com.countryservice.demo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {ControllerIntegrationTests.class})
public class ControllerIntegrationTests {
	
	
	@Test
	@Order(1)
	void getAllCountriesIntegrationTest() throws JSONException
	{
		
		
		String expected= "[\n"
				+ "    {\n"
				+ "        \"id\": 1,\n"
				+ "        \"countryName\": \"India\",\n"
				+ "        \"countryCapital\": \"Delhi\"\n"
				+ "    },\n"
				+ "    {\n"
				+ "        \"id\": 2,\n"
				+ "        \"countryName\": \"USA\",\n"
				+ "        \"countryCapital\": \"Washington\"\n"
				+ "    },\n"
				+ "    {\n"
				+ "        \"id\": 3,\n"
				+ "        \"countryName\": \"UK\",\n"
				+ "        \"countryCapital\": \"London\"\n"
				+ "    },\n"
				+ "    {\n"
				+ "        \"id\": 4,\n"
				+ "        \"countryName\": \"Sweden\",\n"
				+ "        \"countryCapital\": \"Stockholm\"\n"
				+ "    },\n"
				+ "    {\n"
				+ "        \"id\": 5,\n"
				+ "        \"countryName\": \"France\",\n"
				+ "        \"countryCapital\": \"Paris\"\n"
				+ "    },\n"
				+ "    {\n"
				+ "        \"id\": 6,\n"
				+ "        \"countryName\": \"Switzerland\",\n"
				+ "        \"countryCapital\": \"Zurich\"\n"
				+ "    },\n"
				+ "    {\n"
				+ "        \"id\": 7,\n"
				+ "        \"countryName\": \"Austria\",\n"
				+ "        \"countryCapital\": \"Vienna\"\n"
				+ "    }\n"
				+ "]";
		
		
		TestRestTemplate restTemplate=new TestRestTemplate();
		
		ResponseEntity<String> response= restTemplate.getForEntity("http://localhost:8080/getcountries", String.class);  //Rest Template for specifying type of requests
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		
		JSONAssert.assertEquals(expected, response.getBody(), false);
	
	}
	
	

}
