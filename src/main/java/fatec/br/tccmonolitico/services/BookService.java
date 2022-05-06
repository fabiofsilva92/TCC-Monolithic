package fatec.br.tccmonolitico.services;

import fatec.br.tccmonolitico.respositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //TODO métodos
}
