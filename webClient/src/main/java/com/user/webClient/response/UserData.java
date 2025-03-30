package com.user.webClient.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserData {

    private int userId;
	private String name;
	private String email;
	private long phone;
	private String description;
}
