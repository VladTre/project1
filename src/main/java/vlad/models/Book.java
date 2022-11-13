package vlad.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int book_id;

    @Size(min = 2, max = 50, message = "Title should be between 2 and 50 characters")
    @NotEmpty(message = "Title should not be empty")
    private String title;

    @Size(min = 2, max = 30, message = "Author should be between 2 and 30 characters")
    @NotEmpty(message = "Author should not be empty")
    private String author;

    @NotEmpty(message = "Year should not be empty")
    @Min(value = 0, message = "Year should be greater than 0")
    private int year;

    private int person_id;

    public Book() {
    }

    public Book(int book_id, String title, String author, int year, int person_id) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.person_id = person_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
