package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginClassView {
	private Connection conn = null;
	private final String PASSWORD = "asdf12";
	private final String USERNAME = "root";
	private final String DBURL = "jdbc:mysql://localhost:3306/login";

	public LoginClassView() {

	}

	@FXML protected Button login;
	@FXML protected Label errors;
	@FXML protected TextField username;
	@FXML protected PasswordField password;

	public void userLogin(ActionEvent event) {
		errors.setTextFill(Color.RED);
		errors.setText("");
		checkLogin();
	}

	private void checkLogin()  {
		Main m = new Main();
		int key;
		try {
			key = checkDetails();
			if(key != -1)
			{
				errors.setTextFill(Color.GREEN);
				errors.setText("Success!");
				m.changeSceneToMain("/MainPageEng.fxml", key, 1400, 750);
			}
			else if (username.getText().isEmpty() || password.getText().isEmpty()) {
				errors.setText("Please Enter Data");
			}
			else
				errors.setText("Wrong Information");
		} catch (ClassNotFoundException | SQLException | IOException e) {
			errors.setText("" + e.getMessage());
		}
	}

	private int checkDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = DBURL; //85.250.112.134:3306
			conn = DriverManager.getConnection(dbURL, USERNAME, PASSWORD);

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from loginT "
					+ "where username like '"+username.getText() +"' and emPassword like '"+ password.getText()+"';");
			if((rs.next()))
				return rs.getInt("emType");

		} catch (ClassNotFoundException | SQLException e) {
			errors.setText("" + e.getMessage());
		}
		return -1;
	}
}

