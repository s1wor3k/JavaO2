package pl.jo2.model;

import pl.jo2.utils.FileBuddyListPersistence;
import pl.jo2.utils.BuddyListPersistence;
import pl.jo2.utils.comparators.BuddyByPresence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-11-13
 * Time: 18:39:14
 */
public class BuddyList {
  private TreeSet<Buddy> buddies;
  private ArrayList<BuddyListChangeListener> buddyListChangeListeners;
  private BuddyListPersistence buddyListPersistence;

  public BuddyList() {
    buddies = new TreeSet<Buddy>(new BuddyByPresence());
    buddyListChangeListeners = new ArrayList<BuddyListChangeListener>();
    buddyListPersistence = new FileBuddyListPersistence();
    List<Buddy> list = null;
    try {
      list = buddyListPersistence.loadBuddies();
    } catch (IOException e) {
      e.printStackTrace(); 
    }
    if (list == null || list.size() == 0) {
      tmpCreateBuddyList();
    } else {
      for (Buddy b : list)
        b.setPresence(new Presence(PresenceType.UNAVAILABLE, null));
      buddies.addAll(list);

    }
  }

  private void tmpCreateBuddyList() {//TODO usunac jak bedzie mozliwosc wczytywania listy z pliku
    System.out.println("tmp buddy list creator");
    List<Buddy> list = new ArrayList<Buddy>(12);
    list.add(new Buddy("edi.marfi", "tlen.pl", "Edi Marfi", new Presence(PresenceType.AVAILABLE, null)));
    list.add(new Buddy("dzon.bon.dzowi", "tlen.pl", "Dzon Bon Dzowi", new Presence(PresenceType.DND, null)));
    list.add(new Buddy("sztefen.myler", "tlen.pl", "Sztefen Myler", new Presence(PresenceType.XA, null)));
    list.add(new Buddy("britnej.spirs", "tlen.pl", "Britnej Spirs", new Presence(PresenceType.INVISIBLE, null)));
    list.add(new Buddy("doda", "tlen.pl", "Doda", new Presence(PresenceType.UNAVAILABLE, null)));
    list.add(new Buddy("nergal", "tlen.pl", "Nergal", new Presence(PresenceType.CHAT, null)));
    list.add(new Buddy("zbysiu", "tlen.pl", "Zbysiu", new Presence(PresenceType.AWAY, "załatwię na 90%")));
    list.add(new Buddy("hristina.agilera", "tlen.pl", "Hristina Agilera", new Presence(PresenceType.AVAILABLE, null)));
    list.add(new Buddy("lejdi.gaga", "tlen.pl", "Lejdi Gaga", new Presence(PresenceType.DND, null)));
    list.add(new Buddy("erik.kartmanr", "tlen.pl", "Erik Kartman", new Presence(PresenceType.XA, null)));
    list.add(new Buddy("stan.marsz", "tlen.pl", "Stan Marsz", new Presence(PresenceType.UNAVAILABLE, null)));
    list.add(new Buddy("kajl.boflofski", "tlen.pl", "Kajl Broflofski", new Presence(PresenceType.CHAT, null)));
    addBuddies(list);
  }

  public List<Buddy> listBuddies() {
    ArrayList<Buddy> result = new ArrayList<Buddy>(buddies.size());
    result.addAll(buddies);
    return result;
  }

  public void addBuddy(Buddy b) {
    if (buddies.add(b)) {
      fireBuddyListChange();
      persistBuddyList();
    }
  }

  public void addBuddies(List<Buddy> buddies) {
    if (this.buddies.addAll(buddies)) {
      fireBuddyListChange();
      persistBuddyList();
    }
  }

  public void deleteBuddy(Buddy b) {
    if (buddies.remove(b)) {
      fireBuddyListChange();
      persistBuddyList();
    }
  }

  private void persistBuddyList() {
    try {
      buddyListPersistence.saveBuddies(listBuddies());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void fireBuddyListChange() {
    for (BuddyListChangeListener b : buddyListChangeListeners) {
      b.buddyListChanged();
    }
  }

  public void addBuddyListChangeListener(BuddyListChangeListener b) {
    buddyListChangeListeners.add(b);
  }

  public void removeBuddyListChangeListener(BuddyListChangeListener b) {
    buddyListChangeListeners.remove(b);
  }

}
