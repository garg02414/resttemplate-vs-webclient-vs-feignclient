package com.user.webClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.user.webClient.dto.User;
import com.user.webClient.dto.UserDto;
import com.user.webClient.response.UserData;

@RestController
public class UserController {
	
	@Autowired
	private WebClient.Builder webClient;

	@GetMapping("/getuserData/{userId}")
	public UserData getUser(@PathVariable int userId) {
		
		User getUser = webClient.build().get()
				.uri("http://localhost:8086/getById/" + userId)
				.retrieve()
				.bodyToMono(User.class)
				.block();
		
		UserData user = new UserData();
		user.setUserId(getUser.getUserId());
		user.setName(getUser.getName());
		user.setEmail(getUser.getEmail());
		user.setPhone(getUser.getPhone());
		user.setDescription("using web client to retrive data");
		
		return user;
	}
	
	@PostMapping("/saveUser")
	public void saveUser(@RequestBody UserDto userDto) {
		webClient.build().post()
		.uri("http://localhost:8086/user/save")
		.bodyValue(userDto)
		.retrieve()
		.bodyToMono(void.class)
		.block();
	
	}
	
	@PutMapping("/update/{userId}")
	public void updateUser(@PathVariable int userId, @RequestBody UserDto userDto) {
		webClient.build()
		.put()
		.uri("http://localhost:8086/update/" + userId)
		.bodyValue(userDto)
		.retrieve()
		.bodyToMono(User.class).block();
	}
	
	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable int userId) {
		webClient.build()
		.delete()
		.uri("http://localhost:8086/deleteById/" + userId)
		.retrieve()
		.bodyToMono(void.class)
		.block();		
	}
}
























