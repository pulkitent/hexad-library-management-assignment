package domain.model;

import java.util.List;

public class Library {
  private final List<String> books;

  public Library(List<String> books) {
    this.books = books;
  }

  public List<String> showBooks() {
    return this.books;
  }

  public void removeBook(String book) {
    this.books.remove(book);
  }
}
