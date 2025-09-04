package com.example.springdatabasicdemo.exeptions;

public class ParcelNotFoundException extends RuntimeException {
	public ParcelNotFoundException() {
		super("Parcel not found: ");
	}
	public ParcelNotFoundException(Integer id) {
		super("Parcel not found: " + id);
	}
}
