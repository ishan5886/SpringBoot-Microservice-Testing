package com.countryservice.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.repositories.CountryRepository;
import com.countryservice.demo.services.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {ServiceMockitoTests.class})   //Specify class is SpringBoot Test Class
public class ServiceMockitoTests {
	
	@Mock  //Mock the CountryRepository Class
	CountryRepository countryrep;

	@InjectMocks //invoke all methods in the class
	CountryService countryService;
	
	public List<Country> mycountries; 
	
	
	@Test  					//Specify Junit Test method
	@Order(1)				//Order of execution
	public void test_getAllCountries()
	{
		List<Country> mycountries= new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washington"));
		mycountries.add(new Country(3, "UK", "London"));
		mycountries.add(new Country(4, "Austria", "Vienna"));
		mycountries.add(new Country(5, "Germany", "Berlin"));
		when(countryrep.findAll()).thenReturn(mycountries);  //Mocking Repository method external dependency
		assertEquals(5,countryService.getAllCountries().size());
	}
	
	
	
	@Test  					//Specify Junit Test method
	@Order(2)				//Order of execution
	public void test_getCountryByID()
	{	
		List<Country> mycountries= new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washington"));
		mycountries.add(new Country(3, "UK", "London"));
		mycountries.add(new Country(4, "Austria", "Vienna"));
		mycountries.add(new Country(5, "Germany", "Berlin"));
		
		int countryID=1;
		when(countryrep.findAll()).thenReturn(mycountries);  //Mocking Repository method external dependency	
		assertEquals(countryID,countryService.getCountryByID(countryID).getId());		
		
	}

	
	@Test  					//Specify Junit Test method
	@Order(3)				//Order of execution
	public void test_getCountryByName()
	{	
		List<Country> mycountries= new ArrayList<Country>();
		mycountries.add(new Country(1, "India", "Delhi"));
		mycountries.add(new Country(2, "USA", "Washington"));
		mycountries.add(new Country(3, "UK", "London"));
		mycountries.add(new Country(4, "Austria", "Vienna"));
		mycountries.add(new Country(5, "Germany", "Berlin"));
		
		String countryName="India";
		when(countryrep.findAll()).thenReturn(mycountries);  //Mocking Repository method external dependency	
		assertEquals(countryName,countryService.getCountryByName(countryName).getCountryName());	
		
	}
	
	
	
	@Test  					//Specify Junit Test method
	@Order(4)				//Order of execution
	public void test_addCountry()
	{	
		Country country = new Country(6,"France","Paris");
		
		when(countryrep.save(country)).thenReturn(country);  //Mocking Repository method external dependency	
		assertEquals(country,countryService.addCountry(country));	
		
	}
	
	
	@Test  					//Specify Junit Test method
	@Order(5)				//Order of execution
	public void test_updateCountry()
	{	
		
		
		Country country = new Country(5,"Poland","Warsaw");
		
		when(countryrep.save(country)).thenReturn(country);  //Mocking Repository method external dependency	
		assertEquals(country,countryService.updateCountry(country));
		
		
	}


	@Test  					//Specify Junit Test method
	@Order(6)				//Order of execution
	public void test_deleteCountry()
	{	
		
		
		Country country = new Country(5,"Poland","Warsaw");	
		countryService.deleteCountry(country);
		verify(countryrep,times(1)).delete(country);
		
		
	}

}











