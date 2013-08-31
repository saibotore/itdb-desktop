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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import com.sun.jersey.api.client.ClientResponse;

import de.tgehring.itdb.client.desktop.model.Hersteller;
import de.tgehring.itdb.client.desktop.model.Lieferant;
import de.tgehring.itdb.client.desktop.model.Monitor;
import de.tgehring.itdb.client.desktop.model.Rechnung;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.desktop.view.SuggestComboBox;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class MonitorController represents the JavaFX Controller for monitors.
 */
public class MonitorController {
	
	/** The id field. */
	@FXML private TextField idField;
	
	/** The description field. */
	@FXML private TextField bezeichnungField;
	
	/** The device number field. */
	@FXML private TextField gNummerField;
	
	/** The inventory number field. */
	@FXML private TextField iNummerField;
	
	/** The inventory number check box. */
	@FXML private CheckBox iNummerCheckBox;
	
	/** The inch field. */
	@FXML private TextField zollField;
	
	/** The touch check box. */
	@FXML private CheckBox touchCheckBox;
	
	/** The manufacturer combo box. */
	@FXML private SuggestComboBox<Hersteller> herstellerComboBox;
	
	/** The invoice combo box. */
	@FXML private SuggestComboBox<Rechnung> rechnungComboBox;
	
	/** The supplier combo box. */
	@FXML private SuggestComboBox<Lieferant> lieferantComboBox;
	
	/** The selected manufacturer. */
	private Hersteller selectedHersteller;
	
	/** The selected invoice. */
	private Rechnung selectedRechnung;
	
	/** The selected supplier. */
	private Lieferant selectedLieferant;
	
	/** The create button. */
	@FXML private Button createButton;
	
	/** The edit button. */
	@FXML private Button editButton;
	
	/** The delete button. */
	@FXML private Button deleteButton;
	
	/** The reset button. */
	@FXML private Button resetButton;
	
	/** The monitor table. */
	@FXML private TableView<Monitor> monitorTable;

	/** The selected monitor. */
	private Monitor selectedMonitor;
	
	/** The list of all monitors. */
	private List<Monitor> monitorList;
	
	/** The ObservableList of all monitors. */
	private ObservableList<Monitor> data;
	
	/**
	 * Instantiates a new monitor controller.
	 */
	public MonitorController() {
		selectedMonitor = new Monitor();
	}
	
	/**
	 * Handles the create button action to create a monitor.
	 *
	 * @param event the event
	 */
	@FXML 
    protected void handleCreateButtonAction(ActionEvent event) {
		if (idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Monitor monitor = new Monitor();
				monitor.setBezeichnung(bezeichnungField.getText());
				monitor.setGerätenummer(gNummerField.getText());
				monitor.setInventarnummer(iNummerField.getText());
				monitor.setZoll(Integer.parseInt(zollField.getText()));
				monitor.setTouch(touchCheckBox.isSelected());
				selectedHersteller = herstellerComboBox.getSelected();
				selectedLieferant = lieferantComboBox.getSelected();
				selectedRechnung = rechnungComboBox.getSelected();
				monitor.setHersteller(selectedHersteller);
				monitor.setRechnung(selectedRechnung);
				monitor.setLieferant(selectedLieferant);
				
				ClientResponse res = CRUDClient.getInstance().addMonitor(monitor);
				if(res.getStatus() == 201) {
					updateList();
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Monitor hinzugefügt");
					clearFields();
				}
			}
		}
	}
	
	/**
	 * Updates the list of all monitors.
	 */
	private void updateList() {
		if(monitorList != null) {
			data.removeAll(monitorList);
		}
		monitorList = new LinkedList<Monitor>();
		monitorList = CRUDClient.getInstance().getAllMonitor();
	}

	/**
	 * Handles the edit button action to edit the selected monitor.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Monitor monitor = new Monitor();
				monitor.setBezeichnung(bezeichnungField.getText());
				monitor.setGerätenummer(gNummerField.getText());
				monitor.setInventarnummer(iNummerField.getText());
				monitor.setZoll(Integer.parseInt(zollField.getText()));
				monitor.setTouch(touchCheckBox.isSelected());
				selectedHersteller = herstellerComboBox.getSelected();
				selectedLieferant = lieferantComboBox.getSelected();
				selectedRechnung = rechnungComboBox.getSelected();
				monitor.setHersteller(selectedHersteller);
				monitor.setRechnung(selectedRechnung);
				monitor.setLieferant(selectedLieferant);
				
				ClientResponse res = CRUDClient.getInstance().editMonitor(monitor);
				if(res.getStatus() == 201) {
					selectedMonitor = monitor;
					for(int i=0; i<monitorList.size(); i++) {
						if(monitorList.get(i).getId() == monitor.getId()) {
							monitorList.set(i, monitor);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Monitor gespeichert.");
				}
			}
		}
	}
	
	/**
	 * Handles the delete button action to delete the selected monitor.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			int id = Integer.parseInt(idField.getText());
			ClientResponse res = CRUDClient.getInstance().deleteMonitor(id);
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				monitorTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Monitor gelöscht.");
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
		monitorTable.getSelectionModel().clearSelection();
		clearFields();
	}

	/**
	 * Checks mandatory fields.
	 *
	 * @return true, if successful
	 */
	private boolean checkMandatoryFields() {
		if(bezeichnungField.getText().isEmpty()) {
			sendMessage("Bezeichnung");
			return false;
		}
		if(gNummerField.getText().isEmpty()) {
			sendMessage("Gerätenummer");
			return false;
		}
		if(iNummerField.getText().isEmpty()) {
			sendMessage("Inventarnummer");
			return false;
		}
		if(zollField.getText().isEmpty()) {
			sendMessage("Zoll");
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
	 * Loads all monitors from the database.
	 */
	private void loadMonitor() {
		monitorList = CRUDClient.getInstance().getAllMonitor();
		data = monitorTable.getItems();
		data.clear();
		data.addAll(monitorList);
		
		List<Hersteller> hlist = CRUDClient.getInstance().getAllHersteller();
		herstellerComboBox.setList(hlist);
		
		List<Rechnung> rlist = CRUDClient.getInstance().getAllRechnung();
		rechnungComboBox.setList(rlist);
		
		List<Lieferant> llist = CRUDClient.getInstance().getAllLieferant();
		lieferantComboBox.setList(llist);
	}
	
	/**
	 * Is called, when the view has bee initialized.
	 */
	public void initialize() {
		iNummerField.setDisable(true);
		long iNummer = Integer.parseInt(CRUDClient.getInstance().getLastInventarnummer().toString());
		iNummer ++;
		iNummerField.setText(String.valueOf(iNummer));
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		loadMonitor();
		initHandler();
		setTableItems();
	}

	/**
	 * Initialized all listeners.
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
					iNummerField.setDisable(true);
					long iNummer = Integer.parseInt(CRUDClient.getInstance().getLastInventarnummer().toString());
					iNummer ++;
					iNummerField.setText(String.valueOf(iNummer));
					createButton.setDisable(false);
					editButton.setDisable(true);
					deleteButton.setDisable(true);
				}
			}
		});
		iNummerCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				if(iNummerCheckBox.isSelected()) {
					iNummerField.setDisable(false);
				} else {
					iNummerField.setDisable(true);
				}
			}
			
		});
		monitorTable.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		monitorTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Monitor>() {

					@Override
					public void changed(ObservableValue<? extends Monitor> arg0,
							Monitor arg1, Monitor arg2) {
						if (monitorList != null) {
							Monitor cpu = monitorTable.getSelectionModel()
									.getSelectedItem();
							if (cpu != null) {
								selectedMonitor = new Monitor();
								long id = Long.valueOf(cpu.getId());
								for (Monitor element : monitorList) {
									if (element.getId() == id) {
										selectedMonitor = element;
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
		if (selectedMonitor != null) {
			idField.setText(String.valueOf(selectedMonitor.getId()));
			bezeichnungField.setText(selectedMonitor.getBezeichnung());
			iNummerField.setText(selectedMonitor.getInventarnummer());
			gNummerField.setText(selectedMonitor.getGerätenummer());
			zollField.setText(String.valueOf(selectedMonitor.getZoll()));
			selectedHersteller = selectedMonitor.getHersteller();
			if(selectedHersteller != null) {
				herstellerComboBox.getSelectionModel().select(selectedHersteller);
			} else {
				herstellerComboBox.getSelectionModel().clearSelection();
			}
			selectedRechnung = selectedMonitor.getRechnung();
			if(selectedRechnung != null) {
				rechnungComboBox.getSelectionModel().select(selectedRechnung);
			} else {
				rechnungComboBox.getSelectionModel().clearSelection();
			}
			selectedLieferant = selectedMonitor.getLieferant();
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
		gNummerField.clear();
		iNummerField.clear();
		zollField.clear();
		touchCheckBox.setSelected(false);
		monitorTable.getSelectionModel().clearSelection();
		selectedMonitor = null;
		herstellerComboBox.clear();
		selectedHersteller = null;
		lieferantComboBox.clear();
		selectedLieferant = null;
		rechnungComboBox.clear();
		selectedRechnung = null;
	}
	
	/**
	 * Sets the table items.
	 */
	private void setTableItems() {
		if (monitorList != null) {
			data = monitorTable.getItems();
			data.clear();
			for (Monitor c : monitorList) {
				data.add(c);
			}
			if(selectedMonitor != null) {
				monitorTable.getSelectionModel().select(selectedMonitor);
			}
		}
	}

}
