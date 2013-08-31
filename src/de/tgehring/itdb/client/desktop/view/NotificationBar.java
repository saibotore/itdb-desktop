/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.view;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import de.tgehring.itdb.client.desktop.utils.Message;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationBar.
 */
public class NotificationBar extends Label implements Observer {
	
	/** The bar. */
	private static NotificationBar bar;
	
	/**
	 * Instantiates a new notification bar.
	 */
	public NotificationBar() {
		this.setFont(new Font(14.0));
		this.setOpacity(0.75);
		this.getStyleClass().add("notificationBar");
		bar = this;
		Message.getInstance().addObserver(this);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable arg0, Object arg1) {
		this.setText(Message.getInstance().getMessage());
		new Thread() {
			
			public void run() {
				try {
					TimeUnit.SECONDS.sleep( 5 );
					NotificationBar.clear();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
			
		}.start();
	}
	
	/**
	 * Clear.
	 */
	public static void clear() {
		Platform.runLater(new Runnable() {
			@Override public void run() {
				NotificationBar.bar.setText("");
			}
		});
	}


}
