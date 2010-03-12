package pl.jo2.core;

import pl.jo2.model.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-10-20
 * Time: 19:05:33
 */
public class ContactsManagerMock implements ContactsManager {
  public void addBuddy(Buddy b) {
    // ma nic nie robic
  }

  public void deleteBuddy(Buddy b) {
    // tez ma nic nie robic
  }

  public List<Buddy> getBuddies() {
    BuddyList bl = new BuddyList();
    //List<Buddy> bl = new ArrayList<Buddy>();
    bl.addBuddy(new Buddy(new ContactInfo("edi.marfi", "tlen.pl", "Edi Marfi"), new Presence(PresenceType.AVAILABLE, null)));
    bl.addBuddy(new Buddy(new ContactInfo("dzon.bon.dzowi", "tlen.pl", "Dzon Bon Dzowi"), new Presence(PresenceType.DND, null)));
    bl.addBuddy(new Buddy(new ContactInfo("sztefen.myler", "tlen.pl", "Sztefen Myler"), new Presence(PresenceType.XA, null)));
    bl.addBuddy(new Buddy(new ContactInfo("britnej.spirs", "tlen.pl", "Britnej Spirs"), new Presence(PresenceType.INVISIBLE, null)));
    bl.addBuddy(new Buddy(new ContactInfo("doda", "tlen.pl", "Doda"), new Presence(PresenceType.UNAVAILABLE, null)));
    bl.addBuddy(new Buddy(new ContactInfo("nergal", "tlen.pl", "Nergal"), new Presence(PresenceType.CHAT, null)));
    bl.addBuddy(new Buddy(new ContactInfo("zbysiu", "tlen.pl", "Zbysiu"), new Presence(PresenceType.AWAY, "załatwię na 90%")));
    bl.addBuddy(new Buddy(new ContactInfo("hristina.agilera", "tlen.pl", "Hristina Agilera"), new Presence(PresenceType.AVAILABLE, null)));
    bl.addBuddy(new Buddy(new ContactInfo("lejdi.gaga", "tlen.pl", "Lejdi Gaga"), new Presence(PresenceType.DND, null)));
    bl.addBuddy(new Buddy(new ContactInfo("erik.kartmanr", "tlen.pl", "Erik Kartman"), new Presence(PresenceType.XA, null)));
    bl.addBuddy(new Buddy(new ContactInfo("stan.marsz", "tlen.pl", "Stan Marsz"), new Presence(PresenceType.UNAVAILABLE, null)));
    bl.addBuddy(new Buddy(new ContactInfo("kajl.boflofski", "tlen.pl", "Kajl Broflofski"), new Presence(PresenceType.CHAT, null)));
    return bl.listBuddies();
  }
}
