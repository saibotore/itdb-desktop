/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class Hersteller represents the manufacturer.
 */
@XmlRootElement
public class Hersteller implements Entity {
	
	/** The id. */
	private long id;
	
	/** The description. */
	private String bezeichnung;
	
	/** The manufacturer type. */
	private HerstellerTyp typ;
	
	/**
	 * Class constructor.
	 */
	public Hersteller() {
		
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
	 * Gets the manufacturer type.
	 *
	 * @return the manufacturer type
	 */
	public HerstellerTyp getTyp() {
		return typ;
	}
	
	/**
	 * Sets the manufacturer type.
	 *
	 * @param typ the new manufacturer type
	 */
	public void setTyp(HerstellerTyp typ) {
		this.typ = typ;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = bezeichnung;
		if(typ != null) {
			result += " (" + typ.name() + ")";
		}
		return result;
	}
	
	
	
}
