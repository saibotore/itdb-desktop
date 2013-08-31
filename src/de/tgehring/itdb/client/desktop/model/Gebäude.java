/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Gebäude represents a building of a company.
 */
@XmlRootElement
public class Gebäude implements Entity {

	/** The id. */
	private long id;
	
	/** The description of the building. */
	private String bezeichnung;
	
	/** The street of the building. */
	private String strasse;
	
	/** The zip code of the building. */
	private String plz;
	
	/** The location of the building. */
	private String ort;
	
	/**
	 * Class constructor.
	 */
	public Gebäude() {
		
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param bezeichnung the new description
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStrasse() {
		return strasse;
	}
	
	/**
	 * Sets the street.
	 *
	 * @param strasse the new street
	 */
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	
	/**
	 * Gets the zip code.
	 *
	 * @return the zip code
	 */
	public String getPlz() {
		return plz;
	}
	
	/**
	 * Sets the zip code.
	 *
	 * @param plz the new zip code
	 */
	public void setPlz(String plz) {
		this.plz = plz;
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getOrt() {
		return ort;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param ort the new location
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return bezeichnung;
	}
	
}
