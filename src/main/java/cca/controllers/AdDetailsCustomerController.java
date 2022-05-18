package cca.controllers;

import cca.Announcement;
import cca.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdDetailsCustomerController extends Controller implements Initializable {

    @FXML
    private Label label_title;
    @FXML
    private Label label_service;
    @FXML
    private Label label_description;
    @FXML
    private Label label_location;
    @FXML
    private Label label_payment;

    @FXML
    private Button button_back;
    @FXML
    private Button button_logout;

    private Announcement ad;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Are you sure you want to log out?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    DBUtils.changeScene(event, "log-in.fxml", "Log In", null, null);
                } else {
                    alert.close();
                }
            }
        });

        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.takeAdsCustomer(event, "ads-list-customer.fxml", "Announcements List", username, role);
            }
        });
    }

    public void displayAnnouncement(Announcement ad) {
        this.ad = ad;
        label_title.setText(ad.getTitle());
        label_service.setText(ad.getService());
        label_description.setText(ad.getDescription());
        label_location.setText(ad.getLocation());
        label_payment.setText(ad.getPayment());
    }
}