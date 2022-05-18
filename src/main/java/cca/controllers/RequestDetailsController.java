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

public class RequestDetailsController extends Controller implements Initializable {

    @FXML
    private Label label_request;
    @FXML
    private Label label_response;

    @FXML
    private TextArea tf_request;
    @FXML
    private TextArea tf_response;

    @FXML
    private Button button_logout;
    @FXML
    private Button button_back;

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
                DBUtils.takeRequests(event, "requests-list.fxml", "Requests", username, role);
            }
        });
    }

    public void getRequest(Request request) {
        this.request = request;
        label_request.setText("Request for " + request.getContractant().getName());
        tf_request.setText(request.getRequest());
        label_response.setText("Response from " + request.getContractant().getName());
        if(request.getResponse() == null) {
            tf_response.setText("No response");
        } else {
            tf_response.setText(request.getResponse());
        }
    }
}