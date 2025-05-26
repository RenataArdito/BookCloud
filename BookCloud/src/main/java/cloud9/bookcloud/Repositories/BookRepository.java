package cloud9.bookcloud.Repositories;

import cloud9.bookcloud.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLidoFalseAndQueroLerFalse();
}