package com.countryservice.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.CountryController;
import com.countryservice.demo.services.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {ControllerMockitoTests.class})
public class ControllerMockitoTests {
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> mycountries;
	Country country;
	
	@Test
	@Order(1)
	public void test_getAllCountries() 
	{
		
		List<Country> mycountries= new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washington"));
		mycountries.add(new Country(3, "UK", "London"));
		mycountries.add(new Country(4, "Austria", "Vienna"));
		mycountries.add(new Country(5, "Germany", "Berlin"));
		
		when(countryService.getAllCountries()).thenReturn(mycountries); 
		
		assertEquals(5,countryController.getCountries().size());
		
		
	}

	
	@Test
	@Order(2)
	public void test_getCountryByID() 
	{
		
		country=new Country(2,"USA","Washington");
		int countryID=2;
		
		when(countryService.getCountryByID(countryID)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountryById(countryID);
		
//		assertEquals(200, res.getStatusCode());
		assertEquals(countryID, res.getBody().getId());
		
		
	}
	
	
	
	@Test
	@Order(3)
	public void test_getCountryByName() 
	{
		
		country=new Country(2,"USA","Washington");
		String countryName="USA";
		
		when(countryService.getCountryByName(countryName)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountryByName(countryName);
		
		//assertEquals(200, res.getStatusCode());
		assertEquals(countryName, res.getBody().getCountryName());
		
		
	}
	
	
	
	@Test
	@Order(4)
	public void test_AddCountry() 
	{
		
		
		country=new Country(2,"Turkey","Istanbul");
		when(countryService.addCountry(country)).thenReturn(country);
		
		
		//assertEquals(200, res.getStatusCode());
//		assertEquals(countryName, res.getBody().getCountryName());
		
		
	}
	
	
	
	@Test
	@Order(5)
	public void test_UpdateCountry() 
	{
		
		country=new Country(3,"Japan","Tokyo");
		int countryID=3;
		
		when(countryService.getCountryByID(countryID)).thenReturn(country);
		
		
		when(countryService.updateCountry(country)).thenReturn(country);
		ResponseEntity<Country> res = countryController.updateCountry(countryID, country);
		
		assertEquals(3, res.getBody().getId());
		assertEquals("Japan", res.getBody().getCountryName());
		assertEquals("Tokyo", res.getBody().getCountryCapital());
		
		
		
	}
	
	
	
	@Test
	@Order(6)
	public void test_DeleteCountry() 
	{
		
		country=new Country(3,"Japan","Tokyo");
		int countryID=3;
		
		when(countryService.getCountryByID(countryID)).thenReturn(country);
		
		
		ResponseEntity<Country> res = countryController.deleteCountry(countryID);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
		
		
	}
	
	
	
}
