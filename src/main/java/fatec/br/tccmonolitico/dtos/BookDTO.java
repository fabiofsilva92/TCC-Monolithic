package fatec.br.tccmonolitico.dtos;

import fatec.br.tccmonolitico.entities.Book;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class BookDTO {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Campo AUTHOR é requerido")
    @Length(min = 3, max = 100, message = "O campo AUTHOR deve ter entre 3 e 100 caracters")
    private String author;

    @NotEmpty(message = "Campo TITLE é requerido")
    @Length(min = 3, max = 200, message = "O campo TITLE deve ter entre 3 e 200 caracters")
    private String title;

    public BookDTO() {
        super();
    }

    public BookDTO(Book obj) {
        super();
        this.id = obj.getId();
        this.author = obj.getAuthor();
        this.title = obj.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
