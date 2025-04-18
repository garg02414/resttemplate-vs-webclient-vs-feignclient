package com.user.webClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
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

import reactor.core.publisher.Mono;

@RestController
public class UserController {
	
	@Autowired
	private WebClient.Builder webClient;

//	@GetMapping("/getuserData/{userId}")
//	public UserData getUser(@PathVariable int userId) {
//		
//		User getUser = webClient.build().get()
//				.uri("http://localhost:8086/getById/" + userId)
//				.retrieve()
//				.bodyToMono(User.class)
//				.block();
//		
//		UserData user = new UserData();
//		user.setUserId(getUser.getUserId());
//		user.setName(getUser.getName());
//		user.setEmail(getUser.getEmail());
//		user.setPhone(getUser.getPhone());
//		user.setDescription("using web client to retrive data");
//		
//		return user;
//	}
	
//	// getUserById -  here is example how can we handle exception using exchnageToMono
//	@GetMapping("/getuserData/{userId}")
//	public Mono<UserData> getUser(@PathVariable int userId) {
//		
//		return webClient.build().get()
//				.uri("http://localhost:8086/getById/" + userId)
//				.exchangeToMono(response->{
//					if (response.statusCode().is2xxSuccessful()) {
//						return response.bodyToMono(User.class);
//					}
//					else if (response.statusCode().is4xxClientError()) {
//						return Mono.error(new RuntimeException("User not found" + userId));
//					}
//					else if (response.statusCode().is5xxServerError()) {
//						return Mono.error(new RuntimeException("Internal Server Error occured "));
//					}
//					else {
//						return Mono.error(new RuntimeException("Unexpected Error occured"));
//					}
//				})
//				.map(getUser -> {
//					 UserData user = new UserData();
//		                user.setUserId(getUser.getUserId());
//		                user.setName(getUser.getName());
//		                user.setEmail(getUser.getEmail());
//		                user.setPhone(getUser.getPhone());
//		                user.setDescription("Using WebClient to retrieve data");
//		                return user;
//				 }).switchIfEmpty(Mono.error(new RuntimeException("No user found")));
//				
//	}
	
	// getUserById -  here is example how can we handle exception without exchnageToMono
	@GetMapping("/getuserData/{userId}")
	public UserData getUser(@PathVariable int userId) {
		try {
			User getUser = webClient.build()
					.get()
					.uri("http://localhost:8086/getById/" + userId)
					.retrieve()
					.onStatus(HttpStatusCode::is4xxClientError, response -> 
                    	Mono.error(new RuntimeException("User not found: " + userId)))
					.onStatus(HttpStatusCode::is5xxServerError, response -> 
						Mono.error(new RuntimeException("Internal Server Error")))
					.bodyToMono(User.class)
					.block();
			
			if (getUser == null) {
	            throw new RuntimeException("No user data found for ID: " + userId);
	        }
			
			UserData user = new UserData();
			user.setUserId(getUser.getUserId());
			user.setName(getUser.getName());
			user.setEmail(getUser.getEmail());
			user.setPhone(getUser.getPhone());
			user.setDescription("using web client to retrive data");
			
			return user;
			
		} catch (RuntimeException  e) {
			 System.err.println("Error while fetching user: " + e.getMessage());
		        throw e;
		}
		
	}
	
//	@PostMapping("/saveUser")
//	public void saveUser(@RequestBody UserDto userDto) {
//		webClient.build().post()
//		.uri("http://localhost:8086/user/save")
//		.bodyValue(userDto)
//		.retrieve()
//		.bodyToMono(void.class)
//		.block();
//	
//	}
	
	@PostMapping("/saveUser")
	public Mono<Void> saveUser(@RequestBody UserDto userDto) {
	    return webClient.build()
	            .post()
	            .uri("http://localhost:8086/user/save")
	            .bodyValue(userDto)
	            .exchangeToMono(clientResponse -> {
	                if (clientResponse.statusCode().is2xxSuccessful()) {
	                    return Mono.empty();
	                } else {
	                    return clientResponse.createException().flatMap(Mono::error);
	                }
	            });
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
























