package pl.jo2.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-10-30
 * Time: 19:43:32
 * To change this template use File | Settings | File Templates.
 */
public class Settings {

  private static Properties settings = null;

  public static Properties getSettings() {
    if (settings == null) {

    }
    return settings;
  }

  private static final String USER_HOME = "user.home";

  private void xxx() {
    String userHomeDir = System.getProperty(USER_HOME);
    File settingDir = new File(userHomeDir + File.separator + ".JavaO2");
    if (!settingDir.exists()) {
      settingDir.mkdirs();
    }

    File windowSettingFile = new File(settingDir.getAbsolutePath() + File.separator + "windowSettings");
    if (!windowSettingFile.exists()) {
      try {
        windowSettingFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

}
