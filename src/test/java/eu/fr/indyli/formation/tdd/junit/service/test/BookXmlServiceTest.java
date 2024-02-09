package eu.fr.indyli.formation.tdd.junit.service.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.text.ParseException;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import eu.fr.indyli.formation.tdd.junit.dbunit.app.Application;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Adherent;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Book;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.IAdherentService;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.IBookService;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.exception.BookException;
import eu.fr.indyli.formation.tdd.junit.dbunit.utils.DateUtils;

@DatabaseSetup({"/adherent.xml", "/book.xml"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestExecutionListeners({
    TransactionalTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
public class BookXmlServiceTest {

  @Resource(name = "bookService")
  private IBookService bookService = null;
  @Resource(name = "adherentService")
  private IAdherentService adherentService = null;

  @Rule
  public ExpectedException thrown = ExpectedException.none();


  @Test
  public void testGetBookById() {
    // Given
    Long bookId = 1L;


    // When
    Book book = this.bookService.findById(bookId);

    // Then
    assertNotNull(book);
  }


  /**
   * TODO 5 : Faites tourner ce test
   */
  @Test
  public void testGetBookByIsbn() {
    // Given
    String isbnRent = "AGRFDSVC789K";


    // When
    Book book = this.bookService.findByIsbn(isbnRent);

    // Then
    assertNotNull(book);
  }

  @Test
  public void testRentBook() throws BookException {
    // Given
    String isbnRent = "AGRFDSVC789K";
    Adherent a1 = this.adherentService.findById(1L);

    // When
    assertTrue(bookService.isBookAvalaible(isbnRent));
    bookService.rentBook(a1, isbnRent);

    // Then
    assertFalse(bookService.isBookAvalaible(isbnRent));
    assertTrue(bookService.getNbRequestCustomerByISBN(isbnRent) == 0);
  }


  // Test de location avec liste d'attente
  @Test
  public void testRentBookWithObserver() throws BookException {
    // Given
    String isbnRent = "AGRFDSVC789K";
    Adherent a1 = this.adherentService.findById(1L);
    Adherent a2 = this.adherentService.findById(2L);

    // When
    assertTrue(bookService.isBookAvalaible(isbnRent));
    bookService.rentBook(a1, isbnRent);
    bookService.rentBook(a2, isbnRent);

    // Then
    assertFalse(bookService.isBookAvalaible(isbnRent));
    assertTrue(bookService.getNbRequestCustomerByISBN(isbnRent) > 0);
  }

  @Test
  public void testTurnBackBook() throws BookException {
    // Given
    String isbnRent = "AGRFDSVC789K";
    Adherent a1 = this.adherentService.findById(1L);
    Book bookBefore = this.bookService.findByIsbn(isbnRent);
    Integer qtyBefore = bookBefore.getQuantity();

    // When
    bookService.turnBackBook(a1, isbnRent);

    // Then
    Book bookAfter = this.bookService.findByIsbn(isbnRent);
    Integer qtyAfter = bookAfter.getQuantity();
    assertTrue(bookService.isBookAvalaible(isbnRent));
    assertTrue(qtyAfter.equals(qtyBefore + 1));
  }

  @Test
  public void testThrowExceptionIfISBNAbsent() throws BookException {
    // Spy
    thrown.expect(BookException.class);
    thrown.expectMessage("AUCUN BOUQUIN PORTANT CET ISBN");

    // Given
    Adherent a1 = this.adherentService.findById(1L);
    String isbn = "XXXXXXXXXXX";

    // When
    this.bookService.rentBook(a1, isbn);

    // Then
  }

  // TODO : Assurez vous que le nombre total de bouquins disponibles et dont la date de parution est
  // post�rieure "strDate" depasse 10
  @Test
  public void testCountNbAvalaibleBookByParutionDateShouldPositive() throws ParseException {
    // Given
    Date datePivot = DateUtils.formatStringToDate("2001-05-20", "yyyy-MM-dd");
    Integer nbBookExpected = 10;
    // When
    Integer nbBook = this.bookService.countAvailableBookByParutionDate(datePivot);

    // Then
    assertTrue(nbBook > nbBookExpected);
  }
}
