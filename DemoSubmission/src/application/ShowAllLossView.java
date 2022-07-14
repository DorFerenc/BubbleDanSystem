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
import model.LostReport;

public class ShowAllLossView implements actionTemplate {

	private UIEventsListener myListener;
	
	@FXML private ListView<LostReport> allLosses;
	@FXML private Label errors;
	@FXML private Button submit;
	
	@Override public void initData() {
		errors.setText("");
		ArrayList<LostReport> arraylist;
		try {
			allLosses.getItems().clear();

			arraylist = new ArrayList<LostReport>(myListener.showAllLostReport());
			allLosses.getItems().addAll(FXCollections.observableArrayList(arraylist));
			allLosses.setCellFactory(lv -> new ListCell<LostReport>() {
			    @Override
			    public void updateItem(LostReport item, boolean empty) {
			        super.updateItem(item, empty);
			        if (empty) {
			            setText(null);
			        } else {
			            String text = "Description: "+item.getItemDescription() +"\t\t Is Valuable: "+ item.isValuable()+
			            		"\nStatus: "+ item.getStatus() +"\t\tDate Found: "+ item.getFoundDate(); 
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
		if (allLosses.getSelectionModel().selectedItemProperty().getValue() != null) {
			errors.setText("");
			Object O = (Object)allLosses.getSelectionModel().selectedItemProperty().getValue();
			myListener.editAction(O, "/MainPageEngEditLoss.fxml");
		}
		else
			errors.setText("No Value Choosen");
    }

}
