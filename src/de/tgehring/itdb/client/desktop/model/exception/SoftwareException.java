/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model.exception;

/**
 * The Class SoftwareException represents a exception,
 *  occuring during the process of adding a software to a computer.
 */
public class SoftwareException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new software exception.
	 *
	 * @param message the message
	 */
	public SoftwareException(String message) {
		super(message);
	}
	
	

}
