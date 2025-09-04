package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.AddressDto;
import com.example.springdatabasicdemo.dtos.ParcelDto;
import com.example.springdatabasicdemo.dtos.StatusHistoryDto;
import com.example.springdatabasicdemo.enums.ParcelStatus;

import java.util.List;
import java.util.Optional;

public interface ParcelService<ID> {
	ParcelDto createParcel(ParcelDto parcelDto);

	Optional<ParcelDto> findParceBylId(ID id);

	void updateStatus(String trackingNumber, StatusHistoryDto statusHistoryDto);

	Optional<ParcelDto> findParcelByTrackingNumber(String trackingNumber);

	List<ParcelDto> findParcelByStatus(ParcelStatus status);

	List<ParcelDto> findParcelsByPostalCode(String postalCode);

	List<ParcelDto> getAllParcel();
}
