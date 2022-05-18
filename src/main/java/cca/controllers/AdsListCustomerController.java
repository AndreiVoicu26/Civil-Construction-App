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

public class AdsListCustomerController extends Controller implements Initializable {

    @FXML
    private Button button_logout;
    @FXML
    private Button button_back;

    @FXML
    private ListView<Announcement> adsListView;

    private ObservableList<Announcement> adsObservableList = FXCollections.observableArrayList();

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
                DBUtils.changeScene(event, "home-customer.fxml", "Home", username, role);
            }
        });
    }

    public void loadData(ArrayList<Announcement> adsList) {
        adsObservableList.removeAll();
        Comparator<Announcement> adComparator = Comparator.comparing(Announcement::getPromoted).reversed();
        adsObservableList.addAll(adsList);
        Collections.sort(adsObservableList, adComparator);
        SortedList<Announcement> sortedAnnouncements = new SortedList<>(adsObservableList, adComparator);
        adsListView.getItems().addAll(adsObservableList);

        adsListView.setCellFactory(announcementListView1 -> new ListCell<Announcement>() {
            public void updateItem(Announcement ad, boolean empty) {
                super.updateItem(ad, empty);
                if(empty) {
                    setText(null);
                } else {
                    setText(ad.getTitle() + "        " + ((ad.getPromoted() == 1)? "Promoted":"") + "\nService: " + ad.getService());
                }
            }

        });
        adsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Announcement ad = adsListView.getSelectionModel().getSelectedItem();
                DBUtils.changeScene10(event, "ad-details-customer.fxml","Announcement Information",username, role, ad);
            }
        });
    }
}