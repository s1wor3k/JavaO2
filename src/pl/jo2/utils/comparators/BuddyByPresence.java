package pl.jo2.utils.comparators;

import pl.jo2.model.Buddy;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-11-21
 * Time: 10:47:45
 */
public class BuddyByPresence implements Comparator<Buddy> {

  @Override
  public int compare(Buddy o1, Buddy o2) {
    if (o1 == o2) {
      return 0;
    }

    if (o1.getPresence().getType() != o2.getPresence().getType()) {
      return o1.getPresence().getType().compareTo(o2.getPresence().getType());

    } else {
      return o1.getContactInfo().getAlias().compareToIgnoreCase(o2.getContactInfo().getAlias());
    }
  }
}
