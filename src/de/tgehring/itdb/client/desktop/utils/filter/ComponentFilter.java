/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.utils.filter;

import java.util.List;

import de.tgehring.itdb.client.desktop.model.Rechner;

/**
 * The Interface ComponentFilter.
 */
public interface ComponentFilter {
	
	/**
	 * Apply filter.
	 *
	 * @param list the list
	 * @return the list
	 */
	List<Rechner> applyFilter(List<Rechner> list);
	
}
