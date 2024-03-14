package lk.ijse.service.impl;

import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.projection.BookIdWithTitle;
import lk.ijse.projection.GetAllTitles;
import lk.ijse.repository.BookRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.impl.BookRepositoryImpl;
import lk.ijse.service.BookService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    private Session session;
    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BOOK);
    @Override
    public Long saveBook(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance()
                .getSession();
        Transaction transaction = session.beginTransaction();
        try {
            bookRepository.setSession(session);
            System.out.println("Check3");
            Long id = bookRepository.save(bookDto.toEntity());
            System.out.println("Check4");
            transaction.commit();
            System.out.println("Check5");
            session.close();
            return id;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return -1L;
        }
    }

    @Override
    public BookDto getBooks(long id) {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        return bookRepository.get(id).toDto();
    }

    @Override
    public boolean updateBook(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance()
                .getSession();
        Transaction transaction = session.beginTransaction();
        try {
            bookRepository.setSession(session);
            bookRepository.update(bookDto.toEntity()); // We pass it to the repository by converting it to an entity
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBook(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance()
                .getSession();
        Transaction transaction = session.beginTransaction();
        try {
            bookRepository.setSession(session);
            bookRepository.delete(bookDto.toEntity()); // We pass it to the repository by converting it to an entity
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            session.close();
            return false;
        }
    }

    @Override
    public List<BookDto> getAllBooks() {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        List<Book> allBooks = bookRepository.getAllBooks();
        List<BookDto> dtoList = new ArrayList<>();
        for (Book book : allBooks){
            dtoList.add(book.toDto());
        }
        return dtoList;
    }

    @Override
    public List<BookIdWithTitle> getIdsAndTitles() {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        List<BookIdWithTitle> idsAndTitles = bookRepository.getIdsAndTitles();
        return idsAndTitles;
    }

    @Override
    public List<GetAllTitles> getAllTitles() {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        List<GetAllTitles> allTitles = bookRepository.getAllTitles();
        return allTitles;
    }

    @Override
    public BookDto getIdByTitle(String searchText) {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        long id = bookRepository.getIdByTitle(searchText);
        return bookRepository.get(id).toDto();
    }
}
