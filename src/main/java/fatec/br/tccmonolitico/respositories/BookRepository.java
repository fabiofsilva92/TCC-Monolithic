package fatec.br.tccmonolitico.respositories;

import fatec.br.tccmonolitico.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT c.* FROM book AS c WHERE c.author= 'Autor mock'", nativeQuery = true)
    List<Book> getBooksMocks();

}
