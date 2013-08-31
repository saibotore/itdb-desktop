/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.view;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

// TODO: Auto-generated Javadoc
/**
 * The Class ITDBHeader.
 */
public class ITDBHeader extends VBox {
	
	/** The menu. */
	private ITDBMenuBar menu;
	
	/** The notification bar. */
	private NotificationBar notificationBar;
	
	/**
	 * Instantiates a new iTDB header.
	 */
	public ITDBHeader() {
		this.setSpacing(10.0);
		this.setAlignment(Pos.CENTER);
		setMenu(new ITDBMenuBar());
		setNotificationBar(new NotificationBar());
		this.getChildren().addAll(menu, notificationBar);
	}

	/**
	 * Gets the menu.
	 *
	 * @return the menu
	 */
	public ITDBMenuBar getMenu() {
		return menu;
	}

	/**
	 * Sets the menu.
	 *
	 * @param menu the new menu
	 */
	public void setMenu(ITDBMenuBar menu) {
		this.menu = menu;
	}

	/**
	 * Gets the notification bar.
	 *
	 * @return the notification bar
	 */
	public NotificationBar getNotificationBar() {
		return notificationBar;
	}

	/**
	 * Sets the notification bar.
	 *
	 * @param notificationBar the new notification bar
	 */
	public void setNotificationBar(NotificationBar notificationBar) {
		this.notificationBar = notificationBar;
	}

}
