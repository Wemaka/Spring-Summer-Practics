package com.example.springdatabasicdemo.exeptions;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(Integer id) {
		super("User not found: " + id);
	}

	public UserNotFoundException() {
		super("User not found");
	}
}
