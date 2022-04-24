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

public class AnnouncementController extends Controller implements Initializable {

    @FXML
    private Button button_back;
    @FXML
    private Button button_publish;
    @FXML
    private Button button_logout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "log-in.fxml", "Log In", null, null);
            }
        });

        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "home-contractant.fxml", "Home", username, role);
            }
        });

        button_publish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "ads-list.fxml", "Announcements List", username, role);
            }
        });

    }
}
