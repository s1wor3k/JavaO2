package pl.jo2;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import pl.jo2.core.BuddyListControllerImpl;

import javax.swing.*;


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

    BuddyListControllerImpl buddyListController = new BuddyListControllerImpl();
    buddyListController.showBuddyList();
  }
}
