package model;

import java.util.Date;

import factory.OficeEmployee;

public class LostReport {
	public enum lostStatus {
		IN_CAR, DELIVERED_TO_USHER, DELIVERED_TO_CONTROL, DELIVERED_TO_CUSTOMER,
	};

	private int serial;
	private String itemDescription;
	private Driver driver;
	private Car car;
	private boolean isValuable;
	private OficeEmployee bakar;
	private String status;
	private String myStatus;
	private Date foundDate; // TODO auto
	private Date transportDate;
	private Date deliveredDate;

	// TODO change to enum
	public LostReport(String itemDescription, int isValuable, String status, Date foundDate, Date ControlDeliveredDate,
			Date customerDeliveredDate) throws Exception {
		super();
		this.checkString(itemDescription, 40);
		this.itemDescription = itemDescription;
		this.isValuable = (isValuable == 1);
		this.status = status;
		this.foundDate = foundDate;
		this.transportDate = ControlDeliveredDate;
		this.deliveredDate = customerDeliveredDate;
		// this.myStatus = mystatus;
	}

	public LostReport(int serial, String itemDescription, int isValuable, String status, Date foundDate,
			Date ControlDeliveredDate, Date customerDeliveredDate) throws Exception {
		super();
		this.checkString(itemDescription, 40);
		this.serial = serial;
		this.itemDescription = itemDescription;
		this.isValuable = (isValuable == 1);
		this.status = status;
		this.foundDate = foundDate;
		this.transportDate = ControlDeliveredDate;
		this.deliveredDate = customerDeliveredDate;
		// this.myStatus = mystatus;
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

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
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
		this.car = car;
	}

	public boolean isValuable() {
		return isValuable;
	}

	public void setValuable(boolean isValuable) {
		this.isValuable = isValuable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}

	public Date getTransportDate() {
		return transportDate;
	}

	public void setTransportDate(Date transportDate) {
		this.transportDate = transportDate;
	}

	public Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public OficeEmployee getBakar() {
		return bakar;
	}

	public void setBakar(OficeEmployee bakar) {
		this.bakar = bakar;
	}

	@Override
	public String toString() {
		return "LostReport "
				+((serial!= 0)?("\nSerial: " + serial):"")+ "\nItem Description: " + itemDescription + "\n" + driver + "\nCar: "
				+ car + "\nIs The Item Valuable: " + isValuable + "\nStatus: " + status //+ "\nMy Status: " + myStatus
				+ "\nFound Date: " + foundDate + "\nTransport Date: " + transportDate + "\nDelivered Date: "
				+ deliveredDate;// + "\n\n\n send from ASDF";
	}

}
