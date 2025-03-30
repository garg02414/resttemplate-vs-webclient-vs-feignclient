package com.user.webClient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class User {
	
	private int userId;
	private String name;
	private String email;
	private long phone;
}
