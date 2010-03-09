package pl.jo2;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import pl.jo2.core.BuddyListControllerImpl;
import pl.jo2.model.BuddyList;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-10-19
 * Time: 23:21:43
 */
public class Main {

  public static void main(String args[]) throws UnsupportedLookAndFeelException {

    UIManager.setLookAndFeel(new NimbusLookAndFeel());
    BuddyListControllerImpl buddyListController = new BuddyListControllerImpl(new BuddyList());//, new BuddyListPanel());
    buddyListController.showBuddyList();
  }
}
