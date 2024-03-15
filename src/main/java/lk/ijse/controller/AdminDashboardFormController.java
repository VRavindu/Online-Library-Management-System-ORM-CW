package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.util.Ui;

import java.io.IOException;

public class AdminDashboardFormController {

    public void btnManageBooksOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("BookManageForm",actionEvent);
    }

    public void btnManageBranchOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("BranchManageForm", actionEvent);
    }

    public void btnManageUserOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UserManageForm.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("LoginForm" , actionEvent);
    }
}
