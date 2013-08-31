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
 * The Class HddFilter.
 */
public class HddFilter implements ComponentFilter {
	
	/** The input. */
	private String input;
	
	/**
	 * Instantiates a new hdd filter.
	 *
	 * @param input the input
	 */
	public HddFilter(String input) {
		this.setInput(input);
	}

	/* (non-Javadoc)
	 * @see de.tgehring.itdb.client.desktop.utils.filter.ComponentFilter#applyFilter(java.util.List)
	 */
	@Override
	public List<Rechner> applyFilter(List<Rechner> list) {
		List<Rechner> result = new LinkedList<Rechner>();
		for(Rechner rechner: list) {
			String hdd = rechner.getHdd1();
			if(hdd.contains(input)) {
				result.add(rechner);
			} else {
				hdd = rechner.getHdd2();
				if(hdd.contains(input)) {
					result.add(rechner);
				} else {
					hdd = rechner.getHdd3();
					if(hdd.contains(input)) {
						result.add(rechner);
					} else {
						hdd = rechner.getHdd4();
						if(hdd.contains(input)) {
							result.add(rechner);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Gets the input.
	 *
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * Sets the input.
	 *
	 * @param input the new input
	 */
	public void setInput(String input) {
		this.input = input;
	}

}
