package cca.controllers;

import cca.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeCustomerController extends Controller implements Initializable {

    @FXML
    private Button button_logout;
    @FXML
    private Button button_announcements;
    @FXML
    private Button button_contractants;
    @FXML
    private Button button_requests;
    @FXML
    private Button button_info;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Are you sure you want to log out?");
                alert.showAndWait();
                if(alert.getResult() == ButtonType.OK) {
                    DBUtils.changeScene(event, "log-in.fxml", "Log In", null, null);
                } else {
                    alert.close();
                }
            }
        });

        button_contractants.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.takeContractants(event, "contractants-list.fxml", "Contractants List", username, role);
            }
        });
        button_announcements.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.takeAdsCustomer(event, "ads-list-customer.fxml", "Announcements List", username, role);
            }
        });
        button_requests.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.takeRequests(event, "requests-list.fxml", "Requests", username, role);
            }
        });

        button_info.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.takeInfo(event, "account-info.fxml", "Info", username, role);
            }
        });

    }
}