package pl.jo2.model;

import java.io.Serializable;

public class Buddy implements Serializable {
  /**
   * dane kontaktu jak login, domena czy alias
   */
  private final ContactInfo myContactInfo;

  /**
   * status kontaktu - dostepnosc plus opis
   */
  private Presence myPresence;

  public Buddy(ContactInfo ci) {
    this(ci, new Presence(PresenceType.UNAVAILABLE, null));
  }

  public Buddy(ContactInfo ci, Presence p) {
    this.myContactInfo = ci;
    this.myPresence = p;
  }

  public ContactInfo getContactInfo() {
    return myContactInfo;
  }

  public Presence getPresence() {
    return myPresence;
  }

  public void setPresence(Presence presence) {
    this.myPresence = presence;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Buddy buddy = (Buddy) o;

    return !(myContactInfo != null ? !myContactInfo.equals(buddy.myContactInfo) : buddy.myContactInfo != null);

  }

  @Override
  public int hashCode() {
    return myContactInfo != null ? myContactInfo.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Buddy{" +
        "contactInfo=" + myContactInfo +
        ", presence=" + myPresence + '}';
  }
}
