package application;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import MVC.UIEventsListener;
import factory.OficeEmployee;
import model.EventReport;
import model.Driver;
import model.Car;
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

public class EditEventView implements editTemplate{

	private UIEventsListener myListener;
	private EventReport myEditEventReport;

	@FXML private ComboBox<Car> Car;
	@FXML private ComboBox<OficeEmployee> Controller;
	@FXML private TextArea Damage;
	@FXML private DatePicker Date;
	@FXML private TextArea Description;
	@FXML private ComboBox<Driver> Driver;
	@FXML private TextField EventName;
	@FXML private TextField Location;
	@FXML private TextArea Passengers;
	@FXML private Button btnRemove;
	@FXML private Button btnUpdate;
	@FXML private Label errors;
	@FXML private Label eventID;
	@FXML private CheckBox rDamage;
	@FXML private CheckBox rPassengers;

	@Override
	public void initData2(Object O) {
		myEditEventReport = (EventReport)O;
		errors.setText("");
		try {
			eventID.setText("Event ID: " + myEditEventReport.getReportID());
			LocalDate localDate = Instant.ofEpochMilli(myEditEventReport.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			Date.setValue(localDate); 
			
			/**
			 * CHECK TODO DELET SYSO
			 */
			System.out.println(myEditEventReport.getDamageDescription());
			System.out.println(myEditEventReport.getPassengerDescription());
			
			if (myEditEventReport.getDamageDescription() != null) {
				Damage.setDisable(false);
				rDamage.setSelected(true);
				Damage.setText(myEditEventReport.getDamageDescription());
			}
			if (myEditEventReport.getPassengerDescription() != null) {
				Passengers.setDisable(false);
				rPassengers.setSelected(true);
				Passengers.setText(myEditEventReport.getPassengerDescription());
			}
			EventName.setText(myEditEventReport.getTitle());
			Location.setText(myEditEventReport.getLocation());
			Description.setText(myEditEventReport.getDescription());
			
			ArrayList<Driver> arraylist = new ArrayList<Driver>(myListener.showAllDrivers());
			Driver.getItems().addAll(FXCollections.observableArrayList(arraylist));
			Driver.setConverter(new StringConverter<Driver>() {
				public String toString(Driver tempDriver) {
					if (tempDriver == null)
						return "Please Choose A Driver";
					return  tempDriver.getFirstName() + " " + tempDriver.getLastName();
				}
				public Driver fromString(String arg0) {
					return null;
				}
			}); 
			Driver.getSelectionModel().select(myEditEventReport.getDriver());

			ArrayList<Car> arraylist3 = new ArrayList<Car>(myListener.showAllCars());
			Car.getItems().addAll(FXCollections.observableArrayList(arraylist3));
			Car.setConverter(new StringConverter<Car>() {
				public String toString(Car tempcar) {
					if (tempcar == null)
						return "Please Choose A Driver";
					return "Via Num: "+ tempcar.getViaNumber() + "\nWindow Number :" + tempcar.getWindowNumber();
				}
				public Car fromString(String arg0) {
					return null;
				}
			});
			Car.getSelectionModel().select(myEditEventReport.getCar());

			ArrayList<OficeEmployee> arraylist2 = new ArrayList<OficeEmployee>(myListener.showAllEmployeeType("bakar"));
			Controller.getItems().addAll(FXCollections.observableArrayList(arraylist2));
			Controller.setConverter(new StringConverter<OficeEmployee>() {
				public String toString(OficeEmployee tempbakar) {
					if (tempbakar == null)
						return "Please Choose A Driver";
					return  tempbakar.getFirstName() + " " + tempbakar.getLastName();
				}
				public OficeEmployee fromString(String arg0) {
					return null;
				}
			});
			Controller.getSelectionModel().select(myEditEventReport.getBakar());

		} catch (Exception e) {errors.setText("" + e.getMessage());}
	}

	public void rDamageAction(ActionEvent event) {
		if (rDamage.isSelected())
			Damage.setDisable(false);
		else
			Damage.setDisable(true);
	}

	public void rPassengerAction(ActionEvent event) {
		if (rPassengers.isSelected())
			Passengers.setDisable(false);
		else
			Passengers.setDisable(true);
	}

	public void removeAction(ActionEvent event) {
		try {
			if (myListener.deleteEventReport(myEditEventReport.getReportID()))
				myListener.successClose("Driver Removed Successfully", 0);
			else
				errors.setText("error");
		} catch (Exception e) {
			errors.setText(e.getMessage());
		}
	}

	public void updateAction(ActionEvent event) {
		/*if(tfLnumber.getText().length() ==0 
				||tfFirstName.getText().length()==0
				||tfLastName.getText().length()==0) {
			errors.setText("Miss value");
		}
		else
		{*/
			try {
				Date date = java.sql.Date.valueOf(Date.getValue());
				if(myListener.updateEventReport(myEditEventReport.getReportID(),EventName.getText(), date, Driver.getValue().getSerial()
						,Car.getValue().getViaNumber(), Car.getValue().getLicencePlateNumber(), Controller.getValue().getId()
						, Location.getText(), Description.getText(), Damage.getText(), Passengers.getText()))
					myListener.successClose("Driver Updated Successfully", 0);
				else
					errors.setText("error");
			} catch (Exception e) {errors.setText(e.getMessage());}
	//	}
	}

	@Override
	public void registerListener(UIEventsListener myListener) {
		this.myListener = myListener;
	}

}
