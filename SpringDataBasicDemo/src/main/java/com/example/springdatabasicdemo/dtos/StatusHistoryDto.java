package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.ParcelStatus;

import java.time.LocalDateTime;

public class StatusHistoryDto {
	private int id;
	private ParcelStatus status;
	private LocalDateTime timestamp;
	private AddressDto location;

	public StatusHistoryDto(int id, ParcelStatus status, AddressDto location) {
		this.id = id;
		this.status = status;
		this.location = location;
	}

	public StatusHistoryDto() {}

	public int getId() {
		return id;
	}

	public ParcelStatus getStatus() {
		return status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public AddressDto getLocation() {
		return location;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStatus(ParcelStatus status) {
		this.status = status;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setLocation(AddressDto location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "StatusHistory{" +
				"id=" + id +
				", status=" + status +
				", timestamp=" + timestamp +
				", location=" + location +
				'}';
	}
}
