package eu.fr.indyli.formation.tdd.junit.dbunit.service;

import eu.fr.indyli.formation.tdd.junit.dbunit.service.exception.MathException;

public interface IMathsService {

  public Integer sum(Integer number1, Integer number2);

  public Integer substract(Integer number1, Integer number2);

  public Integer multiply(Integer number1, Integer number2);

  public Integer divide(Integer number1, Integer number2) throws MathException;

  /**
   * Check if number is prime number
   * 
   * @param numP : Number to be inspect
   * @return
   * @throws MathException
   */
  public Boolean isPrimeNumber(Integer numP) throws MathException;

  /**
   * Calculate the sum of n first number
   * 
   * @param numP : Main integer for the sum
   * @return
   * @throws MathException
   */
  public Integer sumOfNFirstPrimeNumber(Integer numP) throws MathException;

  public Integer average(Integer number1, Integer number2);

  public Integer factorial(Integer numP) throws MathException;

}
