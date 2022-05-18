package cca.controllers;

import cca.Announcement;
import cca.Card;
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
    private Button button_promote;

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
    private ChoiceBox<String> choice_service;

    @FXML
    private CheckBox checkbox_promoted;

    @FXML
    private Label label_custom;

    private Announcement ad;
    private int promoted = 0;
    private String fxmlSource = "announcement.fxml";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        label_custom.setVisible(false);
        tf_service.setVisible(false);

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
                DBUtils.changeScene(event, "home-contractant.fxml", "Home", username, role);
            }
        });

        choice_service.getItems().addAll("Electrical Installation", "Sanitary Installation", "Roof Construction", "Foundation Casting", "Bricklaying", "Yard Paving", "Other option");

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

                if(!tf_title.getText().trim().isEmpty() && !tf_description.getText().trim().isEmpty() && !tf_location.getText().trim().isEmpty() && !tf_payment.getText().trim().isEmpty()
                        && choice_service.getValue() != null && (!choice_service.getValue().equals("Other option") || (choice_service.getValue().equals("Other option") && !tf_service.getText().trim().isEmpty()))) {
                    if (choice_service.getValue().equals("Other option")) {
                        ad = new Announcement(tf_title.getText(), tf_service.getText(), tf_description.getText(), tf_location.getText(), tf_payment.getText(), 0, promoted);
                    } else {
                        ad = new Announcement(tf_title.getText(), choice_service.getValue(), tf_description.getText(), tf_location.getText(), tf_payment.getText(), 0, promoted);
                    }
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Are you sure you want to publish it?");
                    alert.showAndWait();
                    if(alert.getResult() == ButtonType.OK) {
                        DBUtils.addAnnouncement(event, username, role, ad);

                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setHeaderText("Congratulations!Your announcement was published.");
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

        button_promote.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(!tf_title.getText().trim().isEmpty() && !tf_description.getText().trim().isEmpty() && !tf_location.getText().trim().isEmpty() && !tf_payment.getText().trim().isEmpty()
                        && choice_service.getValue() != null && (!choice_service.getValue().equals("Other option") || (choice_service.getValue().equals("Other option") && !tf_service.getText().trim().isEmpty()))) {
                    if (choice_service.getValue().equals("Other option")) {
                        ad = new Announcement(tf_title.getText(), tf_service.getText(), tf_description.getText(), tf_location.getText(), tf_payment.getText(), 0, promoted);
                    } else {
                        ad = new Announcement(tf_title.getText(), choice_service.getValue(), tf_description.getText(), tf_location.getText(), tf_payment.getText(), 0, promoted);
                    }
                    DBUtils.changeScene4(event, "promote-ad.fxml", "Promote Announcement", username, role, ad, fxmlSource);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("All fields are required!");
                    alert.show();
                }

            }
        });

    }

    public void getPromotion() {
        promoted = 1;
        checkbox_promoted.setSelected(true);

    }

    public void getAnnouncement(Announcement ad) {
        this.ad = ad;
        int ok = 0;
        tf_title.setText(ad.getTitle());
        for(String current: choice_service.getItems()) {
            if(ad.getService().equals(current)) {
                choice_service.getSelectionModel().select(current);
                ok = 1;
            }
        }
        if(ok == 0) {
            choice_service.getSelectionModel().select("Other option");
            label_custom.setVisible(true);
            tf_service.setVisible(true);
            tf_service.setText(ad.getService());
        }
        tf_description.setText(ad.getDescription());
        tf_location.setText(ad.getLocation());
        tf_payment.setText(ad.getPayment());
    }



}
