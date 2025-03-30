package com.user.feign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.feign.dto.User;
import com.user.feign.dto.UserData;
import com.user.feign.dto.UserDto;
import com.user.feign.feignclient.UserFeign;

@RestController
public class UserController {

	@Autowired
	private UserFeign userFeign;
	
	@PostMapping("/saveUser")
	public void saveUser(@RequestBody UserDto userDto) {
	
		userFeign.saveUser(userDto);
		
	}
	
	@GetMapping("/getuserData/{userId}")
	public UserData getUser(@PathVariable int userId) {
		
		User getUser = userFeign.getUserById(userId);
		
		UserData user = new UserData();
		user.setUserId(getUser.getUserId());
		user.setName(getUser.getName());
		user.setEmail(getUser.getEmail());
		user.setPhone(getUser.getPhone());
		user.setDescription("using feign client to retrive data");
		
		return user;
	}
	
	@GetMapping("/findAll")
	public List<User> findAllUser(){
		return userFeign.findAllUser();
	}
	
	@PutMapping("/update/{userId}")
	public void updateUser(@PathVariable int userId, @RequestBody UserDto userDto) {
		userFeign.updateUser(userDto, userId);
	}
	
	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userFeign.deleteUser(userId);		
	}

}














