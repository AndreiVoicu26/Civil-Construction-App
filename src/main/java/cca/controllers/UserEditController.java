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
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserEditController extends Controller implements Initializable {

    @FXML
    private Button button_back;
    @FXML
    private Button button_logout;
    @FXML
    private Button button_save;

    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_phone;
    @FXML
    private TextField tf_address;

    User user;

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
                DBUtils.changeScene8(event, "account-info.fxml", "Info", username, role, user);
            }
        });

        button_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_name.getText().trim().isEmpty() && !tf_email.getText().trim().isEmpty() && !tf_phone.getText().trim().isEmpty() && !tf_address.getText().trim().isEmpty()) {
                    user = new User(tf_name.getText(), tf_email.getText(), tf_phone.getText(), tf_address.getText());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Are you sure you want to save it?");
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        DBUtils.updateUser(event, username, role, user);

                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setHeaderText("Congratulations!Your contact information were updated.");
                        alert2.show();
                    } else {
                        alert.close();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("All fields are required!");
                    alert.show();
                }
            }
        });
    }

    public void getUser(User user) {
        this.user = user;
        tf_name.setText(user.getName());
        tf_email.setText(user.getEmail());
        tf_phone.setText(user.getPhone());
        tf_address.setText(user.getAddress());
    }

}
