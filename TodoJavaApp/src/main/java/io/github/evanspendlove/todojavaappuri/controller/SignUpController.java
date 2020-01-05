package io.github.evanspendlove.todojavaappuri.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class SignUpController
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    void initialize()
    {

    }
}
