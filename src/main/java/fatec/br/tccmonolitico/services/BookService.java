package fatec.br.tccmonolitico.services;

import fatec.br.tccmonolitico.dtos.BookDTO;
import fatec.br.tccmonolitico.entities.Book;
import fatec.br.tccmonolitico.respositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //TODO métodos

    //Get
    public BookDTO getBookDTOById(Long id){
        Optional<Book> byId = bookRepository.findById(id);
        return new BookDTO(byId.orElseThrow(()-> new RuntimeException("Book not found")));
    }

    //Get List

    public List<BookDTO> getListOfBooKDTO(){
        List<Book> books = bookRepository.findAll();

        List<BookDTO> collect = books.stream().map(b ->
            new BookDTO(b)
        ).collect(Collectors.toList());

        return collect;
    }

    public Book getBookById(Long id){
        return searchBook(id);
    }

    public List<Book> getBooks(){
        List<Book> books = bookRepository.findAll();
        return books;
    }

    //Post book
    public BookDTO saveBook(Book book){
        Book save = bookRepository.save(book);
        return new BookDTO(save);
    }

    //Put
    public Book updateBook(Book book, Long id){
        searchBook(id);
        book.setId(id);
        return bookRepository.save(book);
    }

    //Delete
    public String deleteBook(Long id){
        Book book = searchBook(id);
        bookRepository.delete(book);
        return "Book "+id+" deleted";
    }

    private Book searchBook(Long id){
        Optional<Book> byId = bookRepository.findById(id);
        return byId.orElseThrow(()-> new RuntimeException("Book "+id+" not found"));
    }
}
