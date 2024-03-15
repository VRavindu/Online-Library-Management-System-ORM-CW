package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.dto.UserDto;
import lk.ijse.projection.BookIdWithTitle;
import lk.ijse.projection.GetAllTitles;
import lk.ijse.service.*;
import lk.ijse.util.Ui;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BorrowBooksFormController {
    @FXML
    private ComboBox<?> cmbSearchBooks;

    @FXML
    private ImageView imgBook;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblCount;

    @FXML
    private Label lblGenre;

    @FXML
    private Label lblIsbn;

    @FXML
    private Label lblName;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblPublicationDate;

    @FXML
    private Label lblTitle;

    @FXML
    private Label nameAuthor;

    @FXML
    private Label nameDate;

    @FXML
    private Label nameGenre;

    @FXML
    private Label nameISBN;

    @FXML
    private Label nameOrder;

    @FXML
    private Label nameRDate;

    @FXML
    private Label nameTItle;

    @FXML
    private DatePicker returnDate;

    @FXML
    private TextField txtSearch;

    UserDto userDto;


    private ObservableList obList = FXCollections.observableArrayList();

    UserService userService = (UserService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.USER);
    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    OrderService orderService = (OrderService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ORDER);
    PlaceOrderService placeOrderService = (PlaceOrderService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PLACE_ORDER);
    static long userId;

    public void initialize(){
        loadIdsAndTitles();
        setName(LoginFormContoller.username);
        searchBooks();
        loadIdsAndTitles();
        setOrderId();
        //setBookCount();
    }

    private void setBookCount() {
        System.out.println(orderService.getBookCount(userId));
        //lblCount.setText(String.valueOf(orderService.getBookCount(userId)));
    }

    private void setOrderId() {
        if (orderService.getOrderId() != -1){
            lblOrderId.setText(String.valueOf(orderService.getOrderId()));
        }else {
            lblOrderId.setText("1");
        }
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

    private void setName(String username) {
        userDto = userService.getUserUsingUsername(username);
        userId = userDto.getId();
        lblName.setText(userDto.getName().getFirstName()+" "+userDto.getName().getLastName());
    }

    public void cmbSearchBooksOnAction(ActionEvent actionEvent) {
        setValuesToFields(bookService.getBooks(split(String.valueOf(cmbSearchBooks.getValue()))));
        visibleTitles();
    }

    private void visibleTitles() {
        nameTItle.setVisible(true);
        nameAuthor.setVisible(true);
        nameGenre.setVisible(true);
        nameDate.setVisible(true);
        nameISBN.setVisible(true);
        nameRDate.setVisible(true);
        nameOrder.setVisible(true);
    }

    public long split(String value){
        String[] parts = value.split(" ");
        return Long.parseLong(parts[0]);
    }

    public void setValuesToFields(BookDto bookDto){
        lblTitle.setText(bookDto.getTitle());
        lblAuthor.setText(bookDto.getAuthor());
        lblGenre.setText(bookDto.getGenre());
        lblPublicationDate.setText(String.valueOf(bookDto.getDate()));
        lblIsbn.setText(String.valueOf(bookDto.getId()));
    }

    public void loadIdsAndTitles(){
        List<BookIdWithTitle> idsAndTitles = bookService.getIdsAndTitles();
        ObservableList obList = FXCollections.observableArrayList();
        for (BookIdWithTitle bookIdsAndTitles : idsAndTitles){
            obList.add(bookIdsAndTitles);
        }
        cmbSearchBooks.setItems(obList);
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        new Ui().setUi("UserDashboardForm" , actionEvent);
    }

    public void btnBuyOnAction(ActionEvent actionEvent) throws IOException {
        PlaceOrderDto placeOrderDto = new PlaceOrderDto(
                Long.parseLong(lblOrderId.getText()),
                userDto.getId(),
                Long.parseLong(lblIsbn.getText()),
                returnDate.getValue()
        );
        if (placeOrderService.placeOrder(placeOrderDto)){
            new Alert(Alert.AlertType.CONFIRMATION, "Successful").show();
            new Ui().setUi("BorrowBooksForm" ,actionEvent);
        }else {
            new Alert(Alert.AlertType.CONFIRMATION, "Not Successful").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String searchText = txtSearch.getText();
        BookDto searchedBook = bookService.getIdByTitle(searchText);
        visibleTitles();
        setValuesToFields(searchedBook);
    }
}
