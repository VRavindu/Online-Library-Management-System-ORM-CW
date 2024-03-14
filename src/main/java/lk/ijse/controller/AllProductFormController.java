package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.BookDto;
import lk.ijse.service.BookService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.tmList.BookTm;
import lk.ijse.util.Ui;

import java.io.IOException;
import java.util.List;

public class AllProductFormController {

    public TableView tblBooks;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colPublicationDate;
    public TableColumn colGenre;
    public TableColumn colIsbn;
    public TableColumn colOption;
    public TableColumn colStatus;
    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize(){
        setCellValueFactory();
        loadAllBooks();
    }

    private void setCellValueFactory() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("bId"));
        colPublicationDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

    }

    void loadAllBooks(){
        ObservableList obList = FXCollections.observableArrayList();
        List<BookDto> allBooks = bookService.getAllBooks();
        for (BookDto bookDto : allBooks){
            JFXButton purchase = new JFXButton("Purchase");
            purchase.setStyle("-fx-background-color: #5ee65e; -fx-text-fill: white; ");
            purchase.setCursor(Cursor.HAND);
            if (bookDto.getQty()==0){
                purchase.setOpacity(0.3);
            }

            obList.add(new BookTm(
                    bookDto.getTitle(),
                    bookDto.getAuthor(),
                    bookDto.getGenre(),
                    bookDto.getId(),
                    bookDto.getDate(),
                    bookDto.getQty()!=0 ? "Available" : "Not Available",
                    purchase
            ));
        }
        tblBooks.setItems(obList);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("UserDashboardForm", actionEvent);
    }
}
