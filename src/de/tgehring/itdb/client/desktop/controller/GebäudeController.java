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

import de.tgehring.itdb.client.desktop.model.Gebäude;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class GebäudeController represents the JavaFX Controller for buildings.
 */
public class GebäudeController {
	
	/** The id field. */
	@FXML private TextField idField;
	
	/** The description field. */
	@FXML private TextField bezeichnungField;
	
	/** The street field. */
	@FXML private TextField strasseField;
	
	/** The zip code field. */
	@FXML private TextField plzField;
	
	/** The lacation field. */
	@FXML private TextField ortField;
	
	/** The building table. */
	@FXML private TableView<Gebäude> gebäudeTable;
	
	/** The create button. */
	@FXML private Button createButton;
	
	/** The edit button. */
	@FXML private Button editButton;
	
	/** The delete button. */
	@FXML private Button deleteButton;
	
	/** The reset button. */
	@FXML private Button resetButton;
	
	/** The selected building. */
	private Gebäude selectedGebäude;
	
	/** The list of all buildings. */
	private List<Gebäude> gebäudeList;
	
	/** The OberservableList of all buildings. */
	private ObservableList<Gebäude> data;
	
	/**
	 * Instantiates a new gebäude controller.
	 */
	public GebäudeController() {
		selectedGebäude = new Gebäude();
	}
	
	/**
	 * Handles the create button action to create a building.
	 *
	 * @param event the event
	 */
	@FXML 
    protected void handleCreateButtonAction(ActionEvent event) {
		if(idField.getText().isEmpty()) {
			if(checkMandatoryFields()) {
				Gebäude gebäude = new Gebäude();
				gebäude.setBezeichnung(bezeichnungField.getText());
				gebäude.setStrasse(strasseField.getText());
				gebäude.setPlz(plzField.getText());
				gebäude.setOrt(ortField.getText());
				ClientResponse res = CRUDClient.getInstance().addGebäude(gebäude);
				if(res.getStatus() == 201) {
					updateList();
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Gebäude hinzugefügt");
					clearFields();
				}
			}
		}
	}
	
	/**
	 * Update the list of all buildings.
	 */
	private void updateList() {
		if(gebäudeList != null) {
			data.removeAll(gebäudeList);
		}
		gebäudeList = new LinkedList<Gebäude>();
		gebäudeList = CRUDClient.getInstance().getAllGebäude();
	}
	
	/**
	 * Handles the edit button action to edit the selected building.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Gebäude gebäude = new Gebäude();
				gebäude.setId(Integer.parseInt(idField.getText()));
				gebäude.setBezeichnung(bezeichnungField.getText());
				gebäude.setStrasse(strasseField.getText());
				gebäude.setPlz(plzField.getText());
				gebäude.setOrt(ortField.getText());
				ClientResponse res = CRUDClient.getInstance().editGebäude(gebäude);
				if(res.getStatus() == 201) {
					selectedGebäude = gebäude;
					for(int i=0; i<gebäudeList.size(); i++) {
						if(gebäudeList.get(i).getId() == gebäude.getId()) {
							gebäudeList.set(i, gebäude);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Gebäude gespeichert.");
				}
			}
		}
	}
	
	/**
	 * Handles the delete button action to delete the selected building.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			ClientResponse res = CRUDClient.getInstance().deleteGebäude(Integer.valueOf(idField.getText()));
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				gebäudeTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Gebäude gelöscht.");
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
		gebäudeTable.getSelectionModel().clearSelection();
		clearFields();
	}
	
	/**
	 * Check the mandatory fields.
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
	 * Loads all buildings from the database.
	 */
	private void loadGebäude() {
		gebäudeList = CRUDClient.getInstance().getAllGebäude();
		data = gebäudeTable.getItems();
		data.clear();
		data.addAll(gebäudeList);
	}
	
	/**
	 * Is called, when the view has been initialized.
	 */
	public void initialize() {
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		loadGebäude();
		initHandler();
		setTableItems();
	}

	/**
	 * Initialized the listeners.
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
		gebäudeTable.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		gebäudeTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Gebäude>() {

					@Override
					public void changed(ObservableValue<? extends Gebäude> arg0,
							Gebäude arg1, Gebäude arg2) {
						if (gebäudeList != null) {
							Gebäude gebäude = gebäudeTable.getSelectionModel()
									.getSelectedItem();
							if (gebäude != null) {
								selectedGebäude = new Gebäude();
								long id = Long.valueOf(gebäude.getId());
								for (Gebäude element : gebäudeList) {
									if (element.getId() == id) {
										selectedGebäude = element;
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
		if (selectedGebäude != null) {
			idField.setText(String.valueOf(selectedGebäude.getId()));
			bezeichnungField.setText(selectedGebäude.getBezeichnung());
			strasseField.setText(selectedGebäude.getStrasse());
			plzField.setText(selectedGebäude.getPlz());
			ortField.setText(selectedGebäude.getOrt());
		}
	}
	
	/**
	 * Clears all input fields.
	 */
	private void clearFields() {
		idField.clear();
		bezeichnungField.clear();
		strasseField.clear();
		plzField.clear();
		ortField.clear();
	}
	
	/**
	 * Fills the table with all buildings.
	 */
	private void setTableItems() {
		if (gebäudeList != null) {
			data = gebäudeTable.getItems();
			data.clear();
			for (Gebäude g : gebäudeList) {
				data.add(g);
			}
			if(selectedGebäude != null) {
				gebäudeTable.getSelectionModel().select(selectedGebäude);
			}
		}
	}
}
