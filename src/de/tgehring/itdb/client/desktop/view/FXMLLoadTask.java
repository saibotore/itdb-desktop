/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.view;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

// TODO: Auto-generated Javadoc
/**
 * The Class FXMLLoadTask.
 */
public class FXMLLoadTask extends Task<Parent> {
	
	/** The fxml location. */
	private final String fxmlLocation;
	
	/**
	 * Instantiates a new fXML load task.
	 *
	 * @param fxmlLocation the fxml location
	 */
	public FXMLLoadTask(String fxmlLocation) {
	    this.fxmlLocation = fxmlLocation ;
  	}
	  	
	/* (non-Javadoc)
	 * @see javafx.concurrent.Task#call()
	 */
	@Override
	protected Parent call() throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource(fxmlLocation));
		return parent ;
	}

}
