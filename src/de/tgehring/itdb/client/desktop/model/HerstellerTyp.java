/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.model;

/**
 * The Enum HerstellerTyp represents the manufacturer type.
 */
public enum HerstellerTyp implements Entity {
	
	/** Determines that the manufacturer produces cpus. */
	Cpu,
	
	/** Determines that the manufacturer produces gpus. */
	Gpu,
	
	/** Determines that the manufacturer produces printers. */
	Drucker,
	
	/** Determines that the manufacturer produces monitors. */
	Monitor,
	
	/** Determines that the manufacturer produces computers. */
	Rechner,
	
	/** Determines that the manufacturer produces tablets. */
	Tablet;

}
