package io.github.evanspendlove.todojavaappuri.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

import io.github.evanspendlove.todojavaappuri.Main;
import io.github.evanspendlove.todojavaappuri.animation.Shaker;
import io.github.evanspendlove.todojavaappuri.database.DatabaseHandler;
import io.github.evanspendlove.todojavaappuri.model.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

// File completed so far

public class AddItemFormController
{
    private int userid;

    private DatabaseHandler dbHandler;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField taskField;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private JFXButton saveTaskButton;

    @FXML
    private Label successLabel;

    @FXML
    private JFXButton todosButton;

    @FXML
    void initialize()
    {
        // Instance variables
        dbHandler = new DatabaseHandler();
        Task task = new Task();

        saveTaskButton.setOnAction((ActionEvent event) ->
        {
            // Creating new Calendar instance for recording the current date and time
            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());

            // Getting and trimming input
            String taskText = taskField.getText().trim();
            String taskDescription = descriptionField.getText().trim();

            if (!taskText.equals("") && !taskDescription.equals(""))  // If these two fields aren't empty
            {
                // Set task values
                task.setUserId(Main.userid);
                task.setDateCreated(timestamp);
                task.setDescription(taskDescription);
                task.setTask(taskText);

                // Insert the task into the data base
                dbHandler.insertTask(task);

                // Let the user know the task has been successfully added
                successLabel.setVisible(true);
                todosButton.setVisible(true);

                // Now, we need to get the number of saved todos.
                int taskNumber = 0;

                taskNumber = dbHandler.getAllTasks(Main.userid);

                todosButton.setText("My todo's: " + "(" + taskNumber + ")");


                // Reset these two fields
                taskField.setText("");
                descriptionField.setText("");

                // If this button is clicked, open the list of all tasks for this user

                todosButton.setOnAction(event1 ->
                {
                    //send users to the list screen

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/list.fxml"));

                    try
                    {
                        loader.load();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    // Update Stage

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();


                });

            }
            else
            {
                System.out.println("Nothing added!");

                // Create two new Shakers for these elements
                Shaker taskFieldShaker = new Shaker(taskField);
                Shaker descriptionFieldShaker = new Shaker(descriptionField);

                // Shake these two fields to indicate nothing added
                taskFieldShaker.shake();
                descriptionFieldShaker.shake();
            }

        });
    }


}
