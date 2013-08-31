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
import de.tgehring.itdb.client.desktop.model.Rechnung;
import de.tgehring.itdb.client.desktop.model.Software;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.desktop.view.SuggestComboBox;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class SoftwareController represents the JavaFX Controller for software.
 */
public class SoftwareController {
	
	/** The id field. */
	@FXML private TextField idField;
	
	/** The description field. */
	@FXML private TextField bezeichnungField;
	
	/** The licences field. */
	@FXML private TextField lizenzenField;
	
	/** The software table. */
	@FXML private TableView<Software> softwareTable;
	
	/** The invoice combo box. */
	@FXML private SuggestComboBox<Rechnung> rechnungComboBox;
	
	/** The supplier combo box. */
	@FXML private SuggestComboBox<Lieferant> lieferantComboBox;
	
	/** The create button. */
	@FXML private Button createButton;
	
	/** The edit button. */
	@FXML private Button editButton;
	
	/** The delete button. */
	@FXML private Button deleteButton;
	
	/** The reset button. */
	@FXML private Button resetButton;
	
	/** The selected invoice. */
	private Rechnung selectedRechnung;
	
	/** The selected supplier. */
	private Lieferant selectedLieferant;

	/** The selected software. */
	private Software selectedSoftware;
	
	/** The list of all software. */
	private List<Software> softwareList;
	
	/** The ObservableList of all software. */
	private ObservableList<Software> data;
	
	
	/**
	 * Instantiates a new software controller.
	 */
	public SoftwareController() {
		selectedSoftware = new Software();
	}
	

	/**
	 * Handles the create button action to create a software.
	 *
	 * @param event the event
	 */
	@FXML 
    protected void handleCreateButtonAction(ActionEvent event) {
		if(idField.getText().isEmpty()) {
			if(checkMandatoryFields()) {
				Software software = new Software();
				software.setBezeichnung(bezeichnungField.getText());
				software.setMaxLizenzen(Integer.parseInt(lizenzenField.getText()));
				software.setAnzahlLizenzen(0);
				selectedLieferant = lieferantComboBox.getSelected();
				selectedRechnung = rechnungComboBox.getSelected();
				software.setLieferant(selectedLieferant);
				software.setRechnung(selectedRechnung);
				
				ClientResponse res = CRUDClient.getInstance().addSoftware(software);
				if(res.getStatus() == 201) {
					updateList();
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Software hinzugefügt");
					clearFields();
				}
			}
		}
	}
	
	/**
	 * Updates the list of all software.
	 */
	private void updateList() {
		if(softwareList != null) {
			data.removeAll(softwareList);
		}
		softwareList = new LinkedList<Software>();
		softwareList = CRUDClient.getInstance().getAllSoftware();
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
		if(lizenzenField.getText().isEmpty()) {
			sendMessage("Lizenzen");
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
	 * Handles the edit button action to edit the selected software.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Software software = new Software();
				software.setId(Integer.parseInt(idField.getText()));
				software.setBezeichnung(bezeichnungField.getText());
				software.setMaxLizenzen(Integer.parseInt(lizenzenField.getText()));
				selectedLieferant = lieferantComboBox.getSelected();
				selectedRechnung = rechnungComboBox.getSelected();
				software.setLieferant(selectedLieferant);
				software.setRechnung(selectedRechnung);
				
				ClientResponse res = CRUDClient.getInstance().editSoftware(software);
				if(res.getStatus() == 201) {
					selectedSoftware = software;
					for(int i=0; i<softwareList.size(); i++) {
						if(softwareList.get(i).getId() == software.getId()) {
							softwareList.set(i, software);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Software gespeichert.");
				}
			}
		}
	}
	
	/**
	 * Handles the delete button action to delete the selected software.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			ClientResponse res = CRUDClient.getInstance().deleteSoftware(Integer.valueOf(idField.getText()));
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				softwareTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Software gelöscht.");
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
		softwareTable.getSelectionModel().clearSelection();
		clearFields();
	}

	/**
	 * Loads all software from the database.
	 */
	private void loadSoftwares() {
		softwareList = CRUDClient.getInstance().getAllSoftware();
		data = softwareTable.getItems();
		data.clear();
		data.addAll(softwareList);
		
		List<Rechnung> rlist = CRUDClient.getInstance().getAllRechnung();
		rechnungComboBox.setList(rlist);
		
		List<Lieferant> llist = CRUDClient.getInstance().getAllLieferant();
		lieferantComboBox.setList(llist);
	}
	
	/**
	 * Is called, when the view has been initialized.
	 */
	public void initialize() {
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		loadSoftwares();
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
		softwareTable.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		softwareTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Software>() {

					@Override
					public void changed(ObservableValue<? extends Software> arg0,
							Software arg1, Software arg2) {
						if (softwareList != null) {
							Software software = softwareTable.getSelectionModel()
									.getSelectedItem();
							if (software != null) {
								selectedSoftware = new Software();
								long id = Long.valueOf(software.getId());
								for (Software element : softwareList) {
									if (element.getId() == id) {
										selectedSoftware = element;
										setFields();
									}
								}
							}
						}
					}

				});
		rechnungComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Rechnung>() {
			
			@Override
			public void changed(ObservableValue<? extends Rechnung> arg0,
					Rechnung arg1, Rechnung arg2) {
				selectedRechnung = rechnungComboBox.getSelectionModel().getSelectedItem();
			}
		});
		lieferantComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Lieferant>() {
			
			@Override
			public void changed(ObservableValue<? extends Lieferant> arg0,
					Lieferant arg1, Lieferant arg2) {
				selectedLieferant = lieferantComboBox.getSelectionModel().getSelectedItem();
			}
		});
	}
	
	/**
	 * Sets all input fields.
	 */
	private void setFields() {
		if (selectedSoftware != null) {
			idField.setText(String.valueOf(selectedSoftware.getId()));
			bezeichnungField.setText(selectedSoftware.getBezeichnung());
			lizenzenField.setText(String.valueOf(selectedSoftware.getMaxLizenzen()));
			selectedRechnung = selectedSoftware.getRechnung();
			if(selectedRechnung != null) {
				rechnungComboBox.getSelectionModel().select(selectedRechnung);
			} else {
				rechnungComboBox.getSelectionModel().clearSelection();
			}
			selectedLieferant = selectedSoftware.getLieferant();
			if(selectedLieferant != null) {
				lieferantComboBox.getSelectionModel().select(selectedLieferant);
			} else {
				lieferantComboBox.getSelectionModel().clearSelection();
			}
		}
	}
	
	/**
	 * Clears all input fields.
	 */
	private void clearFields() {
		idField.clear();
		bezeichnungField.clear();
		lizenzenField.clear();
		selectedSoftware = null;
		softwareTable.getSelectionModel().clearSelection();
		selectedRechnung = null;
		rechnungComboBox.clear();
		selectedLieferant = null;
		lieferantComboBox.clear();
	}
	
	/**
	 * Sets the table items.
	 */
	private void setTableItems() {
		if (softwareList != null) {
			data = softwareTable.getItems();
			data.clear();
			for (Software c : softwareList) {
				data.add(c);
			}
			if(selectedSoftware != null) {
				softwareTable.getSelectionModel().select(selectedSoftware);
			}
		}
	}

}
