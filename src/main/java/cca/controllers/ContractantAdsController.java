package cca.controllers;

import cca.Announcement;
import cca.DBUtils;
import cca.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ContractantAdsController extends Controller implements Initializable {

    @FXML
    private Button button_back;
    @FXML
    private Button button_logout;
    @FXML
    private Button button_request;

    @FXML
    private Label label_name;

    @FXML
    private ListView<Announcement> announcementListView;

    private ObservableList<Announcement> announcementObservableList = FXCollections.observableArrayList();

    private User user;

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
                DBUtils.takeContractants(event, "contractants-list.fxml", "Contractants List", username, role);
            }
        });

        button_request.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene11(event, "customer-request.fxml", "Send Request", username, role, user);
            }
        });
    }

    public void loadData(ArrayList<Announcement> adsList) {

        announcementObservableList.removeAll();
        Comparator<Announcement> adComparator = Comparator.comparing(Announcement::getPromoted).reversed();
        announcementObservableList.addAll(adsList);
        Collections.sort(announcementObservableList, adComparator);
        SortedList<Announcement> sortedAnnouncements = new SortedList<>(announcementObservableList, adComparator);
        announcementListView.getItems().addAll(announcementObservableList);

        announcementListView.setCellFactory(announcementListView1 -> new ListCell<Announcement>() {
            public void updateItem(Announcement ad, boolean empty) {
                super.updateItem(ad, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(ad.getTitle() + "        " + ((ad.getPromoted() == 1) ? "Promoted" : "") + "\nService: " + ad.getService());
                }
            }

        });

        announcementListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Announcement ad = announcementListView.getSelectionModel().getSelectedItem();
                DBUtils.changeScene9(event, "ad-details-client.fxml", "Announcement Information", username, role, ad, user);
            }
        });
    }

    public void getUser(User user) {
        this.user = user;
    }

    public void setLabel() {
        label_name.setText(user.getName() + "'s Announcements");
    }

    public void setButton_request() {
        button_request.setText("SEND REQUEST FOR " + user.getName());
    }
}