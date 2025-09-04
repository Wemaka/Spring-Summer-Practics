package com.example.springdatabasicdemo.enums;

public enum ParcelStatus {
	CREATED,
	IN_TRANSIT,
	ARRIVED_AT_PICKUP_POINT,
	DELIVERED,
	;

	public static boolean isValidTransition(ParcelStatus oldStatus, ParcelStatus newStatus) {
		return switch (oldStatus) {
			case CREATED -> newStatus == IN_TRANSIT;
			case IN_TRANSIT -> newStatus == ARRIVED_AT_PICKUP_POINT;
			case ARRIVED_AT_PICKUP_POINT -> newStatus == DELIVERED;
			default -> false;
		};
	}
}
