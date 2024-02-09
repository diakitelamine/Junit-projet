package eu.fr.indyli.formation.tdd.junit.dbunit.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Book {

  @Id
  private Long id;
  @Column
  private String isbn;
  @Column
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date parutionDate;
  @Column
  private String categorie;
  @Column
  private Integer quantity = 0;

  public Book() {
    this.id = new Date().getTime();
  }

  public Book(String isbn, Integer quantity) {
    super();
    this.isbn = isbn;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public Date getParutionDate() {
    return parutionDate;
  }

  public void setParutionDate(Date parutionDate) {
    this.parutionDate = parutionDate;
  }

  public String getCategorie() {
    return categorie;
  }

  public void setCategorie(String categorie) {
    this.categorie = categorie;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
