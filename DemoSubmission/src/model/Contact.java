package model;

import java.util.regex.Pattern;

public class Contact {
	public enum eContactType {LOST, EVENT, BOTH};
	
	private int serial;
	private String name;
	private String email;
	private String phone;
	private eContactType represent;
	
	public Contact(int serial, String name, String email, String phone, int temp) throws Exception {
		super();
		this.checkName(name);
		this.checkConnection(email);
		this.checkConnection(phone);
		this.serial = serial;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.represent = eContactType.values()[temp];
	}
	
	public Contact( String name, String email, String phone,int temp) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.represent = eContactType.values()[temp];
	}

	
	private boolean checkName(String name) throws Exception{
		if (name.length() > 40) {
			throw new Exception("Name is too long (Max 40)");
		}
		if (!Pattern.matches("[a-zA-Z ]+", name)) {
			throw new Exception("Name must contain only letters");
		}
		if (!(name.indexOf("  ") == (-1))) {
			throw new Exception("cant contain more than one white spaces");
		}
		return true;
	}
	private boolean checkConnection(String name) throws Exception{
		if (name.length() > 40) {
			throw new Exception("email is too long (Max 40)");
		}
		return true;
	}
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public eContactType getRepresent() {
		return represent;
	}
	public int getRepresent2() {
		return represent.ordinal();
	}

	public void setRepresent(int temp) {
		this.represent = eContactType.values()[temp];
	}
	
	
}