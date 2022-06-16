package acceptance_tests.model;

import domain.model.Library;
import domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class UserTest {
  @Test
  @DisplayName("Given there are no books in library, when a user views a book then user sees an empty library")
  void testShowBooks_ShouldReturnEmptyListOfBooksWhenLibraryIsEmpty() {
    //Given
    List<String> expectedEmptyBooksList = null;
    Library library = new Library(expectedEmptyBooksList);
    User user = new User(library);

    //When
    List<String> actualBooks = user.viewBooks();

    //Then
    Assertions.assertNull(actualBooks);
  }

  @Test
  @DisplayName("Given there are books in library, when a user views a book then user sees the list of books")
  void testShowBooks_ShouldReturnTwoBooksWhenLibraryHasTwoBooks() {
    //Given
    List<String> expectedBooks = Arrays.asList("Clean-Code-by-UncleBob", "Refactoring-by-Martin");
    Library library = new Library(expectedBooks);
    User user = new User(library);

    //When
    List<String> actualBooks = user.viewBooks();

    //Then
    Assertions.assertNotNull(actualBooks);
    Assertions.assertEquals(2, actualBooks.size());
    Assertions.assertEquals(expectedBooks.get(0), actualBooks.get(0));
    Assertions.assertEquals(expectedBooks.get(1), actualBooks.get(1));
  }
}
