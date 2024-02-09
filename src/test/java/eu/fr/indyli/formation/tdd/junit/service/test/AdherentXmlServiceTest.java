package eu.fr.indyli.formation.tdd.junit.service.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import eu.fr.indyli.formation.tdd.junit.dbunit.app.Application;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Adherent;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.IAdherentService;

@DatabaseSetup("/adherent.xml")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestExecutionListeners({
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class AdherentXmlServiceTest {
	
	@Resource(name = "adherentService")
	private IAdherentService adherentService;

	@Test
	public void testFindEmployeeGreaterThanWithSuccess() {
	    // Given
		Integer findAge= 50;
		Integer nbPerseHavingThisAge= 2;
	   
	    // When
	    List<Adherent> adherentsGreaterThan50 =  this.adherentService.findByAgeGreaterThan(findAge);

	    // Then
	    assertEquals(nbPerseHavingThisAge, Integer.valueOf(adherentsGreaterThan50.size()));

	}
    


}
