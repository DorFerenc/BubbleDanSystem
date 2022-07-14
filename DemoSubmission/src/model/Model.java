package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;

import MVC.Controller;
import MVC.ModelEventsListener;
import MVC.UIEventsListener;
import SqlConnection.Sql;
import SqlConnection.SqlHelper;
import factory.EmployeeFactory;
import factory.OficeEmployee;
import model.LostReport.lostStatus;

public class Model{

	private Sql mysql;
	private SqlHelper sqlfunc;
	private EmployeeFactory employeeFactory;
	private transient Vector<ModelEventsListener> listeners;
	private MyReportMailSender emailSender;

	
	public Model() throws ClassNotFoundException, SQLException {
		this.mysql = Sql.getMysql();
		this.sqlfunc = new SqlHelper();
		employeeFactory = new EmployeeFactory();
		listeners = new Vector<ModelEventsListener>();
		emailSender = new MyReportMailSender();
	}
	public void registerListener(ModelEventsListener listener) {
		listeners.add(listener);
	}

	public boolean initCar(String licencePlateNumber, int windowNumber) throws SQLException, Exception{
		Car temp = new Car(licencePlateNumber, windowNumber);
		if(mysql.chechViaNum(windowNumber))
		{
			mysql.addNewCar(temp);
			return true;
			//updatecarlist
		}
		else throw new Exception("Window Number is taken");

	}
	public boolean initDriver(String License_Number, String First_Name, String Last_name) throws Exception{
		Driver temp = new Driver(First_Name, Last_name, License_Number);
		mysql.addNewDriver(temp);
		//updatedriverlist
		return true;

	}

	public boolean initEmployee(String ID, String First_Name, String Last_name, Date Birthdate, String typeOf) throws Exception{
		OficeEmployee temp =(OficeEmployee) employeeFactory.getEmployy(typeOf,ID, First_Name, Last_name, Birthdate);
		mysql.addNewEmployee(temp, typeOf);
		//updateEmployee
		return true;	
	}
	
	public boolean initEventReport(String Title,Date thedate, int driver_serial, int car_Via_Number,
			String car_License_Plate_number, String bakar_Id, String location, String theDescription, String damageDescription, String passengerDescription) throws Exception{
		EventReport temp = new EventReport(Title, thedate, location, theDescription,damageDescription,passengerDescription);

		temp.setDriver(sqlfunc.sqlTo1Driver(mysql.getDriverFromKey(driver_serial)));
		temp.setCar(sqlfunc.sqlTo1Car(mysql.getCarFromKey(car_Via_Number)));
		temp.setBakar(sqlfunc.sqlTo1Employee(mysql.getEmployeeFromKey(bakar_Id), employeeFactory));
		mysql.addNewEventReport(temp);
		
		//TODO 
		ResultSet rs = mysql.getAllContactType(1);
		Vector<String> EVec = new Vector<>();
		while(rs.next()) {
			EVec.add(sqlfunc.sqlToEmail(rs));
		}
		emailSender.initSendMail(EVec.toArray(new String[EVec.size()]), "Event Report", temp.toString());
		
		//update EventReport
		return true;

	}
	
	public boolean initLostReport(Date thedate, String itemDescription, int driver_serial, int car_Via_Number,
			String car_License_Plate_number,String bakar_Id, int isItemValuable, String theStatus, Date foundDate, Date ControlDeliveredDate, Date customerDeliveredDate) throws Exception{
		LostReport temp = new LostReport( itemDescription, isItemValuable,
				theStatus, foundDate, ControlDeliveredDate, customerDeliveredDate);
		temp.setDriver(sqlfunc.sqlTo1Driver(mysql.getDriverFromKey(driver_serial)));
		temp.setCar(sqlfunc.sqlTo1Car(mysql.getCarFromKey(car_Via_Number)));
		temp.setBakar(sqlfunc.sqlTo1Employee(mysql.getEmployeeFromKey(bakar_Id), employeeFactory));
		mysql.addNewLostReport(temp);

		ResultSet rs = mysql.getAllContactType(0);
		Vector<String> EVec = new Vector<>();
		while(rs.next()) {
			EVec.add(sqlfunc.sqlToEmail(rs));
		}
		emailSender.initSendMail(EVec.toArray(new String[EVec.size()]), "Lost Report", temp.toString());
		
		return true;
	}
	
	public boolean modelSendsMail(String[] emailReceipients, String emailSubject, String emailText) {
		MyReportMailSender myEmailSender = new MyReportMailSender();
		try {
			myEmailSender.initSendMail(emailReceipients, emailSubject, emailText);
		} catch (Exception e) {
			System.out.println("shiiiiitttt");
			JOptionPane.showInternalMessageDialog(null, "" + e.getMessage() + "\n\n\n");
		}
		return true;
	}
	
	public Vector<Car> showAllCars() throws SQLException, Exception
	{
		Vector<Car> CVec = new Vector<>();
		ResultSet rs=  mysql.getAllCarsFromSql();
		while(rs.next()) {
			CVec.add(sqlfunc.sqlToCar(rs));
		}
		return CVec;

	}
	
	public int[] getAllWindowCarNumbers() throws SQLException, Exception {
		//makes a list of all the window car numbers
		Vector<Car> cVec = showAllCars();
		int[] windowCarNumbersList = new int[cVec.size()];
		for (int i = 0; i < windowCarNumbersList.length; i++) {
			windowCarNumbersList[i] = cVec.get(i).getWindowNumber();
		}
		return windowCarNumbersList;
	}

	public Vector<Driver> showAllDrivers() throws SQLException, Exception
	{
		Vector<Driver> DVec = new Vector<>();
		ResultSet rs=  mysql.getAllDriversFromSql();
		while(rs.next()) {
			DVec.add(sqlfunc.sqlToDriver(rs));
		}
		return DVec;

	}
	
	public String[] showAllNamesDriver() throws SQLException, Exception
	{
		Vector<Driver> DVec = showAllDrivers();
		String nameArr[];
		nameArr = new String[DVec.size()];
		for(int i=0;i<DVec.size() ; i++) {
			nameArr[i] = DVec.get(i).getFirstName()+" "+DVec.get(i).getLastName();
		}
		return nameArr;		
	}
	
	public Vector<OficeEmployee> showAllEmployees() throws SQLException, Exception
	{
		Vector<OficeEmployee> OEVec = new Vector<>();
		ResultSet rs=  mysql.getAllEmployeeFromSql();
		while(rs.next()) {
			OEVec.add(sqlfunc.sqlToEmployee(rs, employeeFactory));
		}
		return OEVec;	
	}
	
	public Vector<OficeEmployee> showAllEmployeeType(String type) throws SQLException, Exception
	{
		Vector<OficeEmployee> OEVec = new Vector<>();
		ResultSet rs=  mysql.getAllEmployeeTypeFromSql(type);
		while(rs.next()) {
			OEVec.add(sqlfunc.sqlToEmployee(rs, employeeFactory));
		}
		return OEVec;
	}
	
	public Vector<EventReport> showAllEventReport() throws SQLException, Exception
	{
		Vector<EventReport> ERVec = new Vector<>();
		ResultSet rs=  mysql.getAllEventReportFromSql();
		while(rs.next()) {
			ERVec.add(sqlfunc.sqlToEventReport(rs));
			ERVec.lastElement().setDriver(sqlfunc.sqlTo1Driver(mysql.getDriverFromKey(rs.getInt("driver_serial"))));
			ERVec.lastElement().setCar(sqlfunc.sqlTo1Car(mysql.getCarFromKey(rs.getInt("car_Via_Number"))));
			ERVec.lastElement().setBakar(sqlfunc.sqlTo1Employee(mysql.getEmployeeFromKey(rs.getString("Employee_Id")), employeeFactory));
		}
		return ERVec;
	}

	public Vector<LostReport> showAllLostReport() throws SQLException, Exception
	{
		Vector<LostReport> LRVec = new Vector<>();
		ResultSet rs=  mysql.getAllLostReportFromSql();
		while(rs.next()) {
			LRVec.add(sqlfunc.sqlToLostReport(rs));
			LRVec.lastElement().setDriver(sqlfunc.sqlTo1Driver(mysql.getDriverFromKey(rs.getInt("driver_serial"))));
			LRVec.lastElement().setCar(sqlfunc.sqlTo1Car(mysql.getCarFromKey(rs.getInt("car_Via_Number"))));
			LRVec.lastElement().setBakar(sqlfunc.sqlTo1Employee(mysql.getEmployeeFromKey(rs.getString("Employee_Id")), employeeFactory));

		}
		return LRVec;
	}

	public String[] showAllNamesBakar() throws SQLException, Exception
	{
		Vector<OficeEmployee> BVec = showAllEmployeeType("Bakar");
		String nameArr[];
		nameArr = new String[BVec.size()];
		for(int i=0;i<BVec.size() ; i++) {
			nameArr[i] = BVec.get(i).getFirstName()+" "+BVec.get(i).getLastName();
		}
		return nameArr;		
	}
	
	public boolean deleteCar(int via_number) throws SQLException, Exception
	{
		mysql.deleteCarOnSql(via_number);
		//update
		return true;
	}

	public boolean deleteDriver(int driver_serial) throws SQLException, Exception
	{
		mysql.deletedriverOnSql(driver_serial);
		//update
		return true;
	}

	public boolean deleteEmployee(String id) throws SQLException, Exception
	{
		mysql.deleteEmployeeOnSql(id);
		//update
		return true;
	}
	public boolean deleteEventReport(int reportID) throws SQLException, Exception
	{
		mysql.deleteEventReportOnSql(reportID);
		//update
		return true;
	}
	public boolean deleteLostReport(int reportID) throws SQLException, Exception
	{
		mysql.deleteLostReportOnSql(reportID);
		//update
		return true;
	}

	public boolean updateEmployee(String ID, String First_Name, String Last_name, Date Birthdate, String typeOf) throws SQLException, Exception {
		OficeEmployee temp =(OficeEmployee) employeeFactory.getEmployy(typeOf,ID, First_Name, Last_name, Birthdate);
		mysql.updateEmployeeOnSql(temp,typeOf);
		//update
		return true;
	}

	public boolean updateCar(String licencePlateNumber, int windowNumber, int viaNumber) throws SQLException, Exception{
		Car temp = new Car(licencePlateNumber, windowNumber, viaNumber);
		//mysql.updateCarOnSql(temp, viaNumber);
		//update
		//return true;
		
		
		if(mysql.chechViaNum(windowNumber))
		{
			mysql.updateCarOnSql(temp, viaNumber);;
			return true;
			//updatecarlist
		}
		else throw new Exception("Window Number is taken");
	}

	public boolean updateDriver(int serial,String License_Number, String First_Name, String Last_name) throws SQLException, Exception{
		Driver temp = new Driver(serial,First_Name, Last_name, License_Number);
		mysql.updateDriverOnSql(temp);
		//update
		return true;
	}

	public boolean updateEventReport(int eventId, String Title,Date thedate, int driver_serial, int car_Via_Number,
			String car_License_Plate_number, String bakar_Id, String location, String theDescription, String damageDescription, String passengerDescription) throws SQLException, Exception{
		EventReport temp = new EventReport(eventId, Title, thedate, location, theDescription,damageDescription, passengerDescription);
		temp.setDriver(sqlfunc.sqlTo1Driver(mysql.getDriverFromKey(driver_serial)));
		temp.setCar(sqlfunc.sqlTo1Car(mysql.getCarFromKey(car_Via_Number)));
		temp.setBakar(sqlfunc.sqlTo1Employee(mysql.getEmployeeFromKey(bakar_Id), employeeFactory));
		mysql.updateEventReportOnSql(temp);
		//update
		ResultSet rs = mysql.getAllContactType(1);
		Vector<String> EVec = new Vector<>();
		while(rs.next()) {
			EVec.add(sqlfunc.sqlToEmail(rs));
		}
		emailSender.initSendMail(EVec.toArray(new String[EVec.size()]), "Update Event Report", temp.toString());
		
		return true;
	}

	public boolean updateLostReport(int eventID,Date thedate, String itemDescription, int driver_serial, int car_Via_Number,
			String car_License_Plate_number,String bakar_Id, int isItemValuable, String theStatus, Date foundDate, Date ControlDeliveredDate, Date customerDeliveredDate) throws SQLException, Exception{
		LostReport temp = new LostReport( eventID,itemDescription, isItemValuable,
				theStatus, foundDate, ControlDeliveredDate, customerDeliveredDate);
		temp.setDriver(sqlfunc.sqlTo1Driver(mysql.getDriverFromKey(driver_serial)));
		temp.setCar(sqlfunc.sqlTo1Car(mysql.getCarFromKey(car_Via_Number)));
		temp.setBakar(sqlfunc.sqlTo1Employee(mysql.getEmployeeFromKey(bakar_Id), employeeFactory));
		mysql.updateLostReportOnSql(temp);

		ResultSet rs = mysql.getAllContactType(0);
		Vector<String> EVec = new Vector<>();
		while(rs.next()) {
			EVec.add(sqlfunc.sqlToEmail(rs));
		}
		emailSender.initSendMail(EVec.toArray(new String[EVec.size()]), "Update Lost Report", temp.toString());
		
		return true;
	}


	public void updateCarList(){
		for(ModelEventsListener l : listeners)
			l.updateCarList();
	}
	public void updateDriverList(){
		for(ModelEventsListener l : listeners)
			l.updateDriverList();


	}
	public void updateEmployeeList() {
		for(ModelEventsListener l : listeners)
			l.updateEmployeeList();

	}
	
	public boolean initContact(String name, String email, String phone,int represent) throws SQLException, Exception{
		Contact temp = new Contact(name, email, phone, represent);
		mysql.addNewContact(temp);
		return true;

	}
	
	public boolean updateContact(int serial, String name, String email, String phone,int represent) throws SQLException, Exception{
		Contact temp = new Contact(serial,name, email, phone, represent);
		mysql.updateContactOnSql(temp);;
		return true;
	}
	
	public boolean deleteContact(int serial) throws SQLException, Exception
	{
		mysql.deleteContactOnSql(serial);
		return true;
	}
	
	public Vector<Contact> showAllContact() throws SQLException, Exception
	{
		Vector<Contact> CVec = new Vector<>();
		ResultSet rs=  mysql.getAllContactFromSql();
		while(rs.next()) {
			CVec.add(sqlfunc.sqlToContact(rs));
		}
		return CVec;
	}
}
/*	
	public  updateCarsToUI() {

	}
/*
	public String[] showAllNamesBakar() throws SQLException, Exception
	{
		Vector<OficeEmployee> BVec = showAllEmployeeType("Bakar");
		String nameArr[];
		nameArr = new String[BVec.size()];
		for(int i=0;i<BVec.size() ; i++) {
			nameArr[i] = BVec.get(i).getFirstName()+" "+BVec.get(i).getLastName();
		}
		return nameArr;		
	}

	public String[] showAllNamesDriver() throws SQLException, Exception
	{
		Vector<Driver> DVec = showAllDrivers();
		String nameArr[];
		nameArr = new String[DVec.size()];
		for(int i=0;i<DVec.size() ; i++) {
			nameArr[i] = DVec.get(i).getFirstName()+" "+DVec.get(i).getLastName();
		}
		return nameArr;		
	}
	public int[] showAllNamesCar() throws SQLException, Exception
	{
		Vector<Car> CVec = showAllCars();
		int numArr[];
		numArr = new int[CVec.size()];
		for(int i=0;i<CVec.size() ; i++) {
			numArr[i] = CVec.get(i).getWindowNumber();
		}
		return numArr;		
	}
 */