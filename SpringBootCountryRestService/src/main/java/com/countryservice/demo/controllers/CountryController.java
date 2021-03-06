package com.countryservice.demo.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.services.CountryService;

@RestController
public class CountryController {
	
	//CountryService countryService=new CountryService();
	
	
	@Autowired
	CountryService countryService;  //Using Autowired, No need to define object...Just declare variable and use it as object
	
	//MappingMethods
	
	@GetMapping("/getcountries")
	public List getCountries()
	
	{
		
		return countryService.getAllCountries();
		
	}
	
	
	
	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value="id") int id)
	
	{
		try {
		
		Country country =  countryService.getCountryByID(id);
		return new ResponseEntity<Country>(country,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryName)
	
	{
		
		try {
			
			Country country =  countryService.getCountryByName(countryName);
			return new ResponseEntity<Country>(country,HttpStatus.OK);
			}
			catch(Exception e)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		
		
		//return countryService.getCountryByName(countryName);
		
	}
	
	
	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country country)
	
	{
		
		return countryService.addCountry(country);
		
	}
	
	
	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="id") int id, @RequestBody Country country)
	
	{
		try {
		 Country existCountry= countryService.getCountryByID(id);
		 existCountry.setCountryName(country.getCountryName());
		 existCountry.setCountryCapital(country.getCountryCapital());
		 
		 Country updated_country=countryService.updateCountry(existCountry);
		 
		 return new ResponseEntity<Country>(updated_country,HttpStatus.OK);
		}
		
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		
	}
	
	
	@DeleteMapping("/deletecountry/{id}")
	public ResponseEntity<Country> deleteCountry(@PathVariable(value="id") int id)
	
	{
		Country country = null;
		try {
			
			country=countryService.getCountryByID(id);
			countryService.deleteCountry(country);
			
			
		} catch (NoSuchElementException e) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Country>(country, HttpStatus.OK);		
	}
	

}
