package pl.jo2.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-10-20
 * Time: 18:55:00
 */
public class Buddy implements Serializable {
  /**
   * identyfikator - login tlen
   */
  private String id;
  /**
   * domana, na razie tylko tlen.pl ale pozniej kto wie
   */
  private String domain;
  /**
   * tekst wyswietlany na liscie kontaktow
   */
  private String alias;
  /**
   * stan kontaktu
   */
  private Presence presence;

  public Buddy() {
  }

  public Buddy(String id, String domain, String alias, Presence presence) {
    this.id = id;
    this.domain = domain;
    this.alias = alias;
    this.presence = presence;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public Presence getPresence() {
    return presence;
  }

  public void setPresence(Presence presence) {
    this.presence = presence;
  }

  @Override
  public String toString() {
    return "Buddy{" +
      "id='" + id + '\'' +
      ", domain='" + domain + '\'' +
      ", alias='" + alias + '\'' +
      ", icons.presence=" + presence +
      '}';
  }
}
