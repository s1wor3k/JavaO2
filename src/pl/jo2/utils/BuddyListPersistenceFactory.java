package pl.jo2.utils;

public class BuddyListPersistenceFactory {

  private static BuddyListPersistence buddyListPersistence = new BuddyListPersistenceMock();

  public static BuddyListPersistence getBuddyListPersistence() {
    return buddyListPersistence;
  }

}
