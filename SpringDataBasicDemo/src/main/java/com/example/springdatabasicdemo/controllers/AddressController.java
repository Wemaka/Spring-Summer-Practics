package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.AddressDto;
import com.example.springdatabasicdemo.exeptions.AddressNotFoundException;
import com.example.springdatabasicdemo.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addressService;

	@PostMapping
	public AddressDto createAddress(@RequestBody AddressDto addressDto) {
		return addressService.createAddress(addressDto);
	}

	@GetMapping("/city/{city}")
	public Iterable<AddressDto> findAddressByCity(@PathVariable String city) {
		return addressService.findAddressByCity(city);
	}

	@GetMapping("/country/{country}")
	public Iterable<AddressDto> findAddressByCountry(@PathVariable String country) {
		return addressService.findAddressByCountry(country);
	}

	@GetMapping("/street/{street}")
	public Iterable<AddressDto> findAddressByStreet(@PathVariable String street) {
		return addressService.findAddressByStreet(street);
	}

	@GetMapping("/postal_code/{postalCode}")
	public Iterable<AddressDto> findAddressByPostalCode(@PathVariable String postalCode) {
		return addressService.findAddressByPostalCode(postalCode);
	}

	@GetMapping
	public Iterable<AddressDto> all() {
		return addressService.getAllAddress();
	}

	@GetMapping("/{id}")
	public AddressDto one(@PathVariable Integer id) throws Throwable {
		return (AddressDto) addressService.findAddress(id).orElseThrow(
				() -> new AddressNotFoundException(id)
		);
	}

	@DeleteMapping("/{id}")
	public void deleteAddress(@PathVariable Integer id) {
		addressService.deleteAddress(id);
	}
}
