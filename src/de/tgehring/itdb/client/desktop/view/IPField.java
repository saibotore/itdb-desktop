/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

// TODO: Auto-generated Javadoc
/**
 * The Class IPField.
 */
public class IPField extends LimitedTextField implements ChangeListener<Boolean> {
	
	/** The focused. */
	private boolean focused;
	
	/**
	 * Instantiates a new iP field.
	 */
	public IPField() {
		super();
		this.focused = false;
		this.setMaxlength(15);
		this.focusedProperty().addListener(this);

	}

	/* (non-Javadoc)
	 * @see javafx.beans.value.ChangeListener#changed(javafx.beans.value.ObservableValue, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1,
			Boolean arg2) {
		if(focused) {
			String content = this.getText();
			if(!content.contains(".") && content.length() == 12) {
				this.setText(getIp(content));
			}
		}
		focused = !focused;
	}
	
	/**
	 * Gets the ip.
	 *
	 * @param value the value
	 * @return the ip
	 */
	private String getIp(String value) {
		String result = value.substring(0, 3);
		result += ".";
		result += getSegment(value.substring(3,6));
		result += ".";
		result += getSegment(value.substring(6,9));
		result += ".";
		result += getSegment(value.substring(9,12));
		return result;
	}
	
	/**
	 * Gets the segment.
	 *
	 * @param value the value
	 * @return the segment
	 */
	private String getSegment(String value) {
		String result = "";
		for(int i=0; i<3; i++) {
			char c = value.charAt(i);
			if(c != '0') {
				return result = value.substring(i);
			}
		}
		if(result.length() == 0) {
			result = value.substring(2);
		}
		return result;
	}

}
