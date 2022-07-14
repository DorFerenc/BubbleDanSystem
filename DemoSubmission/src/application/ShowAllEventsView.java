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
import model.EventReport;

public class ShowAllEventsView implements actionTemplate{

	private UIEventsListener myListener;
	@FXML private ListView<EventReport> allEvents;
	@FXML private Label errors;
	@FXML private Button submit;
	
	@Override
	public void initData() {
		errors.setText("");
		ArrayList<EventReport> arraylist;
		try {
			allEvents.getItems().clear();

			arraylist = new ArrayList<EventReport>(myListener.showAllEventReport());
			allEvents.getItems().addAll(FXCollections.observableArrayList(arraylist));
			
			allEvents.setCellFactory(lv -> new ListCell<EventReport>() {
			    @Override
			    public void updateItem(EventReport item, boolean empty) {
			        super.updateItem(item, empty);
			        if (empty) {
			            setText(null);
			        } else {
			            String text = "Title: "+item.getTitle() +"\t\t Date: "+ item.getDate()+
			            		"\nDescription: "+ item.getDescription() +"\t\tLocation: "+ item.getLocation() ; // get text from item
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
		if (allEvents.getSelectionModel().selectedItemProperty().getValue() != null) {
			errors.setText("");
			Object O = (Object)allEvents.getSelectionModel().selectedItemProperty().getValue();
			myListener.editAction(O, "/MainPageEngEditEvent.fxml");
		}
		else
			errors.setText("No Value Choosen");
	}
}
