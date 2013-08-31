/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Dvm represents the printer material.
 */
@XmlRootElement
public class Dvm implements Entity {
	
	/** The id. */
	private long id;
	
	/** The color black big. */
	private String schwarzgross;
	
	/** The colo black. */
	private String schwarz;
	
	/** The colorpack. */
	private String colorpack;
	
	/** The color cyan. */
	private String cyan;
	
	/** The color magenta. */
	private String magenta;
	
	/** The color yellow. */
	private String yellow;
	
	/** The toner description. */
	private String tonerbezeichnung;
	
	/** The head unit. */
	private String leitereinheit;
	
	/**
	 * Instantiates a new printer material.
	 */
	public Dvm() {
		
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
	 * Gets the color black big.
	 *
	 * @return the color black big
	 */
	public String getSchwarzgross() {
		return schwarzgross;
	}

	/**
	 * Sets the color black big.
	 *
	 * @param schwarzgross the new color black big
	 */
	public void setSchwarzgross(String schwarzgross) {
		this.schwarzgross = schwarzgross;
	}

	/**
	 * Gets the color black.
	 *
	 * @return the color black
	 */
	public String getSchwarz() {
		return schwarz;
	}

	/**
	 * Sets the color black.
	 *
	 * @param schwarz the new color black
	 */
	public void setSchwarz(String schwarz) {
		this.schwarz = schwarz;
	}

	/**
	 * Gets the colorpack.
	 *
	 * @return the colorpack
	 */
	public String getColorpack() {
		return colorpack;
	}

	/**
	 * Sets the colorpack.
	 *
	 * @param colorpack the new colorpack
	 */
	public void setColorpack(String colorpack) {
		this.colorpack = colorpack;
	}

	/**
	 * Gets the color cyan.
	 *
	 * @return the color cyan
	 */
	public String getCyan() {
		return cyan;
	}

	/**
	 * Sets the color cyan.
	 *
	 * @param cyan the new color cyan
	 */
	public void setCyan(String cyan) {
		this.cyan = cyan;
	}

	/**
	 * Gets the color magenta.
	 *
	 * @return the color magenta
	 */
	public String getMagenta() {
		return magenta;
	}

	/**
	 * Sets the color magenta.
	 *
	 * @param magenta the new color magenta
	 */
	public void setMagenta(String magenta) {
		this.magenta = magenta;
	}

	/**
	 * Gets the color yellow.
	 *
	 * @return the color yellow
	 */
	public String getYellow() {
		return yellow;
	}

	/**
	 * Sets the color yellow.
	 *
	 * @param yellow the new color yellow
	 */
	public void setYellow(String yellow) {
		this.yellow = yellow;
	}

	/**
	 * Gets the toner description.
	 *
	 * @return the toner description
	 */
	public String getTonerbezeichnung() {
		return tonerbezeichnung;
	}

	/**
	 * Sets the toner description.
	 *
	 * @param tonerbezeichnung the new toner description
	 */
	public void setTonerbezeichnung(String tonerbezeichnung) {
		this.tonerbezeichnung = tonerbezeichnung;
	}

	/**
	 * Gets the head unit.
	 *
	 * @return the head unit
	 */
	public String getLeitereinheit() {
		return leitereinheit;
	}

	/**
	 * Sets the head unit.
	 *
	 * @param leitereinheit the new head unit
	 */
	public void setLeitereinheit(String leitereinheit) {
		this.leitereinheit = leitereinheit;
	}
	
	
}
