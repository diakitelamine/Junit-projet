package eu.fr.indyli.formation.tdd.junit.dbunit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import eu.fr.indyli.formation.tdd.junit.dbunit.dao.IBookRepository;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Adherent;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Book;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.RequestAdherent;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.IBookService;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.exception.BookException;

@SuppressWarnings("deprecation")
@Service("bookService")
public class BookServiceImpl extends java.util.Observable implements IBookService {

  private static Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
  @Resource(name = "bookRepository")
  private IBookRepository bookRepository = null; 


  /**
   * Map des postulants à un bouquin
   */
  private Map<String, List<Adherent>> mapPretendantsBook;

  public BookServiceImpl() {
    mapPretendantsBook = new HashMap<String, List<Adherent>>();
  }
  
  @Override
  public void addObserver(Observer o) {
    RequestAdherent a = (RequestAdherent) o;
    mapPretendantsBook.putIfAbsent(a.getRequestISBN(), new ArrayList<Adherent>());
    // On ajoute un nouveau pretendant
    mapPretendantsBook.get(a.getRequestISBN()).add(a);
  }

  @Override
  public void deleteObserver(@SuppressWarnings("deprecation") Observer o) {}

  @Override
  public Map<String, List<Adherent>> getMapPretendantsBook() {
    return mapPretendantsBook;
  }

  @Override
  public void setMapPretendantsBook(Map<String, List<Adherent>> mapPretendantsBook) {
    this.mapPretendantsBook = mapPretendantsBook;
  }

  public Boolean rentBook(Adherent a, String isbnRent) throws BookException {
    LOGGER.info("Mr {} a demande le booquin d'ISBN : {}", a.getLastname(), isbnRent);
    Book book = this.bookRepository.findByIsbn(isbnRent);
    if (book == null) {
      throw new BookException("AUCUN BOUQUIN PORTANT CET ISBN");
    }
    if (book.getQuantity() <= 0) {
      RequestAdherent reqAd = new RequestAdherent(a.getId(), a.getLastname(), isbnRent);
      this.addObserver(reqAd);
      return Boolean.FALSE;
    } else {// Quantite disponible, alors on effectue la sortie
      Integer qty = book.getQuantity();
      qty--;
      book.setQuantity(qty);
      this.bookRepository.save(book);
      return Boolean.TRUE;
    }
  }

  public void turnBackBook(Adherent a, String isbn) throws BookException {
	  
	  Book book = this.bookRepository.findByIsbn(isbn);
	    if (book == null) {
	        throw new BookException("AUCUN BOUQUIN PORTANT CET ISBN");
	    }
	    Integer qty = book.getQuantity();
	    qty++;
	    book.setQuantity(qty);
	    this.bookRepository.save(book);
  }

  public Boolean isBookAvalaible(String isbn) {
    Book book = this.bookRepository.findByIsbn(isbn);
    Integer qty = book.getQuantity();
    return qty > 0;
  }

  public Integer getNbRequestCustomerByISBN(String isbn) {
    if (!this.mapPretendantsBook.containsKey(isbn)) {
      return 0;
    }
    return this.mapPretendantsBook.get(isbn).size();
  }

  public Book findByIsbn(String isbn) {
	 return this.bookRepository.findByIsbn(isbn);
  }


  public List<Book> findAll() {
    return this.bookRepository.findAll();
  }

  public Book findById(Long id) {
    return this.bookRepository.findById(id).orElse(null);
  }
  
  @Override
  public Integer countAvailableBookByParutionDate(Date date) {
	  return this.bookRepository.countAvailableBookByParutionDate(date);
  }

}
