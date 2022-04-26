package cca.controllers;

import cca.Announcement;
import cca.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AnnouncementController extends Controller implements Initializable {

    @FXML
    private Button button_back;
    @FXML
    private Button button_publish;
    @FXML
    private Button button_logout;

    @FXML
    private TextField tf_title;
    @FXML
    private TextField tf_service;
    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_location;
    @FXML
    private TextField tf_payment;

    @FXML
    private ChoiceBox<String> choice_service;

    @FXML
    private Label label_custom;

    Announcement ad;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        label_custom.setVisible(false);
        tf_service.setVisible(false);

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

        choice_service.getItems().addAll("Service 1", "Service 2", "Service 3", "Other option");

        choice_service.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (choice_service.getValue().equals("Other option")) {
                    label_custom.setVisible(true);
                    tf_service.setVisible(true);
                } else {
                    label_custom.setVisible(false);
                    tf_service.setVisible(false);
                }
            }
        });

        button_publish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (choice_service.getValue().equals("Other option")) {
                    ad = new Announcement(tf_title.getText(), tf_service.getText(), tf_description.getText(), tf_location.getText(), tf_payment.getText());
                } else {
                    ad = new Announcement(tf_title.getText(), choice_service.getValue(), tf_description.getText(), tf_location.getText(), tf_payment.getText());
                }

                if(!tf_title.getText().trim().isEmpty() && !choice_service.getValue().trim().isEmpty() && (!choice_service.getValue().equals("Other option") || (choice_service.getValue().equals("Other option") && !tf_service.getText().trim().isEmpty()))
                        && !tf_description.getText().trim().isEmpty() && !tf_location.getText().trim().isEmpty() && !tf_payment.getText().trim().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Confirm publication");
                    alert.setContentText("Are you sure you want to publish it?");
                    alert.showAndWait();
                    if(alert.getResult() == ButtonType.OK) {
                        DBUtils.addAnnouncement(event, username, role, ad);

                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setHeaderText("Congratulations!");
                        alert2.setContentText("Your announcement was published");
                        alert2.show();
                    } else {
                        alert.close();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("All fields are required!");
                    alert.show();
                }
            }
        });

    }

}
