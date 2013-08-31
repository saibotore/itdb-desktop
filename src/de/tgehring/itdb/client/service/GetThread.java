/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.service;

import com.sun.jersey.api.client.WebResource;

// TODO: Auto-generated Javadoc
/**
 * The Class GetThread.
 */
public class GetThread extends Thread {
	
	/** The service. */
	private WebResource service;
	
	/** The callbeck. */
	private ResultSetter callbeck;

	/**
	 * Instantiates a new gets the thread.
	 *
	 * @param service the service
	 */
	public GetThread(WebResource service) {
		this.service = service;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		
	}
	
	/**
	 * Sets the callback.
	 *
	 * @param callbeck the new callback
	 */
	public void setCallback(ResultSetter callbeck) {
		
	}

	/**
	 * Gets the callbeck.
	 *
	 * @return the callbeck
	 */
	public ResultSetter getCallbeck() {
		return callbeck;
	}

	/**
	 * Sets the callbeck.
	 *
	 * @param callbeck the new callbeck
	 */
	public void setCallbeck(ResultSetter callbeck) {
		this.callbeck = callbeck;
	}
	
	

}
