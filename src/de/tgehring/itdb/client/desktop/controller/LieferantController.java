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

import de.tgehring.itdb.client.desktop.model.Lieferant;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class LieferantController represents the JavaFX Controller for supplier.
 */
public class LieferantController {
	
	/** The id field. */
	@FXML private TextField idField;
	
	/** The description field. */
	@FXML private TextField bezeichnungField;
	
	/** The street field. */
	@FXML private TextField strasseField;
	
	/** The zip code field. */
	@FXML private TextField plzField;
	
	/** The location field. */
	@FXML private TextField ortField;
	
	/** The mail box field. */
	@FXML private TextField postfachField;
	
	/** The url field. */
	@FXML private TextField urlField;
	
	/** The phone number field. */
	@FXML private TextField telefonField;
	
	/** The fax number field. */
	@FXML private TextField faxField;
	
	/** The hotline field. */
	@FXML private TextField hotlineField;
	
	/** The customer number field. */
	@FXML private TextField kdnrField;
	
	/** The national mark field. */
	@FXML private TextField lkzField;
	
	/** The create button. */
	@FXML private Button createButton;
	
	/** The edit button. */
	@FXML private Button editButton;
	
	/** The delete button. */
	@FXML private Button deleteButton;
	
	/** The reset button. */
	@FXML private Button resetButton;
	
	/** The supplier table. */
	@FXML private TableView<Lieferant> lieferantTable;

	/** The selected supplier. */
	private Lieferant selectedLieferant;
	
	/** The list of all supplier. */
	private List<Lieferant> lieferantList;
	
	/** The ObservableList of all supplier. */
	private ObservableList<Lieferant> data;
	
	/**
	 * Instantiates a new supplier controller.
	 */
	public LieferantController() {
		selectedLieferant = new Lieferant();
	}

	/**
	 * Handles the create button action to create a supplier.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleCreateButtonAction(ActionEvent event) {
		if (idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Lieferant lieferant = new Lieferant();
				lieferant.setBezeichnung(bezeichnungField.getText());
				lieferant.setStrasse(strasseField.getText());
				lieferant.setPlz(plzField.getText());
				lieferant.setOrt(ortField.getText());
				lieferant.setPostfach(postfachField.getText());
				lieferant.setUrl(urlField.getText());
				lieferant.setTelefon(telefonField.getText());
				lieferant.setFax(faxField.getText());
				lieferant.setHotline(hotlineField.getText());
				lieferant.setKundennummer(kdnrField.getText());
				lieferant.setLkz(lkzField.getText());
				
				ClientResponse res = CRUDClient.getInstance().addLieferant(lieferant);
				if(res.getStatus() == 201) {
					updateList();
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Lieferant hinzugefügt");
					clearFields();
				}
			}
		}
	}
	
	/**
	 * Updates the list of all supplier.
	 */
	private void updateList() {
		if(lieferantList != null) {
			data.removeAll(lieferantList);
		}
		lieferantList = new LinkedList<Lieferant>();
		lieferantList = CRUDClient.getInstance().getAllLieferant();
	}

	/**
	 * Check mandatory fields.
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
	 * Handles the edit button action to edit the selected supplier.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Lieferant lieferant = new Lieferant();
				lieferant.setId(Integer.parseInt(idField.getText()));
				lieferant.setBezeichnung(bezeichnungField.getText());
				lieferant.setStrasse(strasseField.getText());
				lieferant.setPlz(plzField.getText());
				lieferant.setOrt(ortField.getText());
				lieferant.setPostfach(postfachField.getText());
				lieferant.setUrl(urlField.getText());
				lieferant.setTelefon(telefonField.getText());
				lieferant.setFax(faxField.getText());
				lieferant.setHotline(hotlineField.getText());
				lieferant.setKundennummer(kdnrField.getText());
				lieferant.setLkz(lkzField.getText());
				
				ClientResponse res = CRUDClient.getInstance().editLieferant(lieferant);
				if(res.getStatus() == 201) {
					selectedLieferant = lieferant;
					for(int i=0; i<lieferantList.size(); i++) {
						if(lieferantList.get(i).getId() == lieferant.getId()) {
							lieferantList.set(i, lieferant);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Lieferant gespeichert.");
				}
			}
		}
	}

	/**
	 * Handles the delete button action to delete the selected supplier.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			ClientResponse res = CRUDClient.getInstance().deleteLieferant(Integer.valueOf(idField.getText()));
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				lieferantTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Lieferant gelöscht.");
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
		lieferantTable.getSelectionModel().clearSelection();
		clearFields();
	}

	/**
	 * Loads all supplier from the database.
	 */
	private void loadLieferanten() {
		lieferantList = CRUDClient.getInstance().getAllLieferant();
		data = lieferantTable.getItems();
		data.clear();
		data.addAll(lieferantList);
	}

	/**
	 * Is called, when the view has been initialized.
	 */
	public void initialize() {
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		loadLieferanten();
		initHandler();
		setTableItems();
	}

	/**
	 * Initialize all listeners.
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
		lieferantTable.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		lieferantTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Lieferant>() {

					@Override
					public void changed(
							ObservableValue<? extends Lieferant> arg0,
							Lieferant arg1, Lieferant arg2) {
						if (lieferantList != null) {
							Lieferant lieferant = lieferantTable
									.getSelectionModel().getSelectedItem();
							if (lieferant != null) {
								selectedLieferant = new Lieferant();
								long id = Long.valueOf(lieferant.getId());
								for (Lieferant element : lieferantList) {
									if (element.getId() == id) {
										selectedLieferant = element;
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
		if (selectedLieferant != null) {
			idField.setText(String.valueOf(selectedLieferant.getId()));
			bezeichnungField.setText(selectedLieferant.getBezeichnung());
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
		postfachField.clear();
		urlField.clear();
		telefonField.clear();
		faxField.clear();
		hotlineField.clear();
		kdnrField.clear();
		lkzField.clear();
	}

	/**
	 * Sets the table items.
	 */
	private void setTableItems() {
		if (lieferantList != null) {
			data = lieferantTable.getItems();
			data.clear();
			for (Lieferant a : lieferantList) {
				data.add(a);
			}
			if(selectedLieferant != null) {
				lieferantTable.getSelectionModel().select(selectedLieferant);
			}
		}
	}

}
