package lk.ijse.projection;

public class BookDetailDto {

    private String title;
    private String author;
    private String genre;
    private String date;

    public BookDetailDto() {}

    public BookDetailDto(String title, String author, String genre, String date) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookDetailDto{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", date='" + date + '\'' +
                '}';
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
