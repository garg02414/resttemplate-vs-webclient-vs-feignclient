package com.rest.template.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.template.dto.UserDto;
import com.rest.template.entity.User;
import com.rest.template.repository.userRepository;

@RestController
public class UserController {

	@Autowired
	private userRepository userRepository;
	
	@PostMapping("user/save")
	public void saveUser(@RequestBody UserDto user) {
		
		User userEntity = new User();
		userEntity.setName(user.getName());
		userEntity.setPhone(user.getPhone());
		userEntity.setEmail(user.getEmail());
		userRepository.save(userEntity);
		
	}
	
	@GetMapping("/getById/{userId}")
	public Optional<User> getUserById(@PathVariable("userId") int id) {
		return userRepository.findById(id);
		
	}
	
	@GetMapping("/findAll")
	public List<User> findAllUser(){
		return userRepository.findAll();
	}
	
	@PutMapping("/update/{userId}")
	public void updateUser(@RequestBody UserDto userDto, @PathVariable int userId) {
		
		User user = userRepository.findById(userId).get();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		userRepository.save(user);
	}
	
	@DeleteMapping("/deleteById/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userRepository.deleteById(userId);
		
	}
	
}






