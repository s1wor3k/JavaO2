package pl.jo2.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-10-30
 * Time: 18:11:04
 * To change this template use File | Settings | File Templates.
 */
public class ToolbarButton extends JButton {

  private static DrawContentOnMouseHover dcomh = new DrawContentOnMouseHover();

  public ToolbarButton() {
    super();
    setup();
  }

  public ToolbarButton(Icon icon) {
    super(icon);
    setup();
  }

  public ToolbarButton(String text) {
    super(text);
    setup();
  }

  public ToolbarButton(Action a) {
    super(a);
    setup();
  }

  public ToolbarButton(String text, Icon icon) {
    super(text, icon);
    setup();
  }

  private void setup() {
    setFocusable(false);
    setContentAreaFilled(false);
    setBorderPainted(false);
    addMouseListener(dcomh);
  }


  private static class DrawContentOnMouseHover extends MouseAdapter {
    @Override
    public void mouseEntered(MouseEvent e) {
      JButton b = (JButton) e.getComponent();
      b.setContentAreaFilled(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
      JButton b = (JButton) e.getComponent();
      b.setContentAreaFilled(false);
    }
  }
}
