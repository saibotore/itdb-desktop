/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.service;

import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class PrimaryStage.
 */
public class PrimaryStage {
	
	/** The stage. */
	private Stage stage;
	
	/** The instance. */
	private static PrimaryStage instance;
	
	/**
	 * Instantiates a new primary stage.
	 */
	private PrimaryStage() {
		setStage(new Stage());
	}
	
	/**
	 * Gets the single instance of PrimaryStage.
	 *
	 * @return single instance of PrimaryStage
	 */
	public static PrimaryStage getInstance() {
		if(instance == null) {
			instance = new PrimaryStage();
		}
		return instance;
	}

	/**
	 * Gets the stage.
	 *
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Sets the stage.
	 *
	 * @param stage the new stage
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
