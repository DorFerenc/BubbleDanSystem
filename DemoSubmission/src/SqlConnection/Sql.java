package SqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import factory.OficeEmployee;
import model.Car;
import model.Contact;
import model.Driver;
import model.EventReport;
import model.LostReport;

//import model.Car;

public class Sql{  
	private static Sql mysql = new Sql();
	private Connection conn = null;
	private final String PASSWORD = "asdf12";
	private final String USERNAME = "root";
	private final String DBURL = "jdbc:mysql://localhost:3306/Bubble";
	

	private Sql() {//throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = DBURL; //85.250.112.134:3306
			conn = DriverManager.getConnection(dbURL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 public static Sql getMysql(){
	      return mysql;
	   }
	
	public ResultSet getAllContactType(int num) throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * From Contact "
				+ " where represent = 2 Or represent ="+ num +";");
		return rs;
	}
	//INPUT: table name
	//OUTPUT: ResultSet of all table
	public ResultSet getAllGeneralFromSql(String table) throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * From "+ table);
		return rs;
	}
	
	public ResultSet getAllEmployeeTypeFromSql(String type) throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from employee\r\n"
				+ "where typeOf like ('"+type+"');");
		return rs;
	}
	
	public ResultSet getAllCarsFromSql() throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * From car");
		return rs;
		/*
		while (rs.next()) {
			System.out.println(rs.getInt("Via_Number")
					+ ","+ rs.getInt("Window_Number")
					+","+ rs.getInt("License_Plate_number"));
		}*/
	}
	
	public ResultSet getAllDriversFromSql() throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * From Driver");
		return rs;
		/*
		while (rs.next()) {
			System.out.println(rs.getLong("License_Number")
					+","+ rs.getString("First_Name")
					+","+ rs.getString("Last_name"));
		}*/
	}
	public ResultSet getAllEmployeeFromSql() throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * From Employee");
		return rs;
		/*
		while (rs.next()) {
			System.out.println(rs.getLong("ID")
					+","+ rs.getString("First_Name")
					+","+ rs.getString("Last_name")
					+","+ rs.getDate("Birthdate")
					+","+ rs.getString("typeOf"));
		}*/
	}
	public ResultSet getAllEventReportFromSql() throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * From EventReport");
		return rs;
		/*
		while (rs.next()) {
			System.out.println(rs.getString("Title")
					+","+ rs.getDate("thedate")
					+","+ rs.getString("driver_License_Number")
					+","+ rs.getInt("car_Via_Number")
					+","+ rs.getInt("car_License_Plate_number")
					+","+ rs.getInt("bakar_Id")
					+","+ rs.getString("location")
					+","+ rs.getString("theDescription")
					+","+ rs.getString("damageDescription")
					+","+ rs.getString("passengerDescription"));
		}*/
	}
	
	public ResultSet getAllLostReportFromSql() throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * From LostReport");
		
		return rs;
		/*
		while (rs.next()) {
			System.out.println(rs.getDate("thedate")
					+","+ rs.getString("itemDescription")
					+","+ rs.getString("driver_License_Number")
					+","+ rs.getInt("car_Via_Number")
					+","+ rs.getInt("car_License_Plate_number")
					+","+ rs.getInt("isItemValuable")
					+","+ rs.getString("theStatus")
					+","+ rs.getDate("foundDate")
					+","+ rs.getDate("ControlDeliveredDate")
					+","+ rs.getDate("customerDeliveredDate"));
		}*/
	}

	public boolean addNewCar(Car car) throws SQLException {
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("insert into Car(Window_number,License_Plate_number)\r\n"
				+ "values ("
				+car.getWindowNumber()
				+",'"+car.getLicencePlateNumber()+"');");
		if(rs == 1)
			return true;
		return false;
	}
	
	public boolean addNewDriver(Driver driver) throws SQLException {
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("insert into Driver(License_Number,First_Name,Last_name)\r\n"
				+ " values ('"+driver.getLicenceNumber()
				+"','"+driver.getFirstName()
				+"','"+driver.getLastName()+"');");
		if(rs == 1)	
			return true;
		return false;
	}
	
	public boolean addNewEmployee(OficeEmployee employee, String typeOf) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sdf.format(employee.getBirthDate());
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("insert into Employee(Id,First_Name,Last_name,Birthdate,typeOf)\r\n"
				+ "values ('"+employee.getId()
				+"','"+employee.getFirstName()
				+"','"+employee.getLastName()
				+"','"+currentdate
				+"','"+typeOf+"');");
		if(rs == 1)	
			return true;
		return false;
	}
	
	public boolean addNewEventReport(EventReport report) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sdf.format(report.getDate());
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("insert into EventReport(Title,thedate,driver_serial,car_Via_Number\r\n" /*,car_License_Plate_number*/
				+ ",Employee_Id,location,theDescription,damageDescription,passengerDescription)\r\n"
				+ "values ('"+report.getTitle()
				+"','"+ currentdate
				+"','"+ report.getDriver().getSerial()
				+"',"+ report.getCar().getViaNumber()
				//+",'"+ report.getCar().getLicencePlateNumber()
				+",'"+ report.getBakar().getId()
				+"','"+ report.getLocation()
				+"','"+ report.getDescription()
				+"','"+ report.getDamageDescription()
				+"','"+ report.getPassengerDescription()+"');");
		if(rs == 1)	
			return true;
		return false;
		}
	
	public boolean addNewLostReport(LostReport report) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sdf.format(report.getFoundDate());
		//String date1 = sdf.format(report.getTransportDate());
		//String date2 = sdf.format(report.getDeliveredDate());		
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("insert into LostReport(itemDescription,driver_serial,car_Via_Number\r\n" /*thedate,  ,car_License_Plate_number*/
				+ ",Employee_Id,isItemValuable,theStatus,foundDate)\r\n"
				+ " values ("//+ currentdate
				+"'"+ report.getItemDescription()
				+"','"+ report.getDriver().getSerial()
				+"',"+ report.getCar().getViaNumber()
				//+",'"+ report.getCar().getLicencePlateNumber()
				+",'"+ report.getBakar().getId()
				+"',"+ ((report.isValuable())?1:0)
				+",'"+ report.getStatus()
				+"','"+ currentdate
				/*+"','"+ date1
				+"','"+ date2*/+"');");
				
		if(rs == 1)	
			return true;
		return false;
	}
	
	public boolean deleteCarOnSql(int via_number) throws SQLException
	{
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("delete from Car "
				+ "where via_number = "+via_number +";");
		if(rs == 1)	
			return true;
		return false;
	}
	
	public boolean deletedriverOnSql(int driver_serial) throws SQLException
	{
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("delete from Driver "
				+ " where driver_serial = "+driver_serial +";");
		if(rs == 1)	
			return true;
		return false;
	}
	public boolean deleteEmployeeOnSql(String id) throws SQLException
	{
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("delete from Employee "
				+ " where Id like '"+id +"';");
		if(rs == 1)	
			return true;
		return false;
	}
	public boolean deleteEventReportOnSql(int reportID) throws SQLException
	{
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("delete from EventReport "
				+ " where reportID ="+reportID +";");
		if(rs == 1)	
			return true;
		return false;
	}
	public boolean deleteLostReportOnSql(int reportID) throws SQLException
	{
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("delete from LostReport "
				+ " where LostID ="+reportID +";");
		if(rs == 1)	
			return true;
		return false;
	}
	public boolean updateEmployeeOnSql(OficeEmployee employee, String typeof) throws SQLException
	{
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("update Employee "
				+" set First_Name = '"+ employee.getFirstName()
				+"', Last_name = '"+ employee.getLastName()
				+"', Birthdate = '"+ employee.getBirthDate()
				+"', typeof = '"+ typeof
				+"'where ID like '"+ employee.getId()+ "';");
		if(rs == 1)	
			return true;
		return false;
	}
	public boolean updateCarOnSql(Car car, int viaNum) throws SQLException
	{
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("update Car "
				+" set Window_number = "+ car.getWindowNumber()
				+" , License_Plate_number = '"+ car.getLicencePlateNumber()
				+"' where Via_Number = "+ viaNum+ ";");
		if(rs == 1)	
			return true;
		return false;
	}
	
	public boolean updateDriverOnSql(Driver driver) throws SQLException
	{
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("update Driver "
				+" set First_Name = '"+ driver.getFirstName()
				+"', Last_name = '"+ driver.getLastName()
				+"' where driver_serial = "+ driver.getSerial()+ ";");
		if(rs == 1)	
			return true;
		return false;
	}
	
	public boolean updateEventReportOnSql(EventReport report) throws SQLException
	{
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("update EventReport "
				+" set Title = '"+ report.getTitle()
				+"', thedate = '"+ report.getDate()
				+"', driver_serial = '"+ report.getDriver().getSerial()
				+"', car_Via_Number = "+ report.getCar().getViaNumber()
				//+", car_License_Plate_number = '"+ report.getCar().getLicencePlateNumber()
				+", Employee_Id = '"+ report.getBakar().getId()
				+"', location = '"+ report.getLocation()
				+"', theDescription = '"+ report.getDescription()
				+"', damageDescription = '"+ ((report.getDamageDescription().isEmpty())?"":report.getDamageDescription())
				+"', damageDescription = '"+ ((report.getPassengerDescription().isEmpty())?"":report.getPassengerDescription())
				+"' where reportID = "+ report.getReportID()+ ";");
		if(rs == 1)	
			return true;
		return false;
	}
	
	public boolean updateLostReportOnSql(LostReport report) throws SQLException
	{
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("update LostReport "
				//+" set thedate = '"+ report.getFoundDate()
				+" set itemDescription = '"+ report.getItemDescription()
				+"', driver_serial = "+ report.getDriver().getSerial()
				+", car_Via_Number = "+ report.getCar().getViaNumber()
				+", Employee_Id = '"+ report.getBakar().getId()
				//+", car_License_Plate_number = '"+ report.getCar().getLicencePlateNumber()
				+"', isItemValuable = "+ (report.isValuable()?1:0)
				+", theStatus = '"+ report.getStatus()
				+"', foundDate = '"+ report.getFoundDate()
				+((report.getTransportDate()!=null)?("', ControlDeliveredDate = '"+ report.getTransportDate()) : (""))
				+((report.getDeliveredDate()!=null)?("', customerDeliveredDate = '"+ report.getDeliveredDate()) : (""))
				+"' where LostID = "+ report.getSerial()+ ";");
		if(rs == 1)	
			return true;
		return false;
	}
	//insert into LostReport(thedate,itemDescription,driver_License_Number,car_Via_Number,car_License_Plate_number\r\n"
	//+ ",isItemValuable,theStatus,foundDate
	public ResultSet getDriverFromKey(int driver_serial) throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from Driver "
				+ "where driver_serial = "+driver_serial +" ;");
		return rs;
	}
	
	public ResultSet getCarFromKey(int viaNumber) throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from Car "
				+ "where via_number = "+viaNumber +";");
		return rs;
	}
	
	public ResultSet getEmployeeFromKey(String id) throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from Employee "
				+ "where Id like '"+id +"';");
		return rs;
	}
	
	public boolean chechViaNum(int windowNumber) throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from Car "
				+ "where Window_number = "+windowNumber +";");
		if(!(rs.next()))
			return true;
		else
			return false;
	}
	
	public boolean addNewContact(Contact contact) throws SQLException {
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("insert into Contact(contactName,phone,email,represent) \r\n"
				+ "values ('"
				+contact.getName()
				+"','"+contact.getPhone()
				+"','"+contact.getEmail()
				+"',"+contact.getRepresent2()+");");
		if(rs == 1)
			return true;
		return false;
	}
	
	public boolean updateContactOnSql(Contact contact) throws SQLException
	{
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("update contact "
				+" set contactName = '"+ contact.getName()
				+"', phone = '"+ contact.getPhone()
				+"', email = '"+ contact.getEmail()
				+"', represent = "+ contact.getRepresent2()
				+" where ContactID = "+ contact.getSerial()+ ";");
		if(rs == 1)	
			return true;
		return false;
	}
	
	public boolean deleteContactOnSql(int serial) throws SQLException
	{
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("delete from Contact "
				+ " where ContactID = "+serial +";");
		if(rs == 1)	
			return true;
		return false;
	}
	
	public ResultSet getAllContactFromSql() throws SQLException
	{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * From Contact");
		return rs;

	}
	
}