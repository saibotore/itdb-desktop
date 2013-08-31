/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Lieferant represents a supplier.
 */
@XmlRootElement
public class Lieferant implements Entity {

	/** The id. */
	private long id;
	
	/** The description. */
	private String bezeichnung;
	
	/** The street. */
	private String strasse;
	
	/** The zip code. */
	private String plz;
	
	/** The location. */
	private String ort;
	
	/** The mailbox. */
	private String postfach;
	
	/** The url. */
	private String url;
	
	/** The phone number. */
	private String telefon;
	
	/** The fax number. */
	private String fax;
	
	/** The hotline. */
	private String hotline;
	
	/** The customer number. */
	private String kundennummer;
	
	/** The national mark. */
	private String lkz;
	
	/**
	 * Class constuctor.
	 */
	public Lieferant() {
		
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
	
	/**
	 * Gets the mailbox.
	 *
	 * @return the mailbox
	 */
	public String getPostfach() {
		return postfach;
	}
	
	/**
	 * Sets the mailbox.
	 *
	 * @param postfach the new mailbox
	 */
	public void setPostfach(String postfach) {
		this.postfach = postfach;
	}
	
	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getTelefon() {
		return telefon;
	}
	
	/**
	 * Sets the phone number.
	 *
	 * @param telefon the new phone number
	 */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	/**
	 * Gets the fax number.
	 *
	 * @return the fax number
	 */
	public String getFax() {
		return fax;
	}
	
	/**
	 * Sets the fax number.
	 *
	 * @param fax the new fax number
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	/**
	 * Gets the hotline.
	 *
	 * @return the hotline
	 */
	public String getHotline() {
		return hotline;
	}
	
	/**
	 * Sets the hotline.
	 *
	 * @param hotline the new hotline
	 */
	public void setHotline(String hotline) {
		this.hotline = hotline;
	}
	
	/**
	 * Gets the customer number.
	 *
	 * @return the customer number
	 */
	public String getKundennummer() {
		return kundennummer;
	}
	
	/**
	 * Sets the customer number.
	 *
	 * @param kundennummer the new customer number
	 */
	public void setKundennummer(String kundennummer) {
		this.kundennummer = kundennummer;
	}
	
	/**
	 * Gets the national mark.
	 *
	 * @return the national mark
	 */
	public String getLkz() {
		return lkz;
	}
	
	/**
	 * Sets the national mark.
	 *
	 * @param lkz the new national mark
	 */
	public void setLkz(String lkz) {
		this.lkz = lkz;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return bezeichnung;
	}
	
}
