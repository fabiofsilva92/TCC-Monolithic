package fatec.br.tccmonolitico.proxy;

import fatec.br.tccmonolitico.dtos.BookDTO;
import fatec.br.tccmonolitico.entities.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@FeignClient(url="${aws.nomePodBookService}", name="book-service-mono")
public interface BookProxy {

    @GetMapping(value = "/book-service-mono/{id}")
    BookDTO findById(@PathVariable Long id);

    @GetMapping(value = "/book-service-mono")
    List<BookDTO> findAll();

    @PostMapping(value = "/book-service-mono")
    BookDTO create(@Valid @RequestBody Book book);

    @PutMapping(value = "/book-service-mono/{id}")
    BookDTO update(@Valid @PathVariable Long id, @RequestBody Book book);

    @DeleteMapping(value = "/book-service-mono/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @GetMapping(value = "/book-service-mono/time")
    List<BookDTO> findAllTimeProcessed();

    @GetMapping(value = "/book-service-mono/delete-mocks")
    ResponseEntity<Void>  deletemocks();

    @GetMapping(value = "/book-service-mono/get-mocks")
    List<Book> getMocks();


    @GetMapping(value = "/book-service-mono/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency);


}
