package factory;

import java.util.Date;

public class EmployeeFactory {
	public enum employeeType {bakar, ofice, field};
	// use getShape method to get object of type shape
	public Employee getEmployy(String EmployeeType ,String id, String firstName, String lastName, Date birthDate) throws Exception {
		if (EmployeeType == null) {
			return null;
		}
		if (EmployeeType.equalsIgnoreCase("bakar")) {
			return new Bakar(id, firstName, lastName, birthDate);

		} else if (EmployeeType.equalsIgnoreCase("ofice")) {
			return new OficeManager(id, firstName, lastName, birthDate);

		} else if (EmployeeType.equalsIgnoreCase("field")) {
			return new FieldManager(id ,firstName, lastName, birthDate);
		}
		return null;
	}
}
