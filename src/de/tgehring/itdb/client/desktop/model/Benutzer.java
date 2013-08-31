/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Benutzer represents a User.
 */
@XmlRootElement
public class Benutzer implements Entity {
	
	/** The id. */
	private long id;
	
	/** The firstname. */
	private String vorname;
	
	/** The lastname. */
	private String nachname;
	
	/** The username. */
	private String benutzername;
	
	/** The password. */
	private String passwort;
	
	/** The admin. */
	private boolean admin;
	
	/**
	 * Instantiates a new user.
	 */
	public Benutzer() {
		
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the firstname.
	 *
	 * @return the firstname
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * Sets the firstname.
	 *
	 * @param vorname the new firstname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * Gets the lastname.
	 *
	 * @return the lastname
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * Sets the lastname.
	 *
	 * @param nachname the new lastname
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getBenutzername() {
		return benutzername;
	}

	/**
	 * Sets the username.
	 *
	 * @param benutzername the new username
	 */
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPasswort() {
		return passwort;
	}

	/**
	 * Sets the password.
	 *
	 * @param passwort the new password
	 */
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	/**
	 * Checks if is admin.
	 *
	 * @return true, if is admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * Sets the admin.
	 *
	 * @param admin the new admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + benutzername;
	}
	
	

}
