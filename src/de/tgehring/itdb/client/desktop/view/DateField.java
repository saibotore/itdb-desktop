/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.view;

// TODO: Auto-generated Javadoc
/**
 * The Class DateField.
 */
public class DateField extends LimitedTextField {
	
	/**
	 * Instantiates a new date field.
	 */
	public DateField() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see de.tgehring.itdb.client.desktop.view.LimitedTextField#replaceText(int, int, java.lang.String)
	 */
	@Override
    public void replaceText(int start, int end, String text) {
		if(text.matches("[0-9]")) {
            super.replaceText(start, end, text);
        }
    }

    /* (non-Javadoc)
     * @see de.tgehring.itdb.client.desktop.view.LimitedTextField#replaceSelection(java.lang.String)
     */
    @Override
    public void replaceSelection(String text) {
    	if(text.matches("[0-9]")) {
            super.replaceSelection(text);
        }
    }

}
