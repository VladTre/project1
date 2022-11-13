package vlad.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vlad.dao.BookDAO;
import vlad.dao.PersonDAO;
import vlad.models.Book;
import vlad.models.Person;
import vlad.util.PersonValidator;

@Component
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonValidator personValidator;


    @Autowired
    public BookController(BookDAO bookDAO, PersonValidator personValidator) {
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("book", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{book_id}")
    public String show(@PathVariable("book_id") int book_id, Model model) {
        model.addAttribute("book", bookDAO.show(book_id));
        return "books/show";
    }

    @GetMapping("/new_book")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "books/new_book";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        personValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors())
            return "books/new_book";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{book_id}/edit")
    public String edit(Model model, @PathVariable("book_id") int book_id) {
        model.addAttribute("book", bookDAO.show(book_id));
        return "people/edit";
    }

    @PatchMapping("/{book_id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("book_id") int book_id) {
        personValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(book_id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{book_id}")
    public String delete(@PathVariable("book_id") int book_id) {
        bookDAO.delete(book_id);
        return "redirect:/books";
    }
}
