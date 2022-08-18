package fatec.br.tccmonolitico.controllers;

import fatec.br.tccmonolitico.dtos.BookDTO;
import fatec.br.tccmonolitico.entities.Book;
import fatec.br.tccmonolitico.entities.Cambio;
import fatec.br.tccmonolitico.proxy.CambioProxy;
import fatec.br.tccmonolitico.services.BookCrudService;
import fatec.br.tccmonolitico.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BookCrudService crudService;

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
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
        Book obj = crudService.findById(id);
        BookDTO book = BookDTO.builder()
                .id(obj.getId())
                .title(obj.getTitle())
                .author(obj.getAuthor())
                .price(obj.getPrice())
                .launchDate(obj.getLaunchDate())
                .build();
        return ResponseEntity.ok().body(book);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> listDTO = crudService.findAll();
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/time")
    public ResponseEntity<Void> findAllTimeProcessed() {
        Long start = System.nanoTime();
        List<BookDTO> listDTO = crudService.findAll();
        Long end = System.nanoTime();
        System.out.println("Tempo passado dentro no metodo :" + (end - start) + " nanossegundos");
        System.out.println("Tempo passado dentro no metodo segundos :" + (end - start) * (Math.pow(10, -9)) + "s");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book obj) {
        obj = crudService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookDTO> update(@Valid @PathVariable Long id, @RequestBody Book bookDto) {
        Book newBook = crudService.update(id, bookDto);
        return ResponseEntity.ok().body(new BookDTO(newBook));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        crudService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/delete-mocks")
    public ResponseEntity<Void>  deletemocks(){
        crudService.deleteMocks();
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/get-mocks")
    public ResponseEntity<List<Book>>  getMocks(){
        return ResponseEntity.ok().body(crudService.getBooksMocks());
    }
}
