package cca.controllers;

import cca.Announcement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label label_username;

    protected String username = null;
    protected String role = null;

    public void setUserInformation(String username, String role) {
        label_username.setText("You are logged in as: " + username + " (" + role + ")");
    }

    public void saveUserInformation(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
