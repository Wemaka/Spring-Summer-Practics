package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.dtos.StatusHistoryDto;
import com.example.springdatabasicdemo.enums.ParcelStatus;
import com.example.springdatabasicdemo.models.Parcel;
import com.example.springdatabasicdemo.models.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Integer> {
	Parcel findByTrackingNumber(String trackingNumber);

	boolean existsByTrackingNumber(String trackingNumber);

	List<Parcel> findAllByStatus(ParcelStatus status);

	List<Parcel> findAllByDestinationPostalCode(String postalCode);
}
