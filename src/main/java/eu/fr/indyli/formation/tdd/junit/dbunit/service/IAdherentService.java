package eu.fr.indyli.formation.tdd.junit.dbunit.service;

import java.util.List;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Adherent;

public interface IAdherentService {

  public Adherent findById(Long id);

  public Adherent save(Adherent a);

  public void update(Adherent a);

  public Adherent findByLastname(String lastname);


  public List<Adherent> findAll();
  
  public List<Adherent> findByAgeGreaterThan(Integer age);
  

}
