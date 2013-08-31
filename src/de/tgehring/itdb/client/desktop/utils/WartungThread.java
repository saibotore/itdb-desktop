/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.tgehring.itdb.client.desktop.model.Rechner;
import de.tgehring.itdb.client.desktop.model.Todo;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class WartungThread create maintenance todos in a separate thread.
 */
public class WartungThread extends Thread {
	
	/** The list of all computer. */
	private List<Rechner> list;
	
	/**
	 * Class constructor with the list of all computers.
	 *
	 * @param list the list
	 */
	public WartungThread(List<Rechner> list) {
		this.list = list;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		super.run();
		for(Rechner rechner: list) {
			if(rechner != null && !rechner.isInitWartung()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				int currentMonth = cal.get(Calendar.MONTH)+1;
				int currentYear = cal.get(Calendar.YEAR);
				String wartung = rechner.getWartung();
				if(wartung != null && wartung.length() > 8) {
					int month = Integer.parseInt(getMonth(wartung));
					int year = Integer.parseInt(getYear(wartung));
					if(currentYear-year == 1) {
						if(currentMonth-month == 0) {
							Todo t = new Todo();
							t.setName("Wartung" + rechner.getComputername());
							t.setBeschreibung(rechner.toString());
							t.setWichtig(3);
							t.setDate("00."+currentMonth+"."+currentYear);
							CRUDClient.getInstance().addTodo(t);
							rechner.setInitWartung(true);
							CRUDClient.getInstance().editRechner(rechner);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Gets the year of a given date string.
	 *
	 * @param date the date
	 * @return the year
	 */
	private String getYear(String date) {
		String[] values = date.split("\\.");
		return values[2];
	}
	
	/**
	 * Gets the month of a given date string.
	 *
	 * @param date the date
	 * @return the month
	 */
	private String getMonth(String date) {
		String[] values = date.split("\\.");
		return values[1];
	}

}
