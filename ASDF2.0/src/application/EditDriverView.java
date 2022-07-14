package application;

import MVC.UIEventsListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Driver;

public class EditDriverView implements editTemplate{

	private UIEventsListener myListener;
	private Driver myEditDriver;

	@FXML private Button btRemove;
	@FXML private Button btnUpdate;
	@FXML private Label errors;
    @FXML private Label lblDriverNumber;
	@FXML private TextField tfFirstName;
	@FXML private TextField tfLastName;
	@FXML private TextField tfLnumber;

	public void removeDriveAction(ActionEvent event) {
		try {
			if (myListener.deleteDriver(myEditDriver.getSerial()))
				myListener.successClose("Driver Removed Successfully", 0);
			else
				errors.setText("error");
		} catch (Exception e) {
			errors.setText(e.getMessage());
		}
	}

	public void updateDriveAction(ActionEvent event) {
		if(tfLnumber.getText().length() ==0 
				||tfFirstName.getText().length()==0
				||tfLastName.getText().length()==0) {
			errors.setText("Miss value");
		}
		else
		{
			try {
				if (myListener.updateDriver(myEditDriver.getSerial(),tfLnumber.getText(), tfFirstName.getText(), tfLastName.getText()))
					myListener.successClose("Driver Updated Successfully", 0);
				else
					errors.setText("error");
			} catch (Exception e) {errors.setText(e.getMessage());}
		}
	}

	public void initData2(Object tempCar) {
		errors.setText("");
		myEditDriver = (Driver)tempCar;
		tfFirstName.setText(myEditDriver.getFirstName());
		tfLastName.setText(myEditDriver.getLastName());
		tfLnumber.setText(myEditDriver.getLicenceNumber());
		lblDriverNumber.setText("Driver Number: " + myEditDriver.getSerial());
	}

	@Override
	public void registerListener(UIEventsListener myListener) {
		this.myListener = myListener;
	}
}


