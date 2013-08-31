/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Drucker represents a Printer.
 */
@XmlRootElement
public class Drucker implements Entity {

	/** The id. */
	private long id;
	
	/** The manufacturer. */
	private Hersteller hersteller;
	
	/** The supplier. */
	private Lieferant lieferant;
	
	/** The invoice. */
	private Rechnung rechnung;
	
	/** The material. */
	private Dvm material;
	
	/** The inventory number. */
	private String inventarnummer;
	
	/** The device number. */
	private String gerätenummer;
	
	/** The description. */
	private String bezeichnung;
	
	/**
	 * Instantiates a new printer.
	 */
	public Drucker() {
		
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
	 * Gets the material.
	 *
	 * @return the material
	 */
	public Dvm getMaterial() {
		return material;
	}
	
	/**
	 * Sets the material.
	 *
	 * @param material the new material
	 */
	public void setMaterial(Dvm material) {
		this.material = material;
	}
	
	/**
	 * Gets the inventory number.
	 *
	 * @return the inventory number
	 */
	public String getInventarnummer() {
		return inventarnummer;
	}
	
	/**
	 * Sets the inventory number.
	 *
	 * @param inventarnummer the new inventory number
	 */
	public void setInventarnummer(String inventarnummer) {
		this.inventarnummer = inventarnummer;
	}
	
	/**
	 * Gets the device number.
	 *
	 * @return the device number
	 */
	public String getGerätenummer() {
		return gerätenummer;
	}
	
	/**
	 * Sets the device number.
	 *
	 * @param gerätenummer the new device number
	 */
	public void setGerätenummer(String gerätenummer) {
		this.gerätenummer = gerätenummer;
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
		return inventarnummer + ": " + bezeichnung;
	}
	
}