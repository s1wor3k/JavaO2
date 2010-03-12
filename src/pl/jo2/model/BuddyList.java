package pl.jo2.model;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-11-13
 * Time: 18:39:14
 */
public class BuddyList {
  private List<Buddy> myBuddies;
  private List<BuddyListChangeListener> myBuddyListChangeListeners = new ArrayList<BuddyListChangeListener>();
  private Comparator<Buddy> myComparator;


  public BuddyList() {

  }

  public BuddyList(List<Buddy> list, Comparator<Buddy> c) {
    this.myComparator = c;
    this.myBuddies = new ArrayList<Buddy>(list.size());
    for (Buddy b : list) {
      if (myBuddies.contains(b))
        continue;
      myBuddies.add(b);
    }
    if (myBuddies.size() > 1) {
      Collections.sort(myBuddies, myComparator);
    }

  }

  //Public Interface

  /**
   * dodaje obiekt do listy ze statusem {@code PresenceType.UNAVAILABLE}
   *
   * @param b obiekt do dodania
   * @return {@code true} jesli obiektu nie bylo wczesniej na liscie
   */
  public boolean addBuddy(Buddy b) {
    return addBuddy(b, new Presence(PresenceType.UNAVAILABLE, null));
  }

  /**
   * dodaje obiekt buddy do listy jesli jeszczo go tam nie ma
   *
   * @param b obiekt do dodania
   * @param p status obiektu
   * @return zwraca {@code true} jesli lista została zmieniona
   */
  public boolean addBuddy(Buddy b, Presence p) {
    if (myBuddies.contains(b)) {
      return false;
    }
    b.setPresence(p);
    Iterator<Buddy> iterator = myBuddies.iterator();
    int i = 0;
    // sortujemy od najmniejszego do najwiekszego
    while (iterator.hasNext() && myComparator.compare(b, iterator.next()) > 0) {
      i++;
    }
    myBuddies.add(i, b);
    fireBuddyListChange();
    return true;
  }

  public void changePresence(Buddy buddy, Presence presence) {
    int index = myBuddies.indexOf(buddy);
    if (myBuddies.get(index).getPresence().equals(presence)) {
      return;
    }

    Buddy tmpBuddy = myBuddies.remove(index);
    addBuddy(tmpBuddy, presence);
  }

  public List<Buddy> listBuddies() {
    return myBuddies;
  }

  public boolean deleteBuddy(Buddy b) {
    if (myBuddies.remove(b)) {
      fireBuddyListChange();
      return true;
    }
    return false;
  }

  public void setComparator(Comparator<Buddy> c) {
    if (c == null)
      throw new NullPointerException("comparator in BuddyList cannot be null, setComparator()");

    if (c.getClass().equals(myComparator.getClass()))
      return;

    myComparator = c;
    Collections.sort(myBuddies, myComparator);
  }

  public boolean addBuddies(List<Buddy> list) {
    boolean modified = false;
    for (Buddy b : list) {
      if (!myBuddies.contains(b)) {
        myBuddies.add(b);
        modified = true;
      }
    }
    if (modified) {
      Collections.sort(myBuddies, myComparator);
      fireBuddyListChange();
    }
    return modified;
  }


  // rejestracja i powiadamianie obiektow - obserwatorow o zmianie zawartosci lub upozadkowania listy

  private void fireBuddyListChange() {
    for (BuddyListChangeListener b : myBuddyListChangeListeners) {
      b.buddyListChanged();
    }
  }

  public void addBuddyListChangeListener(BuddyListChangeListener b) {
    myBuddyListChangeListeners.add(b);
  }

  public void removeBuddyListChangeListener(BuddyListChangeListener b) {
    myBuddyListChangeListeners.remove(b);
  }


}
