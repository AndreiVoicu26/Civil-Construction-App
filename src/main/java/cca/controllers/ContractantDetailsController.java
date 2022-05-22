package cca.controllers;

import cca.Announcement;
import cca.DBUtils;
import cca.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private TextField tf_name;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_phone;
    @FXML
    private TextField tf_address;

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

        button_request.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene12(event, "customer-request.fxml", "Send Request", username, role, contractant, ad);
            }
        });

    }

    public void getAd(Announcement ad) {
        this.ad = ad;
    }

    public void getContractant(User contractant) {
        this.contractant = contractant;
        tf_name.setText(contractant.getName());
        tf_email.setText(contractant.getEmail());
        tf_phone.setText(contractant.getPhone());
        tf_address.setText(contractant.getAddress());
    }

    public void setButton_request() {
        button_request.setText("SEND REQUEST FOR " + contractant.getName());
    };
}