package cca.controllers;

import cca.Announcement;
import cca.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeContractantController extends Controller implements Initializable {

    @FXML
    private Button button_logout;
    @FXML
    private Button button_announcement;
    @FXML
    private Button button_adslist;
    @FXML
    private Button button_clientslist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "log-in.fxml", "Log In", null, null);
            }
        });

        button_announcement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "announcement.fxml", "Add announcement", username, role);
            }
        });

        button_adslist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.takeAnnouncements(event,"ads-list.fxml","Announcements List", username, role);
            }
        });

        button_clientslist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "clients-list.fxml", "Customers List", username, role);
            }
        });

    }
}
