package application;

import MVC.UIEventsListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Contact;
import model.Contact.eContactType;
import model.LostReport.lostStatus;

public class ContactView implements actionTemplate, editTemplate{

	private UIEventsListener myListener;
	private Contact myEditContact;

	@FXML private BorderPane bpAddContact;
	@FXML private BorderPane bpEditContact;
	@FXML private Button btnClose;
	@FXML private Button btnRemove;
	@FXML private Button btnSubmit;
	@FXML private Button btnUpdate;
	@FXML private ComboBox<eContactType> cmbType;
	@FXML private ComboBox<eContactType> cmbType2;
	@FXML private Label errors;
	@FXML private Label errors2;
	@FXML private Label lblContactNumber;
	@FXML private TextField tfEmail;
	@FXML private TextField tfEmail2;
	@FXML private TextField tfName;
	@FXML private TextField tfName2;
	@FXML private TextField tfPhone;
	@FXML private TextField tfPhone2;

	
	public void closeAction(ActionEvent event) {
		myListener.successClose("Closed", 0);
		myListener.successClose("Closed", 1);
	}

	public void removeAction(ActionEvent event) {
		try {
			if (myListener.deleteContact(myEditContact.getSerial()))
				myListener.successClose("Contact Removed Successfully", 2);
			else
				errors2.setText("error");
		} catch (Exception e) {
			errors2.setText(e.getMessage());
		}
	}

	public void submitAction(ActionEvent event) {
		/*if(tfLnumber.getText().length() ==0 
				||tfFirstName.getText().length()==0
				||tfLastName.getText().length()==0) {
			errors.setText("Miss value");
		}
		else
		{*/
			try {
				if (myListener.initContact( tfName.getText(), tfEmail.getText(), tfPhone.getText(), cmbType.getValue().ordinal()))
					myListener.successClose("Contact: Added Successfully", 2);
				else
					errors.setText("error");
			} catch (Exception e) {
				errors.setText(e.getMessage());
			}
		//}
	}

	public void updateAction(ActionEvent event) {
		/*if (tfWindowNumber.getText().isEmpty() || tfLPnum.getText().isEmpty())
			errors.setText("Miss value");
		else {*/
			try {
				
				if (myListener.updateContact(myEditContact.getSerial(), tfName2.getText(), tfEmail2.getText(), tfPhone2.getText(), cmbType2.getValue().ordinal()))
					myListener.successClose("Contact Updated", 2);
				else
					errors2.setText("error");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				errors2.setText(e.getMessage());
			}
		//}
	}

	@Override
	public void initData2(Object O) {
		errors2.setText("");
		bpEditContact.setDisable(false);
		myEditContact = (Contact)O;
		lblContactNumber.setText("Contact Number: " + myEditContact.getSerial());
		cmbType2.getItems().setAll(eContactType.values());
		cmbType2.getSelectionModel().select(myEditContact.getRepresent());
		tfName2.setText(myEditContact.getName());
		tfEmail2.setText(myEditContact.getEmail());
		tfPhone2.setText(myEditContact.getPhone());
	}

	@Override
	public void initData() {
		errors.setText("");
		bpEditContact.setDisable(true);
		cmbType.getItems().setAll(eContactType.values());
	}

	@Override
	public void registerListener(UIEventsListener myListener) {
		this.myListener = myListener;
	}

}
