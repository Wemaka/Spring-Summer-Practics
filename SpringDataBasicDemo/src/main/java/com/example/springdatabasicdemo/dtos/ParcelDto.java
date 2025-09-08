package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.ParcelStatus;

import java.util.ArrayList;
import java.util.List;

public class ParcelDto {
	private int id;
	private String trackingNumber;
	private UserDto sender;
	private UserDto receiver;
	private AddressDto destination;
	private ParcelStatus status = ParcelStatus.CREATED;
	private List<StatusHistoryDto> history = new ArrayList<>();

	public ParcelDto(int id, String trackingNumber, UserDto sender, UserDto receiver, AddressDto destination) {
		this.id = id;
		this.trackingNumber = trackingNumber;
		this.sender = sender;
		this.receiver = receiver;
		this.destination = destination;
	}

	public ParcelDto() {
	}

	public int getId() {
		return id;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public UserDto getSender() {
		return sender;
	}

	public UserDto getReceiver() {
		return receiver;
	}

	public AddressDto getDestination() {
		return destination;
	}

	public ParcelStatus getStatus() {
		return status;
	}

	public List<StatusHistoryDto> getHistory() {
		return history;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public void setSender(UserDto sender) {
		this.sender = sender;
	}

	public void setReceiver(UserDto receiver) {
		this.receiver = receiver;
	}

	public void setDestination(AddressDto destination) {
		this.destination = destination;
	}

	public void setStatus(ParcelStatus status) {
		this.status = status;
	}

	public void setHistory(List<StatusHistoryDto> history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "Parcel{" +
				"id=" + id +
				", trackingNumber='" + trackingNumber + '\'' +
				", status=" + status +
				'}';
	}
}
