package SqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import factory.EmployeeFactory;
import factory.OficeEmployee;
import model.Car;
import model.Contact;
import model.Driver;
import model.EventReport;
import model.LostReport;

public class GoogleSheetsFunctions {
	private static GoogleSheetsFunctions mysql = new GoogleSheetsFunctions();

	public static GoogleSheetsFunctions getMystringArr(){
		return mysql;
	}

	public String[] addNewCar(Car car) {
		String arr[] = {String.valueOf(car.getWindowNumber())
				,car.getLicencePlateNumber()};
		return arr;
	}

	public String[] addNewDriver(Driver driver) {
		String arr[] = { driver.getLicenceNumber()
				,driver.getFirstName()
				,driver.getLastName()};
		return arr;
	}

	public String[] addNewEmployee(OficeEmployee employee, String typeOf) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sdf.format(employee.getBirthDate());
		String arr[] = { employee.getId()
				,employee.getFirstName()
				,employee.getLastName()
				,currentdate
				,typeOf};
		return arr;
	}

	public String[] addNewEventReport(EventReport report) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sdf.format(report.getDate());
		String arr[] = { report.getTitle()
				,currentdate
				,String.valueOf(report.getDriver().getSerial())
				,String.valueOf(report.getCar().getViaNumber())
				,report.getBakar().getId()
				,report.getLocation()
				,report.getDescription()
				,report.getDamageDescription()
				,report.getPassengerDescription()};
		return arr;
	}

	public String[] addNewLostReport(LostReport report) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sdf.format(report.getFoundDate());
		String arr[] = { report.getItemDescription()
				,String.valueOf(report.getDriver().getSerial())
				,String.valueOf(report.getCar().getViaNumber())
				,report.getBakar().getId()
				,String.valueOf(((report.isValuable())?1:0))
				,report.getStatus()
				,currentdate};
		return arr;
	}



	public Driver stringArrToDriver(String []str) throws SQLException, Exception
	{
		return new Driver(
				Integer.parseInt(str[0])
				,str[1]
						, str[2]
								, str[3]);
	}


	public Car stringArrToCar(String []str) throws SQLException, Exception
	{
		return new Car(
				str[0]
						,Integer.parseInt(str[1])
						,Integer.parseInt(str[2]));
	}


	public OficeEmployee stringArrToEmployee(String []str, EmployeeFactory factory) throws SQLException, Exception
	{

		return (OficeEmployee) factory.getEmployy(
				str[1]
						,str[2]
								,str[3]
										,str[3]
												,new SimpleDateFormat("dd/MM/yyyy").parse(str[4]));		}

	public EventReport stringArrToEventReport(String []str) throws SQLException, Exception
	{
		return new EventReport(
				Integer.parseInt(str[0])
				,str[1]
						,new SimpleDateFormat("dd/MM/yyyy").parse(str[2])
						,str[3]
								,str[4]
										,str[5]
												,str[6]);
	}

	public LostReport stringArrToLostReport(String []str) throws SQLException, Exception
	{
		return new LostReport(
				Integer.parseInt(str[0])
				,str[1]
						,Integer.parseInt(str[2])
						,str[3]
								,new SimpleDateFormat("dd/MM/yyyy").parse(str[4])
								,new SimpleDateFormat("dd/MM/yyyy").parse(str[5])
								,new SimpleDateFormat("dd/MM/yyyy").parse(str[6]));
	}


	public Contact stringArrToContact(String []str) throws SQLException, Exception
	{
		return new Contact(
				Integer.parseInt(str[0])
				,str[1]
						,str[2]
								,str[3]
										,Integer.parseInt(str[4])
				);
	}
}
