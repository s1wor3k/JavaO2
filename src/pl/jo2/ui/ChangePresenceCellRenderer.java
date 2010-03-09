package pl.jo2.ui;

import pl.jo2.model.PresenceType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-10-27
 * Time: 20:16:37
 * To change this template use File | Settings | File Templates.
 */
public class ChangePresenceCellRenderer extends DefaultListCellRenderer {

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    if (value instanceof PresenceType) {
      PresenceType type = (PresenceType) value;
      String path = "img/icons.presence/22/" + type.getIdentifier() + ".png";
      setText( -1 == index ? "" : type.getIdentifier());
//      setText("");
      setToolTipText(type.getIdentifier());
      setIcon(new ImageIcon(path));
    }
    return label;
  }
}
