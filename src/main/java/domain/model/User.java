package domain.model;

import java.util.List;

public class User {
  private final Library library;
  private final List<String> borrowedBooks;

  public User(Library library, List<String> borrowedBooks) {
    this.library = library;
    this.borrowedBooks = borrowedBooks;
  }

  public List<String> viewBooks() {
    return this.library.showBooks();
  }

  public void borrowABook(String book) {
    if (library.showBooks().isEmpty()) {
      return;
    }

    this.library.removeBook(book);
    this.borrowedBooks.add(book);
  }

  public Boolean isBorrowedBookListEmpty() {
    return this.borrowedBooks.isEmpty();
  }
}
