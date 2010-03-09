package pl.jo2.actions;

import pl.jo2.core.PresenceController;
import pl.jo2.core.PresenceControllerMock;
import pl.jo2.model.PresenceType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-11-06
 * Time: 22:14:01
 */
public class ChangePresenceAction implements ActionListener {

  private final PresenceType type;
  private final PresenceController presenceController;

  public ChangePresenceAction(PresenceType type) {
    this.type = type;
    presenceController = new PresenceControllerMock();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    SwingWorker w = new ChangePresenceWorker(e);
    w.execute();
  }

  /*
  * SwingWorker sluzy do wykonywania dlugo trwajacych zadan w tle, wykozystywany zeby
  * nie "zapychac" EventDispatchingThread co powodowaloby zablokowanie GUI.
  *
  * ten konkretny obiekt wywoluje metode changePresence a po jej zakonczeniu aktualizuje
  * ikone stanu w wyswietlany na przycisku changePresenceButton. Szczerze to nie wiem czy nie lepsza
  * bylaby wersja z anonimowa klasa, ale jesli nie znajde dobrego powodu zostanie jak jest.
  * */
  private class ChangePresenceWorker extends SwingWorker<Void, Void> {
    private ActionEvent actionEvent;

    ChangePresenceWorker(ActionEvent e) {
      actionEvent = e;
    }

    @Override
    protected Void doInBackground() throws Exception {
      presenceController.changePresence(type);
      return null;
    }

    @Override
    protected void done() {
      Object source = actionEvent.getSource();
      if (source instanceof JMenuItem) {
        JMenuItem jMenuItem = (JMenuItem) source;
        JButton button = (JButton) ((JPopupMenu) jMenuItem.getParent()).getInvoker();
        button.setIcon(jMenuItem.getIcon());
      }
    }
  }
}
