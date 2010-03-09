package pl.jo2.core;

import pl.jo2.model.Buddy;
import pl.jo2.model.BuddyList;
import pl.jo2.model.Presence;
import pl.jo2.model.PresenceType;

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
    bl.addBuddy(new Buddy("edi.marfi", "tlen.pl", "Edi Marfi", new Presence(PresenceType.AVAILABLE, null)));
    bl.addBuddy(new Buddy("dzon.bon.dzowi", "tlen.pl", "Dzon Bon Dzowi", new Presence(PresenceType.DND, null)));
    bl.addBuddy(new Buddy("sztefen.myler", "tlen.pl", "Sztefen Myler", new Presence(PresenceType.XA, null)));
    bl.addBuddy(new Buddy("britnej.spirs", "tlen.pl", "Britnej Spirs", new Presence(PresenceType.INVISIBLE, null)));
    bl.addBuddy(new Buddy("doda", "tlen.pl", "Doda", new Presence(PresenceType.UNAVAILABLE, null)));
    bl.addBuddy(new Buddy("nergal", "tlen.pl", "Nergal", new Presence(PresenceType.CHAT, null)));
    bl.addBuddy(new Buddy("zbysiu", "tlen.pl", "Zbysiu", new Presence(PresenceType.AWAY, "załatwię na 90%")));
    bl.addBuddy(new Buddy("hristina.agilera", "tlen.pl", "Hristina Agilera", new Presence(PresenceType.AVAILABLE, null)));
    bl.addBuddy(new Buddy("lejdi.gaga", "tlen.pl", "Lejdi Gaga", new Presence(PresenceType.DND, null)));
    bl.addBuddy(new Buddy("erik.kartmanr", "tlen.pl", "Erik Kartman", new Presence(PresenceType.XA, null)));
    bl.addBuddy(new Buddy("stan.marsz", "tlen.pl", "Stan Marsz", new Presence(PresenceType.UNAVAILABLE, null)));
    bl.addBuddy(new Buddy("kajl.boflofski", "tlen.pl", "Kajl Broflofski", new Presence(PresenceType.CHAT, null)));
    return bl.listBuddies();
  }
}
