package lk.ijse.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.dto.UserDto;
import lk.ijse.embeded.NameIdentifier;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;

import java.io.IOException;

public class UserProfileFormController {
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    UserDashboardController userDashboardController = new UserDashboardController();
    public void initialize(){
        setValues();
    }

    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        UserDto userDto = userService.getUser(Long.parseLong(txtUserId.getText()));
        boolean isUpdated = userService.updateUser(setNewValuesToEntity(userDto));

        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION, "User Updated").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Update Failed").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        boolean isDelete = userService.deleteUser(get());

        if (isDelete){
            new Alert(Alert.AlertType.CONFIRMATION, "User Deleted").show();
            userDashboardController.stage.close();
        }else {
            new Alert(Alert.AlertType.ERROR, "User not Deleted").show();
        }
    }

    private UserDto setNewValuesToEntity(UserDto userDto) {
        String [] parts = splitName(txtName.getText());
        userDto.setName(new NameIdentifier(parts[0],parts[1]));
        userDto.setAge(Integer.parseInt(txtAge.getText()));
        userDto.setAddress(txtAddress.getText());
        userDto.setEmail(txtEmail.getText());
        userDto.setUsername(txtUserName.getText());
        return userDto;
    }
    private String[] splitName(String name){
        return name.split(" ");
    }

    public UserDto get(){
        String id = txtUserId.getText();
        UserDto userDto = userService.getUser(Long.valueOf(id));
        return userDto;
    }

    private void setValues() {
        UserDto userDto = userService.getUserUsingUsername(LoginFormContoller.username);
        txtUserId.setText(String.valueOf(userDto.getId()));
        txtUserName.setText(userDto.getUsername());
        txtName.setText(userDto.getName().getFirstName() + " " + userDto.getName().getLastName());
        txtAddress.setText(userDto.getAddress());
        txtAge.setText(String.valueOf(userDto.getAge()));
        txtEmail.setText(userDto.getEmail());
    }
}
