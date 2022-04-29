package cca.controllers;

import cca.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button button_signup;
    @FXML
    private Button button_log_in;

    @FXML
    private RadioButton rb_customer;
    @FXML
    private RadioButton rb_contractant;

    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;
    @FXML
    private TextField tf_fullname;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_phone;
    @FXML
    private TextField tf_address;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_customer.setToggleGroup(toggleGroup);
        rb_contractant.setToggleGroup(toggleGroup);

        rb_customer.setSelected(true);

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty() && !tf_fullname.getText().trim().isEmpty()
                   && !tf_email.getText().trim().isEmpty() && !tf_phone.getText().trim().isEmpty() && !tf_address.getText().trim().isEmpty()) {
                    DBUtils.signUpUser(event, tf_username.getText(), tf_password.getText(), toggleName, tf_fullname.getText(), tf_email.getText(), tf_phone.getText(), tf_address.getText());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("All fields are required!");
                    alert.show();
                }
            }
        });

        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                DBUtils.changeScene(event, "log-in.fxml", "Login", null, null);
            }
        });

    }
}
