/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Rechnung represents an invoice.
 */
@XmlRootElement
public class Rechnung implements Entity {
	
	/** The id. */
	private long id;
	
	/** The invoice number. */
	private String rechnungsnummer;
	
	/** The invoice amount. */
	private double rechnungsbetrag;
	
	/** The invoice date. */
	private String rechnungsdatum;
	
	/**
	 * Class constructor.
	 */
	public Rechnung() {
		
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
	 * Gets the invoice number.
	 *
	 * @return the invoice number
	 */
	public String getRechnungsnummer() {
		return rechnungsnummer;
	}

	/**
	 * Sets the invoice number.
	 *
	 * @param rechnungsnummer the new invoice number
	 */
	public void setRechnungsnummer(String rechnungsnummer) {
		this.rechnungsnummer = rechnungsnummer;
	}

	/**
	 * Gets the invoice amount.
	 *
	 * @return the invoice amount
	 */
	public double getRechnungsbetrag() {
		return rechnungsbetrag;
	}

	/**
	 * Sets the invoice amount.
	 *
	 * @param rechnungsbetrag the new invoice amount
	 */
	public void setRechnungsbetrag(double rechnungsbetrag) {
		this.rechnungsbetrag = rechnungsbetrag;
	}

	/**
	 * Gets the invoice date.
	 *
	 * @return the invoice date
	 */
	public String getRechnungsdatum() {
		return this.rechnungsdatum;
	}

	/**
	 * Sets the invoice date.
	 *
	 * @param rechnungsdatum the new invoice date
	 */
	public void setRechnungsdatum(String rechnungsdatum) {
		this.rechnungsdatum = rechnungsdatum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return rechnungsnummer + " (" + rechnungsdatum + ")";
	}
	
}
