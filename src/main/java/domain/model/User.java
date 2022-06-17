package domain.model;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
  private final Library library;
  private final List<String> borrowedBooks;

  private static final Logger logger = Logger.getLogger(User.class.getName());

  public User(Library library) {
    this.library = library;
    this.borrowedBooks = new LinkedList<>();
  }

  public List<String> viewBooks() {
    return this.library.showBooks();
  }

  public void borrowABook(String book) {
    if (library.showBooks().isEmpty()) {
      return;
    }

    int borrowLimit = 2;
    if (borrowedBooks.size() >= borrowLimit) {
      logger.log(Level.WARNING, "Borrowing limit reached");
      return;
    }

    this.library.removeBook(book);
    this.borrowedBooks.add(book);
  }

  public Boolean isBorrowedBookListEmpty() {
    return this.borrowedBooks.isEmpty();
  }

  public Integer getNumberOfBorrowedBooks() {
    return this.borrowedBooks.size();
  }
}
