package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.exeptions.AddressNotFoundException;
import com.example.springdatabasicdemo.dtos.AddressDto;
import com.example.springdatabasicdemo.models.Address;
import com.example.springdatabasicdemo.repositories.AddressRepository;
import com.example.springdatabasicdemo.repositories.StatusHistoryRepository;
import com.example.springdatabasicdemo.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService<Integer> {
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private StatusHistoryRepository statusHistoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AddressDto createAddress(AddressDto addressDto) {
		Address address = modelMapper.map(addressDto, Address.class);
		return modelMapper.map(addressRepository.save(address), AddressDto.class);
	}

	@Override
	public List<AddressDto> findAddressByCity(String city) {
		return map(addressRepository.findAllByCity(city.toLowerCase()));
	}

	@Override
	public List<AddressDto> findAddressByCountry(String county) {
		return map(addressRepository.findAllByCountry(county.toLowerCase()));
	}

	@Override
	public List<AddressDto> findAddressByStreet(String street) {
		return map(addressRepository.findAllByStreet(street.toLowerCase()));
	}

	@Override
	public List<AddressDto> findAddressByPostalCode(String postalCode) {
		return map(addressRepository.findAllByPostalCode(postalCode.toLowerCase()));
	}

	@Override
	public List<AddressDto> getAllAddress() {
		return map(addressRepository.findAll());
	}

	@Override
	public Optional<AddressDto> findAddress(Integer id) {
		return Optional.ofNullable(modelMapper.map(addressRepository.findById(id), AddressDto.class));
	}

	@Override
	public void deleteAddress(Integer id) {
		if (isUsedAddress(id)) {
			throw new IllegalStateException("Cannot deactivate address that is used in packages");
		}

		addressRepository.deleteById(id);
	}

	@Override
	public void deleteAddress(AddressDto addressDto) {
		if (isUsedAddress(addressDto.getId())) {
			throw new IllegalStateException("Cannot deactivate address that is used in packages");
		}

		addressRepository.deleteById(addressDto.getId());
	}

	public boolean isUsedAddress(Integer id) {
		AddressDto address = findAddress(id).orElseThrow(
				() -> new AddressNotFoundException(id)
		);

		return statusHistoryRepository.existsById(address.getId());
	}

	private List<AddressDto> map(List<Address> addresses) {
		return addresses.stream().map(address ->
				modelMapper.map(address, AddressDto.class)
		).toList();
	}
}
