package eu.fr.indyli.formation.tdd.junit.dbunit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("eu.fr.indyli.formation.tdd.junit.dbunit.dao")
@EntityScan("eu.fr.indyli.formation.tdd.junit.dbunit.entity")
@ComponentScan({"eu.fr.indyli.formation.tdd.junit.dbunit.service",
    "eu.fr.indyli.formation.tdd.junit.dbunit.dao"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
