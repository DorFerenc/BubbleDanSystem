package application;

import java.io.IOException;
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
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import model.Car;
import model.Driver;

public class NewEventView implements actionTemplate{
	private UIEventsListener myListener;

	@FXML private ComboBox<Car> Car;
	@FXML private ComboBox<OficeEmployee> Controller;
	@FXML private TextArea Damage;
	@FXML private DatePicker Date;
	@FXML private TextArea Description;
	@FXML private ComboBox<Driver> Driver;
	@FXML private TextField EventName;
	@FXML private TextField Location;
	@FXML private TextArea Passengers;
	@FXML private Label errors;
	@FXML private CheckBox rDamage;
	@FXML private CheckBox rPassengers;
	@FXML private Button submit;

	@Override
	public void initData() {
		try {
			errors.setText("");
			Date.setValue(LocalDate.now()); //todays date
			//	Driver.getItems().addAll(FXCollections.observableArrayList(myListener.viewAsksForAlldriverNames()));
			/*	int[] temp = myListener.viewAsksForAllWindowNumbers();
			for(int i = 0; i < temp.length; i++) {
				Car.getItems().add(myListener.viewAsksForAllWindowNumbers()[i]); 
			}*/
			//Controller.getItems().addAll(FXCollections.observableArrayList(myListener.viewAsksForAllBakarNames()));



			ArrayList<Driver> arraylist = new ArrayList<Driver>(myListener.showAllDrivers());
			Driver.getItems().addAll(FXCollections.observableArrayList(arraylist));
			Driver.setConverter(new StringConverter<Driver>() {
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

			/*	int[] temp = myListener.viewAsksForAllWindowNumbers();
			for(int i = 0; i < temp.length; i++) {
				lossCar.getItems().add(myListener.viewAsksForAllWindowNumbers()[i]); 
			}*/
			//lossController.getItems().addAll(FXCollections.observableArrayList(myListener.viewAsksForAllBakarNames()));
			ArrayList<Car> arraylist3 = new ArrayList<Car>(myListener.showAllCars());
			Car.getItems().addAll(FXCollections.observableArrayList(arraylist3));
			Car.setConverter(new StringConverter<Car>() {
				@Override
				public String toString(Car tempcar) {
					if (tempcar == null)
						return "Please Choose A Driver";
					return "Via Num: "+ tempcar.getViaNumber() + "\nWindow Number :" + tempcar.getWindowNumber();
				}

				@Override
				public Car fromString(String arg0) {
					return null;
				}
			});


			ArrayList<OficeEmployee> arraylist2 = new ArrayList<OficeEmployee>(myListener.showAllEmployeeType("bakar"));
			Controller.getItems().addAll(FXCollections.observableArrayList(arraylist2));
			Controller.setConverter(new StringConverter<OficeEmployee>() {
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




		} catch (Exception e) {errors.setText("" + e.getMessage());}
	}

	@Override
	public void registerListener(UIEventsListener myListener) {
		this.myListener = myListener;	
	}

	public void rDamageAction(ActionEvent event) throws IOException {
		if (rDamage.isSelected())
			Damage.setDisable(false);
		else
			Damage.setDisable(true);
	}

	public void rPassengerAction(ActionEvent event) throws IOException {
		if (rPassengers.isSelected())
			Passengers.setDisable(false);
		else
			Passengers.setDisable(true);
	}

	public void submitAction(ActionEvent event) {

		if (Driver.getValue() == null || Car.getValue() == null || Description.getText().length() == 0
				|| Controller.getValue() == null ||  Location.getText().length() == 0
				||  EventName.getText().length() == 0)
			errors.setText("Miss value");
		else {
			java.sql.Date date = java.sql.Date.valueOf(Date.getValue());
			try {
				if (myListener.initEventReport(EventName.getText(), date, Driver.getValue().getSerial(), Car.getValue().getViaNumber(),
						Car.getValue().getLicencePlateNumber(), Controller.getValue().getId(), Location.getText(), Description.getText(), Damage.getText(), Passengers.getText()))
					myListener.successClose("Event: Added Successfully", 0);
				else
					errors.setText("error");
			} catch (Exception e) {errors.setText(e.getMessage());}
		}
	}
}