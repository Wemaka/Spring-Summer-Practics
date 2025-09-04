package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.enums.ParcelStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parcels")
public class Parcel extends BaseEntity {
	@Column(name = "tracking_number", unique = true, nullable = false, length = 30)
	private String trackingNumber;

	@ManyToOne(optional = false)
	@JoinColumn(name = "sender_id", nullable = false)
	private User sender;

	@ManyToOne(optional = false)
	@JoinColumn(name = "receiver_id", nullable = false)
	private User receiver;

	@ManyToOne(optional = false)
	@JoinColumn(name = "destination_id", nullable = false)
	private Address destination;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ParcelStatus status;

	@OneToMany(mappedBy = "parcel", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StatusHistory> history = new ArrayList<>();

	public Parcel(String trackingNumber, User sender, User receiver, Address destination, ParcelStatus status) {
		this.trackingNumber = trackingNumber;
		this.sender = sender;
		this.receiver = receiver;
		this.destination = destination;
		this.status = status;
	}

	protected Parcel() {}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public User getSender() {
		return sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public Address getDestination() {
		return destination;
	}

	public ParcelStatus getStatus() {
		return status;
	}

	public List<StatusHistory> getHistory() {
		return history;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public void setDestination(Address destination) {
		this.destination = destination;
	}

	public void setStatus(ParcelStatus status) {
		this.status = status;
	}

	public void setHistory(List<StatusHistory> history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "Package{" +
				"id=" + id +
				", trackingNumber='" + trackingNumber + '\'' +
				", status=" + status +
				'}';
	}
}
