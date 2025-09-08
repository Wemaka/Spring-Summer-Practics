package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.enums.ParcelStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "status_history")
public class StatusHistory extends BaseEntity {
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ParcelStatus status;

	@Column(nullable = false)
	private LocalDateTime timestamp;

	@ManyToOne(optional = false)
	@JoinColumn(name = "location_id", nullable = false)
	private Address location;

	@ManyToOne(optional = false)
	@JoinColumn(name = "parcel_id", nullable = false)
	private Parcel parcel;

	public StatusHistory(ParcelStatus status, LocalDateTime timestamp, Address location) {
		this.status = status;
		this.timestamp = timestamp;
		this.location = location;
	}

	protected StatusHistory() {}

	public ParcelStatus getStatus() {
		return status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public Address getLocation() {
		return location;
	}

	public Parcel getParcel() {
		return parcel;
	}

	public void setStatus(ParcelStatus status) {
		this.status = status;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public void setParcel(Parcel parcel) {
		this.parcel = parcel;
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
