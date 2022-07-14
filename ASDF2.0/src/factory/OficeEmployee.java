package factory;

import java.util.Date;
import java.util.regex.Pattern;

public abstract class  OficeEmployee implements Employee{
	
	private String id;
	private String firstName;
	private String lastName;
	private Date birthDate;	
	
	public OficeEmployee(String id, String firstName, String lastName, Date birthDate) throws Exception {
		checkId(id);
		checkName(firstName);
		checkName(lastName);
		checkDate(birthDate);
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public boolean checkId(String id) throws Exception{
		if (!(id.length() == 9)) {
			throw new Exception("ID must have 9 digits.");
		}
		if (!(id.matches("[0-9]+"))) {
			throw new Exception("ID must have only digits.");
		}
		
		if (Integer.parseInt(id) == 0) {
			throw new Exception("ID cant be 000000000.");
		}
		return true;
	}
	public boolean checkName(String name) throws Exception{
		if (name.length() > 20) {
			throw new Exception("Name is too long (Max 20)");
		}
		if (!Pattern.matches("[a-zA-Z ]+", name)) {
			throw new Exception("Name must contain only letters");
		}
		if (!(name.indexOf("  ") == (-1))) {
			throw new Exception("cant contain more than one white spaces");
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean checkDate(Date date) throws Exception{
		/*if (2021 - date.getYear() < 18) {
			throw new Exception("age Must be over 18");
		}
	/*if (2021 - date.getYear() > 120) {
			throw new Exception("age Must be Under 120");
		}*/
		return true;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return " ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Birth Date: "
				+ birthDate;
	}
	
}
