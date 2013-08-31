/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.controller;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import com.sun.jersey.api.client.ClientResponse;

import de.tgehring.itdb.client.desktop.model.Benutzer;
import de.tgehring.itdb.client.desktop.utils.DBUtils;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class UserController represents the JavaFX Controller for users.
 */
public class UserController {

	/** The border pane. */
	@FXML private BorderPane borderPane;
	
	/** The id field. */
	@FXML private TextField idField;
	
	/** The first name field. */
	@FXML private TextField firstnameField;
	
	/** The last name field. */
	@FXML private TextField lastnameField;
	
	/** The user name field. */
	@FXML private TextField usernameField;
	
	/** The password field. */
	@FXML private PasswordField passwordField;
	
	/** The password repeat field. */
	@FXML private PasswordField passwordRepeatField;
	
	/** The admin check box. */
	@FXML private CheckBox adminCheckBox;
	
	/** The create button. */
	@FXML private Button createButton;
	
	/** The edit button. */
	@FXML private Button editButton;
	
	/** The delete button. */
	@FXML private Button deleteButton;
	
	/** The reset button. */
	@FXML private Button resetButton;
	
	/** The user table. */
	@FXML private TableView<Benutzer> userTable;

	/** The selected user. */
	private Benutzer selectedBenutzer;
	
	/** The list of all users. */
	private List<Benutzer> benutzerList;
	
	/** The ObservableList of all users. */
	private ObservableList<Benutzer> data;

	/**
	 * Instantiates a new user controller.
	 */
	public UserController() {
		selectedBenutzer = new Benutzer();
	}

	/**
	 * Handles the create button action to create a user.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleCreateButtonAction(ActionEvent event) {
		if (idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Benutzer benutzer = new Benutzer();
				benutzer.setVorname(firstnameField.getText());
				benutzer.setNachname(lastnameField.getText());
				benutzer.setBenutzername(usernameField.getText());
				String password = DBUtils.md5(passwordField.getText());
				benutzer.setPasswort(password);
				benutzer.setAdmin(adminCheckBox.isSelected());
				
				ClientResponse res = CRUDClient.getInstance().addBenutzer(benutzer);
				if(res.getStatus() == 201) {
					updateList();
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Benutzer hinzugefügt");
					clearFields();
				}
			}
		}
	}
	
	/**
	 * Updates the list of all users.
	 */
	private void updateList() {
		if(benutzerList != null) {
			data.removeAll(benutzerList);
		}
		benutzerList = new LinkedList<Benutzer>();
		benutzerList = CRUDClient.getInstance().getAllBenutzer();
	}

	/**
	 * Check mandatory fields.
	 *
	 * @return true, if successful
	 */
	private boolean checkMandatoryFields() {
		if (firstnameField.getText().isEmpty()) {
			sendMessage("Vorname");
			return false;
		}
		if (lastnameField.getText().isEmpty()) {
			sendMessage("Nachname");
			return false;
		}
		if (usernameField.getText().isEmpty()) {
			sendMessage("Benutzername");
			return false;
		}
		if (passwordField.getText().isEmpty()) {
			sendMessage("Passwort");
			return false;
		}
		if (passwordRepeatField.getText().isEmpty()) {
			sendMessage("Passwort wiederholen");
			return false;
		}
		if (!passwordField.getText().equals(passwordRepeatField.getText())) {
			Message.getInstance().setType(MessageType.General);
			Message.getInstance().setMessage("Die Passwörter stimmen nicht überein");
			return false;
		}
		return true;
	}
	
	/**
	 * Sends message, which field have to be set.
	 * 
	 * @param fieldname the name of the field
	 */
	private void sendMessage(String fieldname) {
		String message = "Folgendes Feld ist ein Pflichtfeld: ";
		Message m = Message.getInstance();
		m.setType(MessageType.General);
		m.setMessage(message + fieldname);
	}

	/**
	 * Handles the edit button action to edit the selected user.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Benutzer benutzer = new Benutzer();
				benutzer.setId(Long.parseLong(idField.getText()));
				benutzer.setVorname(firstnameField.getText());
				benutzer.setNachname(lastnameField.getText());
				benutzer.setBenutzername(usernameField.getText());
				benutzer.setPasswort(passwordField.getText());
				benutzer.setAdmin(adminCheckBox.isSelected());
				
				ClientResponse res = CRUDClient.getInstance().editBenutzer(benutzer);
				if(res.getStatus() == 201) {
					selectedBenutzer = benutzer;
					for(int i=0; i<benutzerList.size(); i++) {
						if(benutzerList.get(i).getId() == benutzer.getId()) {
							benutzerList.set(i, benutzer);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Benutzer gespeichert.");
				}
			}
		}
	}

	/**
	 * Handles the delete button action to delete the selected user.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			ClientResponse res = CRUDClient.getInstance().deleteBenutzer(
					Long.parseLong(idField.getText()));
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				userTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Benutzer gelöscht.");
			}
		}
	}

	/**
	 * Handles the reset button action to reset the view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleResetButtonAction(ActionEvent event) {
		userTable.getSelectionModel().clearSelection();
		clearFields();
	}

	/**
	 * Loads all users from the database.
	 */
	public void loadBenutzer() {
		benutzerList = CRUDClient.getInstance().getAllBenutzer();
	}

	
	/**
	 * Is called, when the view has been initialized.
	 */
	public void initialize() {
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		loadBenutzer();
		initHandler();
		setTableItems();
	}

	/**
	 * Initializes all listeners.
	 */
	private void initHandler() {
		idField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				if (!idField.getText().isEmpty()) {
					createButton.setDisable(true);
					editButton.setDisable(false);
					deleteButton.setDisable(false);
				} else {
					createButton.setDisable(false);
					editButton.setDisable(true);
					deleteButton.setDisable(true);
				}
			}
		});
		userTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		userTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Benutzer>() {

					@Override
					public void changed(
							ObservableValue<? extends Benutzer> arg0,
							Benutzer arg1, Benutzer arg2) {
						if (benutzerList != null) {
							Benutzer user = userTable.getSelectionModel()
									.getSelectedItem();
							if (user != null) {
								selectedBenutzer = new Benutzer();
								long id = Long.valueOf(user.getId());
								for (Benutzer benutzer : benutzerList) {
									if (benutzer.getId() == id) {
										selectedBenutzer = benutzer;
										setFields();
									}
								}
							}
						}
					}

				});
	}

	/**
	 * Sets all input fields.
	 */
	private void setFields() {
		clearFields();
		idField.setText(String.valueOf(selectedBenutzer.getId()));
		firstnameField.setText(selectedBenutzer.getVorname());
		lastnameField.setText(selectedBenutzer.getNachname());
		usernameField.setText(selectedBenutzer.getBenutzername());
		passwordField.setText(selectedBenutzer.getPasswort());
		passwordRepeatField.setText(selectedBenutzer.getPasswort());
		adminCheckBox.setSelected(selectedBenutzer.isAdmin());
	}

	/**
	 * Clear all input fields.
	 */
	private void clearFields() {
		idField.clear();
		firstnameField.clear();
		lastnameField.clear();
		usernameField.clear();
		passwordField.clear();
		passwordRepeatField.clear();
		adminCheckBox.setSelected(false);
	}

	/**
	 * Sets the table items.
	 */
	private void setTableItems() {
		if (benutzerList != null) {
			data = userTable.getItems();
			data.clear();
			for (Benutzer b : benutzerList) {
				data.add(b);
			}
			if(selectedBenutzer != null) {
				userTable.getSelectionModel().select(selectedBenutzer);
			}
		}
	}

}
