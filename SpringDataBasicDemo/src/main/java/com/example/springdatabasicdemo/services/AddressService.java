package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.AddressDto;

import java.util.List;
import java.util.Optional;

public interface AddressService<ID> {
	AddressDto createAddress(AddressDto address);

	List<AddressDto> findAddressByCity(String city);

	List<AddressDto> findAddressByCountry(String country);

	List<AddressDto> findAddressByStreet(String street);

	List<AddressDto> findAddressByPostalCode(String postalCode);

	List<AddressDto> getAllAddress();

	Optional<AddressDto> findAddress(ID id);

	void deleteAddress(ID id);

	void deleteAddress(AddressDto addressDto);
}
