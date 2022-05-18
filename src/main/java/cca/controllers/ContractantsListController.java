package cca.controllers;

import cca.Announcement;
import cca.DBUtils;
import cca.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

public class ContractantsListController extends Controller implements Initializable {

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
    private ListView<User> contractantsListView;

    private ObservableList<User> contractantsObservableList = FXCollections.observableArrayList();

    private ObservableList<User> contractantsObservableList2 = FXCollections.observableArrayList();

    private ArrayList<User> contractantsList;

    private ArrayList<User> contractantsArrayList;

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
                    contractantsListView.getItems().clear();
                    contractantsListView.getItems().addAll(contractantsObservableList.filtered(user -> user.getName().contains(tf_search.getText()) || user.getName().toLowerCase().contains(tf_search.getText())));
                } else {
                    if (combobox_filters.getValue().equals("Location")) {
                        contractantsListView.getItems().clear();
                        contractantsListView.getItems().addAll(contractantsObservableList.filtered(user -> user.getAddress().contains(tf_search.getText())));
                    } else {
                        if (combobox_filters.getValue().equals("Service")) {
                            if(combobox_services.getValue().equals("All")) {
                                contractantsListView.getItems().clear();
                                contractantsListView.getItems().addAll(contractantsObservableList);
                            } else {
                                if(combobox_services.getValue().equals("Other option")) {
                                    contractantsArrayList = DBUtils.takeContractantsWithSpecificService(tf_service.getText());
                                } else {
                                    contractantsArrayList = DBUtils.takeContractantsWithSpecificService(combobox_services.getValue());
                                }
                                contractantsObservableList2.clear();
                                contractantsObservableList2.addAll(contractantsArrayList);
                                contractantsListView.getItems().clear();
                                contractantsListView.getItems().addAll(contractantsObservableList2);
                            }
                        }
                    }
                }

                if(contractantsListView.getItems().stream().count() == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("No contractants found!");
                    alert.show();
                }
            }
        });

        button_clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                contractantsListView.getItems().clear();
                contractantsListView.getItems().addAll(contractantsObservableList);
            }
        });

        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "home-customer.fxml", "Home", username, role);
            }
        });
    }


    public void loadData(ArrayList<User> contractantsList) {
        contractantsObservableList.removeAll();

        contractantsObservableList.addAll(contractantsList);

        contractantsListView.getItems().addAll(contractantsObservableList);

        contractantsListView.setCellFactory(contractantsListView1 -> new ListCell<User>() {
            public void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if(empty) {
                    setText(null);
                } else {
                    setText(user.getName() + "\n" + user.getEmail() + "\n" + user.getPhone() + "\n" + user.getAddress());
                }
            }

        });
        contractantsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                User user = contractantsListView.getSelectionModel().getSelectedItem();
                DBUtils.takeContractantsAds(event, "contractant-ads.fxml","Contractant Announcements",username, role, user);
            }
        });

    }
}