package lk.ijse.repository;

import lk.ijse.entity.Book;
import lk.ijse.projection.BookIdWithTitle;
import lk.ijse.projection.GetAllTitles;
import org.hibernate.Session;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long>{

    List<Book> getAllBooks();
    void setSession(Session session);

    List<BookIdWithTitle> getIdsAndTitles();

    List<GetAllTitles> getAllTitles();

    long getIdByTitle(String searchText);
}
