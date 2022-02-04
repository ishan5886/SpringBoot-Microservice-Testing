package com.example.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController     //Invoke class as soon as Restful service request is sent
@RequestMapping("/users")  //Whenever API request sent with resource(here /users) the class will be invoked
public class UsersController 
{
	
//	@GetMapping // Mapping Method with type of Request
//	public String getUsers() 
//	{
//		return "http GET request was sent";
//	
//		
//	}
	
	
	@GetMapping // Mapping Method with type of Request
	public String getUsers(@RequestParam(value="page") int pagenum, @RequestParam(value="limit") int limitnum)   //Request Parameter
	{
		return "http GET request was sent for page: "+pagenum+"  and limit is:"+limitnum;
	
		
	}
	
	
	//GET Request with Query Parameters
	
	@GetMapping(path = "/{userID}") // Mapping Method with type of Request using Path Parameter
	public String getUser(@PathVariable String userID) 
	{
		return "http GET request was sent for userid: "+userID;
	
		
	}
	
	
	

	@PostMapping // Mapping Method with type of Request
	public String createUsers() 
	{
		return "http POST request was sent";
	
		
	}
	
	@PutMapping // Mapping Method with type of Request
	public String updateUsers() 
	{
		return "http PUT request was sent";
	
		
	}
	
	@DeleteMapping // Mapping Method with type of Request
	public String deleteUsers() 
	{
		return "http DELETE request was sent";
	
		
	}
}
