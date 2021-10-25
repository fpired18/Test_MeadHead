package com.architecteLogicielP11.Test_MeadHead;

public interface TripDistance {
	
	public default double distanceGPS(double latPatient, double lonPatient, double latHospital, double lonHospital) {
		double theta = lonPatient - lonHospital;
		double dist = Math.sin(deg2rad(latPatient)) * Math.sin(deg2rad(latHospital))
				+ Math.cos(deg2rad(latPatient)) * Math.cos(deg2rad(latHospital)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.15 * 1.609344;
		return (dist/10);
	}
	
	private double deg2rad(double deg) {
		return (deg * Math.PI) / 180.0;
	}

	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

}
