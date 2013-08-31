/*
 * @author: Tobias Gehring
 * @version 1.0, 01.08.2013
 */
package de.tgehring.itdb.client.desktop.controller;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import com.sun.jersey.api.client.ClientResponse;

import de.tgehring.itdb.client.desktop.model.Todo;
import de.tgehring.itdb.client.desktop.utils.Message;
import de.tgehring.itdb.client.desktop.utils.MessageType;
import de.tgehring.itdb.client.service.CRUDClient;

/**
 * The Class TodoController represents the JavaFX Controller for todos.
 */
public class TodoController {
	
	/** The id field. */
	@FXML private TextField idField;
	
	/** The name field. */
	@FXML private TextField nameField;
	
	/** The description field. */
	@FXML private TextField beschreibungField;
	
	/** The priority slider. */
	@FXML private Slider prioritySlider;
	
	/** The day field. */
	@FXML private TextField dayField;
	
	/** The month field. */
	@FXML private TextField monthField;
	
	/** The year field. */
	@FXML private TextField yearField;
	
	/** The create button. */
	@FXML private Button createButton;
	
	/** The edit button. */
	@FXML private Button editButton;
	
	/** The delete button. */
	@FXML private Button deleteButton;
	
	/** The reset button. */
	@FXML private Button resetButton;
	
	/** The todo list view. */
	@FXML private ListView<Todo> todoListView;

	/** The selected todo. */
	private Todo selectedTodo;
	
	/** The list of all todos. */
	private List<Todo> todos;
	
	/** The ObservableList of all todos. */
	private ObservableList<Todo> data;
	
	/**
	 * Instantiates a new todo controller.
	 */
	public TodoController() {
		selectedTodo = new Todo();
	}

	/**
	 * Handles the create button action to create a todo.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleCreateButtonAction(ActionEvent event) {
		if (idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Todo todo = new Todo();
				todo.setName(nameField.getText());
				todo.setBeschreibung(beschreibungField.getText());
				todo.setWichtig((int) prioritySlider.getValue());
				todo.setDate(makeDate());
				
				ClientResponse res = CRUDClient.getInstance().addTodo(todo);
				if(res.getStatus() == 201) {
					if(res.getStatus() == 201) {
						updateList();
						setTableItems();
						Message message = Message.getInstance();
						message.setType(MessageType.Database);
						message.setMessage("Todo hinzugefügt");
						clearFields();
					}
				}
			}
		}
	}
	
	/**
	 * Makes date string from its components.
	 *
	 * @return the string
	 */
	private String makeDate() {
		String date = dayField.getText();
		date += ".";
		date += monthField.getText();
		date += ".";
		date += yearField.getText();
		return date;
	}
	
	/**
	 * Gets the year of a date string.
	 *
	 * @param date the date
	 * @return the year
	 */
	private String getYear(String date) {
		String[] values = date.split("\\.");
		return values[2];
	}
	
	/**
	 * Gets the month of a date string.
	 *
	 * @param date the date
	 * @return the month
	 */
	private String getMonth(String date) {
		String[] values = date.split("\\.");
		return values[1];
	}
	
	/**
	 * Gets the day of a date string.
	 *
	 * @param date the date
	 * @return the day
	 */
	private String getDay(String date) {
		String[] values = date.split("\\.");
		return values[0];
	}
	
	/**
	 * Updates the list of all todos.
	 */
	private void updateList() {
		if(todos != null) {
			data.removeAll(todos);
		}
		todos = new LinkedList<Todo>();
		todos = CRUDClient.getInstance().getAllTodo();
	}

	/**
	 * Check mandatory fields.
	 *
	 * @return true, if successful
	 */
	private boolean checkMandatoryFields() {
		if(nameField.getText().isEmpty()) {
			sendMessage("Name");
			return false;
		}
		if(beschreibungField.getText().isEmpty()) {
			sendMessage("Beschreibung");
			return false;
		}
		return true;
	}
	
	/**
	 * Sends message, which field have to be set.
	 * 
	 * @param fieldname the name of the field
	 */
	private void sendMessage(String fieldname) {
		String message = "Folgendes Feld ist ein Pflichtfeld: ";
		Message m = Message.getInstance();
		m.setType(MessageType.General);
		m.setMessage(message + fieldname);
	}

	/**
	 * Handles the edit button action to edit the selected todo.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleEditButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			if (checkMandatoryFields()) {
				Todo todo = new Todo();
				todo.setId(Long.parseLong(idField.getText()));
				todo.setName(nameField.getText());
				todo.setBeschreibung(beschreibungField.getText());
				todo.setWichtig((int) prioritySlider.getValue());
				todo.setDate(makeDate());
				
				ClientResponse res = CRUDClient.getInstance().editTodo(todo);
				if(res.getStatus() == 201) {
					selectedTodo = todo;
					for(int i=0; i<todos.size(); i++) {
						if(todos.get(i).getId() == todo.getId()) {
							todos.set(i, todo);
						}
					}
					setTableItems();
					Message message = Message.getInstance();
					message.setType(MessageType.Database);
					message.setMessage("Todo gespeichert.");
				}
			}
		}
	}

	/**
	 * Handles the delete button action to delete the selected todo.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleDeleteButtonAction(ActionEvent event) {
		if (!idField.getText().isEmpty()) {
			ClientResponse res = CRUDClient.getInstance().deleteTodo(Long.parseLong(idField.getText()));
			if(res.getStatus() == 201) {
				updateList();
				setTableItems();
				todoListView.getSelectionModel().clearSelection();
				clearFields();
				Message message = Message.getInstance();
				message.setType(MessageType.Database);
				message.setMessage("Todo gelöscht.");
			}
		}
	}

	/**
	 * Handles the reset button action to reset the view.
	 *
	 * @param event the event
	 */
	@FXML
	protected void handleResetButtonAction(ActionEvent event) {
		todoListView.getSelectionModel().clearSelection();
		clearFields();
	}

	/**
	 * Load all todos from database.
	 */
	private void loadTodos() {
		todos = CRUDClient.getInstance().getAllTodo();
	}

	/**
	 * Is called, when the view has been initialized.
	 */
	public void initialize() {
		createButton.setDisable(false);
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		loadTodos();
		initHandler();
		setTableItems();
	}

	/**
	 * Initializes all listeners.
	 */
	private void initHandler() {
		idField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				if (!idField.getText().isEmpty()) {
					createButton.setDisable(true);
					editButton.setDisable(false);
					deleteButton.setDisable(false);
				} else {
					createButton.setDisable(false);
					editButton.setDisable(true);
					deleteButton.setDisable(true);
				}
			}
		});
		todoListView.getSelectionModel().setSelectionMode(
				SelectionMode.SINGLE);
		todoListView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Todo>() {

					@Override
					public void changed(
							ObservableValue<? extends Todo> arg0,
							Todo arg1, Todo arg2) {
						if (todos != null) {
							Todo todo = todoListView
									.getSelectionModel().getSelectedItem();
							if (todo != null) {
								selectedTodo = new Todo();
								long id = Long.valueOf(todo.getId());
								for (Todo element : todos) {
									if (element.getId() == id) {
										selectedTodo = element;
										setFields();
									}
								}
							}
						}
					}

				});
		prioritySlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0,
					Number arg1, Number arg2) {
				System.out.println(prioritySlider.getValue());
			}
		});
	}

	/**
	 * Sets all input fields.
	 */
	private void setFields() {
		if (selectedTodo != null) {
			idField.setText(String.valueOf(selectedTodo.getId()));
			nameField.setText(selectedTodo.getName());
			beschreibungField.setText(selectedTodo.getBeschreibung());
			prioritySlider.setValue((double) selectedTodo.getWichtig());
			String date = selectedTodo.getDate();
			dayField.setText(getDay(date));
			monthField.setText(getMonth(date));
			yearField.setText(getYear(date));
		}
	}
	
	/**
	 * Clears all input fields.
	 */
	private void clearFields() {
		idField.clear();
		nameField.clear();
		beschreibungField.clear();
		prioritySlider.setValue(1.0);
		dayField.clear();
		monthField.clear();
		yearField.clear();
	}

	/**
	 * Sets the table items.
	 */
	private void setTableItems() {
		if (todos != null) {
			data = todoListView.getItems();
			data.clear();
			for (Todo a : todos) {
				data.add(a);
			}
			if(selectedTodo != null) {
				todoListView.getSelectionModel().select(selectedTodo);
			}
		}
	}

}
