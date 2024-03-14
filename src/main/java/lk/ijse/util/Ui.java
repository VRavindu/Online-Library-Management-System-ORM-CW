package lk.ijse.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Ui {
    public void setUi(String location, ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        openUi(location, node.getScene().getWindow());
    }

    public void setUi(String location, MouseEvent mouseEvent) throws IOException {
        Node node = (Node) mouseEvent.getSource();
        openUi(location, node.getScene().getWindow());
    }

    private void openUi(String location, Window window) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/" + location + ".fxml"));
        Parent parent = loader.load();

        Stage stage = (Stage) window;
        stage.setScene(new Scene(parent));
        stage.show();
    }

}
