package com.example.springdatabasicdemo.services.impl;


import com.example.springdatabasicdemo.exeptions.AddressNotFoundException;
import com.example.springdatabasicdemo.exeptions.ParcelNotFoundException;
import com.example.springdatabasicdemo.dtos.AddressDto;
import com.example.springdatabasicdemo.dtos.ParcelDto;
import com.example.springdatabasicdemo.dtos.StatusHistoryDto;
import com.example.springdatabasicdemo.enums.ParcelStatus;
import com.example.springdatabasicdemo.models.Address;
import com.example.springdatabasicdemo.models.Parcel;
import com.example.springdatabasicdemo.models.StatusHistory;
import com.example.springdatabasicdemo.repositories.AddressRepository;
import com.example.springdatabasicdemo.repositories.ParcelRepository;
import com.example.springdatabasicdemo.repositories.StatusHistoryRepository;
import com.example.springdatabasicdemo.services.StatusHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class StatusHistoryServiceImpl implements StatusHistoryService<Integer> {
	@Autowired
	private StatusHistoryRepository statusHistoryRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ParcelRepository parcelRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public StatusHistoryDto createStatusHistory(StatusHistoryDto statusHistoryDto) {
		StatusHistory sh = modelMapper.map(statusHistoryDto, StatusHistory.class);
		int locationId = sh.getLocation().getId();
		int parcelId = sh.getParcel().getId();

		if (locationId != 0) {
			Address a = addressRepository.findById(locationId).orElseThrow(
					() -> new AddressNotFoundException(locationId)
			);
			sh.setLocation(a);
		}
		if (parcelId != 0) {
			Parcel p = parcelRepository.findById(parcelId).orElseThrow(ParcelNotFoundException::new);
			sh.setParcel(p);
		}

		return modelMapper.map(statusHistoryRepository.save(sh), StatusHistoryDto.class);
	}

	@Override
	public StatusHistoryDto createStatusHistory(ParcelStatus parcelStatus, AddressDto addressDto) {
		StatusHistory sh = new StatusHistory(
				parcelStatus,
				LocalDateTime.now(),
				modelMapper.map(addressDto, Address.class)
		);

		return modelMapper.map(statusHistoryRepository.save(sh), StatusHistoryDto.class);
	}

	@Override
	public List<StatusHistoryDto> getParcelHistoryByParcelId(Integer id) {
		return statusHistoryRepository.findAllStatusHistoriesByParcelId(id).stream().map(
				sh -> modelMapper.map(sh, StatusHistoryDto.class)
		).toList();
	}
}
