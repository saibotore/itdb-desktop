/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import com.sun.jersey.api.client.ClientResponse;

import de.tgehring.itdb.client.desktop.model.Hersteller;
import de.tgehring.itdb.client.desktop.model.HerstellerTyp;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class HerstellerController represents the JavaFX Controller for manufacturers.
 */
public class HerstellerController {
	
	/** The id field. */
	@FXML private TextField idField;
	
	/** The description field. */
	@FXML private TextField bezeichnungField;
	
	/** The manufacturer type combo box. */
	@FXML private ComboBox<HerstellerTyp> typComboBox;
	
	/** The manufacturer table. */
	@FXML private TableView<Hersteller> herstellerTable;
	
	/** The create button. */
	@FXML private Button createButton;
	
	/** The edit button. */
	@FXML private Button editButton;
	
	/** The delete button. */
	@FXML private Button deleteButton;
	
	/** The reset button. */
	@FXML private Button resetButton;
	
	/** The selected manufacturer type. */
	private HerstellerTyp selectedHerstellerTyp;
	
	/** The selected manufacturer. */
	private Hersteller selectedHersteller;
	
	/** The list of all manufacturers. */
	private List<Hersteller> herstellerList;
	
	/** The ObservableList of all manufacturers. */
	private ObservableList<Hersteller> data;
	
	/**
	 * Instantiates a new manufacturer controller.
	 */
	public HerstellerController() {
		selectedHersteller = new Hersteller();
	}
	

	/**
	 * Handles the create button action to create a manufacturer.
	 *
	 * @param event the event
	 */
	@FXML 
    protected void handleCreateButtonAction(ActionEvent event) {
		if(idField.getText().isEmpty()) {
			if(checkMandatoryFields()) {
				Hersteller hersteller = new Hersteller();
				hersteller.setBezeichnung(bezeichnungField.getText());
				hersteller.setTyp(selectedHerstellerTyp);
				
				ClientResponse res = CRUDClient.getInstance().addHersteller(hersteller);
				if(res.getStatus() == 201) {
					updateList();
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Hersteller hinzugefügt");
					clearFields();
				}
			}
		}
	}
	
	/**
	 * Updates the list of all manufacturers.
	 */
	private void updateList() {
		if(herstellerList != null) {
			data.removeAll(herstellerList);
		}
		herstellerList = new LinkedList<Hersteller>();
		herstellerList = CRUDClient.getInstance().getAllHersteller();
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
	 * Handles the edit button action to edit a manufacturer.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Hersteller hersteller = new Hersteller();
				hersteller.setId(Long.parseLong(idField.getText()));
				hersteller.setBezeichnung(bezeichnungField.getText());
				hersteller.setTyp(selectedHerstellerTyp);
				
				ClientResponse res = CRUDClient.getInstance().editHersteller(hersteller);
				if(res.getStatus() == 201) {
					selectedHersteller = hersteller;
					for(int i=0; i<herstellerList.size(); i++) {
						if(herstellerList.get(i).getId() == hersteller.getId()) {
							herstellerList.set(i, hersteller);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Hersteller gespeichert.");
				}
			}
		}
	}
	
	/**
	 * Handles the delete button action to delete a manufacturer.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			ClientResponse res = CRUDClient.getInstance().deleteHersteller(Integer.valueOf(idField.getText()));
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				herstellerTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Hersteller gelöscht.");
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
		herstellerTable.getSelectionModel().clearSelection();
		clearFields();
	}

	/**
	 * Load a manufacturers from the database.
	 */
	private void loadHersteller() {
		herstellerList = CRUDClient.getInstance().getAllHersteller();
		data = herstellerTable.getItems();
		data.clear();
		data.addAll(herstellerList);
		
		List<HerstellerTyp> herstellerTypen = Arrays.asList(HerstellerTyp.values());
		ObservableList<HerstellerTyp> typData = typComboBox.getItems();
		typData.clear();
		typData.addAll(herstellerTypen);
	}
	
	/**
	 * Is called, when the view has been initialized.
	 */
	public void initialize() {
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		loadHersteller();
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
		herstellerTable.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		herstellerTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Hersteller>() {

					@Override
					public void changed(ObservableValue<? extends Hersteller> arg0,
							Hersteller arg1, Hersteller arg2) {
						if (herstellerList != null) {
							Hersteller hersteller = herstellerTable.getSelectionModel()
									.getSelectedItem();
							if (hersteller != null) {
								selectedHersteller = new Hersteller();
								long id = Long.valueOf(hersteller.getId());
								for (Hersteller element : herstellerList) {
									if (element.getId() == id) {
										selectedHersteller = element;
										setFields();
									}
								}
							}
						}
					}
				});
		typComboBox.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<HerstellerTyp>() {

					@Override
					public void changed(
							ObservableValue<? extends HerstellerTyp> arg0,
							HerstellerTyp arg1, HerstellerTyp arg2) {
						selectedHerstellerTyp = typComboBox.getSelectionModel().getSelectedItem();
					}
				});
	}
	
	/**
	 * Sets all input fields.
	 */
	private void setFields() {
		if (selectedHersteller != null) {
			idField.setText(String.valueOf(selectedHersteller.getId()));
			bezeichnungField.setText(selectedHersteller.getBezeichnung());
			selectedHerstellerTyp = selectedHersteller.getTyp();
			typComboBox.getSelectionModel().select(selectedHerstellerTyp);
		}
	}
	
	/**
	 * Clears all input fields.
	 */
	private void clearFields() {
		idField.clear();
		bezeichnungField.clear();
		selectedHerstellerTyp = null;
		typComboBox.getSelectionModel().clearSelection();
		typComboBox.getEditor().setText("");
	}
	
	/**
	 * Fills the table with all manufacturer.
	 */
	private void setTableItems() {
		if (herstellerList != null) {
			data = herstellerTable.getItems();
			data.clear();
			for (Hersteller h : herstellerList) {
				data.add(h);
			}
			if(selectedHersteller != null) {
				herstellerTable.getSelectionModel().select(selectedHersteller);
			}
		}
	}
}