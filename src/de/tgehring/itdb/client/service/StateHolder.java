/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.service;

// TODO: Auto-generated Javadoc
/**
 * The Class StateHolder.
 */
public class StateHolder {
	
	/** The loading. */
	private boolean loading;
	
	/** The instance. */
	private static StateHolder instance;
	
	/**
	 * Instantiates a new state holder.
	 */
	private StateHolder() {
		setLoading(false);
	}
	
	/**
	 * Gets the single instance of StateHolder.
	 *
	 * @return single instance of StateHolder
	 */
	public static StateHolder getInstance() {
		if(instance == null) {
			instance = new StateHolder();
		}
		return instance;
	}

	/**
	 * Checks if is loading.
	 *
	 * @return true, if is loading
	 */
	public boolean isLoading() {
		return loading;
	}

	/**
	 * Sets the loading.
	 *
	 * @param loading the new loading
	 */
	public void setLoading(boolean loading) {
		this.loading = loading;
	}
	
}
