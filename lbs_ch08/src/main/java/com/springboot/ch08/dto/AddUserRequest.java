package com.springboot.ch08.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
	private String email;
	private String password;
}

