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

  public static void main(String args[]) {


    SwingUtilities.invokeLater(new Runnable(){

      @Override
      public void run() {
        try {
          UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
          e.printStackTrace();
        }
      }
    });

    //TODO buddyListController sam powinien tworzyc buddyList a nie zadac jej w konstruktorze
    BuddyListControllerImpl buddyListController = new BuddyListControllerImpl();//, new BuddyListPanel());
    buddyListController.showBuddyList();
  }
}
