package MVC;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

import factory.OficeEmployee;
import model.Car;
import model.Contact;
import model.Driver;
import model.EventReport;
import model.LostReport;
import model.LostReport.lostStatus;

public interface UIEventsListener {
	public boolean initCar(String licencePlateNumber, int windowNumber) throws SQLException, Exception;
	public boolean initDriver(String License_Number, String First_Name, String Last_name) throws Exception;
	public boolean initEmployee(String ID, String First_Name, String Last_name, Date Birthdate, String typeOf)throws Exception;
	public boolean initEventReport(String Title,Date thedate, int driver_serial, int car_Via_Number,
			String car_License_Plate_number, String bakar_Id, String location, String theDescription, String damageDescription, String passengerDescription) throws Exception;	
	public boolean initLostReport(Date thedate, String itemDescription, int driver_serial, int car_Via_Number,
			String car_License_Plate_number,String bakar_Id, int isItemValuable, String theStatus, Date foundDate, Date ControlDeliveredDate, Date customerDeliveredDate) throws Exception;


	public Vector<Car> showAllCars() throws SQLException, Exception;	
	public Vector<Driver> showAllDrivers() throws SQLException, Exception;
	public Vector<OficeEmployee> showAllEmployees() throws SQLException, Exception;
	public Vector<OficeEmployee> showAllEmployeeType(String type) throws SQLException, Exception;
	public Vector<EventReport> showAllEventReport() throws SQLException, Exception;
	public Vector<LostReport> showAllLostReport() throws SQLException, Exception;
	public Vector<Contact> showAllContact() throws SQLException, Exception;
	public boolean deleteCar(int via_number) throws SQLException, Exception;
	public boolean deleteDriver(int driver_serial) throws SQLException, Exception;
	public boolean deleteEmployee(String id) throws SQLException, Exception;
	public boolean deleteEventReport(int reportID) throws SQLException, Exception;
	public boolean deleteLostReport(int reportID) throws SQLException, Exception;
	public boolean updateEmployee(String ID, String First_Name, String Last_name, Date Birthdate, String typeOf) throws SQLException, Exception;
	public boolean updateCar(String licencePlateNumber, int windowNumber, int viaNumber) throws SQLException, Exception;
	public boolean updateDriver(int serial,String License_Number, String First_Name, String Last_name) throws SQLException, Exception;
	public boolean updateEventReport(int eventId, String Title,Date thedate, int driver_serial, int car_Via_Number,
			String car_License_Plate_number, String bakar_Id, String location, String theDescription, String damageDescription, String passengerDescription) throws SQLException, Exception;
	public boolean updateLostReport(int eventID,Date thedate, String itemDescription, int driver_serial, int car_Via_Number,
			String car_License_Plate_number,String bakar_Id, int isItemValuable, String theStatus, Date foundDate, Date ControlDeliveredDate, Date customerDeliveredDate) throws SQLException, Exception;

	public void editAction(Object O, String fxmlName);
	public void successClose(String msg, int num);
	public void doEditandAdd(Object O, String fxmlName);

	public boolean initContact(String name, String email, String phone,int represent) throws SQLException, Exception;
	public boolean updateContact(int serial, String name, String email, String phone,int represent) throws SQLException, Exception;
	public boolean deleteContact(int serial) throws SQLException, Exception;
	/*	public int[] viewAsksForAllWindowNumbers() throws SQLException, Exception;
	public String[] viewAsksForAlldriverNames() throws SQLException, Exception;
	public String[] viewAsksForAllBakarNames() throws SQLException, Exception;*/
}
