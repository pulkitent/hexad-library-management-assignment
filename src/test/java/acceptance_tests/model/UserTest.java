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
  @DisplayName("Given there are no books in library, when a user views a book then user sees an empty library")
  void testShowBooks_ShouldReturnEmptyListOfBooksWhenLibraryIsEmpty() {
    //Given
    List<String> expectedEmptyBooksList = EMPTY_LIST;
    Library library = new Library(expectedEmptyBooksList);
    User user = new User(library, EMPTY_LIST);

    //When
    List<String> actualBooks = user.viewBooks();

    //Then
    Assertions.assertTrue(actualBooks.isEmpty());
  }

  @Test
  @DisplayName("Given there are books in library, when a user views a book then user sees the list of books")
  void testShowBooks_ShouldReturnTwoBooksWhenLibraryHasTwoBooks() {
    //Given
    List<String> expectedBooks = Arrays.asList("Clean-Code-by-UncleBob", "Refactoring-by-Martin");
    Library library = new Library(expectedBooks);
    User user = new User(library, EMPTY_LIST);

    //When
    List<String> actualBooks = user.viewBooks();

    //Then
    Assertions.assertNotNull(actualBooks);
    Assertions.assertEquals(2, actualBooks.size());
    Assertions.assertEquals(expectedBooks.get(0), actualBooks.get(0));
    Assertions.assertEquals(expectedBooks.get(1), actualBooks.get(1));
  }

  @Test
  @DisplayName("Given there are no books in library, when a user chose to borrow a book then no book should be added into his borrowed list")
  void testBorrowABook_ShouldNotAddBookToBorrowedListWhenLibraryIsEmpty() {
    //Given
    List<String> expectedEmptyBooksList = EMPTY_LIST;
    Library library = new Library(expectedEmptyBooksList);
    User user = new User(library, EMPTY_LIST);

    //When
    user.borrowABook("clean-code");

    //Then
    Assertions.assertTrue(user.isBorrowedBookListEmpty());
  }

  @Test
  @DisplayName("Given there are 2 books in library, when a user chose to borrow a book then that book should be added into his borrowed list")
  void testBorrowABook_ShouldAddBookToBorrowedListWhenUserChoosesABook() {
    //Given
    String book = "Clean-Code-by-UncleBob";
    String anotherBook = "Refactoring-by-Martin";
    List<String> expectedBooks = new LinkedList<>();
    expectedBooks.add(book);
    expectedBooks.add(anotherBook);
    Library library = new Library(expectedBooks);
    User user = new User(library, new LinkedList<>());

    //When
    List<String> actualBooksInLibrary = user.viewBooks();

    //Then
    user.borrowABook(actualBooksInLibrary.get(0));
    Assertions.assertFalse(user.isBorrowedBookListEmpty());
  }

  @Test
  @DisplayName("Given there are 2 books in library and user borrow limit is reached, when a user chose to borrow a book then that book should not be added into his borrow list")
  void testBorrowABook_ShouldNotAddBookToBorrowedListWhenUserBorrowListHasAlreadyTwoBooks() {
    //Given
    String book = "Clean-Code-by-UncleBob";
    String anotherBook = "Refactoring-by-Martin";
    String thirdBook = "Desigining Data Intensive Application By Martin";
    List<String> expectedBooks = new LinkedList<>();
    expectedBooks.add(book);
    expectedBooks.add(anotherBook);
    expectedBooks.add(thirdBook);
    List<String> borrowList = new LinkedList<>();
    borrowList.add(book);
    borrowList.add(anotherBook);
    Library library = new Library(expectedBooks);
    User user = new User(library, borrowList);

    //When
    List<String> actualBooksInLibrary = user.viewBooks();

    //Then
    user.borrowABook(actualBooksInLibrary.get(2));
    Assertions.assertFalse(user.isBorrowedBookListEmpty());
    Assertions.assertEquals(2, user.getNumberOfBorrowedBooks());
  }
}
