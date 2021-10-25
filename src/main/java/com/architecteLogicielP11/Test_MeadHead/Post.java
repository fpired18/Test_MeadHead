package com.architecteLogicielP11.Test_MeadHead;

public class Post {
	
	private int id;
	private String specialityGroup;
	private String speciality;
	private String hospitalCenter;
	private int numberOfBeds;
	private int numberOfPatients;
	private int numberOfBedsAvailable;
	private double geographicalPositionLat;
	private double geographicalPositionLon;
	private int distance;

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpecialityGroup() {
		return specialityGroup;
	}

	public void setSpecialityGroup(String specialityGroup) {
		this.specialityGroup = specialityGroup;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getHospitalCenter() {
		return hospitalCenter;
	}

	public void setHospitalCenter(String hospitalCenter) {
		this.hospitalCenter = hospitalCenter;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public int getNumberOfPatients() {
		return numberOfPatients;
	}

	public void setNumberOfPatients(int numberOfPatients) {
		this.numberOfPatients = numberOfPatients;
	}

	public int getNumberOfBedsAvailable() {
		return numberOfBeds - numberOfPatients;
	}

	public void setNumberOfBedsAvailable(int numberOfBedsAvailable) {
		this.numberOfBedsAvailable = numberOfBedsAvailable;
	}
	
	public double getGeographicalPositionLat() {
		return geographicalPositionLat;
	}

	public void setGeographicalPositionLat(double geographicalPositionLat) {
		this.geographicalPositionLat = geographicalPositionLat;
	}

	public double getGeographicalPositionLon() {
		return geographicalPositionLon;
	}

	public void setGeographicalPositionLon(double geographicalPositionLon) {
		this.geographicalPositionLon = geographicalPositionLon;
	}

	@Override
	public String toString() {
		return "Post [specialityGroup=" + specialityGroup + ", speciality=" + speciality + ", hospitalCenter="
				+ hospitalCenter + ", numberOfBeds=" + numberOfBeds + ", numberOfPatients=" + numberOfPatients
				+ ", numberOfBedsAvailable=" + numberOfBedsAvailable + ", geographicalPositionLat="
				+ geographicalPositionLat + ", geographicalPositionLon=" + geographicalPositionLon + ", distance="
				+ distance + "]";
	}

}
