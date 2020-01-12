package io.github.evanspendlove.todojavaappuri.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import io.github.evanspendlove.todojavaappuri.Main;
import io.github.evanspendlove.todojavaappuri.animation.Roller;
import io.github.evanspendlove.todojavaappuri.animation.Shaker;
import io.github.evanspendlove.todojavaappuri.database.Constants;
import io.github.evanspendlove.todojavaappuri.database.DatabaseHandler;
import io.github.evanspendlove.todojavaappuri.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ListController
{

    @FXML
    private JFXButton logoutButton;

    private DatabaseHandler dbHandler;

    @FXML
    private ImageView listRefreshButton;

    @FXML
    private JFXListView<Task> listTasks;

    @FXML
    private JFXTextField listTaskField;

    @FXML
    private JFXTextField listDescriptionField;

    @FXML
    private JFXButton listAddTaskButton;

    private Task selectedTask;

    @FXML
    void initialize() throws SQLException
    {
        // Initialise our variables
        dbHandler = new DatabaseHandler();

        // Initialise our list
        fetchTaskList();

        // If the user tries to refresh the tasks
        listRefreshButton.setOnMouseClicked(event ->
        {
            fetchTaskList();
        });

        // If the user adds a task
        listAddTaskButton.setOnAction(event ->
        {
            saveTask();
        });

        // Show selected tasks in the editor on the left hand side
        listTasks.getSelectionModel().selectedItemProperty().addListener((event) ->
        {
            selectedTask = listTasks.getSelectionModel().getSelectedItem(); // Set selectedTask as the current task selected
            if(selectedTask != null) // If a task has been selected and is not null
            {
                listTaskField.setText(selectedTask.getTask()); // Set the task field to the current task
                listDescriptionField.setText(selectedTask.getDescription()); // Set the description field to the current task
                listAddTaskButton.setText("Update Task"); // Update the task button
            }
        });

        // If the user logs out
        logoutButton.setOnMouseClicked((event) ->
        {
            logout();
        });
    }

    public void fetchTaskList()
    {
        // Create new roller for this icon and roll it
        Roller refreshRoller = new Roller(listRefreshButton);
        refreshRoller.roll();

        ObservableList<Task> list = FXCollections.observableArrayList();

        ResultSet resultSet = dbHandler.getTasksByUser(Main.userid);

        try {
            while (resultSet.next())
            {
                Task task = new Task(); // Create new task

                // Set Values
                task.setTaskId(resultSet.getInt(Constants.TASKS_TASKID));
                task.setTask(resultSet.getString(Constants.TASKS_TASK));
                task.setDateCreated(resultSet.getTimestamp(Constants.TASKS_DATECREATED));
                task.setDescription(resultSet.getString(Constants.TASKS_DESCRIPTION));

                // Add task to list
                list.addAll(task);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

        listTasks.setItems(list);
        listTasks.setCellFactory(CellController -> new CellController());

        // Reset task editor
        selectedTask = null;

        // Reset these two fields
        listTaskField.setText("");
        listDescriptionField.setText("");
    }

    public void saveTask()
    {

        if (!listTaskField.getText().trim().equals("") && !listDescriptionField.getText().trim().equals(""))  // If these two fields aren't empty
        {

            if(selectedTask != null) // If a task has been selected
            {
                updateSelectedTask(); // Update the selected task
            }
            else
            {
                addNewTask(); // If not, add a new task
            }

            // Now update the list

            try
            {
                initialize();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else // User did not enter valid task parameters
        {
            // Create two new Shakers for these elements
            Shaker taskFieldShaker = new Shaker(listTaskField);
            Shaker descriptionFieldShaker = new Shaker(listDescriptionField);

            // Shake these two fields to indicate nothing added
            taskFieldShaker.shake();
            descriptionFieldShaker.shake();
        }
    }

    private void addNewTask()
    {
        Task task = new Task();

        // Getting and trimming input
        String taskText = listTaskField.getText().trim();
        String taskDescription = listDescriptionField.getText().trim();

        // Creating new Calendar instance for recording the current date and time
        Calendar calendar = Calendar.getInstance();

        java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

        // Set task values
        task.setUserId(Main.userid);
        task.setDateCreated(timestamp);
        task.setDescription(taskDescription);
        task.setTask(taskText);

        // Insert the task into the data base
        dbHandler.insertTask(task);
    }

    private void updateSelectedTask()
    {
        // Update selected task with fields before sending to DB

        selectedTask.setTask(listTaskField.getText().trim());
        selectedTask.setDescription(listDescriptionField.getText().trim());
        selectedTask.setUserId(Main.userid);
        selectedTask.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));

        dbHandler.updateTask(selectedTask); // Add to database

        // Reset button text
        listAddTaskButton.setText("Add Task");

        // Reset selected task to null
        selectedTask = null;
    }

    private void logout()
    {
        logoutButton.getScene().getWindow().hide(); // Hide the current window
        Main.userid = -1; // Reset userid

        // Need to re-load the login page
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/login.fxml"));
        try
        {
            loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}




















