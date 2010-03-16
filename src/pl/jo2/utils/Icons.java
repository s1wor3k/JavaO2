package pl.jo2.utils;

import pl.jo2.model.PresenceType;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-11-07
 * Time: 19:51:49
 */
public class Icons {

  public static Map<PresenceType, Icon> small_presence_icons;
  public static Map<PresenceType, Icon> medium_presence_icons;
  public static Icon SETTINGS;
  public static Icon clear_search_field;


  static {
    small_presence_icons = new HashMap<PresenceType, Icon>(PresenceType.values().length);
    medium_presence_icons = new HashMap<PresenceType, Icon>(PresenceType.values().length);
    ResourceBundle bundle = ResourceBundle.getBundle("pl/jo2/utils/IconPath");
    for (PresenceType t : PresenceType.values()) {
      Icon i = IconLoader.loadIcon(bundle.getString("presence.small." + t.getIdentifier()));
      small_presence_icons.put(t, i);
      i = IconLoader.loadIcon(bundle.getString("presence.medium." + t.getIdentifier()));
      medium_presence_icons.put(t, i);
    }

    SETTINGS = IconLoader.loadIcon(bundle.getString("toolbar.settings"));
    clear_search_field = IconLoader.loadIcon(bundle.getString("toolbar.clear.search"));

  }

}
