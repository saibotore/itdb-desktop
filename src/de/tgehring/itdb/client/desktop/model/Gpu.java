/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class Gpu represents the gpu of the computer.
 */
@XmlRootElement
public class Gpu implements Entity {
	
	/** The id. */
	private long id;
	
	/** The manufacturer of the gpu. */
	private Hersteller hersteller;

	/** The supplier of the gpu. */
	private Lieferant lieferant;
	
	/** The invoice of the gpu. */
	private Rechnung rechnung;
	
	/** The interface of the gpu. */
	private String schnittstelle;
	
	/** The memory of the gpu. */
	private String speicher;
	
	/** The description of the gpu. */
	private String bezeichnung;
	
	/**
	 * Class constructor.
	 */
	public Gpu() {
		
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
	 * Gets the manufacturer.
	 *
	 * @return the manufacturer
	 */
	public Hersteller getHersteller() {
		return hersteller;
	}
	
	/**
	 * Sets the manufacturer.
	 *
	 * @param hersteller the new manufacturer
	 */
	public void setHersteller(Hersteller hersteller) {
		this.hersteller = hersteller;
	}
	
	/**
	 * Gets the supplier.
	 *
	 * @return the supplier
	 */
	public Lieferant getLieferant() {
		return lieferant;
	}
	
	/**
	 * Sets the supplier.
	 *
	 * @param lieferant the new supplier
	 */
	public void setLieferant(Lieferant lieferant) {
		this.lieferant = lieferant;
	}
	
	/**
	 * Gets the invoice.
	 *
	 * @return the invoice
	 */
	public Rechnung getRechnung() {
		return rechnung;
	}
	
	/**
	 * Sets the invoice.
	 *
	 * @param rechnung the new invoice
	 */
	public void setRechnung(Rechnung rechnung) {
		this.rechnung = rechnung;
	}
	
	/**
	 * Gets the interface.
	 *
	 * @return the interface
	 */
	public String getSchnittstelle() {
		return schnittstelle;
	}
	
	/**
	 * Sets the interface.
	 *
	 * @param schnittstelle the new interface
	 */
	public void setSchnittstelle(String schnittstelle) {
		this.schnittstelle = schnittstelle;
	}
	
	/**
	 * Gets the memory.
	 *
	 * @return the memory
	 */
	public String getSpeicher() {
		return speicher;
	}
	
	/**
	 * Sets the memory.
	 *
	 * @param speicher the new memory
	 */
	public void setSpeicher(String speicher) {
		this.speicher = speicher;
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
		if(hersteller != null) {
			return hersteller + ": " + bezeichnung;
		}
		return bezeichnung;
	}
	
}
