/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.view.utils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewSettings.
 */
public class ViewSettings {
	
	/**
	 * Maximize.
	 *
	 * @param stage the stage
	 */
	public static void maximize(Stage stage) {
		Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        stage.setX(0);
        stage.setY(0);
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
	}
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public static double getWidth() {
		return Screen.getPrimary().getVisualBounds().getWidth();
	}
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public static double getHeight() {
		return Screen.getPrimary().getVisualBounds().getHeight();
	}
	
}
