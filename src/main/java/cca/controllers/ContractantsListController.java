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
    private ListView<User> contractantsListView;

    private ObservableList<User> contractantsObservableList = FXCollections.observableArrayList();

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