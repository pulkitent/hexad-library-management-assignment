package acceptance_tests.model;

import domain.model.Library;
import domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class UserTest {
  private static final List<String> EMPTY_LIST = Collections.emptyList();

  @Test
  @DisplayName("Given there are no books in library" +
      "when a user views a book" +
      "then user sees an empty library")
  void testShowBooks_ShouldReturnEmptyListWhenLibraryIsEmpty() {
    //Given
    Library library = new Library(EMPTY_LIST);
    User user = new User(library);

    //When
    List<String> actualBooks = user.viewBooks();

    //Then
    Assertions.assertTrue(actualBooks.isEmpty());
  }

  @Test
  @DisplayName("Given there are 2 books in library" +
      "when a user views the books" +
      "then user sees the list of books in the library")
  void testShowBooks_ShouldReturnTwoBooksWhenLibraryHasTwoBooks() {
    //Given
    List<String> expectedBooksInLibrary = Arrays.asList("Clean-Code-by-UncleBob", "Refactoring-by-Martin");
    Library library = new Library(expectedBooksInLibrary);
    User user = new User(library);

    //When
    List<String> actualBooks = user.viewBooks();

    //Then
    Assertions.assertNotNull(actualBooks);
    Assertions.assertEquals(2, actualBooks.size());
    Assertions.assertEquals(expectedBooksInLibrary.get(0), actualBooks.get(0));
    Assertions.assertEquals(expectedBooksInLibrary.get(1), actualBooks.get(1));
  }

  @Test
  @DisplayName("Given there are no books in library" +
      "when a user chose to borrow a book" +
      "then no book should be added into his borrowed list")
  void testBorrowABook_ShouldNotAddBookToBorrowedListWhenLibraryIsEmpty() {
    //Given
    Library library = new Library(EMPTY_LIST);
    User user = new User(library);

    //When
    user.borrowABook("clean-code");

    //Then
    Assertions.assertTrue(user.isBorrowedBookListEmpty());
  }

  @Test
  @DisplayName("Given there are 2 books in library" +
      "when a user chose to borrow a book" +
      "then that book should be added into his borrowed list")
  void testBorrowABook_ShouldAddBookToBorrowedListWhenUserChoosesABook() {
    //Given
    String book = "Clean-Code-by-UncleBob";
    String anotherBook = "Refactoring-by-Martin";
    List<String> books = new LinkedList<>();
    books.add(book);
    books.add(anotherBook);
    Library library = new Library(books);
    User user = new User(library);
    List<String> actualBooksInLibrary = user.viewBooks();

    //When
    user.borrowABook(actualBooksInLibrary.get(0));

    //Then
    Assertions.assertFalse(user.isBorrowedBookListEmpty());
  }

  @Test
  @DisplayName("Given there are 3 books in library and user borrow limit is reached" +
      "when a user chooses to borrow a book " +
      "then that book should not be added into his borrowed list")
  void testBorrowABook_ShouldNotAddBookToBorrowedListWhenUserBorrowListHasAlreadyTwoBooks() {
    //Given
    String book = "Clean-Code-by-UncleBob";
    String secondBook = "Refactoring-by-Martin";
    String thirdBook = "Designing Data Intensive Application By Martin";
    List<String> books = new LinkedList<>();
    books.add(book);
    books.add(secondBook);
    books.add(thirdBook);
    Library library = new Library(books);
    User user = new User(library);
    user.borrowABook(book);
    user.borrowABook(secondBook);

    //When
    user.borrowABook(thirdBook);

    //Then
    Assertions.assertFalse(user.isBorrowedBookListEmpty());
    Assertions.assertEquals(2, user.getNumberOfBorrowedBooks());
  }

  @Test
  @DisplayName("Given there are more than one copy of a book in library" +
      "when a user chooses to borrow a book" +
      "then one copy of that book should be added into his borrowed list " +
      "and library has at least one copy")
  void testBorrowABook_ShouldAddBookToBorrowedListWhenLibraryHasMoreThanOneCopy() {
    //Given
    String book1 = "Clean-Code-by-UncleBob";
    String book1AnotherCopy = "Clean-Code-by-UncleBob";
    String secondBook = "Refactoring-by-Martin";
    String thirdBook = "Designing Data Intensive Application By Martin";
    List<String> books = new LinkedList<>();
    books.add(book1);
    books.add(book1AnotherCopy);
    books.add(secondBook);
    books.add(thirdBook);
    Library library = new Library(books);
    User user = new User(library);
    List<String> actualBooksInLibrary = user.viewBooks();
    String bookBorrowed = actualBooksInLibrary.get(0);

    //When
    user.borrowABook(bookBorrowed);

    //Then
    Assertions.assertFalse(user.isBorrowedBookListEmpty());
    Assertions.assertEquals(1, user.getNumberOfBorrowedBooks());
    Assertions.assertEquals(1, library.getNumberOfCopiesOfGivenBook(bookBorrowed));
  }

  @Test
  @DisplayName("Given there are more than two copies of a book in library" +
      "when a user chooses to borrow two copies of same book" +
      "then one copy of that book should be added into his borrowed list " +
      "and library has two copies of that book")
  void testBorrowABook_ShouldAddOneCopyOfBookToBorrowedListWhenLibraryHasThreeCopy() {
    //Given
    String book1 = "Clean-Code-by-UncleBob";
    String book1SecondCopy = "Clean-Code-by-UncleBob";
    String book1ThirdCopy = "Clean-Code-by-UncleBob";
    String secondBook = "Refactoring-by-Martin";
    String thirdBook = "Designing Data Intensive Application By Martin";
    List<String> books = new LinkedList<>();
    books.add(book1);
    books.add(book1SecondCopy);
    books.add(book1ThirdCopy);
    books.add(secondBook);
    books.add(thirdBook);
    Library library = new Library(books);
    User user = new User(library);
    List<String> actualBooksInLibrary = user.viewBooks();
    String bookBorrowed = actualBooksInLibrary.get(0);

    //When
    user.borrowABook(bookBorrowed);
    user.borrowABook(bookBorrowed);

    //Then
    Assertions.assertFalse(user.isBorrowedBookListEmpty());
    Assertions.assertEquals(1, user.getNumberOfBorrowedBooks());
    Assertions.assertEquals(2, library.getNumberOfCopiesOfGivenBook(bookBorrowed));
  }
}
