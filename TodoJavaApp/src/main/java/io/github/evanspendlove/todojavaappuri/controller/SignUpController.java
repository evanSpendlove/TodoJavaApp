package io.github.evanspendlove.todojavaappuri.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import io.github.evanspendlove.todojavaappuri.Main;
import io.github.evanspendlove.todojavaappuri.animation.Shaker;
import io.github.evanspendlove.todojavaappuri.database.DatabaseHandler;
import io.github.evanspendlove.todojavaappuri.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

// File Complete so far

public class SignUpController
{
    @FXML
    private JFXTextField SignUpFirstName;

    @FXML
    private JFXTextField SignUpSurname;

    @FXML
    private JFXTextField SignUpUsername;

    @FXML
    private JFXCheckBox signUpCheckBoxFemale;

    @FXML
    private JFXCheckBox signUpCheckBoxMale;

    @FXML
    private JFXCheckBox signUpCheckBoxOther;

    @FXML
    private JFXPasswordField SignUpPassword;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private ImageView signUpBackButton;

    @FXML
    void initialize()
    {
        signUpButton.setOnAction((ActionEvent event) ->
        {
            if(createUser())
            {
                // Now lead to load the login.FXML page
                loadLoginScreen();
            }
        });

        signUpBackButton.setOnMouseClicked((event) ->
        {
            loadLoginScreen();
        });
    }

    private boolean createUser()
    {
        DatabaseHandler dbHandler = new DatabaseHandler(); // New Database handler

        String fname = SignUpFirstName.getText();
        String sname = SignUpSurname.getText();
        String uname = SignUpUsername.getText();
        String pwd = SignUpPassword.getText();
        String gender = new String();

        if(signUpCheckBoxFemale.isSelected())
        {
            gender = "Female";
        }
        else if(signUpCheckBoxOther.isSelected())
        {
            gender = "Other";
        }
        else if(signUpCheckBoxMale.isSelected())
        {
            gender = "Male";
        }

        // TODO: Add error checking here to ensure all fields are filled and at most one checkbox is ticked
        // Can you link checkboxes?

        if(validateUserDetails())
        {
            User newUser = new User(fname, sname, uname, pwd, gender);

            dbHandler.signUpUser(newUser);

            return true;
        }
        else
        {
            return false;
        }

    }

    private boolean validateUserDetails()
    {
        boolean allValid = true;

        if(!shakeInvalidField(SignUpFirstName, SignUpFirstName.getText()))
        {
            allValid = false;
        }
        if(!shakeInvalidField(SignUpSurname, SignUpSurname.getText()))
        {
            allValid = false;
        }
        if(!shakeInvalidField(SignUpUsername, SignUpUsername.getText()))
        {
            allValid = false;
        }
        if(!shakeInvalidField(SignUpPassword, SignUpPassword.getText()))
        {
            allValid = false;
        }
        if(!validateCheckboxInput())
        {
            allValid = false;
        }

        System.out.println(allValid);

        return allValid;
    }

    private boolean shakeInvalidField(Node node, String text)
    {
        System.out.println(text);

        if(text.equals("")) // If the text is empty
        {
            System.out.println("Empty String");
            Shaker nodeShaker = new Shaker(node); // Create new Shaker
            nodeShaker.shake(); //  Shake the shaker

            return false; // This is not valid input
        }

        return true; // This is valid input
    }

    private boolean validateCheckboxInput()
    {
        int counter = 0;

        if(signUpCheckBoxMale.isSelected())
        {
            counter++;
        }
        if(signUpCheckBoxOther.isSelected())
        {
            counter++;
        }
        if(signUpCheckBoxFemale.isSelected())
        {
            counter++;
        }

        if(counter != 1) // If no boxes selected, or more than 1 selected, shake boxes.
        {
            // Create new Shakers
            Shaker maleShaker = new Shaker(signUpCheckBoxMale);
            Shaker femaleShaker = new Shaker(signUpCheckBoxFemale);
            Shaker otherShaker = new Shaker(signUpCheckBoxOther);

            // Shake all shakers
            maleShaker.shake();
            femaleShaker.shake();
            otherShaker.shake();

            // Reset boxes
            signUpCheckBoxMale.setSelected(false);
            signUpCheckBoxFemale.setSelected(false);
            signUpCheckBoxOther.setSelected(false);

            return false; // Invalid input
        }

        return true; // Valid input
    }

    private void loadLoginScreen()
    {
        signUpButton.getScene().getWindow().hide(); // Hide the current window

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
