package cca.controllers;

import cca.Announcement;
import cca.DBUtils;
import cca.Request;
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

public class ClientsListController extends Controller implements Initializable {

    @FXML
    private Button button_back;
    @FXML
    private Button button_logout;

    @FXML
    private ListView<Request> requestListView;

    private ObservableList<Request> requestObservableList = FXCollections.observableArrayList();

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
                DBUtils.changeScene(event, "home-contractant.fxml", "Home", username, role);
            }
        });

    }

    public void loadData(ArrayList<Request> requestsList) {
        requestObservableList.removeAll();
        requestObservableList.addAll(requestsList);
        requestListView.getItems().addAll(requestObservableList);

        requestListView.setCellFactory(announcementListView1 -> new ListCell<Request>() {
            public void updateItem(Request request, boolean empty) {
                super.updateItem(request, empty);
                if(empty) {
                    setText(null);
                } else {
                    setText("Sent from: " + request.getUser().getName() + "\nStatus: " + request.getStatus());
                }
            }

        });
        requestListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Request request = requestListView.getSelectionModel().getSelectedItem();
                DBUtils.changeScene14(event, "request-review.fxml", "Request review", username, role, request);
            }
        });
    }
}