package lk.ijse.service;

import lk.ijse.dto.BookDto;
import lk.ijse.projection.BookIdWithTitle;
import lk.ijse.projection.GetAllTitles;

import java.util.List;

public interface BookService extends SuperService {
    Long saveBook(BookDto bookDto);
    BookDto getBooks(long id);

    boolean updateBook(BookDto bookDto);

    boolean deleteBook(BookDto bookDto);

    List<BookDto> getAllBooks();

    List<BookIdWithTitle> getIdsAndTitles();
    List<GetAllTitles> getAllTitles();

    BookDto getIdByTitle(String searchText);
}
