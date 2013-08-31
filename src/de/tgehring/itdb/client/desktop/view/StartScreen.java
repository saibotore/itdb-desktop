/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import de.tgehring.itdb.client.desktop.view.utils.ViewSettings;
import de.tgehring.itdb.client.service.PrimaryStage;

// TODO: Auto-generated Javadoc
/**
 * The Class StartScreen.
 */
public class StartScreen extends Application {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Application.launch(StartScreen.class, args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		PrimaryStage.getInstance().setStage(stage);
		stage.setTitle("ITDB: Desktop Client");

		Parent login = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
		BorderPane pane = (BorderPane) login;
		stage.setScene(new Scene(login, 800, 600));
		pane.setTop(new ITDBHeader());
		stage.show();

		ViewSettings.maximize(stage);
	}

}