package MVC;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import application.MainPageClassView;
import factory.OficeEmployee;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Car;
import model.Contact;
import model.Driver;
import model.EventReport;
import model.LostReport;
import model.Model;
import model.MyReportMailSender;
import model.LostReport.lostStatus;

public class Controller implements  ModelEventsListener, UIEventsListener {
	private Model model;
	private MainPageClassView bigView;

	public Controller(Model m, MainPageClassView bigView, int type) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.model = m;
		this.bigView = bigView;

		bigView.registerListener(this);
		bigView.typeSet(type);
		model.registerListener(this);
	}

	public void editAction(Object O, String fxmlName) {
		bigView.setMyActionScreenToEdit(fxmlName, O);
	}
	
	public void doEditandAdd(Object O, String fxmlName) {
		bigView.setMyActionScreenToEditandAdd(fxmlName, O);
	}

	public void successClose(String msg, int num) {
		bigView.successCloseMe(msg, num);
	}

	@Override
	public void updateCarList() {
		bigView.initViewData();
	}

	@Override
	public void updateDriverList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEmployeeList() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean initCar(String licencePlateNumber, int windowNumber) throws SQLException, Exception {
		if(model.initCar(licencePlateNumber, windowNumber))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean initDriver(String License_Number, String First_Name, String Last_name) throws Exception {
		/*model.initDriver(License_Number, First_Name, Last_name);
		return true;*/
		if(model.initDriver(License_Number, First_Name, Last_name))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean initEmployee(String ID, String First_Name, String Last_name, Date Birthdate, String typeOf)
			throws Exception {
		/*model.initEmployee(ID, First_Name, Last_name, Birthdate, typeOf);
		return true;*/
		if(model.initEmployee(ID, First_Name, Last_name, Birthdate, typeOf))
		{
			bigView.initViewData();
		}
		return true;

	}

	public boolean initEventReport(String Title, Date thedate, int driver_serial, int car_Via_Number,
			String car_License_Plate_number, String bakar_Id, String location, String theDescription,
			String damageDescription, String passengerDescription) throws Exception {
		/*model.initEventReport(Title, thedate, driver_License_Number, car_Via_Number, car_License_Plate_number, bakar_Id, location, theDescription, damageDescription, passengerDescription);
		return true;
		 */
		if(model.initEventReport(Title, thedate, driver_serial, car_Via_Number, car_License_Plate_number, bakar_Id, location, theDescription, damageDescription, passengerDescription))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean initLostReport(Date thedate, String itemDescription, int driver_serial,
			int car_Via_Number, String car_License_Plate_number,String bakar_id, int isItemValuable, String theStatus, Date foundDate,
			Date ControlDeliveredDate, Date customerDeliveredDate) throws Exception {
		/*model.initLostReport(thedate, itemDescription, driver_License_Number, car_Via_Number, car_License_Plate_number, isItemValuable, theStatus, foundDate, ControlDeliveredDate, customerDeliveredDate);
		return true;
		 */
		if(model.initLostReport(thedate, itemDescription, driver_serial, car_Via_Number, car_License_Plate_number,bakar_id,
				isItemValuable, theStatus, foundDate, ControlDeliveredDate, customerDeliveredDate))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public Vector<Car> showAllCars() throws SQLException, Exception {
		return model.showAllCars();
	}
	/*
	public int[] viewAsksForAllWindowNumbers() throws SQLException, Exception {
		return model.getAllWindowCarNumbers();
	}
	 */
	@Override
	public Vector<Driver> showAllDrivers() throws SQLException, Exception {
		return model.showAllDrivers();
	}
	/*
	public String[] viewAsksForAlldriverNames() throws SQLException, Exception {
		return model.showAllNamesDriver();
	}*/

	@Override
	public Vector<OficeEmployee> showAllEmployees() throws SQLException, Exception {
		return model.showAllEmployees();
	}

	@Override
	public Vector<OficeEmployee> showAllEmployeeType(String type) throws SQLException, Exception {
		return model.showAllEmployeeType(type);
	}

	@Override
	public Vector<EventReport> showAllEventReport() throws SQLException, Exception {
		return model.showAllEventReport();
	}

	@Override
	public Vector<LostReport> showAllLostReport() throws SQLException, Exception {
		return model.showAllLostReport();
	}
	/*
	public String[] viewAsksForAllBakarNames() throws SQLException, Exception {
		return model.showAllNamesBakar();
	}
	 */
	@Override
	public boolean deleteCar(int via_number) throws SQLException, Exception {
		/*model.deleteCar(via_number);
		return true;*/
		if(model.deleteCar(via_number))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean deleteDriver(int driver_serial) throws SQLException, Exception {
		/*model.deleteDriver(License_Number);
		return true;*/
		if(model.deleteDriver(driver_serial))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean deleteEmployee(String id) throws SQLException, Exception {
		/*model.deleteEmployee(id);
		return true;*/
		if(model.deleteEmployee(id))
		{
			bigView.initViewData();
		}
		return true;

	}

	@Override
	public boolean deleteEventReport(int reportID) throws SQLException, Exception {
		if(model.deleteEventReport(reportID))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean deleteLostReport(int reportID) throws SQLException, Exception {
		if(model.deleteLostReport(reportID))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean updateEmployee(String ID, String First_Name, String Last_name, Date Birthdate, String typeOf)
			throws SQLException, Exception {
		/*model.updateEmployee(ID, First_Name, Last_name, Birthdate, typeOf);
		return true;*/
		if(model.updateEmployee(ID, First_Name, Last_name, Birthdate, typeOf))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean updateCar(String licencePlateNumber, int windowNumber, int viaNumber)
			throws SQLException, Exception {
		/*model.updateCar(licencePlateNumber, windowNumber, viaNumber);
		return true;*/
		if(model.updateCar(licencePlateNumber, windowNumber, viaNumber))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean updateDriver(int driver_serial,String License_Number, String First_Name, String Last_name)
			throws SQLException, Exception {
		/*model.updateDriver(License_Number, First_Name, Last_name);
		return true;*/
		if(model.updateDriver(driver_serial,License_Number, First_Name, Last_name))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean updateEventReport(int eventId, String Title, Date thedate, int driver_serial, int car_Via_Number,
			String car_License_Plate_number, String bakar_Id, String location, String theDescription,
			String damageDescription, String passengerDescription) throws SQLException, Exception {
		/*model.updateEventReport(Title, thedate, driver_License_Number, car_Via_Number, car_License_Plate_number, bakar_Id, location, theDescription, damageDescription, passengerDescription);
		return true;
		 */
		if(model.updateEventReport(eventId, Title, thedate, driver_serial, car_Via_Number, car_License_Plate_number
				, bakar_Id, location, theDescription, damageDescription, passengerDescription))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean updateLostReport(int eventID,Date thedate, String itemDescription, int driver_serial,
			int car_Via_Number, String car_License_Plate_number,String bakar_Id, int isItemValuable, String theStatus, Date foundDate,
			Date ControlDeliveredDate, Date customerDeliveredDate) throws SQLException, Exception {
		/*model.updateLostReport(eventID,thedate, itemDescription, driver_License_Number, car_Via_Number, car_License_Plate_number,bakar_Id
				, isItemValuable, theStatus, foundDate, ControlDeliveredDate, customerDeliveredDate);
		return true;
		 */
		if(model.updateLostReport(eventID,thedate, itemDescription, driver_serial, car_Via_Number, car_License_Plate_number,bakar_Id
				, isItemValuable, theStatus, foundDate, ControlDeliveredDate, customerDeliveredDate))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean initContact(String name, String email, String phone, int represent) throws SQLException, Exception {
		if(model.initContact(name, email, phone, represent))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean updateContact(int serial, String name, String email, String phone, int represent)
			throws SQLException, Exception {
		if(model.updateContact(serial, name, email, phone, represent))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public boolean deleteContact(int serial) throws SQLException, Exception {
		if(model.deleteContact(serial))
		{
			bigView.initViewData();
		}
		return true;
	}

	@Override
	public Vector<Contact> showAllContact() throws SQLException, Exception {
		return model.showAllContact();
	}

}
