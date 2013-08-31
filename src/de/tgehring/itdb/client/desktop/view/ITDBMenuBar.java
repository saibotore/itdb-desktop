/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import de.tgehring.itdb.client.desktop.view.utils.ViewSettings;
import de.tgehring.itdb.client.service.DesktopSession;
import de.tgehring.itdb.client.service.PrimaryStage;

/**
 * The Class ITDBMenuBar.
 */
public class ITDBMenuBar extends MenuBar {
	
	/** The pop. */
	private Popup pop;
	
	/**
	 * Instantiates a new iTDB menu bar.
	 */
	public ITDBMenuBar() {
		init();
	}

	/**
	 * Inits the ITDBMenuBar.
	 */
	private void init() {
		initGeneralMenu();
		if(DesktopSession.getInstance().isLoggedIn()) {
			initNavigationMenu();
		}
		initOptionsMenu();
	}

	/**
	 * Inits the options menu.
	 */
	private void initOptionsMenu() {
		Menu optionsMenu = new Menu("Einstellungen");
		MenuItem optionsConnectionItem = new MenuItem("Verbindung");
		optionsMenu.getItems().add(optionsConnectionItem);
		
		optionsConnectionItem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				startView("options");
			}
		});
		getMenus().add(optionsMenu);
	}

	/**
	 * Inits the navigation menu.
	 */
	private void initNavigationMenu() {
		Menu navigationMenu = new Menu("Navigation");
		MenuItem navigationStartItem = new MenuItem("Start");
		navigationMenu.getItems().add(navigationStartItem);
		
		Menu navigationEntityMenu = new Menu("Entities");
		
		MenuItem entityAbteilungItem = new MenuItem("Abteilung");
		MenuItem entityBenutzerItem = new MenuItem("Benutzer");
		MenuItem entityCpuItem = new MenuItem("Cpu");
		MenuItem entityDruckerItem = new MenuItem("Drucker");
		MenuItem entityGebäudeItem = new MenuItem("Gebäude");
		MenuItem entityGpuItem = new MenuItem("Gpu");
		MenuItem entityHerstellerItem = new MenuItem("Hersteller");
		MenuItem entityLieferantItem = new MenuItem("Lieferant");
		MenuItem entityMonitorItem = new MenuItem("Monitor");
		MenuItem entityRechnerItem = new MenuItem("Rechner");
		MenuItem entityRechnungItem = new MenuItem("Rechnung");
		MenuItem entitySoftwareItem = new MenuItem("Software");
		MenuItem entityTabletItem = new MenuItem("Tablet");
		MenuItem entityTodoItem = new MenuItem("Todo");
		
		navigationMenu.getItems().add(navigationEntityMenu);
		navigationEntityMenu.getItems().add(entityAbteilungItem);
		navigationEntityMenu.getItems().add(entityBenutzerItem);
		navigationEntityMenu.getItems().add(entityCpuItem);
		navigationEntityMenu.getItems().add(entityDruckerItem);
		navigationEntityMenu.getItems().add(entityGebäudeItem);
		navigationEntityMenu.getItems().add(entityGpuItem);
		navigationEntityMenu.getItems().add(entityHerstellerItem);
		navigationEntityMenu.getItems().add(entityLieferantItem);
		navigationEntityMenu.getItems().add(entityMonitorItem);
		navigationEntityMenu.getItems().add(entityRechnerItem);
		navigationEntityMenu.getItems().add(entityRechnungItem);
		navigationEntityMenu.getItems().add(entitySoftwareItem);
		navigationEntityMenu.getItems().add(entityTabletItem);
		navigationEntityMenu.getItems().add(entityTodoItem);
		
		navigationStartItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	startView("main");
            }
        });
		
		entityAbteilungItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	startView("abteilung");
            }
        });
		entityBenutzerItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("user");
			}
		});
		entityCpuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("cpu");
			}
		});
		entityDruckerItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("drucker");
			}
		});
		entityGebäudeItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("gebäude");
			}
		});
		entityGpuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("gpu");
			}
		});
		entityHerstellerItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("hersteller");
			}
		});
		entityLieferantItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("lieferant");
			}
		});
		entityMonitorItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("monitor");
			}
		});
		entityRechnerItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("rechner");
			}
		});
		entityRechnungItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("rechnung");
			}
		});
		entitySoftwareItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("software");
			}
		});
		entityTabletItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("tablet");
			}
		});
		entityTodoItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startView("todo");
			}
		});
		getMenus().add(navigationMenu);
	}

	/**
	 * Inits the general menu.
	 */
	private void initGeneralMenu() {
		Menu generalMenu = new Menu("Allgemein");
		if(DesktopSession.getInstance().isLoggedIn()) {
			MenuItem generalLogoutItem = new MenuItem("Logout");
			generalLogoutItem.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					DesktopSession session = DesktopSession.getInstance();
					session.setLoggedIn(false);
					session.setBenutzer(null);
					startView("login");
				}
			});
			generalMenu.getItems().add(generalLogoutItem);
		}
		MenuItem generalCloseItem = new MenuItem("Schließen");
		generalCloseItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				javafx.application.Platform.exit();
			}
		});
		generalMenu.getItems().add(generalCloseItem);
		getMenus().add(generalMenu);
	}
	
	/**
	 * Start view.
	 *
	 * @param view the view
	 */
	private void startView(String view) {
		String path = "fxml/" + view + ".fxml";
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
		pop.hide();
		Scene scene = new Scene(parent, ViewSettings.getWidth(), ViewSettings.getHeight());
		BorderPane bp = (BorderPane) parent;
		bp.setTop(new ITDBHeader());
		PrimaryStage.getInstance().getStage().setScene(scene);
	}

}
