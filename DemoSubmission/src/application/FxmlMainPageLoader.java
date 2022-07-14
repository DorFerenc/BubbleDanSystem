package application;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class FxmlMainPageLoader {

	private Pane actionScreen;

	public Pane getPage(String fxmlName) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(fxmlName));
		Parent tempAction = loader.load();
		actionScreen = new Pane(tempAction);
		/*actionTemplate tempController = loader.getController();
		
		URL fileUrl = MainPageClassView.class.getResource(fxmlName);
		new FXMLLoader();
		try {
			actionScreen = FXMLLoader.load(fileUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return actionScreen;


	}
}
