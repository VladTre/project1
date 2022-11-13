package vlad.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import vlad.models.Book;


import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int book_id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{book_id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Book> show(String author) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE author=?", new Object[]{author}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title,year_of_birth) VALUES (?,?,?)", book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int book_id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=? year=? WHERE book_id=?", updatedBook.getTitle(),
                updatedBook.getAuthor(), updatedBook.getYear(), book_id);

    }

    public void delete(int book_id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", book_id);

    }
}
