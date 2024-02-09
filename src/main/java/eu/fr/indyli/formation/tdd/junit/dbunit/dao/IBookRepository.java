package eu.fr.indyli.formation.tdd.junit.dbunit.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Book;

@Repository("bookRepository")
public interface IBookRepository extends JpaRepository<Book, Long> {

  /**
   * Cherche par ISBN
   * 
   * @param isbn
   * @return
   */
  public Book findByIsbn(String isbn);
  
  
  @Query("select sum(b.quantity) From Book b where b.parutionDate >= :pParutionDate and b.quantity > 0")
  public Integer countAvailableBookByParutionDate(@Param("pParutionDate") Date date);
  
}
