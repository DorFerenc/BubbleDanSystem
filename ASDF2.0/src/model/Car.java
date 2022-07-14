package model;

public class Car {
	
	private String licencePlateNumber;
	private int windowNumber;
	private int viaNumber;
	
	public Car(String licencePlateNumber, int windowNumber) throws Exception{
		checkLicencePlateNumber(licencePlateNumber);
		checkWindowNumber(windowNumber);
		this.licencePlateNumber= licencePlateNumber;
		this.windowNumber = windowNumber;
	}
	
	public Car(String licencePlateNumber, int windowNumber, int viaNumber) throws Exception{
		checkLicencePlateNumber(licencePlateNumber);
		checkWindowNumber(windowNumber);
		this.licencePlateNumber= licencePlateNumber;
		this.windowNumber = windowNumber;
		this.viaNumber = viaNumber;
	}
	
	public String getLicencePlateNumber() {
		return licencePlateNumber;
	}
	public void setLicencePlateNumber(String licencePlateNumber) {
		this.licencePlateNumber = licencePlateNumber;
	}
	public int getWindowNumber() {
		return windowNumber;
	}
	public void setWindowNumber(int windowNumber) {
		this.windowNumber = windowNumber;
	}
	public int getViaNumber() {
		return viaNumber;
	}
	public void setViaNumber(int viaNumber) {
		this.viaNumber = viaNumber;
	}
	
	private boolean checkWindowNumber(int windowNumber) throws Exception{
		if (windowNumber <1 || windowNumber> 500) {
			throw new Exception("Window Number must be between 1 and 500 digits.");
		}	
		return true;
	}
	private boolean checkLicencePlateNumber(String licencePlateNumber) throws Exception{
		if (licencePlateNumber.length() <7 || licencePlateNumber.length() > 8) {
			throw new Exception("Licence Plate Number must have 8 or 7 digits.");
		}
		if (!(licencePlateNumber.matches("[0-9]+"))) {
			throw new Exception("ID must have only digits.");
		}
		
		if (Integer.parseInt(licencePlateNumber) == 0) {
			throw new Exception("ID cant be 000000000.");
		}
		return true;
	}

	@Override
	public String toString() {
		return "Car:	Licence Plate Number: " + licencePlateNumber + ", Window Number: " + windowNumber + ", Via Number: "
				+ viaNumber;
	}
	
}
