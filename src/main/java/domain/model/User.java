package domain.model;

import java.util.List;

public class User {
  private final Library library;

  public User(Library library) {
    this.library = library;
  }

  public List<String> viewBooks() {
    return library.showBooks();
  }
}
