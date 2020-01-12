package io.github.evanspendlove.todojavaappuri.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import io.github.evanspendlove.todojavaappuri.Main;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

// File completed so far

public class AddItemController
{
    @FXML
    private JFXButton logoutButton;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView addButton;

    @FXML
    private Label noTasksYetLabel;

    @FXML
    void initialize()
    {
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (event ->
        {
            // Move these items to the top left
            addButton.relocate(0, 20);
            noTasksYetLabel.relocate(0, 45);

            // Make these items not visible
            addButton.setVisible(false);
            noTasksYetLabel.setVisible(false);

            // Now, load the addItemForm.fxml file into the current view
            try
            {
                // Create new AnchorPane and load the addItemForm.fxml file into it
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/view/addItemForm.fxml"));

                FadeTransition rootTransition = new FadeTransition(Duration.millis(1000), formPane);
                rootTransition.setFromValue(0f);
                rootTransition.setToValue(1f);
                rootTransition.setCycleCount(1);
                rootTransition.setAutoReverse(false);
                rootTransition.play();

                rootAnchorPane.getChildren().setAll(formPane);
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

        }));

        logoutButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (event ->
        {
            logout();
        }));
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
