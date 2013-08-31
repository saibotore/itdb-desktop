/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.utils.filter;

import java.util.LinkedList;
import java.util.List;

import de.tgehring.itdb.client.desktop.model.Rechner;

// TODO: Auto-generated Javadoc
/**
 * The Class RechnerFilter.
 */
public class RechnerFilter {
	
	/** The filter list. */
	private List<ComponentFilter> filterList;
	
	/** The rechner list. */
	private List<Rechner> rechnerList;
	
	/**
	 * Instantiates a new rechner filter.
	 */
	public RechnerFilter () {
		this.filterList = new LinkedList<ComponentFilter>();
		this.rechnerList = new LinkedList<Rechner>();
	}
	
	/**
	 * Apply filter.
	 *
	 * @return the list
	 */
	public List<Rechner> applyFilter() {
		List<Rechner> result = new LinkedList<Rechner>();
		result.addAll(rechnerList);
		for(ComponentFilter filter: filterList) {
			result.retainAll(filter.applyFilter(result));
		}
		return result;
	}
	
	/**
	 * Adds the filter.
	 *
	 * @param filter the filter
	 */
	public void addFilter(ComponentFilter filter) {
		this.filterList.add(filter);
	}

	/**
	 * Gets the filter list.
	 *
	 * @return the filter list
	 */
	public List<ComponentFilter> getFilterList() {
		return filterList;
	}

	/**
	 * Sets the filter list.
	 *
	 * @param filterList the new filter list
	 */
	public void setFilterList(List<ComponentFilter> filterList) {
		this.filterList = filterList;
	}

	/**
	 * Gets the rechner list.
	 *
	 * @return the rechner list
	 */
	public List<Rechner> getRechnerList() {
		return rechnerList;
	}

	/**
	 * Sets the rechner list.
	 *
	 * @param rechnerList the new rechner list
	 */
	public void setRechnerList(List<Rechner> rechnerList) {
		this.rechnerList = rechnerList;
	}
	
}