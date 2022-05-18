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

public class CustomerRequestController extends Controller implements Initializable {

    @FXML
    private Button button_logout;
    @FXML
    private Button button_back;
    @FXML
    private Button button_send;

    @FXML
    private TextArea tf_offer;

    @FXML
    private Label label_name;

    private User user;

    private int source;

    private Announcement ad;

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

        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(source == 1) {
                    DBUtils.takeContractantsAds(event, "contractant-ads.fxml","Contractant Announcements",username, role, user);
                } else {
                    DBUtils.takeContractantInfo(event, "contractant-details.fxml", "Contractant Details", username, role, ad);
                }
            }
        });

        button_send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.sendRequest(username, role, user, tf_offer.getText());
            }
        });

    }

    public void getUser(User user) {
        this.user = user;
    }

    public void getAd(Announcement ad) {
        this.ad = ad;
    }

    public void getLabel() {
        label_name.setText("Write your offer and requirements for " + user.getName());
    }

    public void getSource(int source) {
        this.source = source;
    }

}