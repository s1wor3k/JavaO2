package pl.jo2.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import pl.jo2.model.Buddy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-12-02
 * Time: 19:14:36
 * <p/>
 * ta implementacja pozwala na zapisywanie listy kontaktow w pliku umieszczonym
 * w katalogu domowy uzytkownika
 * Przynajmniej w teorii wszsytkie sciezki i separatory katalogow sa niezalezne od SO
 */
public class FileBuddyListPersistence implements BuddyListPersistence {
  private static Logger LOGGER = Logger.getLogger("BuddyListPersistence");
  private static String BUDDY_LIST_FILE_PATH;
  private static String BUDDY_LIST_FILE_NAME = "buddylist.jo2";

  static {
    BUDDY_LIST_FILE_PATH = System.getProperty("user.home") + File.separator + ".JavaO2";
  }
  @SuppressWarnings("unchecked")
  @Override
  public List<Buddy> loadBuddies() throws IOException {
    File file = new File(BUDDY_LIST_FILE_PATH + File.separator + BUDDY_LIST_FILE_NAME);

    if (!file.exists()) {
      LOGGER.log(Level.INFO, "buddyListFile not found, return empty list");
      return new ArrayList<Buddy>();
    }
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuilder sb = new StringBuilder();
    String line;// = null;
    while ((line = reader.readLine()) != null) {
      sb.append(line);
    }
    XStream xs = new XStream(new DomDriver());

    return (List<Buddy>) xs.fromXML(sb.toString());
  }

  @Override
  public void saveBuddies(List<Buddy> input) throws IOException {
    File f = new File(BUDDY_LIST_FILE_PATH + File.separator + BUDDY_LIST_FILE_NAME);
    if (!f.exists()) {
      if (!f.getParentFile().exists()) {
        f.getParentFile().mkdirs();
      }
      f.createNewFile();
      XStream xs = new XStream(new DomDriver());
      String xml = xs.toXML(input);

      System.out.println(xml);

      FileWriter writer = new FileWriter(f, false);
      writer.write(xml);
      writer.flush();
      writer.close();
    }
  }

}
