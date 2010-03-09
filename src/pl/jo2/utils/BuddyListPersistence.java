package pl.jo2.utils;

import pl.jo2.model.Buddy;

import java.io.IOException;
import java.util.List;

public interface BuddyListPersistence {
  List<Buddy> loadBuddies() throws IOException;

  void saveBuddies(List<Buddy> input) throws IOException;
}
