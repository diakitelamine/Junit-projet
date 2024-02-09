package eu.fr.indyli.formation.tdd.junit.dbunit.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Adherent;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Book;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.exception.BookException;

public interface IBookService {

  /**
   * 
   * @return
   */
  public Book findById(Long id);

  /**
   * Cherche par ISBN
   * 
   * @param isbn
   * @return
   */
  public Book findByIsbn(String isbn);

  public List<Book> findAll();

  public void addObserver(Observer o);

  public void deleteObserver(Observer o);

  public void notifyObservers();

  public Map<String, List<Adherent>> getMapPretendantsBook();

  public void setMapPretendantsBook(Map<String, List<Adherent>> mapPretendantsBook);

  public Boolean rentBook(Adherent a, String isbn) throws BookException;

  public void turnBackBook(Adherent a, String isbn) throws BookException;

  public Boolean isBookAvalaible(String isbn) throws BookException;

  /**
   * Retourne le nombre de pretendants pour un bouquin
   * 
   * @param isbn
   * @return
   */
  public Integer getNbRequestCustomerByISBN(String isbn); 

  /**
   * Retourne le nombre de bouquins disponibles dont la date de parution est superieure  a  une
   * certaine date donnee
   * 
   * @param date
   * @return
   */
  public Integer countAvailableBookByParutionDate(Date date);
}
