package application;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import MVC.UIEventsListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class MainPageClassView {

	private UIEventsListener myListener;
	FXMLLoader actionLoader;
	FXMLLoader viewLoader;

	@FXML private Label lblGeneralStatus;
	
	@FXML private BorderPane actionScreen;
	@FXML private Label actionTitle;
	@FXML private Label actionTitle1;
	@FXML private Button addCar;
	@FXML private Button addController;
	@FXML private Button addDriver;
	@FXML private Button allCars;
	@FXML private Button allControllers;
	@FXML private Button allDrivers;
	@FXML private Button allEvents;
	@FXML private Button allLosses;
	@FXML private Label bigError;
	@FXML private MenuItem editDelete;
	@FXML private Button eventActions;
	@FXML private MenuItem helpAbout;
	//@FXML private Label lblGeneralStatus;
	@FXML private Button lossesActions;
	@FXML private MenuItem mainLogout;
	@FXML private Button newEvent;
	@FXML private Button newLoss;
	@FXML private BorderPane viewScreen;


	public void registerListener(UIEventsListener newListener) {
		myListener = newListener;
		bigError.setText("");
		lblGeneralStatus.setText(" ");
		successCloseMe(null, 0);		
		successCloseMe(null, 1);
	}
	
	public void closeInsideAction(ActionEvent event) {
		successCloseMe(null, 0);		
		successCloseMe(null, 1);
	}
	
	public void aboutAction(ActionEvent event) {
		String msg = " BubbleDan° Controller automation by ASDF\r\n"
				+ "\r\n"
				+ "ASDF company was founded in 2021 and is named after its founders:\r\n"
				+ "Blon Shlomi & Dor Ferenc.\r\n"
				+ "\r\n"
				+ "Together, the brilliant duo are changing the world!\r\n"
				+ "\r\n"
				+ "The release of their innovative information management algorithm,\r\n"
				+ "guarantees a better future of automation and data base management!";
		JOptionPane.showMessageDialog(null, msg, "About : ASDF", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void typeSet(int type) {
		switch(type) {
			case 0: { //if bakar
				addCar.setDisable(true);
				allCars.setDisable(true);
				addDriver.setDisable(true);
				allDrivers.setDisable(true);
				addController.setDisable(true);
				allControllers.setDisable(true);
				lossesActions.setDisable(true);
				eventActions.setDisable(true);
				return;
			}
			case 1: { //car lot
				addController.setDisable(true);
				allControllers.setDisable(true);
				lossesActions.setDisable(true);
				eventActions.setDisable(true);
				return;
			}	
		}
	}

	public void userLogOut(ActionEvent event) throws IOException {
		try {
			Main m = new Main();
			m.changeSceneToLogin("/LoginPageEng.fxml", 877, 268);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			bigError.setText("" + e.getMessage());
		}
	}

	public void successCloseMe(String msg, int side) {
		if (side == 0)
			setMyActionScreen("/EmptyAction.fxml");
		else if (side == 1) {
			setMyViewScreen("/EmptyAction.fxml");
			EmptyActionView tempEmpty = viewLoader.getController();
			tempEmpty.noImage();
		}
		if (msg != null)
			lblGeneralStatus.setText("" + msg);
	}

	public void initViewData() {
		actionTemplate tempController = viewLoader.getController();
		tempController.initData();
	}

	//function sets action screen to be an edit theme
	public void setMyActionScreenToEdit(String fxmlName, Object O) {	
		lblGeneralStatus.setText(" ");
		try {
			actionLoader = new FXMLLoader();
			actionLoader.setLocation(getClass().getResource(fxmlName));
			Parent tempAction;
			tempAction = actionLoader.load();
			actionScreen.setCenter(tempAction);
			tempAction.minWidth(500);

			editTemplate tempController = actionLoader.getController();
			tempController.registerListener(myListener);
			tempController.initData2(O);
		} catch (Exception e) {	bigError.setText("" + e.getMessage());}
	}

	/**
	 * function adds new inner screen in action center
	 * then calls its personal controller to initiate data
	 * @throws IOException 
	 */
	private void setMyActionScreen(String fxmlName) {	
		lblGeneralStatus.setText(" ");
		try {
			actionLoader = new FXMLLoader();
			actionLoader.setLocation(getClass().getResource(fxmlName));
			Parent tempAction;
			tempAction = actionLoader.load();
			actionScreen.setCenter(tempAction);
			tempAction.minWidth(500);

			actionTemplate tempController = actionLoader.getController();
			tempController.registerListener(myListener);
			tempController.initData();
		} catch (Exception e) {	bigError.setText("" + e.getMessage());}
	}

	private void setMyViewScreen(String fxmlName) {	
		lblGeneralStatus.setText(" ");
		try {
			viewLoader = new FXMLLoader();
			viewLoader.setLocation(getClass().getResource(fxmlName));
			Parent tempView;
			tempView = viewLoader.load();
			viewScreen.setCenter(tempView);
			tempView.minWidth(400);

			actionTemplate tempController = viewLoader.getController();
			tempController.registerListener(myListener);
			tempController.initData();
		} catch (Exception e) {	bigError.setText(" " + e.getMessage());}
	}

	public void newLossAction(ActionEvent event)  {
		setMyActionScreen("/MainPageEngNewLoss.fxml");
	}

	public void newEventAction(ActionEvent event)  {
		setMyActionScreen("/MainPageEngNewEvent.fxml");
	}

	public void showAllLossesView(ActionEvent event)  {
		setMyViewScreen("/MainPageEngShowLosts2.fxml");
	}

	public void addCarAction(ActionEvent event)  {
		setMyActionScreen("/MainPageEngAddCar.fxml");
	}

	public void showAllEventsAction(ActionEvent event) {
		setMyViewScreen("/MainPageEngShowAllEvents.fxml");
	}

	public void showAllCarAction(ActionEvent event) {
		setMyViewScreen("/MainPageEngShowAllCars.fxml");
	}

	public void openAddDriverAction(ActionEvent event) {
		setMyActionScreen("/MainPageEngAddDriver.fxml");
	}

	public void showAllDriversAction(ActionEvent event) {
		setMyViewScreen("/MainPageEngShowAllDrivers.fxml"); //allDrivers
	}

	public void addControllerAction(ActionEvent event) {
		setMyActionScreen("/MainPageEngAddController.fxml");
	}

	public void showAllControllersActions(ActionEvent event) {
		setMyViewScreen("/MainPageEngShowAllControllers.fxml");
	}
	public void contactsAction(ActionEvent event) {
		setMyActionScreen("/MainPageEngEditContacts.fxml");
		setMyViewScreen("/MainPageEngShowAllContacts.fxml");
	}
	
	public void setMyActionScreenToEditandAdd(String fxmlName, Object O) {	
		lblGeneralStatus.setText(" ");
		try {
			actionLoader = new FXMLLoader();
			actionLoader.setLocation(getClass().getResource(fxmlName));
			Parent tempAction;
			tempAction = actionLoader.load();
			actionScreen.setCenter(tempAction);
			tempAction.minWidth(500);

			actionTemplate tempController = actionLoader.getController();
			tempController.registerListener(myListener);
			tempController.initData();
			
			editTemplate tempController2 = actionLoader.getController();
			tempController2.registerListener(myListener);
			tempController2.initData2(O);
		} catch (Exception e) {	bigError.setText("" + e.getMessage());}
	}
}