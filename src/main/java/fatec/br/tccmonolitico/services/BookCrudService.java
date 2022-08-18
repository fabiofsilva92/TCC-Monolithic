package fatec.br.tccmonolitico.services;

import fatec.br.tccmonolitico.dtos.BookDTO;
import fatec.br.tccmonolitico.entities.Book;
import fatec.br.tccmonolitico.respositories.BookRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookCrudService {

	@Autowired
	private BookRepository bookCrudRepository;

	public Book findById(Long id) {
		Optional<Book> obj = bookCrudRepository.findById(id);
		return obj.orElseThrow(RuntimeException::new);
	}

	public List<BookDTO> findAll(){
		List<Book> books = bookCrudRepository.findAll();
		List<BookDTO> collect = books.stream().map(b ->
				new BookDTO(b)
		).collect(Collectors.toList());
		return collect;
	}

	public Book create(Book obj) {
		obj.setId(null);
		return bookCrudRepository.save(obj);
	}

	public Book update(Long id, Book objDto) {
		Book obj = findById(id);
		objDto.setId(obj.getId());
		obj = objDto;
		return bookCrudRepository.save(obj);
	}

	public void delete(Long id) {
		try {
			bookCrudRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) { // esta exceção é do spring
			System.out.println(e);
		}
	}

    public void deleteMocks() {
		List<Book> books = bookCrudRepository.getBooksMocks();
		for (int i = 0; i < books.size(); i++) {
			System.out.println(books.get(i));
			bookCrudRepository.deleteById(books.get(i).getId());
		}
	}

	public List<Book> getBooksMocks(){
		return bookCrudRepository.getBooksMocks();
	}
}
