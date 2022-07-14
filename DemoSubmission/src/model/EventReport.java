package model;

import java.util.Date;
import java.util.regex.Pattern;

import factory.OficeEmployee;

public class EventReport {
	private int reportID;
	private String title;
	private Date date;
	private Driver driver;
	private Car car;
	private String licencePlateNumber;
	private OficeEmployee bakar;
	private String location;
	private String Description;
	private String damageDescription;
	private String passengerDescription;

	public EventReport(int reportID, String title, Date date, String location, String description,
			String damageDescription, String passengerDescription) throws Exception {
		this.checkString(title, 25);
		this.checkString(location, 30);
		this.checkString(description, 50);
		this.checkString(damageDescription, 50);
		this.checkString(passengerDescription, 50);
		this.reportID = reportID;
		this.title = title;
		this.date = date;
		this.location = location;
		this.Description = description;
		if (damageDescription == null)
			this.damageDescription = "";
		else
			this.damageDescription = damageDescription;
		if (passengerDescription == null)
			this.passengerDescription = "";
		else
			this.passengerDescription = passengerDescription;
	}

	public EventReport(String title, Date date, String location, String description, String damageDescription,
			String passengerDescription) throws Exception {
		this.checkString(title, 25);
		this.checkString(location, 30);
		this.checkString(description, 50);
		this.checkString(damageDescription, 50);
		this.checkString(passengerDescription, 50);
		this.title = title;
		this.date = date;
		this.location = location;
		this.Description = description;
		if (damageDescription == null)
			this.damageDescription = "";
		else
			this.damageDescription = damageDescription;
		if (passengerDescription == null)
			this.passengerDescription = "";
		else
			this.passengerDescription = passengerDescription;
	}

	private boolean checkString(String name, int maxlen) throws Exception {
		if (name != null) {
			if (name.length() > maxlen)
				throw new Exception("Name is too long (Max " + maxlen + ")");
			if (!(name.indexOf("  ") == (-1)))
				throw new Exception("cant contain more than one white spaces");
		}
		return true;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.setLicencePlateNumber(car.getLicencePlateNumber());
		this.car = car;
	}

	public String getLicencePlateNumber() {
		return licencePlateNumber;
	}

	public void setLicencePlateNumber(String licencePlateNumber) {
		this.licencePlateNumber = licencePlateNumber;
	}

	public OficeEmployee getBakar() {
		return bakar;
	}

	public void setBakar(OficeEmployee bakar) {
		this.bakar = bakar;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDamageDescription() {
		return damageDescription;
	}

	public void setDamageDescription(String damageDescription) {
		this.damageDescription = damageDescription;
	}

	public String getPassengerDescription() {
		return passengerDescription;
	}

	public void setPassengerDescription(String passengerDescription) {
		this.passengerDescription = passengerDescription;
	}

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	@Override
	public String toString() {
		return "EventReport " + ((reportID != 0) ? ("\nReport ID: " + reportID) : "") + "\nTitle: " + title + "\nDate: "
				+ date + "\n" + driver + "\nCar : " + car + "\nLocation: " + location + "\nDescription: " + Description
				+ "\nDamage Description: " + damageDescription + "\nPassenger Description: " + passengerDescription
				+ "\nEmployee In Charge: " + bakar;
	}

}
