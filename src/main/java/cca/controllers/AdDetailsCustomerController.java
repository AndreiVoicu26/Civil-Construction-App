package cca.controllers;

import cca.Announcement;
import cca.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AdDetailsCustomerController extends Controller implements Initializable {

    @FXML
    private TextField tf_title;
    @FXML
    private TextField tf_service;
    @FXML
    private TextArea tf_description;
    @FXML
    private TextField tf_location;
    @FXML
    private TextField tf_payment;

    @FXML
    private Button button_back;
    @FXML
    private Button button_logout;
    @FXML
    private Button button_contractant;

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

        button_contractant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.takeContractantInfo(event, "contractant-details.fxml", "Contractant Details", username, role, ad);
            }
        });
    }

    public void displayAnnouncement(Announcement ad) {
        this.ad = ad;
        tf_title.setText(ad.getTitle());
        tf_service.setText(ad.getService());
        tf_description.setText(ad.getDescription());
        tf_location.setText(ad.getLocation());
        tf_payment.setText(ad.getPayment());
    }
}