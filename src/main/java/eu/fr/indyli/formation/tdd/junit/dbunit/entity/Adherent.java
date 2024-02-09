package eu.fr.indyli.formation.tdd.junit.dbunit.entity;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
@Entity
public class Adherent implements Observer {

  @Transient
  private Logger log = LoggerFactory.getLogger(Adherent.class);
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String lastname;
  @Column
  private String firstname;
  @Column
  private Integer age;
  // Liste de bouquins demandes
  @Transient
  private List<Book> requestBooks;


  public Adherent() {}

  public Adherent(String lastname, String firstname, Integer age) {
    super();
    this.lastname = lastname;
    this.firstname = firstname;
    this.age = age;
  }

  public Adherent(Long id, String name) {
    super();
    this.id = id;
    this.lastname = name;
  }



  public void update(@SuppressWarnings("deprecation") Observable o, Object arg) {
    log.info("Moi Mr {} ai bien recu le retour du booking ISBN : {}", lastname, arg);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String name) {
    this.lastname = name;
  }



  public Logger getLog() {
    return log;
  }



  public void setLog(Logger log) {
    this.log = log;
  }



  public String getFirstname() {
    return firstname;
  }



  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }



  public Integer getAge() {
    return age;
  }



  public void setAge(Integer age) {
    this.age = age;
  }



  public List<Book> getRequestBooks() {
    return requestBooks;
  }



  public void setRequestBooks(List<Book> requestBook) {
    this.requestBooks = requestBook;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((age == null) ? 0 : age.hashCode());
    result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
    result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Adherent other = (Adherent) obj;
    if (age == null) {
      if (other.age != null)
        return false;
    } else if (!age.equals(other.age))
      return false;
    if (firstname == null) {
      if (other.firstname != null)
        return false;
    } else if (!firstname.equals(other.firstname))
      return false;
    if (lastname == null) {
      if (other.lastname != null)
        return false;
    } else if (!lastname.equals(other.lastname))
      return false;
    return true;
  }
}
