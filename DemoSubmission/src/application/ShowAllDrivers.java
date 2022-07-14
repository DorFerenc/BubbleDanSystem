package application;

import java.util.ArrayList;

import MVC.UIEventsListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.Driver;

public class ShowAllDrivers implements actionTemplate{
	
	private UIEventsListener myListener;
	
	@FXML private ListView<Driver> allDrivers;
	@FXML private Label errors;
	@FXML private Button submit;
	
	public void submitAction(ActionEvent event) {
		if (allDrivers.getSelectionModel().selectedItemProperty().getValue() != null) {
			errors.setText("");
			Object O = (Object)allDrivers.getSelectionModel().selectedItemProperty().getValue();
			myListener.editAction(O, "/MainPageEngEditDriver.fxml");
		}
		else
			errors.setText("No Value Choosen");
	}

	@Override
	public void initData() {
		errors.setText("");
		ArrayList<Driver> arraylist;
		try {
			allDrivers.getItems().clear();
			arraylist = new ArrayList<Driver>(myListener.showAllDrivers());
			allDrivers.getItems().addAll(FXCollections.observableArrayList(arraylist));
			
			allDrivers.setCellFactory(lv -> new ListCell<Driver>() {
			    @Override
			    public void updateItem(Driver item, boolean empty) {
			        super.updateItem(item, empty);
			        if (empty) {
			            setText(null);
			        } else {
			            String text = "Name: "+item.getFirstName() +" "+ item.getLastName()+
			            		"\nLicence Number: "+item.getLicenceNumber(); // get text from item
			            setText(text);
			        }
			    }
			});
			
		} catch (Exception e) {
			errors.setText("" + e.getMessage());
		}
	
	}

	@Override
	public void registerListener(UIEventsListener myListener) {
		this.myListener = myListener;
	}

}
