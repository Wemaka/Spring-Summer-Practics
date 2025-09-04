package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.AddressDto;
import com.example.springdatabasicdemo.dtos.ParcelDto;
import com.example.springdatabasicdemo.dtos.StatusHistoryDto;
import com.example.springdatabasicdemo.enums.ParcelStatus;
import com.example.springdatabasicdemo.exeptions.ParcelNotFoundException;
import com.example.springdatabasicdemo.models.StatusHistory;
import com.example.springdatabasicdemo.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parcel")
public class ParcelController {
	@Autowired
	private ParcelService parcelService;

	@PostMapping
	public ParcelDto createParcel(@RequestBody ParcelDto parcel) {
		return parcelService.createParcel(parcel);
	}

	@GetMapping("/{id}")
	public ParcelDto findParceBylId(@PathVariable Integer id) throws Throwable {
		return (ParcelDto) parcelService.findParceBylId(id).orElseThrow(
				() -> new ParcelNotFoundException(id)
		);
	}

	@PutMapping("/update_status/{trackingNumber}")
	public void updateStatus(@PathVariable String trackingNumber, @RequestBody StatusHistoryDto status) {
		parcelService.updateStatus(trackingNumber, status);
	}

	@GetMapping("/tracking/{trackingNumber}")
	public ParcelDto findParcelByTrackingNumber(@PathVariable String trackingNumber) throws Throwable {
		return (ParcelDto) parcelService.findParcelByTrackingNumber(trackingNumber).orElseThrow(
				ParcelNotFoundException::new
		);
	}

	@GetMapping("/status/{status}")
	public Iterable<ParcelDto> findParcelByStatus(@PathVariable ParcelStatus status) {
		return parcelService.findParcelByStatus(status);
	}

	@GetMapping("/postal_code/{postalCode}")
	public Iterable<ParcelDto> findParcelsByPostalCode(@PathVariable String postalCode) {
		return parcelService.findParcelsByPostalCode(postalCode);
	}

	@GetMapping
	public Iterable<ParcelDto> all() {
		return parcelService.getAllParcel();
	}
}
