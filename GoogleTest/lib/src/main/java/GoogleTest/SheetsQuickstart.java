package GoogleTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class SheetsQuickstart  extends Application{

	/*
	 * Global instance of the scopes required by this quickstart. If modifying these
	 * scopes, delete your previously saved tokens/ folder.
	 * 
	 */

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */


	/**
	 * Prints the names and majors of students in a sample spreadsheet:
	 * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
	 * @throws Exception 
	 */
	public static void main(String... args) throws Exception {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String range = "A2:F10";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, GoogleSheetAPI.JSON_FACTORY, GoogleSheetAPI.getCredentials(HTTP_TRANSPORT))
				.setApplicationName(GoogleSheetAPI.APPLICATION_NAME).build();
		
		GoogleSheetAPI ap = new GoogleSheetAPI();

		//Response Expample
		List<List<Object>> values = ap.getResponse( range);
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {
			System.out.println("Name, Major");
			for (List row : values) {
				// Print columns A and E, which correspond to indices 0 and 4.
				System.out.printf("%s, %s\n", row.get(0), row.get(1));
			}
		}
		
		
		ValueRange body = new ValueRange()
				.setValues(Arrays.asList(Arrays.asList("Tamir", "ya" ,"gay")));
		
	//	UpdateValuesResponse append = service.spreadsheets().values().update(spreadsheetId, range, body).setValueInputOption("USER_ENTERED").execute();
		//AppendValuesResponse
        launch(args);

	}

    @Override
    public void start(Stage primaryStage) throws Exception {
        String message = "Hello World!";
        Button btnHello = new Button();
        btnHello.setText(message);
 
        // A layout container for UI controls
        StackPane root = new StackPane();
        root.getChildren().add(btnHello);
 
        // Top level container for all view content
        Scene scene = new Scene(root, 300, 250);
 
        // primaryStage is the main top level window created by platform
        primaryStage.setTitle(message);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}