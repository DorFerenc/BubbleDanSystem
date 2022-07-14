package application;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import MVC.UIEventsListener;
import factory.OficeEmployee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
import model.LostReport.lostStatus;
import model.Car;
import model.Driver;

public class NewLostView implements actionTemplate{

	private UIEventsListener myListener;
	@FXML private BorderPane actionScreen;
	@FXML private Label errors;

	@FXML private DatePicker lossDate;
	@FXML private ComboBox<Driver> lossDriver;
	@FXML private ComboBox<Car> lossCar;
	@FXML private ComboBox<OficeEmployee> lossController;
	@FXML private TextArea lossDescription;
	@FXML private CheckBox highValue;
	@FXML private ComboBox<lostStatus> lossStatus;
	@FXML private Button submit;


	public void registerListener(UIEventsListener myListener) {
		this.myListener = myListener;
	}

	//Initiate the data (date, combobox's..)
	public void initData() {
		try {
			errors.setText("");
			lossDate.setValue(LocalDate.now()); //todays date
			ArrayList<Driver> arraylist = new ArrayList<Driver>(myListener.showAllDrivers());
			lossDriver.getItems().addAll(FXCollections.observableArrayList(arraylist));
			lossDriver.setConverter(new StringConverter<Driver>() {
				@Override
				public String toString(Driver tempDriver) {
					if (tempDriver == null)
						return "Please Choose A Driver";
					return  tempDriver.getFirstName() + " " + tempDriver.getLastName();
				}

				@Override
				public Driver fromString(String arg0) {
					return null;
				}
			}); 

			ArrayList<Car> arraylist3 = new ArrayList<Car>(myListener.showAllCars());
			lossCar.getItems().addAll(FXCollections.observableArrayList(arraylist3));
			lossCar.setConverter(new StringConverter<Car>() {
				@Override
				public String toString(Car tempcar) {
					if (tempcar == null)
						return "Please Choose A Driver";
					return "Via Num: "+ tempcar.getViaNumber() + "\tWindow Number :" + tempcar.getWindowNumber();
				}

				@Override
				public Car fromString(String arg0) {
					return null;
				}
			});


			ArrayList<OficeEmployee> arraylist2 = new ArrayList<OficeEmployee>(myListener.showAllEmployeeType("bakar"));
			lossController.getItems().addAll(FXCollections.observableArrayList(arraylist2));
			lossController.setConverter(new StringConverter<OficeEmployee>() {
				@Override
				public String toString(OficeEmployee tempbakar) {
					if (tempbakar == null)
						return "Please Choose A Driver";
					return  tempbakar.getFirstName() + " " + tempbakar.getLastName();
				}

				@Override
				public OficeEmployee fromString(String arg0) {
					return null;
				}
			});
			lossStatus.getItems().setAll(lostStatus.values());
		} catch (Exception e) {errors.setText("" + e.getMessage());}
	}

	public void submitAction(ActionEvent event)  {
		System.out.println("hi");
		// TODO add checks
		if (lossDriver.getValue() == null || lossCar.getValue() == null || lossDescription.getText().length() == 0
				|| lossStatus.getValue() == null)
			errors.setText("Miss value");
		else {
			// TODO add status update
			Date date = java.sql.Date.valueOf(lossDate.getValue());
			try {
				if (myListener.initLostReport(date, lossDescription.getText(), lossDriver.getValue().getSerial(),
						lossCar.getValue().getViaNumber(), lossCar.getValue().getLicencePlateNumber(),
						lossController.getValue().getId(),(highValue.isSelected()) ? 1 : 0, lossStatus.getValue().toString(), date, new Date(2000, 1, 1),
								new Date(2000, 1, 1)))
					myListener.successClose("Loss: Added Successfully", 0);
				else
					errors.setText("error");
			} catch (Exception e) {
				errors.setText(e.getMessage());
			}
			// myListener.initLostReport(lossDate.getValue(), lossDescription.getText(),
			// lossDriver.getValue(), lossCar.getValue(), lossController.getValue(),
			// highValue.isArmed(), lossStatus.getValue())
		}
	}
}