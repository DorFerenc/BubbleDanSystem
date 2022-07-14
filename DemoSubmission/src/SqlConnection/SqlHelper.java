package SqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

import factory.EmployeeFactory;
import factory.OficeEmployee;
import model.Car;
import model.Contact;
import model.Driver;
import model.EventReport;
import model.LostReport;

public class SqlHelper {

	public String sqlToEmail(ResultSet rs) throws SQLException, Exception
	{
		return 	rs.getString("email");
	}
	public Driver sqlToDriver(ResultSet rs) throws SQLException, Exception
	{
		return new Driver(
				rs.getInt("driver_serial")
				,rs.getString("First_Name")
				, rs.getString("Last_name")
				, rs.getString("License_Number"));
	}
	
	
	public Car sqlToCar(ResultSet rs) throws SQLException, Exception
	{
		return new Car(
				rs.getString("License_Plate_number")
				,rs.getInt("Window_Number")
				,rs.getInt("Via_Number"));
	}
	
	
	public OficeEmployee sqlToEmployee(ResultSet rs, EmployeeFactory factory) throws SQLException, Exception
	{
		
		return (OficeEmployee) factory.getEmployy(
				rs.getString("typeOf")
				,rs.getString("ID")
				,rs.getString("First_Name")
				, rs.getString("Last_name")
				, rs.getDate("Birthdate"));
	}
	
	public EventReport sqlToEventReport(ResultSet rs) throws SQLException, Exception
	{
		return new EventReport(
				rs.getInt("reportID")
				,rs.getString("Title")
				, rs.getDate("thedate")
				, rs.getString("location")
				, rs.getString("theDescription")
				,rs.getString("damageDescription")
				,rs.getString("passengerDescription"));
	}
	
	public LostReport sqlToLostReport(ResultSet rs) throws SQLException, Exception
	{
		return new LostReport(
				rs.getInt("LostID")
				, rs.getString("itemDescription")
				, rs.getInt("isItemValuable")
				, rs.getString("theStatus")
				, rs.getDate("foundDate")
				, rs.getDate("ControlDeliveredDate")
				, rs.getDate("customerDeliveredDate"));
	}
	
	public Driver sqlTo1Driver(ResultSet rs) throws SQLException, Exception
	{
		rs.next();
		return new Driver(
				rs.getInt("driver_serial")
				,rs.getString("First_Name")
				, rs.getString("Last_name")
				, rs.getString("License_Number"));
	}
	
	
	public Car sqlTo1Car(ResultSet rs) throws SQLException, Exception
	{
		rs.next();
		return new Car(
				rs.getString("License_Plate_number")
				,rs.getInt("Window_Number")
				,rs.getInt("Via_Number"));
	}
	
	public OficeEmployee sqlTo1Employee(ResultSet rs, EmployeeFactory factory) throws SQLException, Exception
	{
		rs.next();
		return (OficeEmployee) factory.getEmployy(
				rs.getString("typeOf")
				,rs.getString("ID")
				,rs.getString("First_Name")
				, rs.getString("Last_name")
				, rs.getDate("Birthdate"));
	}
	public Contact sqlToContact(ResultSet rs) throws SQLException, Exception
	{
		//contactName,phone,email,represent)
		return new Contact(
				rs.getInt("contactId")
				, rs.getString("contactName")
				, rs.getString("phone")
				, rs.getString("email")
				, rs.getInt("represent"));
	}
	
}