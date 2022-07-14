package application;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import MVC.UIEventsListener;
import factory.EmployeeFactory.employeeType;
import factory.OficeEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditEmployeeView implements editTemplate{

	private UIEventsListener myListener;
	private OficeEmployee myEditEmployee;

	@FXML private Button btRemove;
	@FXML private Button btUpdate;
	@FXML private ComboBox<employeeType> cmbEmployeeType;
	@FXML private DatePicker dateBirthDate;
	@FXML private Label errors;
	@FXML private Label lblEdditingEmployeeID;
	@FXML private TextField tfFirstName;
	@FXML private TextField tfID;
	@FXML private TextField tfLastName;

	public void removeAction(ActionEvent event) {
		try {
			if (myListener.deleteEmployee(tfID.getText()))
				myListener.successClose("Driver Removed Successfully", 0);
			else
				errors.setText("error");
		} catch (Exception e) {
			errors.setText(e.getMessage());
		}
	}

	public void updateAction(ActionEvent event) {
		if(tfFirstName.getText().isEmpty() || tfLastName.getText().isEmpty()) {
			errors.setText("Miss value");
		}
		else
		{
			try {
				java.sql.Date date = java.sql.Date.valueOf(dateBirthDate.getValue());
				
				String cmbEmpTypeString = myEditEmployee.getClass().getSimpleName().substring(0, 5).toLowerCase();
				if (cmbEmployeeType.getValue() != null)
					cmbEmpTypeString = cmbEmployeeType.getValue().toString();
				
				if (myListener.updateEmployee(tfID.getText(), tfFirstName.getText(), tfLastName.getText(), date, cmbEmpTypeString))
					myListener.successClose("Driver Updated Successfully", 0);
				else
					errors.setText("error");
			} catch (Exception e) {errors.setText(e.getMessage());}
		}
	}

	@Override
	public void initData2(Object O) {
		//EmployeeFactory temp = (EmployeeFactory)O;
		errors.setText("");
		myEditEmployee = (OficeEmployee)O;

		cmbEmployeeType.getItems().setAll(employeeType.values());
		cmbEmployeeType.setPromptText(myEditEmployee.getClass().getSimpleName().substring(0, 5).toLowerCase()); 

		lblEdditingEmployeeID.setText("Editing Employee ID: " + myEditEmployee.getId());
		tfID.setText(myEditEmployee.getId());

		LocalDate localDate = Instant.ofEpochMilli(myEditEmployee.getBirthDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		dateBirthDate.setValue(localDate);

		tfFirstName.setText(myEditEmployee.getFirstName());
		tfLastName.setText(myEditEmployee.getLastName());
	}

	@Override
	public void registerListener(UIEventsListener myListener) {
		this.myListener = myListener;
	}


}
