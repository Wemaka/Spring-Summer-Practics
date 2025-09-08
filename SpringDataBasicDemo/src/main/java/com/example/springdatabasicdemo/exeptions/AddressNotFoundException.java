package com.example.springdatabasicdemo.exeptions;

public class AddressNotFoundException extends RuntimeException {
	public AddressNotFoundException(Integer id) {
		super("Address not found: " + id);
	}
}
