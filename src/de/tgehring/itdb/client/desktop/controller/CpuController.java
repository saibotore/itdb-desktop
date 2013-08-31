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

import de.tgehring.itdb.client.desktop.model.Cpu;
import de.tgehring.itdb.client.desktop.model.Hersteller;
import de.tgehring.itdb.client.desktop.model.Lieferant;
import de.tgehring.itdb.client.desktop.model.Rechnung;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.desktop.view.SuggestComboBox;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class CpuController represents the JavaFX Controller for cpus.
 */
public class CpuController {
	
	/** The id field. */
	@FXML private TextField idField;
	
	/** The description field. */
	@FXML private TextField bezeichnungField;
	
	/** The socket field. */
	@FXML private TextField sockelField;
	
	/** The clocking field. */
	@FXML private TextField taktungField;
	
	/** The cpu table. */
	@FXML private TableView<Cpu> cpuTable;
	
	/** The manufacturer combo box. */
	@FXML private SuggestComboBox<Hersteller> herstellerComboBox;
	
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
	
	/** The manufacturer data. */
	private ObservableList<Hersteller> herstellerData;
	
	/** The selected manufacturer. */
	private Hersteller selectedHersteller;
	
	/** The ObservableList of all invoices. */
	private ObservableList<Rechnung> rechnungData;
	
	/** The selected invoice. */
	private Rechnung selectedRechnung;
	
	/** The supplier data. */
	private ObservableList<Lieferant> lieferantData;
	
	/** The selected supplier. */
	private Lieferant selectedLieferant;

	/** The selected cpu. */
	private Cpu selectedCpu;
	
	/** The cpu list. */
	private List<Cpu> cpuList;
	
	/** The ObservableList of all cpus. */
	private ObservableList<Cpu> data;
	
	/**
	 * Instantiates a new cpu controller.
	 */
	public CpuController() {
		selectedCpu = new Cpu();
	}
	

	/**
	 * Handles the create button action to create a cpu.
	 *
	 * @param event the event
	 */
	@FXML 
    protected void handleCreateButtonAction(ActionEvent event) {
		if(idField.getText().isEmpty()) {
			if(checkMandatoryFields()) {
				Cpu cpu = new Cpu();
				cpu.setBezeichnung(bezeichnungField.getText());
				cpu.setSockel(sockelField.getText());
				cpu.setTaktung(taktungField.getText());
				selectedHersteller = herstellerComboBox.getSelected();
				selectedLieferant = lieferantComboBox.getSelected();
				selectedRechnung = rechnungComboBox.getSelected();
				cpu.setHersteller(selectedHersteller);
				cpu.setLieferant(selectedLieferant);
				cpu.setRechnung(selectedRechnung);
				ClientResponse res = CRUDClient.getInstance().addCpu(cpu);
				if(res.getStatus() == 201) {
					if(res.getStatus() == 201) {
						updateList();
						setTableItems();
						Message message = Message.getInstance();
						message.setType(MessageType.Database);
						message.setMessage("Cpu hinzugefügt");
						clearFields();
					}
				}
			}
		}
	}
		
	/**
	 * Updates the list of all cpus.
	 */
	private void updateList() {
		if(cpuList != null) {
			data.removeAll(cpuList);
		}
		cpuList = new LinkedList<Cpu>();
		cpuList = CRUDClient.getInstance().getAllCpu();
	}
	
	/**
	 * Checks the mandatory fields.
	 *
	 * @return true, if successful
	 */
	private boolean checkMandatoryFields() {
		if(bezeichnungField.getText().isEmpty()) {
			sendMessage("Bezeichnung");
			return false;
		}
		if(sockelField.getText().isEmpty()) {
			sendMessage("Sockel");
			return false;
		}
		if(taktungField.getText().isEmpty()) {
			sendMessage("Taktung");
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
	 * Handles the edit button action to edit a cpu.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Cpu cpu = new Cpu();
				cpu.setId(Long.parseLong(idField.getText()));
				cpu.setBezeichnung(bezeichnungField.getText());
				cpu.setSockel(sockelField.getText());
				cpu.setTaktung(taktungField.getText());
				selectedHersteller = herstellerComboBox.getSelected();
				selectedLieferant = lieferantComboBox.getSelected();
				selectedRechnung = rechnungComboBox.getSelected();
				cpu.setHersteller(selectedHersteller);
				cpu.setLieferant(selectedLieferant);
				cpu.setRechnung(selectedRechnung);
				ClientResponse res = CRUDClient.getInstance().editCpu(cpu);
				if(res.getStatus() == 201) {
					selectedCpu = cpu;
					for(int i=0; i<cpuList.size(); i++) {
						if(cpuList.get(i).getId() == cpu.getId()) {
							cpuList.set(i, cpu);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Cpu gespeichert.");
				}
			}
		}
	}
	
	/**
	 * Handles the delete button action to delete a cpu.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			ClientResponse res = CRUDClient.getInstance().deleteCpu(Long.parseLong(idField.getText()));
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				cpuTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Cpu gelöscht.");
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
		cpuTable.getSelectionModel().clearSelection();
		clearFields();
	}

	/**
	 * Load all cpus from the database.
	 */
	private void loadCpus() {
		cpuList = CRUDClient.getInstance().getAllCpu();
		data = cpuTable.getItems();
		data.clear();
		data.addAll(cpuList);
		
		List<Hersteller> hlist = CRUDClient.getInstance().getAllCpuHersteller();
		herstellerComboBox.setList(hlist);
		herstellerData = herstellerComboBox.getItems();
		herstellerData.clear();
		herstellerData.addAll(hlist);
		
		List<Rechnung> rlist = CRUDClient.getInstance().getAllRechnung();
		rechnungComboBox.setList(rlist);
		rechnungData = rechnungComboBox.getItems();
		rechnungData.clear();
		rechnungData.addAll(rlist);
		
		List<Lieferant> llist = CRUDClient.getInstance().getAllLieferant();
		lieferantComboBox.setList(llist);
		lieferantData = lieferantComboBox.getItems();
		lieferantData.clear();
		lieferantData.addAll(llist);
	}
	
	/**
	 * Is called, when the view has been initialized
	 */
	public void initialize() {
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		loadCpus();
		initHandler();
		setTableItems();
	}

	/**
	 * Initializes the listener.
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
		cpuTable.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		cpuTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Cpu>() {

					@Override
					public void changed(ObservableValue<? extends Cpu> arg0,
							Cpu arg1, Cpu arg2) {
						if (cpuList != null) {
							Cpu cpu = cpuTable.getSelectionModel()
									.getSelectedItem();
							if (cpu != null) {
								selectedCpu = new Cpu();
								long id = Long.valueOf(cpu.getId());
								for (Cpu element : cpuList) {
									if (element.getId() == id) {
										selectedCpu = element;
										setFields();
									}
								}
							}
						}
					}

				});
	}
	
	/**
	 * Sets the fields with the data of the selected cpu.
	 */
	private void setFields() {
		if (selectedCpu != null) {
			idField.setText(String.valueOf(selectedCpu.getId()));
			bezeichnungField.setText(selectedCpu.getBezeichnung());
			sockelField.setText(selectedCpu.getSockel());
			taktungField.setText(selectedCpu.getTaktung());
			selectedHersteller = selectedCpu.getHersteller();
			if(selectedHersteller != null) {
				herstellerComboBox.getSelectionModel().select(selectedHersteller);
			} else {
				herstellerComboBox.getSelectionModel().clearSelection();
			}
			selectedRechnung = selectedCpu.getRechnung();
			if(selectedRechnung != null) {
				rechnungComboBox.getSelectionModel().select(selectedRechnung);
			} else {
				rechnungComboBox.getSelectionModel().clearSelection();
			}
			selectedLieferant = selectedCpu.getLieferant();
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
		sockelField.clear();
		taktungField.clear();
		cpuTable.getSelectionModel().clearSelection();
		herstellerComboBox.clear();
		rechnungComboBox.clear();
		lieferantComboBox.clear();
		selectedCpu = null;
		selectedHersteller = null;
		selectedRechnung = null;
		selectedLieferant = null;
		herstellerComboBox.clear();
		rechnungComboBox.clear();
		lieferantComboBox.clear();
	}
	
	/**
	 * Fills the table with all departments.
	 */
	private void setTableItems() {
		if (cpuList != null) {
			data = cpuTable.getItems();
			data.clear();
			for (Cpu c : cpuList) {
				data.add(c);
			}
			if(selectedCpu != null) {
				cpuTable.getSelectionModel().select(selectedCpu);
			}
		}
	}
	
}
