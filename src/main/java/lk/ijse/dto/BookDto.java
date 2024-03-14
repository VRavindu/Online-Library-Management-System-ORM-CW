package lk.ijse.dto;

import lk.ijse.entity.Book;
import lk.ijse.entity.User;

public class BookDto {
    private long id;
    private String title;
    private String author;
    private String genre;
    private int qty;
    private String date;

    public BookDto() {
    }

    public BookDto(int id, String title, String author, String genre, int qty, String date) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.qty = qty;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", qty=" + qty +
                ", date='" + date + '\'' +
                '}';
    }

    public Book toEntity(){
        Book bookDto = new Book();
        bookDto.setId(this.id);
        bookDto.setTitle(this.title);
        bookDto.setAuthor(this.author);
        bookDto.setGenre(this.genre);
        bookDto.setQty(this.qty);
        bookDto.setDate(this.date);
        return bookDto;
    }
}
