package pl.jo2.utils.comparators;

import org.junit.Test;
import pl.jo2.model.Buddy;
import pl.jo2.model.ContactInfo;
import pl.jo2.model.Presence;
import pl.jo2.model.PresenceType;

import java.util.Comparator;

import static org.junit.Assert.*;


public class BuddyByPresenceTest {


  @Test
  public void test() {

    Comparator<Buddy> c = new BuddyByPresence();

    Buddy b1 = new Buddy(new ContactInfo("l1", "d1", "aa"), new Presence(PresenceType.XA, null));
    Buddy b2 = new Buddy(new ContactInfo("l2", "d2", "bb"), new Presence(PresenceType.XA, null));
    Buddy b3 = new Buddy(new ContactInfo("l3", "d3", "cc"), new Presence(PresenceType.AWAY, null));
    Buddy b4 = new Buddy(new ContactInfo("l4", "d4", "cc"), new Presence(PresenceType.AWAY, null));

    // oba obiekty rowne
    assertTrue(c.compare(b3, b4) == 0);
    assertTrue(c.compare(b1, b1) == 0);


    // pierwszy obiekt wiekszy
    assertTrue(c.compare(b1, b2) < 0);
    assertTrue(c.compare(b3, b2) < 0);

    // drugi obiekt wiekszy
    assertTrue(c.compare(b1, b4) > 0);    




  }


}
