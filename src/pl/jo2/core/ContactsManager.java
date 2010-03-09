package pl.jo2.core;

import pl.jo2.model.Buddy;

import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-10-19
 * Time: 23:37:01
 * To change this template use File | Settings | File Templates.
 */
public interface ContactsManager {
  void addBuddy(Buddy b);  
  void deleteBuddy(Buddy b);
  List<Buddy> getBuddies();
}
