package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.AddressDto;
import com.example.springdatabasicdemo.dtos.StatusHistoryDto;
import com.example.springdatabasicdemo.enums.ParcelStatus;

import java.util.List;

public interface StatusHistoryService<ID> {
	StatusHistoryDto createStatusHistory(StatusHistoryDto statusHistoryDto);

	StatusHistoryDto createStatusHistory(ParcelStatus parcelStatus, AddressDto addressDto);

	List<StatusHistoryDto> getParcelHistoryByParcelId(ID id);
}
