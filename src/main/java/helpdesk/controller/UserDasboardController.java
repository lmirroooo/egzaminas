package helpdesk.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class UserDasboardController {

    @FXML
    private Button search;
    @FXML
    private TextField title;
    @FXML
    private Label message;


    public void onSearchAction(ActionEvent actionEvent) {
        String title = this.title.getText();
    }

}
