package pl.jo2.model;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-11-20
 * Time: 18:35:13
 * <p/>
 * <p/>
 * interfejs który powinien byc zaimplementowany przez klasy zainteresowane
 * zmianami stanu listy kontakow {@link pl.jo2.model.BuddyList}
 */
public interface BuddyListChangeListener {
  void buddyListChanged();
}
