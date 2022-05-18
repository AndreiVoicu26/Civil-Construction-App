package cca.controllers;

import cca.DBUtils;
import cca.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountInfoController extends Controller implements Initializable {

    @FXML
    private Label label_name;
    @FXML
    private Label label_email;
    @FXML
    private Label label_phone;
    @FXML
    private Label label_address;
    @FXML
    private Label label_username;
    @FXML
    private Label label_role;

    @FXML
    private Button button_back;
    @FXML
    private Button button_logout;
    @FXML
    private Button button_edit;
    @FXML
    private Button button_password;

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
                if(role.equals("Contractant")) {
                    DBUtils.changeScene(event, "home-contractant.fxml", "Home", username, role);
                } else {
                    DBUtils.changeScene(event, "home-customer.fxml", "Home", username, role);
                }

            }
        });

        button_edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene7(event, "edit-user.fxml","Edit User", username, role, user);
            }
        });

        button_password.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "change-password.fxml", "Change password", username, role);
            }
        });

    }

    public void getUser(User user) {
        this.user = user;
        label_username.setText("Username: " + username);
        label_role.setText("Role: " + role);
        label_name.setText("Name: " + user.getName());
        label_email.setText("Email: " + user.getEmail());
        label_phone.setText("Phone: " + user.getPhone());
        label_address.setText("Address: " + user.getAddress());
    }
}
