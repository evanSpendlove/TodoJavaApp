package io.github.evanspendlove.todojavaappuri.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

import io.github.evanspendlove.todojavaappuri.Main;
import io.github.evanspendlove.todojavaappuri.database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UpdateTaskController
{
    @FXML
    private JFXTextField updateTaskField;

    @FXML
    private JFXTextField updateDescriptionField;

    @FXML
    public JFXButton updateTaskButton;

    // Getters and Setters

    public String getUpdateTaskField()
    {
        return updateTaskField.getText().trim();
    }

    public void setUpdateTaskField(String task) {
        this.updateTaskField.setText(task);
    }

    public String getUpdateDescriptionField() {
        return updateDescriptionField.getText().trim();
    }

    public void setUpdateDescriptionField(String description) {
        this.updateDescriptionField.setText(description);
    }

    @FXML
    void initialize()
    {
        // Empty
    }
}

