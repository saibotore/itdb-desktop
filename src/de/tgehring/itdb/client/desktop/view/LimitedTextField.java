/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.view;

import javafx.scene.control.TextField;

// TODO: Auto-generated Javadoc
/**
 * The Class LimitedTextField.
 */
public class LimitedTextField extends TextField {  

    /** The maxlength. */
    private int maxlength;

    /**
     * Instantiates a new limited text field.
     */
    public LimitedTextField() {
        this.maxlength = 10;
    }

    /**
     * Sets the maxlength.
     *
     * @param maxlength the new maxlength
     */
    public void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }

    /* (non-Javadoc)
     * @see javafx.scene.control.TextInputControl#replaceText(int, int, java.lang.String)
     */
    @Override
    public void replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        verifyInputLength();
    }

    /* (non-Javadoc)
     * @see javafx.scene.control.TextInputControl#replaceSelection(java.lang.String)
     */
    @Override
    public void replaceSelection(String text) {
        super.replaceSelection(text);
        verifyInputLength();
    }

    // Verifies the length of the input.
    /**
     * Verify input length.
     */
    private void verifyInputLength() {
        if (getText().length() > maxlength) {
            setText(getText().substring(0, maxlength));
        }
    }
    
}