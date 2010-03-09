package pl.jo2.ui;

import pl.jo2.model.Buddy;
import pl.jo2.utils.IconPath;
import pl.jo2.utils.Icons;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-10-22
 * Time: 18:47:55
 *
 * Moj wlasny "kastomowy" cel renderer, ustawia ickone na odpowiadajaca statusowi,
 * dodatkowo formatuje text za pomoca <html>
 */
public class BuddyListCellRenderer extends DefaultListCellRenderer {

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    if (value instanceof Buddy) {
      Buddy buddy = (Buddy) value;
      JLabel jLabel = (JLabel) c;
      jLabel.setIcon(Icons.medium_presence_icons.get(buddy.getPresence().getType()));

      StringBuilder sb = new StringBuilder();
      sb.append("<html><b>");
      boolean aliasIsEmpty = (buddy.getAlias() == null || buddy.getAlias().isEmpty());
      sb.append(aliasIsEmpty ? (buddy.getId() + "@" + buddy.getDomain()) : buddy.getAlias());
      sb.append("</b><br>[");
      boolean statusIsEmpty = (buddy.getPresence().getStatus() == null || buddy.getPresence().getStatus().isEmpty());
      sb.append(statusIsEmpty ? buddy.getPresence().getType().getIdentifier() : buddy.getPresence().getStatus());
      sb.append("]</html>");

      jLabel.setText(sb.toString());
    }
    return c;
  }

}
