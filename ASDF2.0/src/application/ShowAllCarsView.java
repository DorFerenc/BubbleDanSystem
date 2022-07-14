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
import model.Car;

public class ShowAllCarsView implements actionTemplate{

	private UIEventsListener myListener;
	@FXML private ListView<Car> allCars;
	@FXML private Label errors;
	@FXML private Button submit;
	
	@Override
	public void initData() {
		errors.setText("");
		ArrayList<Car> arraylist;
		try {
			allCars.getItems().clear();

			arraylist = new ArrayList<Car>(myListener.showAllCars());
			allCars.getItems().addAll(FXCollections.observableArrayList(arraylist));
			
			allCars.setCellFactory(lv -> new ListCell<Car>() {
			    @Override
			    public void updateItem(Car item, boolean empty) {
			        super.updateItem(item, empty);
			        if (empty) {
			            setText(null);
			        } else {
			            String text = "Via Number: "+item.getViaNumber() +"\t\tWindowNumber: "+ item.getWindowNumber()+
			            		"\nLicence Plate Number: "+ item.getLicencePlateNumber()  ; 
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
	
	public void submitAction(ActionEvent event) {
		if (allCars.getSelectionModel().selectedItemProperty().getValue() != null) {
			errors.setText("");
			Object O = (Object)allCars.getSelectionModel().selectedItemProperty().getValue();
			myListener.editAction(O, "/MainPageEngEditCar2.fxml");
		}
		else
			errors.setText("No Value Choosen");
	}
}
