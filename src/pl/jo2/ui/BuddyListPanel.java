package pl.jo2.ui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import pl.jo2.actions.ChangePresenceAction;
import pl.jo2.core.BuddyListControllerImpl;
import pl.jo2.model.Buddy;
import pl.jo2.model.BuddyList;
import pl.jo2.model.BuddyListChangeListener;
import pl.jo2.model.PresenceType;
import pl.jo2.utils.Icons;
import pl.jo2.utils.KeyCodes;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;

public class BuddyListPanel implements BuddyListChangeListener {
  private JPanel panel1;
  private JList buddies;
  private JTextField searchTextField;
  private JButton presenceButton;
  private JButton settingsButton;
  private JButton cButton;
  private JPanel searchPanel;
  private JPopupMenu presencePopup;
  private JPopupMenu settingsPopup;
  private JPopupMenu buddiesPopup;


  private ResourceBundle bundle = ResourceBundle.getBundle("pl/jo2/ui/BuddyListPanel");
  private BuddyListControllerImpl buddyListController;
  private BuddyList buddyList;

  public BuddyListPanel(BuddyListControllerImpl buddyListController, BuddyList buddyList) {
    this.buddyListController = buddyListController;
    this.buddyList = buddyList;
    this.buddyList.addBuddyListChangeListener(this);
    $$$setupUI$$$();
  }

  public JPanel getMainPanel() {
    return panel1;
  }

  /**
   * w zalozeniach metode ma wykozystywac jakis obiekt controller ktory bedzie edytowal liste kontakow, a pozniej
   * odswierzal widok
   */

  public void refreshBuddyList() {
    buddies.setModel(createModel(buddyList.listBuddies()));
  }

  private void setupSearchPanel() {
    searchPanel = new JPanel();
    searchPanel.setBorder(searchTextField.getBorder());
    searchTextField.setBorder(null);
    searchPanel.setBackground(searchTextField.getBackground());
    cButton = new JButton(new ImageIcon("/home/przemek/clear.png"));
    cButton.setBorderPainted(false);
    cButton.setContentAreaFilled(false);
    cButton.setFocusable(false);
  }

  private void setupSearchTextField() {
    searchTextField = new JTextField();
    searchTextField.setToolTipText("search");
    searchTextField.setOpaque(false);
    // document listener dla listy
    //TODO filtrowanie listy na stale, bo znika przy odswiezeniu
    searchTextField.getDocument().addDocumentListener(new DocumentListener() {
      public void insertUpdate(DocumentEvent e) {
        String documentText = searchTextField.getText();
        ListModel listModel = filterBuddys(buddies.getModel(), documentText);
        buddies.setModel(listModel);
        if (listModel.getSize() == 1 && listModel.getElementAt(0) instanceof String) {
          buddies.setEnabled(false);
        } else {
          buddies.setEnabled(true);
        }
      }

      public void removeUpdate(DocumentEvent e) {
        String documentText = searchTextField.getText();
        ListModel listModel = filterBuddys(createModel(buddyList.listBuddies()), documentText);
        buddies.setModel(listModel);
        if (listModel.getSize() == 1 && listModel.getElementAt(0) instanceof String) {
          buddies.setEnabled(false);
        } else {
          buddies.setEnabled(true);
        }
      }

      public void changedUpdate(DocumentEvent e) {
        //Nic nie robi bo tak
      }

      private ListModel filterBuddys(ListModel m, String criteria) {
        DefaultListModel result = new DefaultListModel();
        for (int i = 0, j = m.getSize(); i < j; i++) {
          Object o = m.getElementAt(i);
          if (!(o instanceof Buddy)) {
            continue;
          }
          Buddy b = (Buddy) o;
          if (b.getContactInfo().getAlias().toLowerCase().contains(criteria.toLowerCase())) {
            result.addElement(b);
          }
        }
        if (result.getSize() == 0) {
          result.addElement("no results...");
        }
        return result;
      }
    });

    // keyListener dla pola wyszukiwania
    // reaguje tylko na strzalke w dol
    searchTextField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
          case KeyCodes.DOWN:
            if (buddies.getModel().getSize() > 0 && buddies.isEnabled()) {
              buddies.grabFocus();
              buddies.setSelectedIndex(0);
            }
            break;
          case KeyCodes.ESCAPE:
            searchTextField.setText("");
            break;
        }
      }
    });
  }

  private void setupBuddyList() {
    buddies = new JList();
    buddies.setCellRenderer(new BuddyListCellRenderer());
    buddies.setModel(createModel(buddyList.listBuddies()));
    buddies.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        if (KeyCodes.ENTER == e.getKeyCode()) {
          if (buddies.getSelectedIndex() != -1) {
            showChatFrame((Buddy) buddies.getSelectedValue());
          }
        }
      }
    });
    buddies.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (2 == e.getClickCount()) {
          if (buddies.getSelectedIndex() != -1) {
            showChatFrame((Buddy) buddies.getSelectedValue());
          }
        }
      }

      @Override
      public void mousePressed(MouseEvent e) {
        selectAndShowPopUp(e);

      }

      @Override
      public void mouseReleased(MouseEvent e) {
        selectAndShowPopUp(e);
      }

      /*
     * metoda podobno jest "os independent", a za zadanie ma wymuszenie zaznaczania
     * elementu po kliknieciu prawym przyciskiem lub czymstam na macach :)
     * */
      private void selectAndShowPopUp(MouseEvent e) {
        JList l = (JList) e.getSource();
        if (e.isPopupTrigger() && l.isEnabled()) {
          l.setSelectedIndex(l.locationToIndex(e.getPoint()));
          buddiesPopup.show((Component) e.getSource(), e.getX(), e.getY());
        }
      }
    });

  }


  private void showChatFrame(Buddy b) {
    buddyListController.openChatWindow(b);
  }

  // funkcja "pakuje" elementy listy do klasy DefaultListModel

  private DefaultListModel createModel(List<Buddy> buddyList) {
    DefaultListModel model = new DefaultListModel();
    for (Buddy b : buddyList) {
      model.addElement(b);
    }
    return model;
  }

  private void setupPresenceButton() {
//    String path = MessageFormat.format(IconPath.PRESENCE_ICON_PATH_SMALL, PresenceType.AVAILABLE.getIdentifier());
    presenceButton = new ToolbarButton(Icons.small_presence_icons.get(PresenceType.AVAILABLE));
    presenceButton.setToolTipText("<html>Change <b>Presence</b>");
    presenceButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int x = (int) (e.getComponent().getWidth() - presencePopup.getPreferredSize().getWidth() - 2);
        int y = e.getComponent().getHeight() - 2;
        presencePopup.show(e.getComponent(), x, y);
      }
    });
  }

  private void setupSettingsButton() {
    //settingsButton = new ToolbarButton(IconLoader.getInstance().loadIcon(IconPath.SETTINGS_ICON_PATH));
    settingsButton = new ToolbarButton(Icons.SETTINGS);
    settingsButton.setToolTipText("<html><b>Settings");
    settingsButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int x = (int) (e.getComponent().getWidth() - settingsPopup.getPreferredSize().getWidth() - 2);
        int y = e.getComponent().getHeight() - 2;
        settingsPopup.show(e.getComponent(), x, y);
      }
    });

  }

  private void setupSettingPopup() {
    settingsPopup = new JPopupMenu();
    settingsPopup.add("options");
    JMenu buddies = new JMenu("buddies");
    buddies.add("add new");
    buddies.add("import from server");
    buddies.add("import from file");
    buddies.add("export to server");
    buddies.add("export to file");
    settingsPopup.add(buddies);

    settingsPopup.addSeparator();
    JMenuItem exitMenuItem = new JMenuItem("exit");
    exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
    exitMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("powinno wyjsc");
      }
    });
    settingsPopup.add(exitMenuItem);
  }

  private void setupPresencePopup() {
    presencePopup = new JPopupMenu();
    for (PresenceType p : PresenceType.values()) {
      //String s = MessageFormat.format(IconPath.PRESENCE_ICON_PATH_SMALL, p.getIdentifier());
      JMenuItem menuItem = new JMenuItem(Icons.small_presence_icons.get(p));
      menuItem.setText(bundle.getString("icons.presence." + p.getIdentifier()));
      menuItem.addActionListener(new ChangePresenceAction(p));
      presencePopup.add(menuItem);
    }
  }

  private void setupBuddiestPopup() {
    buddiesPopup = new JPopupMenu();
    //OPEN CHAT
    JMenuItem chat = new JMenuItem(bundle.getString("list.chat"));
    chat.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showChatFrame((Buddy) buddies.getSelectedValue());
      }
    });
    buddiesPopup.add(chat);
    //DELETE BUDDY
    JMenuItem delBuddy = new JMenuItem(bundle.getString("list.delete"));
    delBuddy.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("delete buddy" + buddies.getSelectedValue());
        buddyListController.deleteBuddy((Buddy) buddies.getSelectedValue());
      }
    });
    buddiesPopup.add(delBuddy);
    //EDIT BUDDY
    JMenuItem editBuddy = new JMenuItem(bundle.getString("list.edit"));
    editBuddy.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("TODO wyswietlajaca dialog z formularzem edycji");
      }
    });
    buddiesPopup.add(editBuddy);
  }

  private void createUIComponents() {
    setupBuddyList();
    setupSearchTextField();
    setupPresenceButton();
    setupPresencePopup();
    setupSettingsButton();
    setupSettingPopup();
    setupBuddiestPopup();
    setupSearchPanel();
  }

  @Override
  public void buddyListChanged() {
    refreshBuddyList();
  }

  /**
   * Method generated by IntelliJ IDEA GUI Designer
   * >>> IMPORTANT!! <<<
   * DO NOT edit this method OR call it in your code!
   *
   * @noinspection ALL
   */
  private void $$$setupUI$$$() {
    createUIComponents();
    panel1 = new JPanel();
    panel1.setLayout(new FormLayout("fill:d:grow", "center:30px:noGrow,top:0dlu:noGrow,center:d:grow"));
    final JScrollPane scrollPane1 = new JScrollPane();
    scrollPane1.setPreferredSize(new Dimension(206, 282));
    CellConstraints cc = new CellConstraints();
    panel1.add(scrollPane1, cc.xy(1, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
    buddies.setSelectionMode(0);
    scrollPane1.setViewportView(buddies);
    final JPanel panel2 = new JPanel();
    panel2.setLayout(new FormLayout("fill:p:grow,left:0dlu:noGrow,center:26px:noGrow,left:0dlu:noGrow,fill:26px:noGrow", "center:d:noGrow"));
    panel1.add(panel2, cc.xy(1, 1));
    panel2.add(presenceButton, cc.xy(3, 1, CellConstraints.FILL, CellConstraints.FILL));
    panel2.add(settingsButton, cc.xy(5, 1, CellConstraints.FILL, CellConstraints.FILL));
    searchPanel.setLayout(new BorderLayout(0, 0));
    panel2.add(searchPanel, cc.xy(1, 1, CellConstraints.FILL, CellConstraints.FILL));
    searchTextField.setMargin(new Insets(0, 3, 0, 0));
    searchPanel.add(searchTextField, BorderLayout.CENTER);
    searchPanel.add(cButton, BorderLayout.EAST);
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return panel1;
  }
}
