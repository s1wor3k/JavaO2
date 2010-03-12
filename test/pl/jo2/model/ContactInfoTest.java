package pl.jo2.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactInfoTest {
  private ContactInfo ci1;
  private ContactInfo ci2;

  @Before
  public void setUp() {
    ci1 = new ContactInfo("login1", "domain1", "alias1");
    ci2 = new ContactInfo("login2", "domain2", "alias2");
  }

  @Test
  public void equalsTest() {
    assertFalse(ci1.equals(ci2));

    ci2.setAlias("alias1");
    assertFalse(ci1.equals(ci2));

    ci2.setAlias("alias2");
    ci2.setLogin("login1");
    ci2.setDomain("domain1");
    assertTrue(ci1.equals(ci2));
  }
  @Test
  public void hashcodeTest() {
    assertFalse(ci1.hashCode() == ci2.hashCode());

    ci2.setAlias("alias1");
    assertFalse(ci1.hashCode() == ci2.hashCode());

    ci2.setAlias("alias2");
    ci2.setLogin("login1");
    ci2.setDomain("domain1");
    assertTrue(ci1.hashCode() == ci2.hashCode());
  }


}
