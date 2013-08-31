/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.desktop.view.FXMLLoadTask;
import de.tgehring.itdb.client.desktop.view.ITDBHeader;
import de.tgehring.itdb.client.desktop.view.utils.ViewSettings;
import de.tgehring.itdb.client.service.PrimaryStage;

/**
 * The Class MainController represents the JavaFX Controller for the main view.
 */
public class MainController {

	/** The loading pop. */
	private static Popup pop;

	/**
	 * Start department view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startAbteilungView(MouseEvent event) {
		startView("abteilung");
	}

	/**
	 * Start user view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startBenutzerView(MouseEvent event) {
		startView("user");
	}

	/**
	 * Start cpu view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startCpuView(MouseEvent event) {
		startView("cpu");
	}

	/**
	 * Start building view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startGebäudeView(MouseEvent event) {
		startView("gebäude");
	}

	/**
	 * Start manufacturer view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startHerstellerView(MouseEvent event) {
		startView("hersteller");
	}

	/**
	 * Start printer view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startDruckerView(MouseEvent event) {
		startView("drucker");
	}

	/**
	 * Start gpu view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startGpuView(MouseEvent event) {
		startView("gpu");
	}

	/**
	 * Start monitor view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startMonitorView(MouseEvent event) {
		startView("monitor");
	}

	/**
	 * Start computer view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startRechnerView(MouseEvent event) {
		startView("rechner");
	}

	/**
	 * Start invoice view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startRechnungView(MouseEvent event) {
		startView("rechnung");
	}

	/**
	 * Start software view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startSoftwareView(MouseEvent event) {
		startView("software");
	}

	/**
	 * Start tablet view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startTabletView(MouseEvent event) {
		startView("tablet");
	}

	/**
	 * Start todo view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startTodoView(MouseEvent event) {
		startView("todo");
	}

	/**
	 * Start supplier view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void startLieferantView(MouseEvent event) {
		startView("lieferant");
	}

	/**
	 * Start the referenced view.
	 *
	 * @param view the view
	 */
	private void startView(String view) {
		String path = "../view/fxml/" + view + ".fxml";
		pop = new Popup();
		final ProgressIndicator indicator = new ProgressIndicator();
		pop.getContent().add(indicator);
		pop.show(PrimaryStage.getInstance().getStage());
		pop.centerOnScreen();
		final Task<Parent> loadTask = new FXMLLoadTask(path);
		loadTask.stateProperty().addListener(
				new ChangeListener<Worker.State>() {
					@Override
					public void changed(
							ObservableValue<? extends Worker.State> stateProp,
							Worker.State oldState, Worker.State newState) {
						switch (newState) {
						case SCHEDULED:
							indicator.setProgress(-1);
							break;
						case SUCCEEDED:
							setView(loadTask.getValue());
							break;
						default:
							break;
						}
					}
				});
		new Thread(loadTask).start();
	}
	
	/**
	 * Sets the view.
	 *
	 * @param parent the new view
	 */
	private void setView(Parent parent) {
		if(parent != null) {
			Stage stage = PrimaryStage.getInstance().getStage();
			stage.setScene(new Scene(parent, ViewSettings.getWidth(),
					ViewSettings.getHeight()));
			BorderPane pane = (BorderPane) parent;
			pane.setTop(new ITDBHeader());
			MainController.pop.hide();
		} else {
			Message m = Message.getInstance();
			m.setType(MessageType.General);
			m.setMessage("Ansicht konnte nicht geladen werden.");
		}
	}

}
