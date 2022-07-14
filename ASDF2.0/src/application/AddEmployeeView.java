package application;

import java.sql.Date;

import MVC.UIEventsListener;
import factory.EmployeeFactory.employeeType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddEmployeeView implements actionTemplate{

	private UIEventsListener myListener;
	
	@FXML private ComboBox<employeeType> cmbEmployeeType;
	@FXML private DatePicker dateBirthDate;
	@FXML private Label errors;
	@FXML private Button submit;
	@FXML private TextField tfFirstName;
	@FXML private TextField tfID;
	@FXML private TextField tfLastName;

	
	public void submitAction(ActionEvent event)  {
		/*if (windowNumber == 0 || lpNumber == 0)
			errors.setText("Miss value");
		else {*/
			Date date = java.sql.Date.valueOf(dateBirthDate.getValue());
			try {
				if (myListener.initEmployee(tfID.getText(), tfFirstName.getText(), tfLastName.getText(), date, cmbEmployeeType.getValue().toString()))
					myListener.successClose("Employee: Added Successfully", 0);
				else
					errors.setText("error");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				errors.setText(e.getMessage());
			}
		//}
	}


	@Override
	public void initData() {
		errors.setText("");
		cmbEmployeeType.getItems().setAll(employeeType.values());
	}


	@Override
	public void registerListener(UIEventsListener myListener) {
		this.myListener = myListener;
	}

}

