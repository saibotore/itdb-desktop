/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import javax.xml.bind.annotation.XmlRootElement;

import de.tgehring.itdb.client.desktop.model.exception.SoftwareException;

/**
 * The Class Software represents the used software on computer.
 */
@XmlRootElement
public class Software implements Entity {

	/** The id. */
	private long id;
	
	/** The supplier. */
	private Lieferant lieferant;
	
	/** The invoice. */
	private Rechnung rechnung;
	
	/** The maximum licences . */
	private int maxLizenzen;
	
	/** The number of used licences. */
	private int anzahlLizenzen;
	
	/** The description. */
	private String bezeichnung;
	
	/**
	 * Class constructor.
	 */
	public Software() {
		
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
	 * Gets the number of maximum licenses.
	 *
	 * @return the max lizenzen
	 */
	public int getMaxLizenzen() {
		return maxLizenzen;
	}
	
	/**
	 * Sets number of maximum licenses.
	 *
	 * @param maxLizenzen the new number of maximum licenses
	 */
	public void setMaxLizenzen(int maxLizenzen) {
		this.maxLizenzen = maxLizenzen;
	}
	
	/**
	 * Gets the number of used licenses.
	 *
	 * @return the number of used licenses
	 */
	public int getAnzahlLizenzen() {
		return anzahlLizenzen;
	}
	
	/**
	 * Sets the number of used licenses.
	 *
	 * @param anzahlLizenzen the new number of used licenses
	 */
	public void setAnzahlLizenzen(int anzahlLizenzen) {
		this.anzahlLizenzen = anzahlLizenzen;
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
		return bezeichnung + " : " + anzahlLizenzen + " von " + maxLizenzen;
	}

	/**
	 * Use licence.
	 *
	 * @throws SoftwareException the software exception
	 */
	public void useLicence() throws SoftwareException {
		if(anzahlLizenzen < maxLizenzen) {
			setAnzahlLizenzen(this.anzahlLizenzen+1);
		} else {
			throw new SoftwareException("Keine verfügbaren Lizenzen übrig.");
		}
	}

	/**
	 * Unuse licence.
	 *
	 * @throws SoftwareException the software exception
	 */
	public void unuseLicence() throws SoftwareException {
		if(anzahlLizenzen > 0) {
			setAnzahlLizenzen(this.anzahlLizenzen-1);
		} else {
			throw new SoftwareException("Die Anzahl der genutzen Lizenzen darf nicht kleiner als 0 sein.");
		}
		
	}
	
	
	
}
