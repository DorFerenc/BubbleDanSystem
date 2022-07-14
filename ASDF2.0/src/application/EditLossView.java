package application;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import MVC.UIEventsListener;
import factory.OficeEmployee;
import model.Car;
import model.Driver;
import model.LostReport;
import model.LostReport.lostStatus;
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

public class EditLossView implements editTemplate{
	private UIEventsListener myListener;
	private LostReport myEditLostReport;

	@FXML private Button btnRemove;
	@FXML private Button btnUpdate;
	@FXML private CheckBox chRecived;
	@FXML private CheckBox chRetrieved;
	@FXML private DatePicker dpReceivedDate;
	@FXML private DatePicker dpRetrievedDate;
	@FXML private Label errors;
	@FXML private CheckBox highValue;
	@FXML private Label lblLossNumber;
	@FXML private ComboBox<Car> lossCar;
	@FXML private ComboBox<OficeEmployee> lossController;
	@FXML private DatePicker lossDate;
	@FXML private TextArea lossDescription;
	@FXML private ComboBox<Driver> lossDriver;
	@FXML private TextField lossNumber;
	@FXML private ComboBox<lostStatus> lossStatus;

	@Override
	public void initData2(Object O) {
		errors.setText("");
		myEditLostReport = (LostReport)O;

		try {
			lossNumber.setText(""+myEditLostReport.getSerial()+"");

			LocalDate localDate = Instant.ofEpochMilli(myEditLostReport.getFoundDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			lossDate.setValue(localDate); 
			
			ArrayList<Driver> arraylist = new ArrayList<Driver>(myListener.showAllDrivers());
			lossDriver.getItems().addAll(FXCollections.observableArrayList(arraylist));
			lossDriver.setConverter(new StringConverter<Driver>() {
				public String toString(Driver tempDriver) {
					if (tempDriver == null)
						return "Please Choose A Driver";
					return  tempDriver.getFirstName() + " " + tempDriver.getLastName();
				}
				public Driver fromString(String arg0) {
					return null;
				}
			}); 
			lossDriver.getSelectionModel().select(myEditLostReport.getDriver());

			ArrayList<Car> arraylist3 = new ArrayList<Car>(myListener.showAllCars());
			lossCar.getItems().addAll(FXCollections.observableArrayList(arraylist3));
			lossCar.setConverter(new StringConverter<Car>() {
				public String toString(Car tempcar) {
					if (tempcar == null)
						return "Please Choose A Driver";
					return "Via Num: "+ tempcar.getViaNumber() + "\tWindow Number :" + tempcar.getWindowNumber();
				}
				public Car fromString(String arg0) {
					return null;
				}
			});
			lossCar.getSelectionModel().select(myEditLostReport.getCar());


			ArrayList<OficeEmployee> arraylist2 = new ArrayList<OficeEmployee>(myListener.showAllEmployeeType("bakar"));
			lossController.getItems().addAll(FXCollections.observableArrayList(arraylist2));
			lossController.setConverter(new StringConverter<OficeEmployee>() {
				public String toString(OficeEmployee tempbakar) {
					if (tempbakar == null)
						return "Please Choose A Driver";
					return  tempbakar.getFirstName() + " " + tempbakar.getLastName();
				}
				public OficeEmployee fromString(String arg0) {
					return null;
				}
			});
			lossController.getSelectionModel().select(myEditLostReport.getBakar());// TODO add to lost employee in charge
			
			lossStatus.getItems().setAll(lostStatus.values());
			lossStatus.setPromptText(myEditLostReport.getStatus()); 
			
			lossDescription.setText(myEditLostReport.getItemDescription());
			
			highValue.setSelected(myEditLostReport.isValuable());
			
			if (myEditLostReport.getTransportDate() == null){
				chRecived.setSelected(false);
				dpReceivedDate.setDisable(true);
			}
			else {
				LocalDate localDate2 = Instant.ofEpochMilli(myEditLostReport.getTransportDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				dpReceivedDate.setValue(localDate2); 
			}
			
			if (myEditLostReport.getDeliveredDate() == null){
				chRetrieved.setSelected(false);
				dpRetrievedDate.setDisable(true);
			}
			else {
				LocalDate localDate3 = Instant.ofEpochMilli(myEditLostReport.getDeliveredDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				dpRetrievedDate.setValue(localDate3); 
			}
				
		} catch (Exception e) {errors.setText("" + e.getMessage());}
	}

	@Override
	public void registerListener(UIEventsListener myListener) {
		this.myListener = myListener;
	}

	public void dateReceivedAction(ActionEvent event) {

	}

	public void dateRetrievedAction(ActionEvent event) {

	}

	public void recivedAction(ActionEvent event) {
		if (chRecived.isSelected())
			dpReceivedDate.setDisable(false);
		else
			dpReceivedDate.setDisable(true);
	}
	
	public void retrievedAction(ActionEvent event) {
		if (chRetrieved.isSelected())
			dpRetrievedDate.setDisable(false);
		else
			dpRetrievedDate.setDisable(true);
	}
	
	public void statusChangeAction(ActionEvent event) {
		
	}
	
	public void removeAction(ActionEvent event) {
		try {
			if (myListener.deleteLostReport(myEditLostReport.getSerial()))
				myListener.successClose("Driver Removed Successfully", 0);
			else
				errors.setText("error");
		} catch (Exception e) {
			errors.setText(e.getMessage());
		}
	}
	

	public void updateAction(ActionEvent event) {
		if(lossDescription.getText().isEmpty()) {
			errors.setText("Miss value");
		}
		else
		{
			try {
				java.sql.Date date = java.sql.Date.valueOf(lossDate.getValue());
				java.sql.Date date1;
				java.sql.Date date2;
				if(dpReceivedDate.getValue()==null)
					date1 = null;
				else
					date1 = java.sql.Date.valueOf(dpReceivedDate.getValue());
				if(dpReceivedDate.getValue()==null)
					date2 = null;
				else
					date2 = java.sql.Date.valueOf(dpRetrievedDate.getValue());
				
				String cmbStatusValueString = myEditLostReport.getStatus();
				if (lossStatus.getValue() != null)
					cmbStatusValueString = lossStatus.getValue().toString();

				
				if (myListener.updateLostReport(myEditLostReport.getSerial(),date, lossDescription.getText(), lossDriver.getValue().getSerial()
						,lossCar.getValue().getViaNumber(),lossCar.getValue().getLicencePlateNumber(),lossController.getValue().getId()
						,(highValue.isSelected())?1:0,cmbStatusValueString,date ,date1,date2))
					myListener.successClose("Driver Updated Successfully", 0);
				else
					errors.setText("error");
			} catch (Exception e) {errors.setText(e.getMessage());}
		}
	}

}
