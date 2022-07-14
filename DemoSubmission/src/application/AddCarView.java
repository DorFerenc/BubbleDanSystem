package application;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import MVC.UIEventsListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Car;

public class AddCarView implements actionTemplate {

	private UIEventsListener myListener;

	@FXML private Label errors;
	@FXML private Button submit;
	@FXML private TextField tfLPnum;
	@FXML private TextField tfWindowNumber;

	@Override
	public void initData() {
		errors.setText("");
	}

	@Override
	public void registerListener(UIEventsListener myListener) {
		this.myListener = myListener;
	}

	public void submitCarAction(ActionEvent event)  {
		// TODO check existing window number
		int windowNumber = Integer.parseInt(tfWindowNumber.getText());
		int lpNumber = Integer.parseInt(tfLPnum.getText());
		// myListener.initCar(lpNumber, windowNumber, 0);
		if (windowNumber == 0 || lpNumber == 0)
			errors.setText("Miss value");
		else {
			try {
				if (myListener.initCar(tfLPnum.getText(), windowNumber))
					myListener.successClose("Car: Added Successfully", 0);
				else
					errors.setText("error");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				errors.setText(e.getMessage());
			}
		}
	}

}
