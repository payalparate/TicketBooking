package com.training.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.training.constant.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	@NotEmpty(message = "Username is required")
	String userName;
	@NotEmpty(message = "Password is required")
	String password;
	@NotEmpty(message = "Age is required")
	String age;
	@NotEmpty(message = "Contact details is required")
	@Size(max = 10, min = 10)
	String phoneNo;
	Role role;

}
