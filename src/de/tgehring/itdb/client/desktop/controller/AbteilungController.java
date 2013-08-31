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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import com.sun.jersey.api.client.ClientResponse;

import de.tgehring.itdb.client.desktop.model.Abteilung;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class AbteilungController represents the JavaFX Controller for departments.
 */
public class AbteilungController {

	/** The id field. */
	@FXML private TextField idField;
	
	/** The description field. */
	@FXML private TextField bezeichnungField;
	
	/** The create button. */
	@FXML private Button createButton;
	
	/** The edit button. */
	@FXML private Button editButton;
	
	/** The delete button. */
	@FXML private Button deleteButton;
	
	/** The reset button. */
	@FXML private Button resetButton;
	
	/** The departments table. */
	@FXML private TableView<Abteilung> abteilungTable;

	/** The selected department. */
	private Abteilung selectedAbteilung;
	
	/** The list of all departments. */
	private List<Abteilung> abteilungList;
	
	/** The observable list of all departments. */
	private ObservableList<Abteilung> data;

	/**
	 * Instantiates a new department controller.
	 */
	public AbteilungController() {
		selectedAbteilung = new Abteilung();
	}

	/**
	 * Handles the create button action to create a department.
	 *
	 * @param event the ActionEvent
	 */
	@FXML
	protected void handleCreateButtonAction(ActionEvent event) {
		if (idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Abteilung abteilung = new Abteilung();
				abteilung.setBezeichnung(bezeichnungField.getText());
				ClientResponse res = CRUDClient.getInstance().addAbteilung(abteilung);
				if(res.getStatus() == 201) {
					if(res.getStatus() == 201) {
						updateList();
						setTableItems();
						Message message = Message.getInstance();
						message.setType(MessageType.Database);
						message.setMessage("Abteilung hinzugefügt");
						clearFields();
					}
				}
			}
		}
	}
	
	/**
	 * Updates the list of all departments.
	 */
	private void updateList() {
		if(abteilungList != null) {
			abteilungTable.getItems().removeAll(abteilungList);
		}
		abteilungList = new LinkedList<Abteilung>();
		abteilungList = CRUDClient.getInstance().getAllAbteilung();
	}

	/**
	 * Check if the mandatory fields are filled in.
	 *
	 * @return true, if successful
	 */
	private boolean checkMandatoryFields() {
		if(bezeichnungField.getText().isEmpty()) {
			sendMessage("Bezeichnung");
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
	 * Handles the edit button action to edit the selected department.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Abteilung abteilung = new Abteilung();
				abteilung.setId(Long.parseLong(idField.getText()));
				abteilung.setBezeichnung(bezeichnungField.getText());
				ClientResponse res = CRUDClient.getInstance().editAbteilung(abteilung);
				if(res.getStatus() == 201) {
					selectedAbteilung = abteilung;
					for(int i=0; i<abteilungList.size(); i++) {
						if(abteilungList.get(i).getId() == abteilung.getId()) {
							abteilungList.set(i, abteilung);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Abteilung gespeichert.");
				}
			}
		}
	}

	/**
	 * Handles the delete button action to delete the selected department.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			ClientResponse res = CRUDClient.getInstance().deleteAbteilung(Long.parseLong(idField.getText()));
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				abteilungTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Abteilung gelöscht.");
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
		abteilungTable.getSelectionModel().clearSelection();
		clearFields();
	}

	/**
	 * Load all departments from the database.
	 */
	private void loadAbteilungen() {
		abteilungList = CRUDClient.getInstance().getAllAbteilung();
	}

	/**
	 * Is called, when the view has been initialized
	 */
	public void initialize() {
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		loadAbteilungen();
		initHandler();
		setTableItems();
	}

	/**
	 * Initialize all Listeners.
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
		abteilungTable.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		abteilungTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Abteilung>() {

					@Override
					public void changed(
							ObservableValue<? extends Abteilung> arg0,
							Abteilung arg1, Abteilung arg2) {
						if (abteilungList != null) {
							Abteilung abteilung = abteilungTable
									.getSelectionModel().getSelectedItem();
							if (abteilung != null) {
								selectedAbteilung = new Abteilung();
								long id = Long.valueOf(abteilung.getId());
								for (Abteilung element : abteilungList) {
									if (element.getId() == id) {
										selectedAbteilung = element;
										setFields();
									}
								}
							}
						}
					}

				});
	}

	/**
	 * Sets the fields with the data of the selected department.
	 */
	private void setFields() {
		if (selectedAbteilung != null) {
			idField.setText(String.valueOf(selectedAbteilung.getId()));
			bezeichnungField.setText(selectedAbteilung.getBezeichnung());
		}
	}
	
	/**
	 * Clears all input fields.
	 */
	private void clearFields() {
		idField.clear();
		bezeichnungField.clear();
	}

	/**
	 * Fills the table with all departments.
	 */
	private void setTableItems() {
		if (abteilungList != null) {
			data = abteilungTable.getItems();
			data.clear();
			for(Abteilung a: abteilungList) {
				data.add(a);
			}
			if(selectedAbteilung != null) {
				abteilungTable.getSelectionModel().select(selectedAbteilung);
			}
		}
	}

}
