package pl.jo2;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import pl.jo2.core.BuddyListControllerImpl;
import javax.swing.*;


public class Main {

  public static void main(String args[]) throws UnsupportedLookAndFeelException {

//    UIManager.setLookAndFeel(new NimbusLookAndFeel());
    UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
    BuddyListControllerImpl buddyListController = new BuddyListControllerImpl();
    buddyListController.showBuddyList();
  }
}
