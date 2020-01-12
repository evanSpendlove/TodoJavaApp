package io.github.evanspendlove.todojavaappuri.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.jfoenix.controls.JFXListCell;
import io.github.evanspendlove.todojavaappuri.Main;
import io.github.evanspendlove.todojavaappuri.database.DatabaseHandler;
import io.github.evanspendlove.todojavaappuri.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CellController extends JFXListCell<Task>
{
    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Label taskLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label dateCreatedLabel;

    @FXML
    private ImageView taskDeleteButton;

    @FXML
    private ImageView taskCompleteButton;

    private FXMLLoader fxmlLoader;

    private DatabaseHandler dbHandler;

    @FXML
    void initialize() throws SQLException
    {

    }

    @Override
    public void updateItem(Task myTask, boolean empty)
    {
        // Initialise variables
        dbHandler = new DatabaseHandler();

        super.updateItem(myTask, empty);

        if(empty || myTask == null)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            // Check that the cell.FXML is loaded
            if(fxmlLoader == null) // If not
            {
                // Load the cell.fxml file
                fxmlLoader = new FXMLLoader(getClass().getResource("/view/cell.fxml"));
                fxmlLoader.setController(this);

                try
                {
                    fxmlLoader.load();
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }

            // Set the values for the various labels
            taskLabel.setText(myTask.getTask());
            dateCreatedLabel.setText(myTask.getDateCreated().toString());
            descriptionLabel.setText(myTask.getDescription());

            int taskId = myTask.getTaskId();

            taskCompleteButton.setOnMouseClicked((event) ->
            {
                dbHandler.deleteTask(Main.userid, taskId); // Remove from the Database
                getListView().getItems().remove(getItem()); // Remove from the list view
            });

            /*
            // If the update button is clicked
            taskUpdateButton.setOnMouseClicked((event) ->
            {
                // Create new FXML loader
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/updateTaskForm.fxml")); // Set the location of the FXML file

                // Load the FXML
                try
                {
                    loader.load();
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }

                // Update the Stage
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                // Update the fields in the UpdateTaskForm FXML file
                UpdateTaskController updateTaskController = loader.getController();
                updateTaskController.setUpdateTaskField(myTask.getTask());
                updateTaskController.setUpdateDescriptionField(myTask.getDescription());

                // When the updateTaskButton is clicked
                updateTaskController.updateTaskButton.setOnMouseClicked((event1) ->
                {
                    Calendar calendar = Calendar.getInstance(); // Create new Calendar instance

                    Timestamp timestamp = new Timestamp(calendar.getTimeInMillis()); // Set the time

                    // Create new task
                    Task updatedTask = new Task(updateTaskController.getUpdateTaskField(), timestamp, updateTaskController.getUpdateDescriptionField(), Main.userid);
                    updatedTask.setTaskId(myTask.getTaskId());

                    // Update the task in the database
                    dbHandler.updateTask(updatedTask);

                    // Need to close stuff here!
                    stage.close();
                });

                stage.show();

            });
             */

            // Delete button clicked
            taskDeleteButton.setOnMouseClicked((event) ->
            {
                dbHandler.deleteTask(Main.userid, taskId); // Remove from the Database
                getListView().getItems().remove(getItem()); // Remove from the list view
            });

            setText(null);
            setGraphic(rootAnchorPane);
        }

    }

}

