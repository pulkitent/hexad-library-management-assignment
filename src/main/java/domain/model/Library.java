package domain.model;

import java.util.List;

public class Library {
  private final List<String> books;

  public Library(List<String> books) {
    this.books = books;
  }

  List<String> showBooks() {
    return books;
  }
}
