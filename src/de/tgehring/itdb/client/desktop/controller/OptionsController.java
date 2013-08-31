/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.controller;

import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.service.ConnectionClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * The Class OptionsController represents the JavaFX Controller for the options view.
 */
public class OptionsController {
	
	/** The url field. */
	@FXML private TextField urlField;
	
	/**
	 * Instantiates a new options controller.
	 */
	public OptionsController() {

	}

	/**
	 * Handles the edit button action to edit the options.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleEditButton(ActionEvent event) {
		String url = urlField.getText();
		
		ConnectionClient.getInstance().setUrl(url);
		Message m = Message.getInstance();
		m.setType(MessageType.Options);
		m.setMessage("Einstellungen gespeichert.");
	}

	/**
	 * Is called, when the view has been initialized.
	 */
	public void initialize() {
		urlField.setText(ConnectionClient.getInstance().getUrl());
	}

}
