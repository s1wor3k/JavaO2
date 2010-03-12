package pl.jo2.model;

import org.junit.Before;
import org.junit.Test;
import pl.jo2.utils.comparators.BuddyByAlias;
import pl.jo2.utils.comparators.BuddyByPresence;

import java.util.ArrayList;
import java.util.Arrays;import java.util.List;

import static org.junit.Assert.*;

public class BuddyListTest {

  private BuddyList bl;
  private Buddy BUDDY_A;
  private Buddy BUDDY_B;
  private Buddy BUDDY_C;
  private Buddy BUDDY_D;
  private Buddy BUDDY_E;

  @Before
  public void setUp() {
    BUDDY_A = new Buddy(new ContactInfo("aaa", "tlen.pl", "Aaa"), new Presence(PresenceType.AWAY, null));
    BUDDY_B = new Buddy(new ContactInfo("bbb", "tlen.pl", "Bbbb"), new Presence(PresenceType.AVAILABLE, null));
    BUDDY_C = new Buddy(new ContactInfo("ccc", "tlen.pl", "Cccc"), new Presence(PresenceType.INVISIBLE, null));
    BUDDY_D = new Buddy(new ContactInfo("ddd", "tlen.pl", "Dddd"), new Presence(PresenceType.AWAY, null));
    BUDDY_E = new Buddy(new ContactInfo("eee", "tlen.pl", "Dddd"), new Presence(PresenceType.XA, null));

    bl = new BuddyList(createList(BUDDY_A, BUDDY_B, BUDDY_C), new BuddyByPresence());
  }

  @Test
  public void sortOnCreateTest() {
    BuddyList testBuddyList = new BuddyList(createList(BUDDY_C, BUDDY_A, BUDDY_B, BUDDY_D),
        new BuddyByPresence());
    List<Buddy> buddies = testBuddyList.listBuddies();
    assertEquals(BUDDY_B, buddies.get(0));
    assertEquals(BUDDY_A, buddies.get(1));
    assertEquals(BUDDY_D, buddies.get(2));
    assertEquals(BUDDY_C, buddies.get(3));
  }

  @Test
  public void addTest() {
    assertFalse(bl.addBuddy(BUDDY_B));
    assertTrue(bl.addBuddy(BUDDY_D));
  }

  @Test
  public void deleteTest() {
    assertFalse(bl.deleteBuddy(BUDDY_D));
    assertTrue(bl.deleteBuddy(BUDDY_A));
  }

  @Test
  public void sortOnAddTest() {
    bl.addBuddy(BUDDY_D, BUDDY_D.getPresence());
    assertEquals(BUDDY_D, bl.listBuddies().get(2));
    bl.addBuddy(BUDDY_E, BUDDY_E.getPresence());
    assertEquals(BUDDY_E, bl.listBuddies().get(3));
  }

  @Test
  public void sortOnMultiAddTest() {
    assertTrue(bl.addBuddies(Arrays.asList(BUDDY_D, BUDDY_E)));
    assertEquals(BUDDY_D, bl.listBuddies().get(2));
    assertEquals(BUDDY_E, bl.listBuddies().get(3));

  }

  @Test
  public void sortOnEditTest() {
    assertEquals(BUDDY_A, bl.listBuddies().get(1));
    bl.changePresence(BUDDY_A, new Presence(PresenceType.AVAILABLE, null));
    assertEquals(BUDDY_A, bl.listBuddies().get(0));
  }

  @Test
  public void sortOnComparatorChange() {
    bl.setComparator(new BuddyByAlias());
    List<Buddy> buddies = bl.listBuddies();
    assertEquals(BUDDY_A, buddies.get(0));
    assertEquals(BUDDY_B, buddies.get(1));
    assertEquals(BUDDY_C, buddies.get(2));
  }

  private List<Buddy> createList(Buddy... buddies) {
    List<Buddy> list = new ArrayList<Buddy>();
    list.addAll(Arrays.asList(buddies));
    return list;
  }

}
