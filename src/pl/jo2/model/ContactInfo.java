package pl.jo2.model;

import java.io.Serializable;

public class ContactInfo implements Serializable {

  /**
   * identyfikator - login tlen
   */
  private String myLogin;

  /**
   * domana, na razie tylko tlen.pl ale pozniej kto wie
   */
  private String myDomain;

  /**
   * tekst wyswietlany na liscie kontaktow
   */
  private String myAlias;

  /**
   * konstruktor
   *
   * @param login  - login/username uzywany do zidentyfikowania uzytkownika na serwerze
   * @param domain - adres serwera XMPP (poki co tylko tlen.pl)
   * @param alias  - tekst wyswietlany na liscie kontaktow zamiast domyslnego login@domain, wartosc moze byc {@code null}
   */
  public ContactInfo(String login, String domain, String alias) {
    this.myLogin = login;
    this.myDomain = domain;
    this.myAlias = alias;
  }

  public String getLogin() {
    return myLogin;
  }

  public void setLogin(String login) {
    this.myLogin = login;
  }

  public String getDomain() {
    return myDomain;
  }

  public void setDomain(String domain) {
    this.myDomain = domain;
  }

  public String getAlias() {
    return myAlias;
  }

  public void setAlias(String alias) {
    this.myAlias = alias;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactInfo that = (ContactInfo) o;

    if (myDomain != null ? !myDomain.equals(that.myDomain) : that.myDomain != null) return false;
    if (myLogin != null ? !myLogin.equals(that.myLogin) : that.myLogin != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = myLogin != null ? myLogin.hashCode() : 0;
    result = 31 * result + (myDomain != null ? myDomain.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactInfo{" +
        "myLogin='" + myLogin + '\'' +
        ", myDomain='" + myDomain + '\'' +
        ", myAlias='" + myAlias + '\'' +
        '}';
  }
}