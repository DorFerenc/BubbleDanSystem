/**
 * 
 */
package GoogleTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
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

/**
 * @author yuval Yuval Mastey Tamir Spilberg
 */
public class GoogleSheetAPI {
	public static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	public static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	public static final String TOKENS_DIRECTORY_PATH = "tokens";
	public static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
	public static final String CREDENTIALS_FILE_PATH = "/credentials.json";
	public static final String spreadsheetId = "1Cc1QQEwv3vfQrxS_Jd05oM8rZwSBRV7Bax9TJMydzmg";
	public NetHttpTransport HTTP_TRANSPORT;
	public static Sheets service = null;

	

	public GoogleSheetAPI() throws Exception, IOException {
		HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		service = new Sheets.Builder(HTTP_TRANSPORT, this.JSON_FACTORY, GoogleSheetAPI.getCredentials(HTTP_TRANSPORT))
				.setApplicationName(GoogleSheetAPI.APPLICATION_NAME).build();
			}

	public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
		// Load client secrets.
		InputStream in = SheetsQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
						.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
						.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	public static List<List<Object>> getResponse(String range) throws IOException {
			com.google.api.services.sheets.v4.model.ValueRange response = service.spreadsheets().values()
					.get(spreadsheetId, range).execute();
			return response.getValues();
	}

	public Sheets getService() {
		return service;
	}


	/*
	 * public static void updateLineToSheet(Sheets service,String[] arr,String
	 * range) { ValueRange body = new ValueRange()
	 * .setValues(Arrays.asList(Arrays.asList("Tamir", "ya" ,"gay")));
	 * 
	 * ValueRange body = new ValueRange()
	 * .setValues(Arrays.asList((List<String>)Arrays.asList(arr)));
	 * 
	 * UpdateValuesResponse update =
	 * service.spreadsheets().values().update(spreadsheetId, range,
	 * body).setValueInputOption("USER_ENTERED").execute();
	 * 
	 * }
	 */

}
