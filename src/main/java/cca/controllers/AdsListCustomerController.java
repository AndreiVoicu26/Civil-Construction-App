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
    private Button button_search;
    @FXML
    private Button button_clear;
    @FXML
    private Button button_back;

    @FXML
    private TextField tf_search;
    @FXML
    private TextField tf_service;

    @FXML
    private ComboBox<String> combobox_filters;

    @FXML
    private ComboBox<String> combobox_services;

    @FXML
    private ListView<Announcement> adsListView;

    private ObservableList<Announcement> adsObservableList = FXCollections.observableArrayList();

    private ObservableList<Announcement> adsObservableList2 = FXCollections.observableArrayList();

    private ArrayList<Announcement> adsList;

    private ArrayList<Announcement> adsArrayList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tf_search.setVisible(false);
        tf_service.setVisible(false);
        combobox_services.setVisible(false);
        button_search.setVisible(false);
        button_clear.setVisible(false);

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

        combobox_filters.getItems().addAll("Name", "Service", "Location");
        combobox_services.getItems().addAll("All", "Electrical Installation", "Sanitary Installation", "Roof Construction", "Foundation Casting", "Bricklaying", "Yard Paving", "Other option");


        combobox_filters.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(combobox_filters.getValue().equals("Service")) {
                    combobox_services.setVisible(true);
                    combobox_services.getSelectionModel().selectFirst();
                } else {
                    tf_search.setVisible(true);
                    tf_search.setPromptText("Search " + combobox_filters.getValue());
                    tf_search.clear();
                    tf_service.setVisible(false);
                    combobox_services.getSelectionModel().selectFirst();
                    combobox_services.setVisible(false);
                }
                button_search.setVisible(true);
                button_clear.setVisible(true);
            }
        });

        combobox_services.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(combobox_services.getValue().equals("Other option")) {
                    tf_service.setVisible(true);
                } else {
                    tf_service.setVisible(false);
                }
            }
        });

        button_search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(combobox_filters.getValue().equals("Name")) {
                    adsListView.getItems().clear();
                    adsListView.getItems().addAll(adsObservableList.filtered(announcement -> announcement.getTitle().contains(tf_search.getText()) || announcement.getTitle().toLowerCase().contains(tf_search.getText())));
                } else {
                    if (combobox_filters.getValue().equals("Location")) {
                        adsListView.getItems().clear();
                        adsListView.getItems().addAll(adsObservableList.filtered(announcement -> announcement.getLocation().contains(tf_search.getText())));
                    } else {
                        if (combobox_filters.getValue().equals("Service")) {
                            if(combobox_services.getValue().equals("All")) {
                                adsListView.getItems().clear();
                                adsListView.getItems().addAll(adsObservableList);
                            } else {
                                if(combobox_services.getValue().equals("Other option")) {
                                    adsArrayList = DBUtils.takeAnnouncementsWithSpecificService(tf_service.getText());
                                } else {
                                    adsArrayList = DBUtils.takeAnnouncementsWithSpecificService(combobox_services.getValue());
                                }
                                adsObservableList2.clear();
                                Comparator<Announcement> adComparator = Comparator.comparing(Announcement::getPromoted).reversed();
                                adsObservableList2.addAll(adsArrayList);
                                Collections.sort(adsObservableList2, adComparator);
                                SortedList<Announcement> sortedAnnouncements = new SortedList<>(adsObservableList2, adComparator);
                                adsListView.getItems().clear();
                                adsListView.getItems().addAll(adsObservableList2);
                            }
                        }
                    }
                }

                if(adsListView.getItems().stream().count() == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("No announcements found!");
                    alert.show();
                }
            }
        });

        button_clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                adsListView.getItems().clear();
                adsListView.getItems().addAll(adsObservableList);
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
        this.adsList = adsList;
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