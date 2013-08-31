/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import de.tgehring.itdb.client.desktop.view.FXMLLoadTask;
import de.tgehring.itdb.client.desktop.view.ITDBHeader;
import de.tgehring.itdb.client.desktop.view.utils.ViewSettings;
import de.tgehring.itdb.client.service.ConnectionClient;
import de.tgehring.itdb.client.service.DesktopSession;
import de.tgehring.itdb.client.service.PrimaryStage;

/**
 * The Class LoginController represents the JavaFX Controller for the login process.
 */
public class LoginController {

	/** The border pane. */
	@FXML private BorderPane borderPane;
	
	/** The username field. */
	@FXML private TextField usernameField;
	
	/** The password field. */
	@FXML private PasswordField passwordField;
	
	/** The loading pop. */
	private Popup pop;
	
	/**
	 * Instantiates a new login controller.
	 */
	public LoginController() {
		pop = new Popup();
	}

	/**
	 * Handles the login button action to login a user.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleLoginButton(ActionEvent event) {
		String benutzername = usernameField.getText();
		String passwort = passwordField.getText();
		ConnectionClient.getInstance().login(benutzername, passwort);
		DesktopSession.getInstance().setLoggedIn(true);
		pop = new Popup();
		final ProgressIndicator indicator = new ProgressIndicator();
		pop.getContent().add(indicator);
		pop.show(PrimaryStage.getInstance().getStage());
		pop.centerOnScreen();
		final Task<Parent> loadTask = new FXMLLoadTask("../view/fxml/main.fxml");
		loadTask.stateProperty().addListener(
				new ChangeListener<Worker.State>() {
					@Override
					public void changed(
							ObservableValue<? extends Worker.State> stateProp,
							Worker.State oldState, Worker.State newState) {
						switch (newState) {
						case SCHEDULED:
							indicator.setProgress(-1);
							break;
						case SUCCEEDED:
							setView(loadTask.getValue());
							break;
						default:
							break;
						}
					}

				});
		new Thread(loadTask).start();
	}
	
	/**
	 * Sets the view.
	 *
	 * @param parent the new view
	 */
	private void setView(Parent parent) {
		pop.hide();
		Stage stage = PrimaryStage.getInstance().getStage();
		stage.setScene(new Scene(parent, ViewSettings.getWidth(),
				ViewSettings.getHeight()));
		BorderPane pane = (BorderPane) parent;
		pane.setTop(new ITDBHeader());
	}
	
	/**
	 * Is called, when the view has been initialized.
	 */
	public void initialize() {
		
	}

}
