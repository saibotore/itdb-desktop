/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.view;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;

/**
 * The Class SuggestComboBox.
 *
 * @param <Entity> the generic type
 */
public class SuggestComboBox<Entity> extends ComboBox<Entity> implements ChangeListener<String> {
	
	/** The list. */
	private List<Entity> list;
	
	/** The backup. */
	private List<Entity> backup;
	
	/** The selected. */
	private Entity selected;
	
	/**
	 * Instantiates a new suggest combo box.
	 */
	public SuggestComboBox() {
		super();
		setEditable(true);
		editorProperty().getValue().textProperty().addListener(this);
		selectionModelProperty().get().selectedItemProperty().addListener(new ChangeListener<Entity>() {

			@Override
			public void changed(ObservableValue<? extends Entity> arg0, Entity oldValue,
					Entity newValue) {
				if(newValue instanceof String) {
					for (Entity element: list) {
						if(element.toString().equals(newValue)) {
							selected = element;
						}
					}
				} else {
					selected = newValue;
				}
			}
			
		});
	}
	
	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<Entity> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list the new list
	 */
	public void setList(List<Entity> list) {
		this.list = list;
		this.backup = new LinkedList<Entity>();
		this.backup.addAll(list);
		resetList();
	}
	
	/**
	 * Reset list.
	 */
	public void resetList() {
		getItems().clear();
		getItems().addAll(backup);
	}
	
	/**
	 * Filter list.
	 *
	 * @param filtered the filtered
	 */
	private void filterList(List<Entity> filtered) {
		this.list = filtered;
		getItems().clear();
		getItems().addAll(list);
	}
	
	/* (non-Javadoc)
	 * @see javafx.beans.value.ChangeListener#changed(javafx.beans.value.ObservableValue, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void changed(ObservableValue<? extends String> arg0, String oldValue,
			String newValue) {
		if(oldValue.length() > newValue.length()) {
			setList(backup);
		}
		if(newValue.length() == 0) {
			super.hide();
		}
		if(newValue.length() == 1) {
			super.show();
		}
		String input = newValue;
		List<Entity> temp = new LinkedList<Entity>();
		for(Entity element: list) {
			if(element.toString().contains(input)) {
				temp.add(element);
			}
		}
		filterList(temp);
	}
	
	/**
	 * Clear.
	 */
	public void clear() {
		clearText();
		clearSelection();
	}

	/**
	 * Clear text.
	 */
	private void clearText() {
		editorProperty().getValue().textProperty().set("");
	}
	
	/**
	 * Clear selection.
	 */
	private void clearSelection() {
		selectionModelProperty().get().clearSelection();
	}

	public Entity getSelected() {
		return selected;
	}

	public void setSelected(Entity selected) {
		this.selected = selected;
	}
	

}
