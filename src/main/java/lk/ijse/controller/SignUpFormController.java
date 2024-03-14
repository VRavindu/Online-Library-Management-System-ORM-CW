package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.dto.UserDto;
import lk.ijse.embeded.NameIdentifier;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;
import lk.ijse.service.impl.UserServiceImpl;
import lk.ijse.util.Ui;

import java.io.IOException;

public class SignUpFormController {
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtLName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);
    public void txtLoginMouseClicked(MouseEvent mouseEvent) throws IOException {
        new Ui().setUi("LoginForm", mouseEvent);
    }

    public void btnSignUpOnAction(ActionEvent actionEvent) throws IOException {
        String fName = txtFName.getText();
        String lName = txtLName.getText();
        String address = txtAddress.getText();
        int age = Integer.parseInt(txtAge.getText());
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        String confirmPass = txtConfirmPassword.getText();
        String email = txtEmail.getText();

        if (password.equals(confirmPass)) {
            UserDto userDto = getUser(fName, lName, address, age, email, userName, password);
            Long id = userService.saveUser(userDto);
            System.out.println(id);
            new Alert(Alert.AlertType.CONFIRMATION, "User Registered Successfully").show();
            new Ui().setUi("LoginForm", actionEvent);
        }else {
            new Alert(Alert.AlertType.WARNING, "Password not Match").show();
        }

    }

    private UserDto getUser(String fName, String lName, String address, int age, String email, String userName, String password) {
        UserDto user = new UserDto();
        NameIdentifier nameIdentifier = new NameIdentifier();

        nameIdentifier.setFirstName(fName);
        nameIdentifier.setLastName(lName);
        user.setName(nameIdentifier);
        user.setAddress(address);
        user.setAge(age);
        user.setEmail(email);
        user.setUsername(userName);
        user.setPassword(password);
        return user;
    }
}
