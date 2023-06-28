package com.asan.couirertracking.utility;

public class CalculateUtil {

	public final static double AVERAGE_RADIUS_OF_EARTH = 6371;

	/**
	 * This method calculate distance person current location between venue location.
	 * @param courierLat
	 * @param courierLng
	 * @param venueLat
	 * @param venueLng
	 * @return
	 */
	public static double calculateDistance(double courierLat, double courierLng, double venueLat, double venueLng) {

		double latDistance = Math.toRadians(courierLat - venueLat);
		double lngDistance = Math.toRadians(courierLng - venueLng);

		double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
				(Math.cos(Math.toRadians(courierLat))) *
						(Math.cos(Math.toRadians(venueLat))) *
						(Math.sin(lngDistance / 2)) *
						(Math.sin(lngDistance / 2));

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return (double) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));

	}
}
