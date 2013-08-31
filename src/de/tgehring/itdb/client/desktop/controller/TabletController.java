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

import de.tgehring.itdb.client.desktop.model.Benutzer;
import de.tgehring.itdb.client.desktop.model.Hersteller;
import de.tgehring.itdb.client.desktop.model.Lieferant;
import de.tgehring.itdb.client.desktop.model.Rechnung;
import de.tgehring.itdb.client.desktop.model.Tablet;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.desktop.view.IPField;
import de.tgehring.itdb.client.desktop.view.SuggestComboBox;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class TabletController represents the JavaFX Controller for tablets.
 */
public class TabletController {
	
	/** The id field. */
	@FXML private TextField idField;
	
	/** The description field. */
	@FXML private TextField bezeichnungField;
	
	/** The ip field. */
	@FXML private IPField ipField;
	
	/** The subnet field. */
	@FXML private IPField subnetField;
	
	/** The gateway field. */
	@FXML private IPField gatewayField;
	
	/** The primary dns field. */
	@FXML private IPField pDnsField;
	
	/** The secondary dns field. */
	@FXML private IPField sDnsField;
	
	/** The tablet table. */
	@FXML private TableView<Tablet> tabletTable;
	
	/** The user combo box. */
	@FXML private SuggestComboBox<Benutzer> benutzerComboBox;
	
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
	
	/** The ObservableList of all users. */
	private ObservableList<Benutzer> benutzerData;
	
	/** The selected user. */
	private Benutzer selectedBenutzer;
	
	/** The ObservableList of all manufacturers. */
	private ObservableList<Hersteller> herstellerData;
	
	/** The selected manufacturer. */
	private Hersteller selectedHersteller;
	
	/** The ObservableList of all invoices. */
	private ObservableList<Rechnung> rechnungData;
	
	/** The selected invoice. */
	private Rechnung selectedRechnung;
	
	/** The ObservableList of all suppliers. */
	private ObservableList<Lieferant> lieferantData;
	
	/** The selected supplier. */
	private Lieferant selectedLieferant;

	/** The selected tablet. */
	private Tablet selectedTablet;
	
	/** The list of all tablets. */
	private List<Tablet> tabletList;
	
	/** The ObservableList of all tablets. */
	private ObservableList<Tablet> data;
	
	/**
	 * Instantiates a new tablet controller.
	 */
	public TabletController() {
		selectedTablet = new Tablet();
	}
	

	/**
	 * Handles the create button action to create a tablet.
	 *
	 * @param event the event
	 */
	@FXML 
    protected void handleCreateButtonAction(ActionEvent event) {
		if(idField.getText().isEmpty()) {
			if(checkMandatoryFields()) {
				Tablet tablet = new Tablet();
				tablet.setBezeichnung(bezeichnungField.getText());
				tablet.setSubnet(subnetField.getText());
				tablet.setGateway(gatewayField.getText());
				tablet.setDnsPrimary(pDnsField.getText());
				tablet.setDnsSecondary(sDnsField.getText());
				selectedBenutzer = benutzerComboBox.getSelected();
				selectedHersteller = herstellerComboBox.getSelected();
				selectedLieferant = lieferantComboBox.getSelected();
				selectedRechnung = rechnungComboBox.getSelected();
				tablet.setBenutzer(selectedBenutzer);
				tablet.setHersteller(selectedHersteller);
				tablet.setLieferant(selectedLieferant);
				tablet.setRechnung(selectedRechnung);
				
				ClientResponse res = CRUDClient.getInstance().addTablet(tablet);
				if(res.getStatus() == 201) {
					updateList();
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Tablet hinzugefügt");
					clearFields();
				}
			}
		}
	}
	
	/**
	 * Updates the list of all tablets.
	 */
	private void updateList() {
		if(tabletList != null) {
			data.removeAll(tabletList);
		}
		tabletList = new LinkedList<Tablet>();
		tabletList = CRUDClient.getInstance().getAllTablet();
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
	 * Handles the edit button action to edit the selected tablet.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Tablet tablet = new Tablet();
				tablet.setId(Integer.parseInt(idField.getText()));
				tablet.setBezeichnung(bezeichnungField.getText());
				tablet.setSubnet(subnetField.getText());
				tablet.setGateway(gatewayField.getText());
				tablet.setDnsPrimary(pDnsField.getText());
				tablet.setDnsSecondary(sDnsField.getText());
				selectedBenutzer = benutzerComboBox.getSelected();
				selectedHersteller = herstellerComboBox.getSelected();
				selectedLieferant = lieferantComboBox.getSelected();
				selectedRechnung = rechnungComboBox.getSelected();
				tablet.setBenutzer(selectedBenutzer);
				tablet.setHersteller(selectedHersteller);
				tablet.setLieferant(selectedLieferant);
				tablet.setRechnung(selectedRechnung);
				
				ClientResponse res = CRUDClient.getInstance().editTablet(tablet);
				if(res.getStatus() == 201) {
					selectedTablet = tablet;
					for(int i=0; i<tabletList.size(); i++) {
						if(tabletList.get(i).getId() == tablet.getId()) {
							tabletList.set(i, tablet);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Tablet gespeichert.");
				}
			}
		}
	}
	
	/**
	 * Handles the delete button action to delete the selected tablet.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			ClientResponse res = CRUDClient.getInstance().deleteTablet(Integer.valueOf(idField.getText()));
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				tabletTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Tablet gelöscht.");
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
		tabletTable.getSelectionModel().clearSelection();
		clearFields();
	}

	/**
	 * Loads all tablets from the database.
	 */
	private void loadTablets() {
		tabletList = CRUDClient.getInstance().getAllTablet();
		data = tabletTable.getItems();
		data.clear();
		data.addAll(tabletList);
		
		List<Benutzer> blist = CRUDClient.getInstance().getAllBenutzer();
		benutzerComboBox.setList(blist);
		benutzerData = benutzerComboBox.getItems();
		benutzerData.clear();
		benutzerData.addAll(blist);

		List<Hersteller> hlist = CRUDClient.getInstance().getAllTabletHersteller();
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
	 * Is called, when the view has been initialized.
	 */
	public void initialize() {
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		loadTablets();
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
		tabletTable.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		tabletTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Tablet>() {

			@Override
			public void changed(ObservableValue<? extends Tablet> arg0,
					Tablet arg1, Tablet arg2) {
				if (tabletList != null) {
					Tablet tablet = tabletTable.getSelectionModel()
							.getSelectedItem();
					if (tablet != null) {
						selectedTablet = new Tablet();
						long id = Long.valueOf(tablet.getId());
						for (Tablet element : tabletList) {
							if (element.getId() == id) {
								selectedTablet = element;
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
		if (selectedTablet != null) {
			idField.setText(String.valueOf(selectedTablet.getId()));
			bezeichnungField.setText(selectedTablet.getBezeichnung());
			subnetField.setText(selectedTablet.getSubnet());
			gatewayField.setText(selectedTablet.getGateway());
			pDnsField.setText(selectedTablet.getDnsPrimary());
			sDnsField.setText(selectedTablet.getDnsSecondary());
			selectedHersteller = selectedTablet.getHersteller();
			if(selectedHersteller != null) {
				herstellerComboBox.getSelectionModel().select(selectedHersteller);
			} else {
				herstellerComboBox.getSelectionModel().clearSelection();
			}
			selectedRechnung = selectedTablet.getRechnung();
			if(selectedRechnung != null) {
				rechnungComboBox.getSelectionModel().select(selectedRechnung);
			} else {
				rechnungComboBox.getSelectionModel().clearSelection();
			}
			selectedLieferant = selectedTablet.getLieferant();
			if(selectedLieferant != null) {
				lieferantComboBox.getSelectionModel().select(selectedLieferant);
			} else {
				lieferantComboBox.getSelectionModel().clearSelection();
			}
			selectedBenutzer = selectedTablet.getBenutzer();
			if(selectedBenutzer != null) {
				benutzerComboBox.getSelectionModel().select(selectedBenutzer);
			} else {
				benutzerComboBox.getSelectionModel().clearSelection();
			}
		}
	}
	
	/**
	 * Clears all input fields.
	 */
	private void clearFields() {
		idField.clear();
		bezeichnungField.clear();
		ipField.clear();
		subnetField.clear();
		gatewayField.clear();
		pDnsField.clear();
		sDnsField.clear();
		selectedTablet = null;
		tabletTable.getSelectionModel().clearSelection();
		benutzerComboBox.clear();
		herstellerComboBox.clear();
		rechnungComboBox.clear();
		lieferantComboBox.clear();
		selectedBenutzer = null;
		selectedHersteller = null;
		selectedRechnung = null;
		selectedLieferant = null;
	}
	
	/**
	 * Sets the table items.
	 */
	private void setTableItems() {
		if (tabletList != null) {
			data = tabletTable.getItems();
			data.clear();
			for (Tablet c : tabletList) {
				data.add(c);
			}
			if(selectedTablet != null) {
				tabletTable.getSelectionModel().select(selectedTablet);
			}
		}
	}
}
