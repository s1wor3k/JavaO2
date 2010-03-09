package pl.jo2.core;

import pl.jo2.model.Buddy;
import pl.jo2.model.BuddyList;
import pl.jo2.ui.BuddyListPanel;
import pl.jo2.ui.ChatPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-11-21
 * Time: 22:14:05
 */
public class BuddyListControllerImpl {
  //TODO jak juz bede wiedzial jakie to ma miec metody to wyciagne je do interfejsu

  private BuddyList buddyList;
  private BuddyListPanel buddyListPanel;
  private Map<Buddy, JFrame> openChatWindows = new HashMap<Buddy, JFrame>();

  public BuddyListControllerImpl(BuddyList buddyList) {
    this.buddyList = buddyList;
    //this.buddyListPanel = buddyListPanel;
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

    JFrame chatWindow = openChatWindows.get(buddy);
    if (chatWindow != null) {
      chatWindow.requestFocus();
      return;
    }

    chatWindow = new JFrame(buddy.getAlias());
    chatWindow.setContentPane(new ChatPanel().getMainPanel());
    chatWindow.setSize(200, 200);
    chatWindow.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        openChatWindows.remove(buddy);
      }

    });
    chatWindow.setVisible(true);
    openChatWindows.put(buddy, chatWindow);
  }


}
