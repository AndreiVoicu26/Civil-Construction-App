package cca.controllers;

import cca.DBUtils;
import cca.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountInfoController extends Controller implements Initializable {

    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_phone;
    @FXML
    private TextField tf_address;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_role;

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
        tf_username.setText(username);
        tf_role.setText(role);
        tf_name.setText(user.getName());
        tf_email.setText(user.getEmail());
        tf_phone.setText(user.getPhone());
        tf_address.setText(user.getAddress());
    }
}
