package eu.fr.indyli.formation.tdd.junit.dbunit.entity;

public class RequestAdherent extends Adherent {

  private String requestISBN;

  public RequestAdherent(Long id, String name) {
    super(id, name);
  }

  public RequestAdherent(Long id, String name, String reqISBN) {
    super(id, name);
    this.requestISBN = reqISBN;
  }

  public String getRequestISBN() {
    return requestISBN;
  }

  public void setRequestISBN(String requestISBN) {
    this.requestISBN = requestISBN;
  }

}
