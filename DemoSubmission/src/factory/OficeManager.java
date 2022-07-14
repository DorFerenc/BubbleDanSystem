package factory;

import java.util.Date;

public class  OficeManager extends OficeEmployee{

	
	public OficeManager(String id, String firstName, String lastName, Date birthDate) throws Exception {
		super(id, firstName, lastName, birthDate);

	}

	@Override
	public String toString() {
		return "OficeManager:	" + super.toString();
	}

	
	
}
