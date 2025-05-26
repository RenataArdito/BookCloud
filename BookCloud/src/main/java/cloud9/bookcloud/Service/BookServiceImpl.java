package cloud9.bookcloud.Service;

import cloud9.bookcloud.Models.Book;
import cloud9.bookcloud.Repositories.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repo;

    public BookServiceImpl(BookRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Book> findAll() {
        return repo.findAll();
    }

    @Override
    public Book findById(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new NoSuchElementException("Book not found: " + id));
    }

    @Override
    public Book save(Book book) {
        return repo.save(book);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Book> findUnmarked() {
        return repo.findByLidoFalseAndQueroLerFalse();
    }

    @Override
    public Book markAsRead(Long id) {
        Book b = findById(id);
        b.setLido(true);
        b.setQueroLer(false);
        return repo.save(b);
    }

    @Override
    public Book markAsToRead(Long id) {
        Book b = findById(id);
        b.setQueroLer(true);
        b.setLido(false);
        return repo.save(b);
    }
}