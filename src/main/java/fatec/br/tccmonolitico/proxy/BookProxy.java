package fatec.br.tccmonolitico.proxy;

import fatec.br.tccmonolitico.dtos.BookDTO;
import fatec.br.tccmonolitico.entities.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(url="http://localhost:8081", name="book-service-mono")
public interface BookProxy {

    @GetMapping(value = "/book-service-mono/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency);

    @GetMapping(value = "/book-service-mono/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id);

    @GetMapping(value = "/book-service-mono")
    public ResponseEntity<List<BookDTO>> findAll();

    @PostMapping(value = "/book-service-mono")
    public ResponseEntity<BookDTO> create(@Valid @RequestBody Book book);

    @PutMapping(value = "/book-service-mono/{id}")
    public ResponseEntity<BookDTO> update(@Valid @PathVariable Long id, @RequestBody Book book);

    @DeleteMapping(value = "/book-service-mono/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id);

    @GetMapping(value = "/book-service-mono/time")
    public ResponseEntity<List<BookDTO>> findAllTimeProcessed();
}
