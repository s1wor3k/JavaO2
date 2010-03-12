package pl.jo2.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: przemek
 * Date: 2009-10-19
 * Time: 23:39:59
 * To change this template use File | Settings | File Templates.
 * <p/>
 * <p/>
 * type stanow jakie moze przyjmowac
 */
public enum PresenceType {

  AVAILABLE("available", false),//dostępny
  CHAT("chat", false), //	porozmawiajmy
  DND("dnd", false), //	jestem zajęty
  AWAY("away", false), //	zaraz wracam
  XA("xa", false), //	wrócę później
  INVISIBLE("invisible", true), //	niewidoczny
  UNAVAILABLE("unavailable", true); //	niedostepny

  private static final String BASIC_MESSAGE = "<icons.presence><show>{0}</show></icons.presence>";
  private static final String BASIC_MESSAGE_WITH_STATUS = "<icons.presence><show>{0}</show><icons.presence>{1}</icons.presence></icons.presence>";
  private static final String SPECIAL_MESSAGE = "<icons.presence type=\"{0}\"/>";
  private static final String SPECIAL_MESSAGE_WITH_STATUS = "<icons.presence type=\"{0}\"><icons.presence>{1}</icons.presence></icons.presence>";
  private static final String MESSAGE_ENCODING = "UTF-8";
  /**
   * wartosc jaka zostanie wysłana do serwera w celu ustawienia stanu
   */
  private String myIdentifier;
  /**
   * flaga okreslajaca czy stan jest tzw, stanem specjalnym.
   * true oznaczae ze jest, false natomiast oznacze ze ejst to stan podstawowy.
   * Stany specjalne róznia sie od podstawowych formatem wiadomości jaki nalezy
   * wysłac do serwera.
   */
  private boolean mySpecial;

  private PresenceType(String s, boolean b) {
    this.myIdentifier = s;
    this.mySpecial = b;
  }

  public String getIdentifier() {
    return myIdentifier;
  }

  /**
   * metoda generuje wiadomosc jaka powinna zostac wyslana do serwera w celu ustawienia danego stanu.
   * @return zwraca wygenerowana wiadomosc
   */
  public String generateChangePresenceMessage() {
    if (mySpecial) {
      return MessageFormat.format(SPECIAL_MESSAGE, myIdentifier);
    }
    return MessageFormat.format(BASIC_MESSAGE, myIdentifier);
  }

  /**
   * metoda generuje wiaodmosc potrzebna do ustawienia statusu opisowego
   * @return zwraca wygenerowana wiadomosc
   * */
  public String generateChangePresenceMessage(String status) {
//    if (icons.presence == null || icons.presence.isEmpty()) {
//      throw new IllegalArgumentException("icons.presence mustn't be empty nor null");
//    }
    String urlEncodedStatus;// = null;
    try {
      urlEncodedStatus = URLEncoder.encode(status, MESSAGE_ENCODING);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("nieobslugiwane kodowanie znakow", e);
    }
    if (mySpecial) {
      return MessageFormat.format(SPECIAL_MESSAGE_WITH_STATUS, myIdentifier, urlEncodedStatus);
    }
    return MessageFormat.format(BASIC_MESSAGE_WITH_STATUS, myIdentifier, urlEncodedStatus);
  }
}
