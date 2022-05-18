package cca.controllers;

import cca.DBUtils;
import cca.Request;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestReviewController extends Controller implements Initializable {

    @FXML
    private Label label_request;
    @FXML
    private Label label_response;

    @FXML
    private TextArea tf_request;
    @FXML
    private TextArea tf_response;

    @FXML
    private RadioButton rb_accept;
    @FXML
    private RadioButton rb_reject;

    @FXML
    private Button button_logout;
    @FXML
    private Button button_back;
    @FXML
    private Button button_send;

    private Request request;

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
                DBUtils.takeRequests2(event, "clients-list.fxml", "Customers Requests", username, role);
            }
        });

        ToggleGroup toggleGroup = new ToggleGroup();
        rb_accept.setToggleGroup(toggleGroup);
        rb_reject.setToggleGroup(toggleGroup);

        rb_accept.setSelected(true);

        button_send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Are you sure you want to send response?");
                alert.showAndWait();
                if(alert.getResult() == ButtonType.OK) {
                    DBUtils.updateRequest(event, username, role, request.getUser().getUsername(), tf_response.getText(), toggleName);
                } else {
                    alert.close();
                }
            }
        });

    }

    public void getRequest(Request request) {
        this.request = request;
        label_request.setText("Request from " + request.getUser().getName());
        tf_request.setText(request.getRequest());
        label_response.setText("Write your response for " + request.getUser().getName());
    }
}
