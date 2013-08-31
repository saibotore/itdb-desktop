/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.service;

import java.util.Observable;

import javafx.stage.Stage;
import de.tgehring.itdb.client.desktop.model.Benutzer;

// TODO: Auto-generated Javadoc
/**
 * The Class DesktopSession.
 */
public class DesktopSession extends Observable {
	
	/** The instance. */
	private static DesktopSession instance = null;
	
	/** The benutzer. */
	private Benutzer benutzer;
	
	/** The logged in. */
	private boolean loggedIn;
	
	/**
	 * Instantiates a new desktop session.
	 */
	private DesktopSession() {
		
	}
	
	/**
	 * Gets the single instance of DesktopSession.
	 *
	 * @return single instance of DesktopSession
	 */
	public static DesktopSession getInstance() {
		if(instance == null) {
			instance = new DesktopSession();
		}
		return instance;
	}

	/**
	 * Gets the benutzer.
	 *
	 * @return the benutzer
	 */
	public Benutzer getBenutzer() {
		return benutzer;
	}

	/**
	 * Sets the benutzer.
	 *
	 * @param benutzer the new benutzer
	 */
	public void setBenutzer(Benutzer benutzer) {
		if(benutzer == null) {
			setLoggedIn(false);
		} else {
			setLoggedIn(true);
		}
		this.benutzer = benutzer;
	}

	/**
	 * Checks if is logged in.
	 *
	 * @return true, if is logged in
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Sets the logged in.
	 *
	 * @param loggedIn the new logged in
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

}
