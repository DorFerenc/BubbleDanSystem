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
import model.Contact;

public class ShowAllContactsView implements actionTemplate{

	private UIEventsListener myListener;
	@FXML private ListView<Contact> allContacts;
	@FXML private Label errors;
	@FXML private Button submit;
	
	@Override
	public void initData() {
		errors.setText("");
		ArrayList<Contact> arraylist;
		try {
			allContacts.getItems().clear();

			arraylist = new ArrayList<Contact>(myListener.showAllContact()); 
			allContacts.getItems().addAll(FXCollections.observableArrayList(arraylist));
			
			allContacts.setCellFactory(lv -> new ListCell<Contact>() {
			    @Override
			    public void updateItem(Contact item, boolean empty) {
			        super.updateItem(item, empty);
			        if (empty) {
			            setText(null);
			        } else {
			            String text = "Name: "+item.getName() +"\t\t Represen: "+ item.getRepresent()+
			            		"\nPhone: "+ item.getPhone() +"\t\tEmail: "+ item.getEmail() ;
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
		if (allContacts.getSelectionModel().selectedItemProperty().getValue() != null) {
			errors.setText("");
			Object O = (Object)allContacts.getSelectionModel().selectedItemProperty().getValue();
			//myListener.editAction(O, "/MainPageEngEditContacts.fxml");
			myListener.doEditandAdd(O, "/MainPageEngEditContacts.fxml");

		}
		else
			errors.setText("No Value Choosen");
	}
}
