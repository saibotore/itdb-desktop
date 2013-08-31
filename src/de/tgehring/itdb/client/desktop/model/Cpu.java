/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Cpu represents a cpu.
 */
@XmlRootElement
public class Cpu implements Entity {

	/** The id. */
	private long id;
	
	/** The manufacturer. */
	private Hersteller hersteller;
	
	/** The supplier. */
	private Lieferant lieferant;
	
	/** The invoice. */
	private Rechnung rechnung;
	
	/** The socket. */
	private String sockel;
	
	/** The clocking. */
	private String taktung;
	
	/** The description. */
	private String bezeichnung;
	
	/**
	 * Instantiates a new cpu.
	 */
	public Cpu() {
		
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
	 * Gets the socket.
	 *
	 * @return the socket
	 */
	public String getSockel() {
		return sockel;
	}
	
	/**
	 * Sets the socket.
	 *
	 * @param sockel the new socket
	 */
	public void setSockel(String sockel) {
		this.sockel = sockel;
	}
	
	/**
	 * Gets the clocking.
	 *
	 * @return the clocking
	 */
	public String getTaktung() {
		return taktung;
	}
	
	/**
	 * Sets the clocking.
	 *
	 * @param taktung the new clocking
	 */
	public void setTaktung(String taktung) {
		this.taktung = taktung;
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
