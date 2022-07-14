package application;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import MVC.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Model;

public class Main extends Application implements Serializable{
	
	private static Stage stg; 
	
	@Override
	public void start(Stage primaryStage) {
		/*String whatTheUserEntered = JOptionPane.showInputDialog("Enter MySQL Password");
		if (whatTheUserEntered == null) {
		    System.out.println("The user canceled");
		}*/
		stg = primaryStage;
		Image iconImg = new Image(getClass().getResourceAsStream("/img/logoNoBackCrop.png"));
		primaryStage.getIcons().add(iconImg);
		
		try {
		Parent root = FXMLLoader.load(getClass().getResource("/LoginPageEng.fxml"));
		primaryStage.setTitle("BubbleDan°");
		primaryStage.setScene(new Scene(root, 877, 232));
		primaryStage.setMinHeight(268);
		primaryStage.setMinWidth(877);
		primaryStage.show();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "" + e.getMessage(), "Main Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}	
	}
	
	public void changeSceneToMain(String fxml,int type, double x, double y) throws IOException, ClassNotFoundException, SQLException {
		//input: fxml file
		//output: function opens new fxml file
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(fxml));
		Parent pane = loader.load();
		stg.getScene().setRoot(pane);
		stg.setWidth(x);
		stg.setHeight(y);
		stg.setMinHeight(y - 20);
		stg.setMinWidth(x - 20);

		MainPageClassView mainPageView = loader.getController();
		Model model = new Model();
		Controller controller = new Controller(model, mainPageView, type);
	}
	
	public void changeSceneToLogin(String fxml, double x, double y) throws IOException, ClassNotFoundException, SQLException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(fxml));
		Parent pane = loader.load();
		stg.getScene().setRoot(pane);
		stg.setWidth(x);
		stg.setHeight(y);
		stg.setMinHeight(y - 20);
		stg.setMinWidth(x - 20);
	}

	public static void main(String[] args){
		launch(args);
}
}
