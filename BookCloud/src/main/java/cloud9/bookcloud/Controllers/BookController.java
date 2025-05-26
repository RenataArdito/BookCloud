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

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("books", service.findAll());
        return "home";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        service.save(book);
        return "redirect:/home";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/home";
    }

    @PostMapping("/status/{id}")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam("status") String status
    ) {
        if ("LIDO".equals(status)) {
            service.markAsRead(id);
        } else if ("QUERO_LER".equals(status)) {
            service.markAsToRead(id);
        }
        return "redirect:/home";
    }
}