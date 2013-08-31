/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Inventarnummer represtens the inventory number.
 */
@XmlRootElement
public class Inventarnummer implements Entity {
	
	/** The id. */
	private long id;
	
	/** The inventory number. */
	private String nummer;
	
	/**
	 * Class constructor
	 */
	public Inventarnummer() {
		
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
	 * Gets the inventory number.
	 *
	 * @return the inventory number
	 */
	public String getNummer() {
		return nummer;
	}

	/**
	 * Sets the inventory number.
	 *
	 * @param nummer the new inventory number
	 */
	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nummer;
	}
	
}
