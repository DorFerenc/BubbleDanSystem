package factory;

import java.util.Date;

public class FieldManager extends OficeEmployee {
	
	
	public FieldManager(String id, String firstName, String lastName, Date birthDate) throws Exception {
		super(id, firstName, lastName, birthDate);

	}

	@Override
	public String toString() {
		return "FieldManager:	" + super.toString();
	}

	
	


}
