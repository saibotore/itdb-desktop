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

import de.tgehring.itdb.client.desktop.model.Drucker;
import de.tgehring.itdb.client.desktop.model.Dvm;
import de.tgehring.itdb.client.desktop.model.Hersteller;
import de.tgehring.itdb.client.desktop.model.Inventarnummer;
import de.tgehring.itdb.client.desktop.model.Lieferant;
import de.tgehring.itdb.client.desktop.model.Rechnung;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.desktop.view.SuggestComboBox;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class DruckerController represents the JavaFX Controller for printers.
 */
public class DruckerController {
	
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
	
	/** The manufacturer combo box. */
	@FXML private SuggestComboBox<Hersteller> herstellerComboBox;
	
	/** The invoice combo box. */
	@FXML private SuggestComboBox<Rechnung> rechnungComboBox;
	
	/** The supplier combo box. */
	@FXML private SuggestComboBox<Lieferant> lieferantComboBox;
	
	/** The material. */
	private Dvm material;
	
	/** The material id field. */
	@FXML private TextField dvmIdField;
	
	/** The black big color field. */
	@FXML private TextField schwarzGrossField;
	
	/** The black color field. */
	@FXML private TextField schwarzField;
	
	/** The colorpack field. */
	@FXML private TextField colorpackField;
	
	/** The cyan color field. */
	@FXML private TextField cyanField;
	
	/** The magenta color field. */
	@FXML private TextField magentaField;
	
	/** The yellow color field. */
	@FXML private TextField yellowField;
	
	/** The toner field. */
	@FXML private TextField tonerField;
	
	/** The head unit field. */
	@FXML private TextField leitereinheitField;
	
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
	
	/** The printer table. */
	@FXML private TableView<Drucker> druckerTable;

	/** The selected printer. */
	private Drucker selectedDrucker;
	
	/** The list of all printers. */
	private List<Drucker> druckerList;
	
	/** The ObservableList of all printers. */
	private ObservableList<Drucker> data;
	
	/**
	 * Instantiates a new printer controller.
	 */
	public DruckerController() {
		selectedDrucker = new Drucker();
	}
	
	/**
	 * Handles the create button action to create a printer.
	 *
	 * @param event the event
	 */
	@FXML 
    protected void handleCreateButtonAction(ActionEvent event) {
		if (idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				createDrucker();
			}
		}
	}
	
	/**
	 * Creates the printer.
	 */
	private void createDrucker() {
		Drucker drucker = new Drucker();
		drucker.setBezeichnung(bezeichnungField.getText());
		drucker.setGerätenummer(gNummerField.getText());
		drucker.setInventarnummer(iNummerField.getText());
		selectedHersteller = herstellerComboBox.getSelected();
		selectedRechnung = rechnungComboBox.getSelected();
		selectedLieferant = lieferantComboBox.getSelected();
		drucker.setHersteller(selectedHersteller);
		drucker.setRechnung(selectedRechnung);
		drucker.setLieferant(selectedLieferant);
		
		drucker.setMaterial(createDvm());
		
		ClientResponse res = CRUDClient.getInstance().addDrucker(drucker);
		if(res.getStatus() == 201) {
			Inventarnummer iNummer = CRUDClient.getInstance().getLastInventarnummer();
			if(iNummer.toString().equals(drucker.getInventarnummer())) {
				CRUDClient.getInstance().addInventarnummer(iNummer);
			}
			updateList();
			setTableItems();
			Message message = Message.getInstance();
			message.setType(MessageType.Database);
			message.setMessage("Drucker hinzugefügt");
			clearFields();
		}
	}

	/**
	 * Creates the printer material.
	 *
	 * @return the dvm
	 */
	private Dvm createDvm() {
		Dvm dvm = new Dvm();
		dvm.setSchwarzgross(schwarzGrossField.getText());
		dvm.setSchwarz(schwarzField.getText());
		dvm.setColorpack(colorpackField.getText());
		dvm.setCyan(cyanField.getText());
		dvm.setMagenta(magentaField.getText());
		dvm.setYellow(yellowField.getText());
		dvm.setTonerbezeichnung(tonerField.getText());
		dvm.setLeitereinheit(leitereinheitField.getText());
		
		ClientResponse dvmRes = CRUDClient.getInstance().addDvm(dvm);
		if(dvmRes.getStatus() == 201) {
			Message message = Message.getInstance();
			message.setType(MessageType.Database);
			message.setMessage("DVM hinzugefügt");
			List<Dvm> list = CRUDClient.getInstance().getAllDvm();
			return list.get(list.size()-1);
		}
		return dvm;
	}

	/**
	 * Update list.
	 */
	private void updateList() {
		if(druckerList != null) {
			data.removeAll(druckerList);
		}
		druckerList = new LinkedList<Drucker>();
		druckerList = CRUDClient.getInstance().getAllDrucker();
	}

	/**
	 * Handles the edit button action to edit the selected printer.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Drucker drucker = new Drucker();
				drucker.setId(Long.parseLong(idField.getText()));
				drucker.setBezeichnung(bezeichnungField.getText());
				drucker.setGerätenummer(gNummerField.getText());
				drucker.setInventarnummer(iNummerField.getText());
				selectedHersteller = herstellerComboBox.getSelected();
				selectedRechnung = rechnungComboBox.getSelected();
				selectedLieferant = lieferantComboBox.getSelected();
				drucker.setHersteller(selectedHersteller);
				drucker.setRechnung(selectedRechnung);
				drucker.setLieferant(selectedLieferant);
				
				Dvm dvm = new Dvm();
				dvm.setId(Long.parseLong(dvmIdField.getText()));
				dvm.setSchwarzgross(schwarzGrossField.getText());
				dvm.setSchwarz(schwarzField.getText());
				dvm.setColorpack(colorpackField.getText());
				dvm.setCyan(cyanField.getText());
				dvm.setMagenta(magentaField.getText());
				dvm.setYellow(yellowField.getText());
				dvm.setTonerbezeichnung(tonerField.getText());
				dvm.setLeitereinheit(leitereinheitField.getText());
				
				drucker.setMaterial(dvm);
				
				ClientResponse dvmRes = CRUDClient.getInstance().editDvm(dvm);
				if(dvmRes.getStatus() == 201) {
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("DVM gespeichert.");
				}

				ClientResponse res = CRUDClient.getInstance().editDrucker(drucker);
				if(res.getStatus() == 201) {
					System.out.println("Drucker bearbeitet.");
					selectedDrucker = drucker;
					for(int i=0; i<druckerList.size(); i++) {
						if(druckerList.get(i).getId() == drucker.getId()) {
							druckerList.set(i, drucker);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Drucker gespeichert.");
				}
			}
		}
	}
	
	/**
	 * Handles the delete button action to delete the selected printer.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleDeleteButtonAction(ActionEvent event) {
		if(!dvmIdField.getText().isEmpty()) {
			long dvmId = Long.parseLong(dvmIdField.getText());
			ClientResponse dvmRes = CRUDClient.getInstance().deleteDvm(dvmId);
			if(dvmRes.getStatus() == 201) {
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("DVM gelöscht.");
			}
		}
		if (!idField.getText().isEmpty()) {
			long id = Long.parseLong(idField.getText());
			ClientResponse res = CRUDClient.getInstance().deleteDrucker(id);
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				druckerTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Drucker gelöscht.");
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
		druckerTable.getSelectionModel().clearSelection();
		clearFields();
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
		if(gNummerField.getText().isEmpty()) {
			sendMessage("Gerätenummer");
			return false;
		}
		if(iNummerField.getText().isEmpty()) {
			sendMessage("Inventarnummer");
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
	 * Load all printers from the database.
	 */
	private void loadDrucker() {
		druckerList = CRUDClient.getInstance().getAllDrucker();
		data = druckerTable.getItems();
		data.clear();
		data.addAll(druckerList);
		
		List<Hersteller> hlist = CRUDClient.getInstance().getAllDruckerHersteller();
		herstellerComboBox.setList(hlist);
		
		List<Rechnung> rlist = CRUDClient.getInstance().getAllRechnung();
		rechnungComboBox.setList(rlist);
		
		List<Lieferant> llist = CRUDClient.getInstance().getAllLieferant();
		lieferantComboBox.setList(llist);
	}
	
	/**
	 * Is called, when the view has been initialized
	 */
	public void initialize() {
		iNummerField.setDisable(true);
		long iNummer = Integer.parseInt(CRUDClient.getInstance().getLastInventarnummer().toString());
		iNummer ++;
		iNummerField.setText(String.valueOf(iNummer));
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		loadDrucker();
		initHandler();
		setTableItems();
	}

	/**
	 * Initialize the listeners.
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
		druckerTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		druckerTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Drucker>() {

			@Override
			public void changed(ObservableValue<? extends Drucker> arg0,
					Drucker arg1, Drucker arg2) {
				if (druckerList != null) {
					Drucker drucker = druckerTable.getSelectionModel()
							.getSelectedItem();
					if (drucker != null) {
						selectedDrucker = new Drucker();
						long id = Long.valueOf(drucker.getId());
						for (Drucker element : druckerList) {
							if (element.getId() == id) {
								selectedDrucker = element;
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
		if (selectedDrucker != null) {
			idField.setText(String.valueOf(selectedDrucker.getId()));
			gNummerField.setText(selectedDrucker.getGerätenummer());
			iNummerField.setText(selectedDrucker.getInventarnummer());
			bezeichnungField.setText(selectedDrucker.getBezeichnung());
			selectedHersteller = selectedDrucker.getHersteller();
			if(selectedHersteller != null) {
				herstellerComboBox.getSelectionModel().select(selectedHersteller);
			} else {
				herstellerComboBox.getSelectionModel().clearSelection();
			}
			selectedRechnung = selectedDrucker.getRechnung();
			if(selectedRechnung != null) {
				rechnungComboBox.getSelectionModel().select(selectedRechnung);
			} else {
				rechnungComboBox.getSelectionModel().clearSelection();
			}
			selectedLieferant = selectedDrucker.getLieferant();
			if(selectedLieferant != null) {
				lieferantComboBox.getSelectionModel().select(selectedLieferant);
			} else {
				lieferantComboBox.getSelectionModel().clearSelection();
			}
			material = selectedDrucker.getMaterial();
			dvmIdField.setText(String.valueOf(material.getId()));
			schwarzGrossField.setText(material.getSchwarzgross());
			schwarzField.setText(material.getSchwarz());
			colorpackField.setText(material.getColorpack());
			cyanField.setText(material.getCyan());
			magentaField.setText(material.getMagenta());
			yellowField.setText(material.getYellow());
			leitereinheitField.setText(material.getLeitereinheit());
			tonerField.setText(material.getTonerbezeichnung());
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
		druckerTable.getSelectionModel().clearSelection();
		selectedDrucker = null;
		herstellerComboBox.clear();
		lieferantComboBox.clear();
		rechnungComboBox.clear();
		selectedLieferant = null;
		selectedHersteller = null;
		selectedRechnung = null;
		
		dvmIdField.clear();
		schwarzField.clear();
		schwarzGrossField.clear();
		cyanField.clear();
		magentaField.clear();
		yellowField.clear();
		colorpackField.clear();
		leitereinheitField.clear();
		tonerField.clear();
	}
	
	/**
	 * Fills the table with all departments.
	 */
	private void setTableItems() {
		if (druckerList != null) {
			data = druckerTable.getItems();
			data.clear();
			for (Drucker c : druckerList) {
				data.add(c);
			}
		}
	}

}
