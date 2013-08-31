/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Todo.
 */
@XmlRootElement
public class Todo implements Entity {
	
	/** The id. */
	private long id;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String beschreibung;
	
	/** The importance. */
	private int wichtig;
	
	/** The date. */
	private String date;
	
	/**
	 * Class constructor.
	 */
	public Todo() {
		
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Sets the description.
	 *
	 * @param beschreibung the new description
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * Gets the importance.
	 *
	 * @return the importance
	 */
	public int getWichtig() {
		return wichtig;
	}

	/**
	 * Sets the importance.
	 *
	 * @param wichtig the new importance
	 */
	public void setWichtig(int wichtig) {
		this.wichtig = wichtig;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + " (" + date + ")";
	}
	
	
	
}
