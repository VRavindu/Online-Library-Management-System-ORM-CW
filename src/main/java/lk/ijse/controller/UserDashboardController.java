package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.util.Ui;

import java.io.IOException;

public class UserDashboardController {

    static Stage stage = new Stage();
    public void btnProductOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("AllProductForm", actionEvent);
    }

    public void btnProfileOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UserProfileForm.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("LoginForm", actionEvent);
    }

    public void btnBorrowBooksOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("BorrowBooksForm", actionEvent);
    }
}
