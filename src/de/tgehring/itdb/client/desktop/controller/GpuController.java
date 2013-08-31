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

import de.tgehring.itdb.client.desktop.model.Gpu;
import de.tgehring.itdb.client.desktop.model.Hersteller;
import de.tgehring.itdb.client.desktop.model.Lieferant;
import de.tgehring.itdb.client.desktop.model.Rechnung;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.desktop.view.SuggestComboBox;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class GpuController represents the JavaFX Controller for gpus.
 */
public class GpuController {
	
	/** The id field. */
	@FXML private TextField idField;
	
	/** The description field. */
	@FXML private TextField bezeichnungField;
	
	/** The interface field. */
	@FXML private TextField schnittstelleField;
	
	/** The memory field. */
	@FXML private TextField speicherField;
	
	/** The gpu table. */
	@FXML private TableView<Gpu> gpuTable;
	
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
	
	/** The selected manufacturer. */
	private Hersteller selectedHersteller;
	
	/** The selected invoice. */
	private Rechnung selectedRechnung;
	
	/** The selected supplier. */
	private Lieferant selectedLieferant;

	/** The selected gpu. */
	private Gpu selectedGpu;
	
	/** The list of all gpus. */
	private List<Gpu> gpuList;
	
	/** The ObservableList of all gpus. */
	private ObservableList<Gpu> data;	
	
	/**
	 * Instantiates a new gpu controller.
	 */
	public GpuController() {
		selectedGpu = new Gpu();
	}
	
	/**
	 * Handles the create button action to create a gpu.
	 *
	 * @param event the event
	 */
	@FXML 
    protected void handleCreateButtonAction(ActionEvent event) {
		if(idField.getText().isEmpty()) {
			if(checkMandatoryFields()) {
				Gpu gpu = new Gpu();
				gpu.setBezeichnung(bezeichnungField.getText());
				gpu.setSchnittstelle(schnittstelleField.getText());
				gpu.setSpeicher(speicherField.getText());
				gpu.setHersteller(herstellerComboBox.getSelected());
				gpu.setLieferant(lieferantComboBox.getSelected());
				gpu.setRechnung(rechnungComboBox.getSelected());
				
				ClientResponse res = CRUDClient.getInstance().addGpu(gpu);
				if(res.getStatus() == 201) {
					updateList();
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Gpu hinzugefügt");
					clearFields();
				}
			}
		}
	}
	
	/**
	 * Update the list of all gpus.
	 */
	private void updateList() {
		if(gpuList != null) {
			data.removeAll(gpuList);
		}
		gpuList = new LinkedList<Gpu>();
		gpuList = CRUDClient.getInstance().getAllGpu();
	}
	
	/**
	 * Checks all mandatory fields.
	 *
	 * @return true, if successful
	 */
	private boolean checkMandatoryFields() {
		if(bezeichnungField.getText().isEmpty()) {
			sendMessage("Bezeichnung");
			return false;
		}
		if(schnittstelleField.getText().isEmpty()) {
			sendMessage("Schnittstelle");
			return false;
		}
		if(speicherField.getText().isEmpty()) {
			sendMessage("Speicher");
			return false;
		}
		if(herstellerComboBox.getSelected() == null) {
			sendMessage("Hersteller");
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
	 * Handles the edit button action to edit a gpu.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Gpu gpu = new Gpu();
				gpu.setId(Integer.parseInt(idField.getText()));
				gpu.setBezeichnung(bezeichnungField.getText());
				gpu.setSchnittstelle(schnittstelleField.getText());
				gpu.setSpeicher(speicherField.getText());
				gpu.setHersteller(herstellerComboBox.getSelected());
				gpu.setLieferant(lieferantComboBox.getSelected());
				gpu.setRechnung(rechnungComboBox.getSelected());
				ClientResponse res = CRUDClient.getInstance().editGpu(gpu);
				if(res.getStatus() == 201) {
					selectedGpu = gpu;
					for(int i=0; i<gpuList.size(); i++) {
						if(gpuList.get(i).getId() == gpu.getId()) {
							gpuList.set(i, gpu);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Gpu gespeichert.");
				}
			}
		}
	}
	
	/**
	 * Handles the delete button action to delete a gpu.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			ClientResponse res = CRUDClient.getInstance().deleteGpu(Integer.valueOf(idField.getText()));
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				gpuTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Gpu gelöscht.");
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
		gpuTable.getSelectionModel().clearSelection();
		clearFields();
	}

	/**
	 * Load all gpus from the databse.
	 */
	private void loadGpus() {
		gpuList = CRUDClient.getInstance().getAllGpu();
		data = gpuTable.getItems();
		data.clear();
		data.addAll(gpuList);
		
		List<Hersteller> hlist = CRUDClient.getInstance().getAllGpuHersteller();
		herstellerComboBox.setList(hlist);
		
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
		loadGpus();
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
		gpuTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		gpuTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Gpu>() {

			@Override
			public void changed(ObservableValue<? extends Gpu> arg0,
					Gpu arg1, Gpu arg2) {
				if (gpuList != null) {
					Gpu gpu = gpuTable.getSelectionModel()
							.getSelectedItem();
					if (gpu != null) {
						selectedGpu = new Gpu();
						long id = Long.valueOf(gpu.getId());
						for (Gpu element : gpuList) {
							if (element.getId() == id) {
								selectedGpu = element;
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
		if (selectedGpu != null) {
			idField.setText(String.valueOf(selectedGpu.getId()));
			bezeichnungField.setText(selectedGpu.getBezeichnung());
			schnittstelleField.setText(selectedGpu.getSchnittstelle());
			speicherField.setText(selectedGpu.getSpeicher());
			selectedHersteller = selectedGpu.getHersteller();
			if(selectedHersteller != null) {
				herstellerComboBox.getSelectionModel().select(selectedHersteller);
			} else {
				herstellerComboBox.getSelectionModel().clearSelection();
			}
			selectedRechnung = selectedGpu.getRechnung();
			if(selectedRechnung != null) {
				rechnungComboBox.getSelectionModel().select(selectedRechnung);
			} else {
				rechnungComboBox.getSelectionModel().clearSelection();
			}
			selectedLieferant = selectedGpu.getLieferant();
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
		schnittstelleField.clear();
		speicherField.clear();
		selectedGpu = null;
		gpuTable.getSelectionModel().clearSelection();
		selectedHersteller = null;
		selectedRechnung = null;
		selectedLieferant = null;
		herstellerComboBox.clear();
		rechnungComboBox.clear();
		lieferantComboBox.clear();
	}
	
	/**
	 * Fills the table with all gpus.
	 */
	private void setTableItems() {
		if (gpuList != null) {
			data = gpuTable.getItems();
			data.clear();
			for (Gpu c : gpuList) {
				data.add(c);
			}
			if(selectedGpu != null) {
				gpuTable.getSelectionModel().select(selectedGpu);
			}
		}
	}

}
