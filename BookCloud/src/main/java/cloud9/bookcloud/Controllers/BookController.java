package cloud9.bookcloud.Controllers;

import cloud9.bookcloud.Models.Book;
import cloud9.bookcloud.Service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping({"/" , "/home"})
    public String home(Model model) {
        model.addAttribute("books", service.findAll());
        return "home";
    }

    @GetMapping("/new")
    public String newBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "NewBook";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        service.save(book);
        return "redirect:/home";
    }

    @PostMapping("/read/{id}")
    public String markRead(@PathVariable Long id) {
        service.markAsRead(id);
        return "redirect:/home";
    }

    @PostMapping("/to-read/{id}")
    public String markToRead(@PathVariable Long id) {
        service.markAsToRead(id);
        return "redirect:/home";
    }

    @GetMapping("/unmarked")
    public String listUnmarked(Model model) {
        model.addAttribute("books", service.findUnmarked());
        return "home";
    }

    @GetMapping("/category/{category}")
    public String listByCategory(@PathVariable String category, Model model) {
        model.addAttribute("books", service.findByCategory(category));
        return "home";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", service.findById(id));
        return "NewBook";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        Book existing = service.findById(id);
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setPublisher(book.getPublisher());
        existing.setPages(book.getPages());
        existing.setCategory(book.getCategory());
        existing.setLido(book.isLido());
        existing.setQueroLer(book.isQueroLer());
        service.save(existing);
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/home";
    }
}