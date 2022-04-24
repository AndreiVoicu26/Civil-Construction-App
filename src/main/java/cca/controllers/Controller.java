package cca.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label label_username;

    String username = null;
    String role = null;

    public void setUserInformation(String username, String role) {
        label_username.setText("You are logged in as: " + username + " (" + role + ")");
    }

    public void saveUserInformation(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
