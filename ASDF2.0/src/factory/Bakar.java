package factory;

import java.util.Date;

import Exceptions.EmployeeExceptions;

//import java.util.Date;

public class Bakar extends OficeEmployee{

	
	public Bakar(String id, String firstName, String lastName, Date birthDate) throws Exception {
		super(id, firstName, lastName, birthDate);
	}

	@Override
	public String toString() {
		return "Bakar:	"+ super.toString() ;
	}





	

	


}
