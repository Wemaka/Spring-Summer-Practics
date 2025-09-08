package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.AddressDto;
import com.example.springdatabasicdemo.dtos.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService<ID> {
	UserDto register(UserDto userDto);

	Optional<UserDto> findUserByEmail(String email);

	Optional<UserDto> findUserByPhone(String phone);

	Optional<UserDto> findUserById(ID id);

	void updateAddress(ID id, AddressDto groupDto);

	List<UserDto> getAllUsers();

	void deleteUser(ID id);
}
