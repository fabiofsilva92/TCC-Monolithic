package fatec.br.tccmonolitico.controllers;

import fatec.br.tccmonolitico.dtos.BookDTO;
import fatec.br.tccmonolitico.entities.Book;
import fatec.br.tccmonolitico.entities.Cambio;
import fatec.br.tccmonolitico.proxy.CambioProxy;
import fatec.br.tccmonolitico.services.BookService;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/book-service-mono")
public class BookController {

    private  BookService bookService;

    private CambioProxy proxy;

    private Environment environment;

    public BookController(BookService bookService, CambioProxy proxy, Environment environment) {
        this.bookService = bookService;
        this.proxy = proxy;
        this.environment = environment;
    }

    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

        Book book = bookService.getBookById(id);
        if (book == null)
            throw new RuntimeException("Book not Found");

        Cambio cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");

        book.setEnvironment("Book Port: " + port
                + " Cambio Port " + cambio.getEnvironment());
        book.setPrice((cambio.getConvertedValue()));

        return book;
    }

    //TODO endpoints

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {

        Book obj = bookService.getBookById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> listDTO = bookService.getListOfBooKDTO();

        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@Valid @RequestBody Book book) {
        BookDTO bookDTO = bookService.saveBook(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bookDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(bookDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookDTO> update(@Valid @PathVariable Long id, @RequestBody Book book) {

        Book book1 = bookService.updateBook(book, id);

        return ResponseEntity.ok().body(new BookDTO(book1));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }
}
