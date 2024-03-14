package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;
import lk.ijse.util.Ui;

import java.io.IOException;

public class LoginFormContoller {

    public static String username;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;
    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);
    public void initialize() throws IOException {
        validate(txtUserName, "\\d*");
    }

    private void validate(TextField name, String regex) {
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                name.setStyle("-fx-border-color: #ff0000; -fx-border-width: 2px;");
            } else if (!newValue.matches(regex)) { // Ensure only numeric input
                name.setStyle("-fx-border-color: #ff0000; -fx-border-width: 2px;");
            }else {
                name.setStyle("");
            }
        });
    }

    public void txtForgotPwOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void txtSignUpOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        new Ui().setUi("SignUpForm", mouseEvent);
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        username = txtUserName.getText();
        String password = txtPassword.getText();

        String userPass = checkUserPass(username);
        String adminPass = checkAdminPass("admin");

        if (username.equalsIgnoreCase("admin")){
            if (password.equals(adminPass)){
                new Ui().setUi("AdminDashboardForm", actionEvent);
            }else {
                new Alert(Alert.AlertType.ERROR, "Wrong Password").show();
            }

        }else if (password.equals(userPass)){
            new Ui().setUi("UserDashboardForm", actionEvent);
        }else {
            new Alert(Alert.AlertType.ERROR, "Wrong Password").show();
        }

    }

    private String checkAdminPass(String adminName) {
        return userService.getAdminPass(adminName);
    }

    private String checkUserPass(String username) {
        String password = userService.getUserPass(username);
        return password;
    }

    public void txtUsernameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        btnLoginOnAction(actionEvent);
    }
}
