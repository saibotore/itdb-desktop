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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.sun.jersey.api.client.ClientResponse;

import de.tgehring.itdb.client.desktop.model.Abteilung;
import de.tgehring.itdb.client.desktop.model.Benutzer;
import de.tgehring.itdb.client.desktop.model.Cpu;
import de.tgehring.itdb.client.desktop.model.Gebäude;
import de.tgehring.itdb.client.desktop.model.Gpu;
import de.tgehring.itdb.client.desktop.model.Hersteller;
import de.tgehring.itdb.client.desktop.model.Lieferant;
import de.tgehring.itdb.client.desktop.model.Monitor;
import de.tgehring.itdb.client.desktop.model.Rechner;
import de.tgehring.itdb.client.desktop.model.Rechnung;
import de.tgehring.itdb.client.desktop.model.Software;
import de.tgehring.itdb.client.desktop.model.exception.SoftwareException;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.desktop.utils.WartungThread;
import de.tgehring.itdb.client.desktop.utils.filter.CpuFilter;
import de.tgehring.itdb.client.desktop.utils.filter.GpuFilter;
import de.tgehring.itdb.client.desktop.utils.filter.HddFilter;
import de.tgehring.itdb.client.desktop.utils.filter.InventarnummerFilter;
import de.tgehring.itdb.client.desktop.utils.filter.RamFilter;
import de.tgehring.itdb.client.desktop.utils.filter.RechnerFilter;
import de.tgehring.itdb.client.desktop.view.DateField;
import de.tgehring.itdb.client.desktop.view.IPField;
import de.tgehring.itdb.client.desktop.view.SuggestComboBox;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class RechnerController represents the JavaFX Controller for computers.
 */
public class RechnerController {
	
	/** The id field. */
	@FXML private TextField idField;
	
	/** The cpu combo box. */
	@FXML private SuggestComboBox<Cpu> cpuComboBox;
	
	/** The gpu combo box. */
	@FXML private SuggestComboBox<Gpu> gpuComboBox;
	
	/** The ram field. */
	@FXML private TextField ramField;
	
	/** The first drive field. */
	@FXML private TextField laufwerk1Field;
	
	/** The second drive field. */
	@FXML private TextField laufwerk2Field;
	
	/** The first hdd field. */
	@FXML private TextField hdd1Field;
	
	/** The second hdd field. */
	@FXML private TextField hdd2Field;
	
	/** The third hdd field. */
	@FXML private TextField hdd3Field;
	
	/** The fourth hdd field. */
	@FXML private TextField hdd4Field;
	
	/** The monitor combo box. */
	@FXML private SuggestComboBox<Monitor> monitorComboBox;
	
	/** The inventory number field. */
	@FXML private TextField iNummerField;
	
	/** The inventory number check box. */
	@FXML private CheckBox iNummerCheckBox;
	
	/** The device number field. */
	@FXML private TextField gNummerField;
	
	/** The building combo box. */
	@FXML private SuggestComboBox<Gebäude> gebäudeComboBox;
	
	/** The department combo box. */
	@FXML private SuggestComboBox<Abteilung> abteilungComboBox;
	
	/** The user combo box. */
	@FXML private SuggestComboBox<Benutzer> benutzerComboBox;
	
	/** The computer name field. */
	@FXML private TextField cNameField;
	
	/** The working group field. */
	@FXML private TextField aGruppeField;
	
	/** The domain field. */
	@FXML private TextField domäneField;
	
	/** The warranty day field. */
	@FXML private DateField warrantyDayField;
	
	/** The warranty month field. */
	@FXML private DateField warrantyMonthField;
	
	/** The warranty year field. */
	@FXML private DateField warrantyYearField;
	
	/** The notes field. */
	@FXML private TextArea anmerkungenField;

	/** The first ip field. */
	@FXML private IPField ip1Field;
	
	/** The first subnet field. */
	@FXML private IPField subnet1Field;
	
	/** The gateway field. */
	@FXML private IPField gatewayField;
	
	/** The primary dns field. */
	@FXML private IPField pDnsField;
	
	/** The secondary dns field. */
	@FXML private IPField sDnsField;
	
	/** The second ip field. */
	@FXML private IPField ip2Field;
	
	/** The second ip field. */
	@FXML private IPField subnet2Field;
	
	/** The manufacturer combo box. */
	@FXML private SuggestComboBox<Hersteller> herstellerComboBox;
	
	/** The supplier combo box. */
	@FXML private SuggestComboBox<Lieferant> lieferantComboBox;
	
	/** The invoice combo box. */
	@FXML private SuggestComboBox<Rechnung> rechnungComboBox;
	
	/** The maintenance day field. */
	@FXML private DateField maintenanceDayField;
	
	/** The maintenance month field. */
	@FXML private DateField maintenanceMonthField;
	
	/** The maintenance year field. */
	@FXML private DateField maintenanceYearField;
	
	
	/** The create button. */
	@FXML private Button createButton;
	
	/** The edit button. */
	@FXML private Button editButton;
	
	/** The delete button. */
	@FXML private Button deleteButton;
	
	/** The reset button. */
	@FXML private Button resetButton;
	
	/** The scrapped check box. */
	@FXML private CheckBox ausrangiertCheckBox;
	
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
	
	/** The ObservableList of all users. */
	private ObservableList<Benutzer> benutzerData;
	
	/** The selected user. */
	private Benutzer selectedBenutzer;
	
	/** The ObservableList of all departments. */
	private ObservableList<Abteilung> abteilungData;
	
	/** The selected department. */
	private Abteilung selectedAbteilung;
	
	/** The ObservableList of all buildings. */
	private ObservableList<Gebäude> gebäudeData;
	
	/** The selected building. */
	private Gebäude selectedGebäude;
	
	/** The ObservableList of all cpus. */
	private ObservableList<Cpu> cpuData;
	
	/** The selected cpu. */
	private Cpu selectedCpu;
	
	/** The ObservableList of all gpus. */
	private ObservableList<Gpu> gpuData;
	
	/** The selected gpu. */
	private Gpu selectedGpu;
	
	/** The ObservableList of all monitors. */
	private ObservableList<Monitor> monitorData;
	
	/** The selected monitor. */
	private Monitor selectedMonitor;
	
	/** The software combo box. */
	@FXML private SuggestComboBox<Software> softwareComboBox;
	
	/** The add software button. */
	@FXML private Button addSoftwareButton;
	
	/** The remove software button. */
	@FXML private Button removeSoftwareButton;
	
	/** The software list. */
	@FXML private ListView<Software> softwareList;
	
	/** The ObservableList of all available software. */
	private ObservableList<Software> softwareComboBoxData;
	
	/** The ObservableList of all used software. */
	private ObservableList<Software> softwareListData;
	
	/** The available software combo box. */
	private Software selectedSoftwareComboBox;
	
	/** The used software list. */
	private Software selectedSoftwareList;
	
	/** The computer table. */
	@FXML private TableView<Rechner> rechnerTable;
	
	/** The filter by inventory number field. */
	@FXML private TextField filterNummerField;
	
	/** The filter by ram field. */
	@FXML private TextField filterRamField;
	
	/** The filter by hdd field. */
	@FXML private TextField filterHddField;
	
	/** The filter by cpu field. */
	@FXML private TextField filterCpuField;
	
	/** The filter by gpu field. */
	@FXML private TextField filterGpuField;
	
	/** The filter submit button. */
	@FXML private Button filterSubmitButton;
	
	/** The filter reset button. */
	@FXML private Button filterResetButton;
	
	/** The selected computer. */
	private Rechner selectedRechner;
	
	/** The list of all computers. */
	private List<Rechner> rechnerList;
	
	/** The ObservableList of all computers. */
	private ObservableList<Rechner> data;
	
	/** The cache. */
	private List<Rechner> cache;
	
	/**
	 * Instantiates a new computer controller.
	 */
	public RechnerController() {
		selectedRechner = new Rechner();
	}
	
	/**
	 * Load all computers from the database.
	 */
	private void loadRechner() {
		rechnerList = CRUDClient.getInstance().getAllRechner();
		cache = new LinkedList<Rechner>();
		cache.addAll(rechnerList);
		data = rechnerTable.getItems();
		data.clear();
		data.addAll(rechnerList);
		
		List<Hersteller> hlist = CRUDClient.getInstance().getAllRechnerHersteller();
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
		
		List<Benutzer> blist = CRUDClient.getInstance().getAllBenutzer();
		benutzerComboBox.setList(blist);
		benutzerData = benutzerComboBox.getItems();
		benutzerData.clear();
		benutzerData.addAll(blist);
		
		List<Abteilung> alist = CRUDClient.getInstance().getAllAbteilung();
		abteilungComboBox.setList(alist);
		abteilungData = abteilungComboBox.getItems();
		abteilungData.clear();
		abteilungData.addAll(alist);
		
		List<Gebäude> glist = CRUDClient.getInstance().getAllGebäude();
		gebäudeComboBox.setList(glist);
		gebäudeData = gebäudeComboBox.getItems();
		gebäudeData.clear();
		gebäudeData.addAll(glist);
		
		List<Cpu> clist = CRUDClient.getInstance().getAllCpu();
		cpuComboBox.setList(clist);
		cpuData = cpuComboBox.getItems();
		cpuData.clear();
		cpuData.addAll(clist);
		
		List<Gpu> gplist = CRUDClient.getInstance().getAllGpu();
		gpuComboBox.setList(gplist);
		gpuData = gpuComboBox.getItems();
		gpuData.clear();
		gpuData.addAll(gplist);
		
		List<Monitor> mlist = CRUDClient.getInstance().getAllMonitor();
		monitorComboBox.setList(mlist);
		monitorData = monitorComboBox.getItems();
		monitorData.clear();
		monitorData.addAll(mlist);
		
		List<Software> slist = CRUDClient.getInstance().getAllSoftware();
		softwareComboBox.setList(slist);
		softwareComboBoxData = softwareComboBox.getItems();
		softwareComboBoxData.clear();
		softwareComboBoxData.addAll(slist);
	}

	/**
	 * Checks all mandatory fields.
	 *
	 * @return true, if successful
	 */
	private boolean checkMandatoryFields() {
		if(iNummerField.getText().isEmpty()) {
			sendMessage("Inventarnummer");
			return false;
		}
		if(gNummerField.getText().isEmpty()) {
			sendMessage("Gerätenummer");
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
	 * Handles the create button action to create a computer.
	 *
	 * @param event the event
	 */
	@FXML 
    protected void handleCreateButtonAction(ActionEvent event) {
		if(idField.getText().isEmpty()) {
			if(checkMandatoryFields()) {
				Rechner rechner = new Rechner();
				selectedMonitor = monitorComboBox.getSelected();
				selectedGebäude = gebäudeComboBox.getSelected();
				selectedAbteilung = abteilungComboBox.getSelected();
				selectedBenutzer = benutzerComboBox.getSelected();
				selectedHersteller = herstellerComboBox.getSelected();
				selectedLieferant = lieferantComboBox.getSelected();
				selectedRechnung = rechnungComboBox.getSelected();
				selectedCpu = cpuComboBox.getSelected();
				selectedGpu = gpuComboBox.getSelected();
				
				rechner.setCpu(selectedCpu);
				rechner.setGpu(selectedGpu);
				rechner.setRam(ramField.getText());
				rechner.setLaufwerk1(laufwerk1Field.getText());
				rechner.setLaufwerk2(laufwerk2Field.getText());
				rechner.setHdd1(hdd1Field.getText());
				rechner.setHdd2(hdd2Field.getText());
				rechner.setHdd3(hdd3Field.getText());
				rechner.setHdd4(hdd4Field.getText());
				rechner.setMonitor(selectedMonitor);
				
				rechner.setInventarnummer(iNummerField.getText());
				rechner.setGerätenummer(gNummerField.getText());
				rechner.setGebäude(selectedGebäude);
				rechner.setAbteilung(selectedAbteilung);
				rechner.setBenutzer(selectedBenutzer);
				rechner.setComputername(cNameField.getText());
				rechner.setArbeitsgruppe(aGruppeField.getText());
				rechner.setDomäne(domäneField.getText());
				rechner.setGarantie(makeWarranty());
				rechner.setAnmerkungen(anmerkungenField.getText());
				
				rechner.setIp1(ip1Field.getText());
				rechner.setSubnet1(subnet1Field.getText());
				rechner.setGateway(gatewayField.getText());
				rechner.setDnsPrimary(pDnsField.getText());
				rechner.setDnsSecondary(sDnsField.getText());
				rechner.setIp2(ip2Field.getText());
				rechner.setSubnet2(subnet2Field.getText());
				rechner.setHersteller(selectedHersteller);
				rechner.setRechnung(selectedRechnung);
				rechner.setLieferant(selectedLieferant);

				rechner.setAusrangiert(ausrangiertCheckBox.isSelected());
				rechner.setSoftware(softwareListData);
				
				rechner.setInitWartung(false);
				rechner.setWartung(makeMaintenance());
				
				ClientResponse res = CRUDClient.getInstance().editRechner(rechner);
				if(res.getStatus() == 201) {
					updateList();
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Rechner hinzugefügt");
					clearFields();
				}
			}
		}
	}
	
	/**
	 * Updates the list of all computers.
	 */
	private void updateList() {
		if(rechnerList != null) {
			data.removeAll(rechnerList);
		}
		rechnerList = new LinkedList<Rechner>();
		rechnerList = CRUDClient.getInstance().getAllRechner();
	}
	
	/**
	 * Make the components of the warranty to a date string.
	 *
	 * @return the string
	 */
	private String makeWarranty() {
		String date = warrantyDayField.getText();
		date += ".";
		date += warrantyMonthField.getText();
		date += ".";
		date += warrantyYearField.getText();
		if(date.length() < 8) {
			return "";
		}
		return date;
	}
	
	/**
	 * Make the components of the maintenance to a date string.
	 *
	 * @return the string
	 */
	private String makeMaintenance() {
		String date = maintenanceDayField.getText();
		date += ".";
		date += maintenanceMonthField.getText();
		date += ".";
		date += maintenanceYearField.getText();
		if(date.length() < 8) {
			return "";
		}
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
	 * Gets the month of a date string.
	 *
	 * @param date the date
	 * @return the month
	 */
	private String getMonth(String date) {
		String[] values = date.split("\\.");
		return values[1];
	}
	
	/**
	 * Gets the day of a date string.
	 *
	 * @param date the date
	 * @return the day
	 */
	private String getDay(String date) {
		String[] values = date.split("\\.");
		return values[0];
	}

	/**
	 * Sets the table items.
	 */
	private void setTableItems() {
		if (rechnerList != null) {
			data = rechnerTable.getItems();
			data.clear();
			for (Rechner r: rechnerList) {
				data.add(r);
			}
			if(selectedRechner != null) {
				rechnerTable.getSelectionModel().select(selectedRechner);
			}
		}
	}

	/**
	 * Handles the edit button action to edit the selected computer.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Rechner rechner = new Rechner();
				selectedMonitor = monitorComboBox.getSelected();
				selectedGebäude = gebäudeComboBox.getSelected();
				selectedAbteilung = abteilungComboBox.getSelected();
				selectedBenutzer = benutzerComboBox.getSelected();
				selectedHersteller = herstellerComboBox.getSelected();
				selectedLieferant = lieferantComboBox.getSelected();
				selectedRechnung = rechnungComboBox.getSelected();
				selectedCpu = cpuComboBox.getSelected();
				selectedGpu = gpuComboBox.getSelected();
				
				rechner.setId(Integer.parseInt(idField.getText()));
				rechner.setCpu(selectedCpu);
				rechner.setGpu(selectedGpu);
				rechner.setRam(ramField.getText());
				rechner.setLaufwerk1(laufwerk1Field.getText());
				rechner.setLaufwerk2(laufwerk2Field.getText());
				rechner.setHdd1(hdd1Field.getText());
				rechner.setHdd2(hdd2Field.getText());
				rechner.setHdd3(hdd3Field.getText());
				rechner.setHdd4(hdd4Field.getText());
				rechner.setMonitor(selectedMonitor);
				
				rechner.setInventarnummer(iNummerField.getText());
				rechner.setGerätenummer(gNummerField.getText());
				rechner.setGebäude(selectedGebäude);
				rechner.setAbteilung(selectedAbteilung);
				rechner.setBenutzer(selectedBenutzer);
				rechner.setComputername(cNameField.getText());
				rechner.setArbeitsgruppe(aGruppeField.getText());
				rechner.setDomäne(domäneField.getText());
				rechner.setGarantie(makeWarranty());
				rechner.setAnmerkungen(anmerkungenField.getText());
				
				rechner.setIp1(ip1Field.getText());
				rechner.setSubnet1(subnet1Field.getText());
				rechner.setGateway(gatewayField.getText());
				rechner.setDnsPrimary(pDnsField.getText());
				rechner.setDnsSecondary(sDnsField.getText());
				rechner.setIp2(ip2Field.getText());
				rechner.setSubnet2(subnet2Field.getText());
				rechner.setHersteller(selectedHersteller);
				rechner.setRechnung(selectedRechnung);
				rechner.setLieferant(selectedLieferant);

				rechner.setAusrangiert(ausrangiertCheckBox.isSelected());
				
				rechner.setSoftware(softwareListData);
				
				ClientResponse res = CRUDClient.getInstance().editRechner(rechner);
				if(res.getStatus() == 201) {
					selectedRechner = rechner;
					for(int i=0; i<rechnerList.size(); i++) {
						if(rechnerList.get(i).getId() == rechner.getId()) {
							rechnerList.set(i, rechner);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Rechner gespeichert.");
				}
			}
		}
	}
	
	/**
	 * Handles the delete button action to delete the selected computer.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			ClientResponse res = CRUDClient.getInstance().deleteRechner(Integer.valueOf(idField.getText()));
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				rechnerTable.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Hersteller gelöscht.");
			}
		}
	}
	
	/**
	 * Handle reset button action to reset the view.
	 *
	 * @param event the event
	 */
	@FXML 
	protected void handleResetButtonAction(ActionEvent event) {
		rechnerTable.getSelectionModel().clearSelection();
		clearFields();
	}
	
	/**
	 * Clears all input fields.
	 */
	private void clearFields() {
		idField.clear();
		selectedCpu = null;
		cpuComboBox.clear();
		selectedGpu = null;
		gpuComboBox.clear();
		ramField.clear();
		laufwerk1Field.clear();
		laufwerk2Field.clear();
		hdd1Field.clear();
		hdd2Field.clear();
		hdd3Field.clear();
		hdd4Field.clear();
		selectedMonitor = null;
		monitorComboBox.clear();
		
		
		iNummerField.clear();
		gNummerField.clear();
		selectedGebäude = null;
		gebäudeComboBox.clear();
		selectedAbteilung = null;
		abteilungComboBox.clear();
		selectedBenutzer = null;
		benutzerComboBox.clear();
		cNameField.clear();
		aGruppeField.clear();
		domäneField.clear();
		warrantyDayField.clear();
		warrantyMonthField.clear();
		warrantyYearField.clear();
		maintenanceDayField.clear();
		maintenanceMonthField.clear();
		maintenanceYearField.clear();
		anmerkungenField.clear();
		
		
		ip1Field.clear();
		subnet1Field.clear();
		gatewayField.clear();
		pDnsField.clear();
		sDnsField.clear();
		ip2Field.clear();
		subnet2Field.clear();
		selectedHersteller = null;
		herstellerComboBox.clear();
		selectedRechnung = null;
		rechnungComboBox.clear();
		selectedLieferant = null;
		lieferantComboBox.clear();
		softwareComboBox.setValue(null);
		selectedSoftwareComboBox = null;
		softwareComboBox.clear();
		softwareListData = null;
		softwareList.getSelectionModel().clearSelection();
		ausrangiertCheckBox.setSelected(selectedRechner.isAusrangiert());
	}

	/**
	 * Handles the add software button action to add a software to the selected computer.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleAddSoftwareAction(ActionEvent event) {
		if(selectedSoftwareComboBox != null) {
			softwareListData = softwareList.getItems();
			if(!softwareListData.contains(selectedSoftwareComboBox)) {
				softwareListData.add(selectedSoftwareComboBox);
				try {
					selectedSoftwareComboBox.useLicence();
				} catch (SoftwareException e) {
					Message message = Message.getInstance();
					message.setType(MessageType.General);
					message.setMessage(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Handles the remove software button action to remove a software from the selected computer.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleRemoveSoftwareAction(ActionEvent event) {
		if(selectedSoftwareList != null) {
			softwareListData = softwareList.getItems();
			if (softwareListData.contains(selectedSoftwareList)) {
				softwareListData.remove(selectedSoftwareList);
				try {
					selectedSoftwareList.unuseLicence();
				} catch (SoftwareException e) {
					Message message = Message.getInstance();
					message.setType(MessageType.General);
					message.setMessage(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Handle filter submit button action to submit the filters to the list of computers.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleFilterSubmitAction(ActionEvent event) {
		RechnerFilter filter = new RechnerFilter();
		filter.setRechnerList(rechnerList);
		if(filterNummerField.getText().length() > 0) {
			String input = filterNummerField.getText();
			InventarnummerFilter iFilter = new InventarnummerFilter(input);
			filter.addFilter(iFilter);
		}
		if(filterRamField.getText().length() > 0) {
			String input = filterRamField.getText();
			RamFilter rFilter = new RamFilter(input);
			filter.addFilter(rFilter);
		}
		if(filterHddField.getText().length() > 0) {
			String input = filterHddField.getText();
			HddFilter hFilter = new HddFilter(input);
			filter.addFilter(hFilter);
		}
		if(filterCpuField.getText().length() > 0) {
			String input = filterCpuField.getText();
			CpuFilter cFilter = new CpuFilter(input);
			filter.addFilter(cFilter);
		}
		if(filterGpuField.getText().length() > 0) {
			String input = filterGpuField.getText();
			GpuFilter gFilter = new GpuFilter(input);
			filter.addFilter(gFilter);
		}
		if(rechnerList != null) {
			data.removeAll(rechnerList);
		}
		rechnerList = filter.applyFilter();
		data.addAll(rechnerList);
	}
	
	/**
	 * Handle filter reset button action to reset the filter view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleFilterResetAction(ActionEvent event) {
		rechnerList.clear();
		rechnerList.addAll(cache);
	}
	
	/**
	 * Is called, when the view has been initialized.
	 */
	public void initialize() {
		iNummerField.setDisable(true);
		long iNummer = Integer.parseInt(CRUDClient.getInstance().getLastInventarnummer().toString());
		iNummer ++;
		iNummerField.setText(String.valueOf(iNummer));
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		loadRechner();
		initHandler();
		setTableItems();
		warrantyDayField.setMaxlength(2);
		warrantyMonthField.setMaxlength(2);
		warrantyYearField.setMaxlength(4);
		maintenanceDayField.setMaxlength(2);
		maintenanceMonthField.setMaxlength(2);
		maintenanceYearField.setMaxlength(4);
		WartungThread wt = new WartungThread(rechnerList);
		wt.run();
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
		rechnerTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		rechnerTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Rechner>() {

			@Override
			public void changed(ObservableValue<? extends Rechner> arg0,
					Rechner arg1, Rechner arg2) {
				if (rechnerList != null) {
					softwareList.getItems().removeAll(selectedRechner.getSoftware());
					Rechner rechner = rechnerTable.getSelectionModel()
							.getSelectedItem();
					if (rechner != null) {
						selectedRechner = new Rechner();
						long id = Long.valueOf(rechner.getId());
						for (Rechner element : rechnerList) {
							if (element.getId() == id) {
								selectedRechner = element;
								clearFields();
								setFields();
							}
						}
					}
				}
			}
		});
		softwareComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Software>() {

			@Override
			public void changed(ObservableValue<? extends Software> arg0,
					Software arg1, Software arg2) {
				selectedSoftwareComboBox = softwareComboBox.getSelectionModel().getSelectedItem();
			}
		});
		softwareList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Software>() {

			@Override
			public void changed(ObservableValue<? extends Software> arg0,
					Software arg1, Software arg2) {
				selectedSoftwareList = softwareList.getSelectionModel().getSelectedItem();
			}
		});
	}
	
	/**
	 * Sets all input fields.
	 */
	private void setFields() {
		if (selectedRechner != null) {
			idField.setText(String.valueOf(selectedRechner.getId()));
			cpuComboBox.getSelectionModel().select(selectedRechner.getCpu());
			gpuComboBox.getSelectionModel().select(selectedRechner.getGpu());
			ramField.setText(selectedRechner.getRam());
			laufwerk1Field.setText(selectedRechner.getLaufwerk1());
			laufwerk2Field.setText(selectedRechner.getLaufwerk2());
			hdd1Field.setText(selectedRechner.getHdd1());
			hdd2Field.setText(selectedRechner.getHdd2());
			hdd3Field.setText(selectedRechner.getHdd3());
			hdd4Field.setText(selectedRechner.getHdd4());
			monitorComboBox.getSelectionModel().select(selectedRechner.getMonitor());
			
			iNummerField.setText(selectedRechner.getInventarnummer());
			gNummerField.setText(selectedRechner.getGerätenummer());
			gebäudeComboBox.getSelectionModel().select(selectedRechner.getGebäude());
			abteilungComboBox.getSelectionModel().select(selectedRechner.getAbteilung());
			benutzerComboBox.getSelectionModel().select(selectedRechner.getBenutzer());
			cNameField.setText(selectedRechner.getComputername());
			aGruppeField.setText(selectedRechner.getArbeitsgruppe());
			domäneField.setText(selectedRechner.getDomäne());
			String date = selectedRechner.getGarantie();
			if(date.length() > 0) {
				warrantyDayField.setText(getDay(date));
				warrantyMonthField.setText(getMonth(date));
				warrantyYearField.setText(getYear(date));
			}
			anmerkungenField.setText(selectedRechner.getAnmerkungen());
			
			ip1Field.setText(selectedRechner.getIp1());
			subnet1Field.setText(selectedRechner.getSubnet1());
			gatewayField.setText(selectedRechner.getGateway());
			pDnsField.setText(selectedRechner.getDnsPrimary());
			sDnsField.setText(selectedRechner.getDnsSecondary());
			ip2Field.setText(selectedRechner.getIp2());
			subnet2Field.setText(selectedRechner.getSubnet2());
			herstellerComboBox.getSelectionModel().select(selectedRechner.getHersteller());
			rechnungComboBox.getSelectionModel().select(selectedRechner.getRechnung());
			lieferantComboBox.getSelectionModel().select(selectedRechner.getLieferant());
			softwareListData = null;
			softwareList.getSelectionModel().clearSelection();
			ObservableList<Software> sList = softwareList.getItems();
			sList.clear();
			if(selectedRechner.getSoftware() != null) {
				sList.addAll(selectedRechner.getSoftware());
			}
			ausrangiertCheckBox.setSelected(selectedRechner.isAusrangiert());
			String wartung = selectedRechner.getWartung();
			if(wartung.length() > 0) {
				maintenanceDayField.setText(getDay(wartung));
				maintenanceMonthField.setText(getMonth(wartung));
				maintenanceYearField.setText(getYear(wartung));
			}
		}
	}

}
