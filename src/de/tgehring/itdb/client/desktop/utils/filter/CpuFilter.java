/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.utils.filter;

import java.util.LinkedList;
import java.util.List;

import de.tgehring.itdb.client.desktop.model.Cpu;
import de.tgehring.itdb.client.desktop.model.Rechner;

/**
 * The Class CpuFilter.
 */
public class CpuFilter implements ComponentFilter {

	/** The input. */
	private String input;
	
	/**
	 * Instantiates a new cpu filter.
	 *
	 * @param input the input
	 */
	public CpuFilter(String input) {
		this.setInput(input);
	}

	/* (non-Javadoc)
	 * @see de.tgehring.itdb.client.desktop.utils.filter.ComponentFilter#applyFilter(java.util.List)
	 */
	@Override
	public List<Rechner> applyFilter(List<Rechner> list) {
		List<Rechner> result = new LinkedList<Rechner>();
		for(Rechner rechner: list) {
			Cpu cpu = rechner.getCpu();
			if(cpu != null) {
				String hersteller = cpu.getHersteller().getBezeichnung();
				String bezeichnung = cpu.getBezeichnung();
				if(hersteller.contains(input)) {
					result.add(rechner);
				} else if(bezeichnung.contains(input)) {
					result.add(rechner);
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
