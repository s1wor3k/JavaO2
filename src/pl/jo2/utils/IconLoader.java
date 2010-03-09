/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-11-01
 * Time: 11:09:35
 * To change this template use File | Settings | File Templates.
 */
package pl.jo2.utils;

import javax.swing.*;
import java.net.URL;

public class IconLoader {
  private static IconLoader ourInstance = new IconLoader();

//  public static IconLoader getInstance() {
//    return ourInstance;
//  }

  private IconLoader() {
  }

  public static ImageIcon loadIcon(String path) {
    URL resPath = ourInstance.getClass().getResource(path);
//    System.out.println("path: " + path + "; icon path:" + resPath);
    if (resPath == null)
      return null;

    return new ImageIcon(resPath);
  }
}
