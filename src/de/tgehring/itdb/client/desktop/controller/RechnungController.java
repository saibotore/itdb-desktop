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

import de.tgehring.itdb.client.desktop.model.Rechnung;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.desktop.view.DateField;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class RechnungController represents the JavaFX Controller for gpus..
 */
public class RechnungController {
	
	/** The id field. */
	@FXML private TextField idField;
	
	/** The invoice number field. */
	@FXML private TextField rNummerField;
	
	/** The invoice amount field. */
	@FXML private TextField betragField;
	
	/** The invoice date day field. */
	@FXML private DateField rDateDayField;
	
	/** The invoice date month field. */
	@FXML private DateField rDateMonthField;
	
	/** The invoice date year field. */
	@FXML private DateField rDateYearField;
	
	/** The invoice table. */
	@FXML private TableView<Rechnung> rechnungTable;
	
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
	
	/** The list of all invoices. */
	private List<Rechnung> rechnungList;
	
	/** The ObservableList of all invoices. */
	private ObservableList<Rechnung> data;
	
	/**
	 * Instantiates a new invoice controller.
	 */
	public RechnungController() {
		selectedRechnung = new Rechnung();
	}
	

	/**
	 * Handles the create button action to create a invoice.
	 *
	 * @param event the event
	 */
	@FXML 
    protected void handleCreateButtonAction(ActionEvent event) {
		if(idField.getText().isEmpty()) {
			if(checkMandatoryFields()) {
				Rechnung rechnung = new Rechnung();
				rechnung.setRechnungsbetrag(Double.parseDouble(betragField.getText()));
				rechnung.setRechnungsnummer(rNummerField.getText());
				rechnung.setRechnungsdatum(makeDate());
				
				ClientResponse res = CRUDClient.getInstance().addRechnung(rechnung);
				if(res.getStatus() == 201) {
					updateList();
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Rechnung hinzugefügt");
					clearFields();
				}
			}
		}
	}
	
	/**
	 * Updates the list of all invoices.
	 */
	private void updateList() {
		if(rechnungList != null) {
			data.removeAll(rechnungList);
		}
		rechnungList = new LinkedList<Rechnung>();
		rechnungList = CRUDClient.getInstance().getAllRechnung();
	}
	
	/**
	 * Makes a date string at the base of its components.
	 *
	 * @return the string
	 */
	private String makeDate() {
		String date = rDateDayField.getText();
		date += ".";
		date += rDateMonthField.getText();
		date += ".";
		date += rDateYearField.getText();
		return date;
	}
	
	/**
	 * Gets the year of a date string.
	 *
	 * @param date the date
	 * @return the year
	 */
	private String getYear(String date) {
		String[] values = date.split("\\.");
		return values[2];
	}
	
	/**
	 * Gets the month a date string.
	 *
	 * @param date the date
	 * @return the month
	 */
	private String getMonth(String date) {
		String[] values = date.split("\\.");
		return values[1];
	}
	
	/**
	 * Gets the day a date string.
	 *
	 * @param date the date
	 * @return the day
	 */
	private String getDay(String date) {
		String[] values = date.split("\\.");
		return values[0];
	}


	/**
	 * Check mandatory fields.
	 *
	 * @return true, if successful
	 */
	private boolean checkMandatoryFields() {
		if(rNummerField.getText().isEmpty()) {
			sendMessage("Rechnungsnummer");
			return false;
		}
		if(betragField.getText().isEmpty()) {
			sendMessage("Rechnungsbetrag");
			return false;
		}
		if(rDateDayField.getText().isEmpty()) {
			sendMessage("Rechnungsdatum (Tag)");
			return false;
		}
		if(rDateMonthField.getText().isEmpty()) {
			sendMessage("Rechnungsdatum (Monat)");
			return false;
		}
		if(rDateYearField.getText().isEmpty()) {
			sendMessage("Rechnungsdatum (Jahr)");
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
	 * Handles the edit button action to edit the selected invoice.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Rechnung rechnung = new Rechnung();
				rechnung.setId(Integer.parseInt(idField.getText()));
				rechnung.setRechnungsbetrag(Double.parseDouble(betragField.getText()));
				rechnung.setRechnungsnummer(rNummerField.getText());
				rechnung.setRechnungsdatum(makeDate());
				
				ClientResponse res = CRUDClient.getInstance().editRechnung(rechnung);
				if(res.getStatus() == 201) {
					selectedRechnung = rechnung;
					for(int i=0; i<rechnungList.size(); i++) {
						if(rechnungList.get(i).getId() == rechnung.getId()) {
							rechnungList.set(i, rechnung);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Rechnung gespeichert.");
				}
			}
		}
	}
	
	/**
	 * Handles the delete button action to delete the selected invoice.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			ClientResponse res = CRUDClient.getInstance().deleteRechnung(Integer.valueOf(idField.getText()));
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				rechnungTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Rechnung gelöscht.");
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
		rechnungTable.getSelectionModel().clearSelection();
		clearFields();
	}

	/**
	 * Load all invoices from the database.
	 */
	private void loadRechnungen() {
		rechnungList = CRUDClient.getInstance().getAllRechnung();
		data = rechnungTable.getItems();
		data.clear();
		data.addAll(rechnungList);
	}
	
	/**
	 * Is called, when the view has been initialized.
	 */
	public void initialize() {
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		rDateYearField.setMaxlength(4);
		rDateMonthField.setMaxlength(2);
		rDateDayField.setMaxlength(2);
		loadRechnungen();
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
		rechnungTable.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		rechnungTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Rechnung>() {

					@Override
					public void changed(ObservableValue<? extends Rechnung> arg0,
							Rechnung arg1, Rechnung arg2) {
						if (rechnungList != null) {
							Rechnung rechnung = rechnungTable.getSelectionModel()
									.getSelectedItem();
							if (rechnung != null) {
								selectedRechnung = new Rechnung();
								long id = Long.valueOf(rechnung.getId());
								for (Rechnung element : rechnungList) {
									if (element.getId() == id) {
										selectedRechnung = element;
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
		if (selectedRechnung != null) {
			idField.setText(String.valueOf(selectedRechnung.getId()));
			rNummerField.setText(selectedRechnung.getRechnungsnummer());
			betragField.setText(String.valueOf(selectedRechnung.getRechnungsbetrag()));
			String date = selectedRechnung.getRechnungsdatum();
			rDateYearField.setText(getYear(date));
			rDateMonthField.setText(getMonth(date));
			rDateDayField.setText(getDay(date));
		}
	}
	
	/**
	 * Clears all input fields.
	 */
	private void clearFields() {
		idField.clear();
		rNummerField.clear();
		betragField.clear();
		rDateYearField.clear();
		rDateMonthField.clear();
		rDateDayField.clear();
	}
	
	/**
	 * Sets the table items.
	 */
	private void setTableItems() {
		if (rechnungList != null) {
			data = rechnungTable.getItems();
			data.clear();
			for (Rechnung c : rechnungList) {
				data.add(c);
			}
			if(selectedRechnung != null) {
//				rechnungTable.getSelectionModel().select(selectedRechnung);
			}
		}
	}

}
