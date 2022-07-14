package factory;

import java.util.Date;

import Exceptions.EmployeeExceptions;

public interface Employee {

	boolean checkDate(Date date) throws Exception;
	boolean checkId(String id) throws Exception;
	boolean checkName(String name) throws Exception;
}
