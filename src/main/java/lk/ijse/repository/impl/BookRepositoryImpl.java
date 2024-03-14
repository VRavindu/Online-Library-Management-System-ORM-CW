package lk.ijse.repository.impl;

import lk.ijse.entity.Book;
import lk.ijse.projection.BookIdWithTitle;
import lk.ijse.projection.GetAllTitles;
import lk.ijse.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<BookIdWithTitle> getIdsAndTitles() {
        String sqlQuery = "SELECT new lk.ijse.projection.BookIdWithTitle (B.id, B.title) FROM Book AS B";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<GetAllTitles> getAllTitles() {
        String sqlQuery = "SELECT new lk.ijse.projection.GetAllTitles (B.title) FROM Book AS B";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public long getIdByTitle(String searchText) {
        String sqlQuery = "SELECT b.id FROM Book b WHERE b.title = :title";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("title", searchText);
        long id = (long) query.uniqueResult();
        return id;
    }


    @Override
    public List<Book> getAllBooks() {
        String sqlQuery = "FROM Book";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public Long save(Book book) {
        return (Long) session.save(book);
    }

    @Override
    public void update(Book book) {
        session.update(book);
    }

    @Override
    public Book get(Long id) {
        return session.get(Book.class, id);
    }

    @Override
    public void delete(Book book) {
        session.delete(book);
    }
}
