package pl.jo2.utils.comparators;

import pl.jo2.model.Buddy;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-11-21
 * Time: 10:45:16
 */
public class BuddyByAlias implements Comparator<Buddy> {
  @Override
  public int compare(Buddy o1, Buddy o2) {
    if (o1 == o2) {
      return 0;
    }
    return o1.getContactInfo().getAlias().compareToIgnoreCase(o2.getContactInfo().getAlias());
  }
}
