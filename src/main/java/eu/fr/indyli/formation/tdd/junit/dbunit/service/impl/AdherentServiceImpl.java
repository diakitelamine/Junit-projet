package eu.fr.indyli.formation.tdd.junit.dbunit.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import eu.fr.indyli.formation.tdd.junit.dbunit.dao.IAdherentRepository;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Adherent;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.IAdherentService;

@Service("adherentService")
public class AdherentServiceImpl implements IAdherentService {

  @Resource(name = "adherentRepository")
  private IAdherentRepository adherentRepository = null;

  public Adherent findById(Long id) {
    return adherentRepository.findById(id).orElse(null);
  }

  public Adherent save(Adherent a) {
    return adherentRepository.save(a);
  }

  public Adherent findByLastname(String lastname) {
    return adherentRepository.findByLastname(lastname).orElse(null);
  }

  public List<Adherent> findAll() {
    return this.findAll();
  }

  public void update(Adherent a) {
    adherentRepository.saveAndFlush(a);
  }

	@Override
	public List<Adherent> findByAgeGreaterThan(Integer age) {
		return adherentRepository.findByAgeGreaterThan(age);
	}

}
