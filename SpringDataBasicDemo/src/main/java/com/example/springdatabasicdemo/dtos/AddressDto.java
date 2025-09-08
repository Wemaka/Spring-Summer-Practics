package com.example.springdatabasicdemo.dtos;

public class AddressDto {
	private int id;
	private String street;
	private String city;
	private String postalCode;
	private String country;

	public AddressDto(int id, String street, String city, String postalCode, String country) {
		this.id = id;
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}

	public AddressDto() {}

	public int getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "AddressDto{" +
				"id=" + id +
				", street='" + street + '\'' +
				", city='" + city + '\'' +
				", postalCode='" + postalCode + '\'' +
				", country='" + country + '\'' +
				'}';
	}
}
