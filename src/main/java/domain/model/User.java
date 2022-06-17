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
      logger.log(Level.WARNING, "Library is empty");
      return;
    }

    int borrowLimit = 2;
    if (borrowedBooks.size() >= borrowLimit) {
      logger.log(Level.WARNING, "Borrowing limit reached for user");
      return;
    }

    if (Boolean.TRUE.equals(isGivenCopyAlreadyBorrowed(book))) {
      logger.log(Level.WARNING, "Only one copy of a book can be borrowed by a user");
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

  public void returnABook(String book) {
    if (this.borrowedBooks.isEmpty() || !this.borrowedBooks.contains(book)) {
      logger.log(Level.WARNING, "User hasn't borrowed any book from library");
      return;
    }

    this.borrowedBooks.remove(book);
    this.library.addBook(book);
  }

  private Boolean isGivenCopyAlreadyBorrowed(String book) {
    return this.borrowedBooks.contains(book);
  }
}
