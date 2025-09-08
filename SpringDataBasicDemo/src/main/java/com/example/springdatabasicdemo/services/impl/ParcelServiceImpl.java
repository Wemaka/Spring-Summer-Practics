package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.AddressDto;
import com.example.springdatabasicdemo.dtos.ParcelDto;
import com.example.springdatabasicdemo.dtos.StatusHistoryDto;
import com.example.springdatabasicdemo.dtos.UserDto;
import com.example.springdatabasicdemo.enums.ParcelStatus;
import com.example.springdatabasicdemo.exeptions.AddressNotFoundException;
import com.example.springdatabasicdemo.exeptions.ParcelNotFoundException;
import com.example.springdatabasicdemo.exeptions.UserNotFoundException;
import com.example.springdatabasicdemo.models.Address;
import com.example.springdatabasicdemo.models.Parcel;
import com.example.springdatabasicdemo.models.StatusHistory;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.repositories.AddressRepository;
import com.example.springdatabasicdemo.repositories.ParcelRepository;
import com.example.springdatabasicdemo.repositories.StatusHistoryRepository;
import com.example.springdatabasicdemo.repositories.UserRepository;
import com.example.springdatabasicdemo.services.ParcelService;
import com.example.springdatabasicdemo.services.StatusHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.springdatabasicdemo.enums.ParcelStatus.isValidTransition;

@Service
@Transactional
public class ParcelServiceImpl implements ParcelService<Integer> {
	@Autowired
	private ParcelRepository parcelRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private StatusHistoryRepository statusHistoryRepository;
	@Autowired
	private StatusHistoryService statusHistoryService;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ParcelDto createParcel(ParcelDto parcelDto) {
		if (parcelRepository.existsByTrackingNumber(parcelDto.getTrackingNumber())) {
			throw new IllegalArgumentException("Tracking number already exists");
		}

		Parcel parcel = modelMapper.map(parcelDto, Parcel.class);
		User sender = parcel.getSender();
		User receiver = parcel.getReceiver();
		Address destination = parcel.getDestination();

		parcel.setSender(findOrCreateUser(sender));
		parcel.setReceiver(findOrCreateUser(receiver));
		if (destination.getId() != 0) {
			parcel.setDestination(
					addressRepository.findById(destination.getId()).orElseThrow(
							() -> new AddressNotFoundException(destination.getId()))
			);
		} else {
			parcel.setDestination(addressRepository.save(destination));
		}

		StatusHistory statusHistory = new StatusHistory(
				parcel.getStatus(), LocalDateTime.now(), parcel.getDestination()
		);
		statusHistory.setParcel(parcel);
		parcel.getHistory().add(modelMapper.map(statusHistory, StatusHistory.class));

		return modelMapper.map(parcelRepository.save(parcel), ParcelDto.class);
	}

	@Override
	public Optional<ParcelDto> findParceBylId(Integer id) {
		return Optional.ofNullable(modelMapper.map(parcelRepository.findById(id), ParcelDto.class));
	}

	@Override
	public void updateStatus(String trackingNumber, StatusHistoryDto statusHistoryDto) {
//		ParcelDto parcelDto = findParcelByTrackingNumber(trackingNumber)
//				.orElseThrow(ParcelNotFoundException::new);
//		Parcel parcel = modelMapper.map(parcelDto, Parcel.class);

		Parcel parcel = parcelRepository.findByTrackingNumber(trackingNumber);

		if (parcel == null) {
			throw new ParcelNotFoundException();
		}

		if (!isValidTransition(parcel.getStatus(), statusHistoryDto.getStatus())) {
			throw new IllegalArgumentException("Invalid status transition");
		}
		parcel.setStatus(statusHistoryDto.getStatus());

		StatusHistory statusHistory = new StatusHistory(
				parcel.getStatus(), LocalDateTime.now(),
				findOrCreateAddress(modelMapper.map(statusHistoryDto.getLocation(), Address.class))
		);
		statusHistory.setParcel(parcel);

		parcel.getHistory().add(statusHistory);
//		parcel.getHistory().add(modelMapper.map(statusHistory, StatusHistory.class));

		parcelRepository.save(parcel);
	}

	@Override
	public Optional<ParcelDto> findParcelByTrackingNumber(String trackingNumber) {
		return Optional.ofNullable(modelMapper.map(
				parcelRepository.findByTrackingNumber(trackingNumber), ParcelDto.class));
	}

	@Override
	public List<ParcelDto> findParcelByStatus(ParcelStatus status) {
		return map(parcelRepository.findAllByStatus(status));
	}

	@Override
	public List<ParcelDto> findParcelsByPostalCode(String postalCode) {
		return map(parcelRepository.findAllByDestinationPostalCode(postalCode));
	}

	@Override
	public List<ParcelDto> getAllParcel() {
		return map(parcelRepository.findAll());
	}

	private List<ParcelDto> map(List<Parcel> addresses) {
		return addresses.stream().map(address ->
				modelMapper.map(address, ParcelDto.class)
		).toList();
	}

	private User findOrCreateUser(User user) {
		if (user.getId() != 0) {
			return userRepository.findById(user.getId()).orElseThrow(
					() -> new UserNotFoundException(user.getId()));
		} else {
			return userRepository.save(user);
		}
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
