package eu.fr.indyli.formation.tdd.junit.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.fr.indyli.formation.tdd.junit.dbunit.service.IMathsService;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.exception.MathException;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.impl.MathsServiceImpl;

public class MathsServiceTest {

  private IMathsService mathsService = null;

  @Before
  public void initTest() {
    mathsService = new MathsServiceImpl();
  }

  @Test
  public void testSum() {
    Integer sum = this.mathsService.sum(3, 5);
    assertTrue(sum.equals(8));
  }

  @Test
  public void testMultiply() {
    assertTrue(mathsService.multiply(10, 40).equals(400));
  }

  @Test
  public void testFactorial() throws MathException {
    assertTrue(mathsService.factorial(9).equals(362880));
  }

  @Test
  public void testIsPrimeNumber() throws MathException {
    assertTrue(mathsService.isPrimeNumber(7));
  }

  @Test
  public void testIsNotPrimeNumber() throws MathException {
	  Assert.assertFalse(mathsService.isPrimeNumber(9));
  }

  @Test
  public void testSumNPrimeNumber() throws MathException {
	  assertEquals(new Integer(17), mathsService.sumOfNFirstPrimeNumber(7));
  }

  @Test(expected = MathException.class)
  public void testThrowsErrorPrimeNumber() throws MathException {
    mathsService.isPrimeNumber(-4);
  }

  @After
  public void afterTest() {
    this.mathsService = null;
  }
}
