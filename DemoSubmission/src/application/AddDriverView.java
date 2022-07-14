package application;

import MVC.UIEventsListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddDriverView implements actionTemplate {

	private UIEventsListener myListener;

	@FXML private Label errors;
	@FXML private Button submit;
	@FXML private TextField tfFirstName;
	@FXML private TextField tfLastName;
	@FXML private TextField tfLnumber;

	@Override
	public void registerListener(UIEventsListener myListener) {
		this.myListener = myListener;
	}
	
	@Override
	public void initData() {
		errors.setText("");
	}
	
	public void submitDriverAction(ActionEvent event)  {
		if(tfLnumber.getText().length() ==0 
				||tfFirstName.getText().length()==0
				||tfLastName.getText().length()==0) {
			errors.setText("Miss value");
		}
		else
		{
			try {
				if (myListener.initDriver(tfLnumber.getText(), tfFirstName.getText(), tfLastName.getText()))
					myListener.successClose("Driver: Added Successfully", 0);
				else
					errors.setText("error");
			} catch (Exception e) {
				errors.setText(e.getMessage());
			}
		}
	}

}

