package com.user.feign.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.feign.dto.User;
import com.user.feign.dto.UserDto;

@FeignClient(name = "user-service", url = "http://localhost:8086")
public interface UserFeign {

	@PostMapping("/user/save")
	void saveUser(@RequestBody UserDto user);
	
	@GetMapping("/getById/{userId}")
	User getUserById(@PathVariable("userId") int id);
	
	@GetMapping("/findAll")
	public List<User> findAllUser();
	
	@PutMapping("/update/{userId}")
	public void updateUser(@RequestBody UserDto userDto, @PathVariable int userId);
	
	@DeleteMapping("/deleteById/{userId}")
	public void deleteUser(@PathVariable int userId);
}
