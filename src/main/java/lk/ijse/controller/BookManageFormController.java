package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.dto.BookDto;
import lk.ijse.projection.BookIdWithTitle;
import lk.ijse.projection.GetAllTitles;
import lk.ijse.service.BookService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.impl.BookServiceImpl;
import lk.ijse.util.Ui;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookManageFormController {
    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBId;

    @FXML
    private DatePicker txtDate;

    @FXML
    private ComboBox txtGenre;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtTitle;

    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize(){
        loadBookGenresToGenreComboBox();
        searchBooks();
    }

    private void searchBooks() {
        List<String> suggestions = new ArrayList<>();
        List<GetAllTitles> allTitles = bookService.getAllTitles();
        for (GetAllTitles titles : allTitles){
            suggestions.add(
                    titles.getTitle()
            );
        }
        AutoCompletionBinding<String> autoCompletionBinding = TextFields.bindAutoCompletion(txtSearch, suggestions);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("AdminDashboardForm", actionEvent);
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        long bId = Integer.parseInt(txtBId.getText());
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = String.valueOf(txtGenre.getValue());
        int qty = Integer.parseInt(txtQty.getText());
        String date = String.valueOf(txtDate.getValue());

        BookDto bookDto = new BookDto();
        bookDto.setId(bId);
        bookDto.setTitle(title);
        bookDto.setAuthor(author);
        bookDto.setGenre(genre);
        bookDto.setQty(qty);
        bookDto.setDate(date);

        Long id = bookService.saveBook(bookDto);
        System.out.println(id);
        clearFields();
        if (id != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Book Saved");
            new Ui().setUi("BookManageForm", actionEvent);
        }else {
            new Alert(Alert.AlertType.ERROR, "Book not Saved").show();
        }
    }

    private void clearFields() {
        txtBId.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtGenre.setValue(null);
        txtQty.clear();
        txtDate.setValue(null);
        txtSearch.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        long bId = Integer.parseInt(txtBId.getText());
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = (String) txtGenre.getValue();
        String date = String.valueOf(txtDate.getValue());

        BookDto existBook = bookService.getBooks(bId);
        existBook.setTitle(title);
        existBook.setAuthor(author);
        existBook.setGenre(genre);
        existBook.setDate(date);

        boolean isUpdated = bookService.updateBook(existBook);

        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION, "Book Updated").show();
            clearFields();
        }else {
            new Alert(Alert.AlertType.ERROR, "Book not Updated").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent)  {
        int bId = Integer.parseInt(txtBId.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you want to Delete ?");
        alert.setContentText("Choose your option.");
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeYes) {
                boolean isDeleted = bookService.deleteBook(bookService.getBooks(bId));
                if (isDeleted){
                    new Alert(Alert.AlertType.CONFIRMATION, "Book Deleted").show();
                    try {
                        new Ui().setUi("BookManageForm", actionEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR, "Book not Deleted").show();
                }
            } else if (response == buttonTypeNo) {
            }
        });
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String searchText = txtSearch.getText();
        BookDto searchedBook = bookService.getIdByTitle(searchText);

        txtBId.setText(String.valueOf(searchedBook.getId()));
        txtTitle.setText(searchedBook.getTitle());
        txtGenre.setValue(searchedBook.getGenre());
        txtAuthor.setText(searchedBook.getAuthor());
        txtQty.setText(String.valueOf(searchedBook.getQty()));
        txtDate.setValue(LocalDate.parse(searchedBook.getDate()));
    }

    public void loadBookGenresToGenreComboBox(){
        String [] genres = {"Biography", "Science", "Fantasy", "Romance", "Mystery", "Poetry", "Children's'", "Cookbooks", "Travel", "Historical-Fiction", "Self-Help"};
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (String genre : genres){
            obList.add(genre);
        }
        txtGenre.setItems(obList);
    }
}
