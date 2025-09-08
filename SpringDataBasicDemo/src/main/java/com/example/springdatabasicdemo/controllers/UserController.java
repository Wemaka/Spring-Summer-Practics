package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.AddressDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.exeptions.UserNotFoundException;
import com.example.springdatabasicdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public UserDto register(@RequestBody UserDto userDto) {
		return userService.register(userDto);
	}

	@GetMapping("/email/{email}")
	public UserDto findUserByEmail(@PathVariable String email) throws Throwable {
		return (UserDto) userService.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
	}

	@GetMapping("/phone/{phone}")
	public UserDto findUserByPhone(@PathVariable String phone) throws Throwable {
		return (UserDto) userService.findUserByPhone(phone).orElseThrow(UserNotFoundException::new);
	}

	@GetMapping("/{id}")
	public UserDto findUserById(@PathVariable Integer id) throws Throwable {
		return (UserDto) userService.findUserById(id).orElseThrow(UserNotFoundException::new);
	}

	@PutMapping("/address/{id}")
	public void updateAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
		userService.updateAddress(id, addressDto);
	}

	@GetMapping
	public Iterable<UserDto> all() {
		return userService.getAllUsers();
	}

	@DeleteMapping("/{id}")
	public void deleteOne(@PathVariable Integer id) {
		userService.deleteUser(id);
	}
}
