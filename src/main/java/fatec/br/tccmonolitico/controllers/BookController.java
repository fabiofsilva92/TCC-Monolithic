package fatec.br.tccmonolitico.controllers;

import fatec.br.tccmonolitico.respositories.BookRepository;
import fatec.br.tccmonolitico.services.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-service-mono")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //TODO endpoints
}
