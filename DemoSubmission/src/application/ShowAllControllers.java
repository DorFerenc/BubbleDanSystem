package application;

import java.util.ArrayList;

import MVC.UIEventsListener;
import factory.Bakar;
import factory.OficeEmployee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.Car;

public class ShowAllControllers implements actionTemplate{
	
	private UIEventsListener myListener;
	
	@FXML private ListView<OficeEmployee> allControllers;
	@FXML private Label errors;
	@FXML private Button submit;
	
	public void submitAction(ActionEvent event) {
		if (allControllers.getSelectionModel().selectedItemProperty().getValue() != null) {
			errors.setText("");
			Object O = (Object)allControllers.getSelectionModel().selectedItemProperty().getValue();
			myListener.editAction(O, "/MainPageEngEditEmployee.fxml");
		}
		else
			errors.setText("No Value Choosen");
	}

	@Override
	public void initData() {
		errors.setText("");
		ArrayList<OficeEmployee> arraylist;
		try {
			allControllers.getItems().clear();

			arraylist = new ArrayList<OficeEmployee>(myListener.showAllEmployees());
			allControllers.getItems().addAll(FXCollections.observableArrayList(arraylist));
			
			allControllers.setCellFactory(lv -> new ListCell<OficeEmployee>() {
			    @Override
			    public void updateItem(OficeEmployee item, boolean empty) {
			        super.updateItem(item, empty);
			        if (empty) {
			            setText(null);
			        } else {
			            String text = "Name: "+item.getFirstName() +" "+ item.getLastName()+
			            		"\nClass: "+item.getClass().getSimpleName()+
			            		"\nBirthDate: "+ item.getBirthDate()  ; // get text from item
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
