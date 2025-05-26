package cloud9.bookcloud.Service;

import cloud9.bookcloud.Models.Book;
import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Long id);
    Book save(Book book);
    void deleteById(Long id);

    List<Book> findUnmarked();

    Book markAsRead(Long id);
    Book markAsToRead(Long id);
}