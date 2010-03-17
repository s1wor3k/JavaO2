package pl.jo2.core;

import pl.jo2.model.Buddy;
import pl.jo2.model.BuddyList;
import pl.jo2.ui.BuddyListPanel;
import pl.jo2.ui.ChatPanel;
import pl.jo2.utils.BuddyListPersistence;
import pl.jo2.utils.BuddyListPersistenceFactory;
import pl.jo2.utils.comparators.BuddyByPresence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class BuddyListControllerImpl {

  //TODO jak juz bede wiedzial jakie to ma miec metody to wyciagne je do interfejsu

  private BuddyList buddyList;
  private BuddyListPanel buddyListPanel;
  private Map<Buddy, JFrame> openedChatWindows = new HashMap<Buddy, JFrame>();
  private BuddyListPersistence buddyListPersistence = BuddyListPersistenceFactory.getBuddyListPersistence();

  public BuddyListControllerImpl() {
    try {
      this.buddyList = new BuddyList(buddyListPersistence.loadBuddies(), new BuddyByPresence());
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "nie udalo sie wczytac listy kontaktow", "blad", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
  }


  public void addBuddy() {
    JOptionPane.showMessageDialog(buddyListPanel.getMainPanel(), "dialog do wprowadzania danych nowego kontaktu");
    //buddyList.addBuddy(nowy obiekt z dialogu);
  }

  public void deleteBuddy(Buddy b) {
    //TODO zaimplementowac i pomyslec co ma przyjmowac metoda jako parametr
    buddyList.deleteBuddy(b);
    System.out.println(this.getClass().getName() + ".deleteBuddy(): + " + b);
  }

  public void showBuddyList() {
    if (buddyListPanel == null) {
      buddyListPanel = new BuddyListPanel(this, buddyList);
    }
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        JFrame frame = new JFrame("buddy list");
        frame.setContentPane(buddyListPanel.getMainPanel());
        frame.pack();
        frame.setLocation(getDefaultLocation(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
      }

      private Point getDefaultLocation(JFrame frame) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        return new Point(screenSize.width - screenSize.width / 3, screenSize.height / 2 - frame.getPreferredSize().height / 2);
      }
    });

  }

  public void openChatWindow(final Buddy buddy) {

    JFrame chatWindow = openedChatWindows.get(buddy);
    if (chatWindow != null) {
      chatWindow.requestFocus();
      return;
    }

    chatWindow = new JFrame(buddy.getContactInfo().getAlias());
    chatWindow.setContentPane(new ChatPanel().getMainPanel());
    chatWindow.setSize(200, 200);
    chatWindow.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        openedChatWindows.remove(buddy);
      }

    });
    chatWindow.setVisible(true);
    openedChatWindows.put(buddy, chatWindow);
  }


}
