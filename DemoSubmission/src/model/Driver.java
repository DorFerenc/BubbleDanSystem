package model;

import java.util.regex.Pattern;

public class Driver {
	private int serial;
	private String firstName;
	private String lastName;
	private String licenceNumber;
	
	public Driver(String firstName, String lastName, String licenceNumber) throws Exception {
		checkName(firstName);
		checkName(lastName);
		checklicenceNumber(licenceNumber);
		this.firstName= firstName;
		this.lastName = lastName;
		this.licenceNumber = licenceNumber;	
	}
	public Driver(int serial,String firstName, String lastName, String licenceNumber) throws Exception {
		checkName(firstName);
		checkName(lastName);
		checklicenceNumber(licenceNumber);
		this.serial = serial;
		this.firstName= firstName;
		this.lastName = lastName;
		this.licenceNumber = licenceNumber;	
	}
	
	
	private boolean checkName(String name) throws Exception{
		if (name.length() > 20) {
			throw new Exception("Name is too long (Max 20)");
		}
		if (!Pattern.matches("[a-zA-Z ]+", name)) {
			throw new Exception("Name must contain only letters");
		}
		if (!(name.indexOf("  ") == (-1))) {
			throw new Exception("cant contain more than one white spaces");
		}
		return true;
	}
	public int getSerial() {
		return serial;
	}


	public void setSerial(int serial) {
		this.serial = serial;
	}
	private boolean checklicenceNumber(String licenceNumber) throws Exception{
		if (!(licenceNumber.length() ==7)) {
			throw new Exception("Window Number must have 7 digits.");
		}	
		if (!(licenceNumber.matches("[0-9]+"))) {
			throw new Exception("ID must have only digits.");
		}
		
		if (Integer.parseInt(licenceNumber) == 0) {
			throw new Exception("ID cant be 000000000.");
		}
		return true;
	}
	
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getLicenceNumber() {
		return licenceNumber;
	}


	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}


	@Override
	public String toString() {
		return "Driver:		First Name: " + firstName + ", Last Name: " + lastName + ", Licence Number: " + licenceNumber ;
	}
	
	
}
