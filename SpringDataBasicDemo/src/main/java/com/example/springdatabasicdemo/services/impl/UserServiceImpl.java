package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.exeptions.AddressNotFoundException;
import com.example.springdatabasicdemo.exeptions.UserNotFoundException;
import com.example.springdatabasicdemo.dtos.AddressDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.models.Address;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.repositories.AddressRepository;
import com.example.springdatabasicdemo.repositories.UserRepository;
import com.example.springdatabasicdemo.services.AddressService;
import com.example.springdatabasicdemo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService<Integer> {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AddressService<Integer> addressService;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto register(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		Address address = user.getAddress();

		if (address != null) {
			user.setAddress(findOrCreateAddress(address));
		}

		return modelMapper.map(userRepository.save(user), UserDto.class);
	}

	@Override
	public Optional<UserDto> findUserByEmail(String email) {
		return Optional.ofNullable(modelMapper.map(userRepository.findByEmail(email),
				UserDto.class));
	}

	@Override
	public Optional<UserDto> findUserByPhone(String phone) {
		return Optional.ofNullable(modelMapper.map(userRepository.findByPhone(phone),
				UserDto.class));
	}

	@Override
	public Optional<UserDto> findUserById(Integer id) {
		return Optional.ofNullable(modelMapper.map(userRepository.findById(id), UserDto.class));
	}

	@Override
	public void updateAddress(Integer id, AddressDto addressDto) {
		User user = userRepository.findById(id).orElseThrow(
				() -> new UserNotFoundException(id)
		);
		Address address = modelMapper.map(addressDto, Address.class);

		user.setAddress(findOrCreateAddress(address));

		userRepository.save(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().map(user ->
				modelMapper.map(user, UserDto.class)
		).toList();
	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	private Address findOrCreateAddress(Address address) {
		if (address.getId() != 0) {
			return addressRepository.findById(address.getId()).orElseThrow(
					() -> new AddressNotFoundException(address.getId()));
		} else {
			return addressRepository.save(address);
		}
	}
}
