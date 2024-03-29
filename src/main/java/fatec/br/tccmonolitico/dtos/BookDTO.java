package fatec.br.tccmonolitico.dtos;

import fatec.br.tccmonolitico.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
public class BookDTO {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Campo AUTHOR é requerido")
    @Length(min = 3, max = 100, message = "O campo AUTHOR deve ter entre 3 e 100 caracters")
    private String author;

    @NotEmpty(message = "Campo TITLE é requerido")
    @Length(min = 3, max = 200, message = "O campo TITLE deve ter entre 3 e 200 caracters")
    private String title;

    private Double price;

    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date launchDate;


    public BookDTO() {
        super();
    }

    public BookDTO(Book obj) {
        super();
        this.id = obj.getId();
        this.author = obj.getAuthor();
        this.title = obj.getTitle();
        this.price = obj.getPrice();
        this.launchDate = obj.getLaunchDate();
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }


}
