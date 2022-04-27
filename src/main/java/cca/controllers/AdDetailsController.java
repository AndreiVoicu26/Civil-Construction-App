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

public class AdDetailsController extends Controller implements Initializable {

    @FXML
    private Label label_announcement;

    @FXML
    private Button button_back;
    @FXML
    private Button button_logout;
    @FXML
    private Button button_edit;
    @FXML
    private Button button_delete;

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
                DBUtils.takeAnnouncements(event, "ads-list.fxml","Announcements List",username, role);
            }
        });

        button_edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene3(event, "edit-ad.fxml","Edit Announcement", username, role, ad);
            }
        });

        button_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Are you sure you want to delete it?");
                alert.showAndWait();
                if(alert.getResult() == ButtonType.OK) {
                    DBUtils.deleteAnnouncement(event, username, role, ad);

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setHeaderText("Your announcement was deleted.");
                    alert2.show();
                } else {
                    alert.close();
                }
            }
        });


    }

    public void displayAnnouncement(Announcement ad){
        this.ad = ad;
        label_announcement.setText(ad.toString());
    }
}
