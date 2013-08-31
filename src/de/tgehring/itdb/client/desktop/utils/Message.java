/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * The Class Message represents a message thrown by the system.
 */
public class Message extends Observable {
	
	/** The message type. */
	private MessageType type;
	
	/** The message. */
	private String message;
	
	/** The instance. */
	private static Message instance;
	
	/** The observers. */
	private List<Observer> observers;
	
	/**
	 * Instantiates a new message.
	 */
	private Message() {
		super();
		observers = new LinkedList<Observer>();
	}
	
	/**
	 * Gets the single instance of Message.
	 *
	 * @return single instance of Message
	 */
	public static Message getInstance() {
		if(instance == null) {
			instance = new Message();
		}
		return instance;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public MessageType getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(MessageType type) {
		this.type = type;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
		this.notifyObservers(message);
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	@Override
	public void notifyObservers(Object arg) {
		for(Observer o: observers) {
			o.update(instance, message);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + type + "]: " + message;
	}

	/**
	 * Clears the message.
	 */
	public void clear() {
		this.setType(MessageType.Clear);
		this.setMessage("");
	}

	/* (non-Javadoc)
	 * @see java.util.Observable#addObserver(java.util.Observer)
	 */
	@Override
	public synchronized void addObserver(Observer o) {
		this.observers.add(o);
	}
	
	
	

}
