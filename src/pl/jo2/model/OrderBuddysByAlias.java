package pl.jo2.model;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-11-20
 * Time: 20:18:49
 */
public class OrderBuddysByAlias implements Comparator<Buddy> {

  @Override
  public int compare(Buddy o1, Buddy o2) {
    if (o1 == o2) {
      return 0;
    }
    if (o1.hashCode() == o2.hashCode() && o1.equals(o2)) {
      return 0;
    }

    /*  @return a negative integer, zero, or a positive integer as the
     * 	 first argument is less than, equal to, or greater than the
     *	 second. */

    

    return 0;
  }
}
