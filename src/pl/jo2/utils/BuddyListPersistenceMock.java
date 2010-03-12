package pl.jo2.utils;

import pl.jo2.model.Buddy;
import pl.jo2.model.ContactInfo;
import pl.jo2.model.Presence;
import pl.jo2.model.PresenceType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuddyListPersistenceMock implements BuddyListPersistence {
  @Override
  public List<Buddy> loadBuddies() throws IOException {
    List<Buddy> list = new ArrayList<Buddy>(12);
    list.add(new Buddy(new ContactInfo("edi.marfi", "tlen.pl", "Edi Marfi"), new Presence(PresenceType.AVAILABLE, null)));
    list.add(new Buddy(new ContactInfo("dzon.bon.dzowi", "tlen.pl", "Dzon Bon Dzowi"), new Presence(PresenceType.DND, null)));
    list.add(new Buddy(new ContactInfo("sztefen.myler", "tlen.pl", "Sztefen Myler"), new Presence(PresenceType.XA, null)));
    list.add(new Buddy(new ContactInfo("britnej.spirs", "tlen.pl", "Britnej Spirs"), new Presence(PresenceType.INVISIBLE, null)));
    list.add(new Buddy(new ContactInfo("doda", "tlen.pl", "Doda"), new Presence(PresenceType.UNAVAILABLE, null)));
    list.add(new Buddy(new ContactInfo("nergal", "tlen.pl", "Nergal"), new Presence(PresenceType.CHAT, null)));
    list.add(new Buddy(new ContactInfo("zbysiu", "tlen.pl", "Zbysiu"), new Presence(PresenceType.AWAY, "załatwię na 90%")));
    list.add(new Buddy(new ContactInfo("hristina.agilera", "tlen.pl", "Hristina Agilera"), new Presence(PresenceType.AVAILABLE, null)));
    list.add(new Buddy(new ContactInfo("lejdi.gaga", "tlen.pl", "Lejdi Gaga"), new Presence(PresenceType.DND, null)));
    list.add(new Buddy(new ContactInfo("erik.kartmanr", "tlen.pl", "Erik Kartman"), new Presence(PresenceType.XA, null)));
    list.add(new Buddy(new ContactInfo("stan.marsz", "tlen.pl", "Stan Marsz"), new Presence(PresenceType.UNAVAILABLE, null)));
    list.add(new Buddy(new ContactInfo("kajl.boflofski", "tlen.pl", "Kajl Broflofski"), new Presence(PresenceType.CHAT, null)));  
    return list;
  }

  @Override
  public void saveBuddies(List<Buddy> input) throws IOException {
    // nic nie robi
  }
}
