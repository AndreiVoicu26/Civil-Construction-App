package cca.controllers;

import cca.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button button_logout;

    //@FXML
    //private Label label_welcome;

    @FXML
    private Label label_username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "log-in.fxml", "Log In", null, null);

            }
        });

    }

    public void setUserInformation(String username, String role) {
        label_username.setText("You are logged in as: " + username + " (" + role + ")");
    }
}