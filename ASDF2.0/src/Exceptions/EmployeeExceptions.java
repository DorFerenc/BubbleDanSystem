package Exceptions;

public class EmployeeExceptions extends Exception {

	public EmployeeExceptions(String msg) {
		super(msg);
	}

	public EmployeeExceptions() {
		super("General Name Exception");
	}
}
