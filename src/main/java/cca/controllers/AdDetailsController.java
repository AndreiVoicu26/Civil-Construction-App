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
                DBUtils.changeScene(event, "log-in.fxml", "Log In", null, null);
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

        /*button_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.deleteAnnouncement(event, "edit-ad.fxml","Edit Announcement", username, role, ad);
            }
        });*/


    }

    public void displayAnnouncement(Announcement ad){
        this.ad = ad;
        label_announcement.setText(ad.toString());
    }
}
