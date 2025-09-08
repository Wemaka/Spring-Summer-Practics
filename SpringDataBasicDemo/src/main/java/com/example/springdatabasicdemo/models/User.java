package com.example.springdatabasicdemo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
	@Column(length = 50, nullable = false)
	private String name;

	@Column(unique = true, length = 50)
	private String email;

	@Column(unique = true, length = 20, nullable = false)
	private String phone;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	public User(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}

	protected User() {
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setName(String username) {
		this.name = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name +
				'}';
	}
}
