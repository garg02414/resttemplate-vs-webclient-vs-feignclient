package com.example.UseRestTemplate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.example.UseRestTemplate.dto.User;
import com.example.UseRestTemplate.dto.UserData;
import com.example.UseRestTemplate.dto.UserDto;


@RestController
public class UserController {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("/getuserData/{userId}")
	public UserData getUser(@PathVariable int userId) {
		
		UserData user = new UserData();
		try {
			
			User getUser = restTemplate.getForObject("http://localhost:8086/getById/" + userId, User.class);
			
			user.setUserId(getUser.getUserId());
			user.setName(getUser.getName());
			user.setEmail(getUser.getEmail());
			user.setPhone(getUser.getPhone());
			user.setDescription("using rest template to retrive data");
			
			
		} catch (ResourceAccessException e) {
		    System.out.println("Connection timeout: " + e.getMessage());
		}
		return user;
		
	}
	
	@PostMapping("/saveUser")
	public void saveUser(@RequestBody UserDto userDto) {
		restTemplate.postForObject("http://localhost:8086/user/save", userDto, void.class);
		
	}
	
	@PutMapping("/update/{userId}")
	public void updateUser(@PathVariable int userId, @RequestBody UserDto userDto) {
		restTemplate.put("http://localhost:8086/update/" + userId, userDto);
	}
	
	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable int userId) {
		restTemplate.delete("http://localhost:8086/deleteById/" + userId);
		
	}
}




















