package cca.controllers;

import cca.Announcement;
import cca.DBUtils;
import cca.User;
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

public class ContractantDetailsController extends Controller implements Initializable {

    @FXML
    private Button button_back;
    @FXML
    private Button button_logout;
    @FXML
    private Button button_request;

    @FXML
    private Label label_name;
    @FXML
    private Label label_email;
    @FXML
    private Label label_phone;
    @FXML
    private Label label_address;

    private Announcement ad;

    private User contractant;

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
                DBUtils.changeScene10(event, "ad-details-customer.fxml","Announcement Information",username, role, ad);
            }
        });

    }

    public void getAd(Announcement ad) {
        this.ad = ad;
    }

    public void getContractant(User contractant) {
        this.contractant = contractant;
        label_name.setText("Name: " + contractant.getName());
        label_email.setText("Email: " + contractant.getEmail());
        label_phone.setText("Phone: " + contractant.getPhone());
        label_address.setText("Address: " + contractant.getAddress());
    }

}