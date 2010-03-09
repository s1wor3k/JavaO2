package pl.jo2.utils;

import org.junit.Assert;
import org.junit.Test;
import pl.jo2.model.BuddyList;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-12-02
 * Time: 20:32:43
 */
public class BuddyListPeristenceTest {

  @Test
  public void testSave(){
    BuddyList bl = new BuddyList();
    BuddyListPersistence persistence = new FileBuddyListPersistence();

    try {
      persistence.saveBuddies(bl.listBuddies());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testLoad(){
    BuddyListPersistence persistence = new FileBuddyListPersistence();
    try {
      List l =  persistence.loadBuddies();
      Assert.assertNotNull(l);
      System.out.println(l);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
