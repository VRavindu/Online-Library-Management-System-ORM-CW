package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import lk.ijse.dto.UserDto;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;
import lk.ijse.util.Ui;

import java.io.IOException;
import java.util.List;

public class UserManageFormController {
    @FXML
    private JFXComboBox<String> cmbUserId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUserName;
    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);

    public void initialize(){
        loadUserId();
    }

    private void loadUserId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<UserDto> allUser = userService.getAllUser();

        for (UserDto dto : allUser){
            obList.add(String.valueOf(dto.getId()));
        }
        cmbUserId.setItems(obList);
    }

    public void cmbUserIdOnAction(ActionEvent actionEvent) {
        setValuesToFields(userService.getUser(Long.parseLong(cmbUserId.getValue())));
    }

    private void setValuesToFields(UserDto userDto) {
        txtUserName.setText(userDto.getUsername());
        txtName.setText(userDto.getName().getFirstName()+ " " +userDto.getName().getLastName());
        txtAddress.setText(userDto.getAddress());
        txtAge.setText(String.valueOf(userDto.getAge()));
        txtEmail.setText(userDto.getEmail());
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you want to Delete ?");
        alert.setContentText("Choose your option.");
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeYes) {
                boolean deleted = userService.deleteUser(userService.getUser(Long.parseLong(cmbUserId.getValue())));
                if (deleted){
                    new Alert(Alert.AlertType.CONFIRMATION, "User Deleted Successfully").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Something went Wrong").show();
                }
            } else if (response == buttonTypeNo) {
            }
        });
    }
}
