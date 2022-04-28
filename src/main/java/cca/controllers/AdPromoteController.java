package cca.controllers;

import cca.Announcement;
import cca.Card;
import cca.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdPromoteController extends Controller implements Initializable {

    @FXML
    private Button button_logout;
    @FXML
    private Button button_back;
    @FXML
    private Button button_pay;

    @FXML
    private TextField tf_serie;
    @FXML
    private TextField tf_date;
    @FXML
    private TextField tf_cvc;

    private Card card;
    private Announcement ad;
    private String fxmlFile;

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
                if(fxmlFile.equals("announcement.fxml")) {
                    DBUtils.changeScene5(event, "announcement.fxml", "Add announcement", username, role, ad);

                } else {
                    DBUtils.changeScene6(event, "edit-ad.fxml", "Edit announcement", username, role, ad);

                }


            }
        });

        button_pay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_serie.getText().trim().isEmpty() && !tf_date.getText().trim().isEmpty() && !tf_cvc.getText().trim().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Confirm payment?");
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        card = new Card(tf_serie.getText(), tf_date.getText(), tf_cvc.getText());
                        if(fxmlFile.equals("announcement.fxml")) {
                            DBUtils.addCard(event, "announcement.fxml", "Add announcement", username, role, card, ad);
                        } else
                        if(fxmlFile.equals("edit-ad.fxml")) {
                            DBUtils.addCard2(event, "edit-ad.fxml", "Edit announcement", username, role, card, ad);
                        }

                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setHeaderText("Congratulations!Your announcement is promoted.");
                        alert2.show();
                    } else {
                        alert.close();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("All fields are required!");
                    alert.showAndWait();
                }
            }
        });
    }

    public void getAnnouncement(Announcement ad, String fxmlFile) {
        this.ad = ad;
        this.fxmlFile = fxmlFile;
    }


}
