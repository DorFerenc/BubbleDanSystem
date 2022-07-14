package application;

import MVC.UIEventsListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Car;

public class EditCarView implements editTemplate {

	private UIEventsListener myListener;
	private Car myEditCar;

	@FXML private Button Update;
	@FXML private Button btRemove;
	@FXML private Label errors;
	@FXML private Label lblCarNumber;
	@FXML private TextField tfLPnum;
	@FXML private TextField tfWindowNumber;

	public void initData2(Object tempCar) {
		errors.setText("");
		myEditCar = (Car) tempCar;
		tfLPnum.setText(myEditCar.getLicencePlateNumber());
		tfWindowNumber.setText("" + myEditCar.getWindowNumber());
		lblCarNumber.setText("Editing Car Via Number: " + myEditCar.getViaNumber());
	}

	@Override
	public void registerListener(UIEventsListener myListener) {
		this.myListener = myListener;
	}

	public void updateCarAction(ActionEvent event) {
		if (tfWindowNumber.getText().isEmpty() || tfLPnum.getText().isEmpty())
			errors.setText("Miss value");
		else {
			try {
				if (myListener.updateCar(tfLPnum.getText(), Integer.parseInt(tfWindowNumber.getText()),
						myEditCar.getViaNumber()))
					myListener.successClose("Car Updated", 0);
				else
					errors.setText("error");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				errors.setText(e.getMessage());
			}
		}
	}

	public void removeCarAction(ActionEvent event) {
		try {
			if (myListener.deleteCar(myEditCar.getViaNumber()))
				myListener.successClose("Car Removed Successfully", 0);
			else
				errors.setText("error");
		} catch (Exception e) {
			errors.setText(e.getMessage());
		}

	}

}
