package helpdesk.controller;

import helpdesk.model.User;
import helpdesk.model.UserDAO;
import helpdesk.utils.Password;
import helpdesk.utils.Validation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    javafx.scene.control.TextField username;
    @FXML
    PasswordField password1;
    @FXML
    PasswordField password2;
    @FXML
    javafx.scene.control.TextField email;
    @FXML
    javafx.scene.control.Label error_message;
    @FXML
    javafx.scene.control.Button button_cancel;
    @FXML
    Button button_register;

    public void onCancelButtonClick(ActionEvent actionEvent) {
        try {
            // Sukuriamas dashboard langas
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/login.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 550, 450));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRegisterButtonClick(ActionEvent actionEvent) {
        if (!Validation.isValidUsername(username.getText())) {
            error_message.setText("Username is not valid");
        } else if (!password1.getText().equals(password2.getText())) {
            error_message.setText("Password does not match");
        } else if (!Validation.isValidPassword(password1.getText())) {
            error_message.setText("Password is not valid");
        } else if (!Validation.isValidEmail(email.getText())) {
            error_message.setText("Email is not valid");
        } else {
            try {
                UserDAO.create(new User(username.getText(), Password.hashPassword(password1.getText()), email.getText(), false));
                onCancelButtonClick(actionEvent);
            } catch (SQLException throwables) {
                error_message.setText("Username or email is already taken");
                throwables.printStackTrace();
            }
        }
    }
}
