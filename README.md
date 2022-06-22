# Library Management System

This assignment purpose is to showcase my clean code & best engineering practice skills

### Build

* ./gradlew clean build

### Run acceptance tests

* ./gradlew clean test

### Test report

/library-management/build/reports/tests/test/classes/acceptance_tests.model.UserTest.html

## Things I tried to follow

1. Tried not to break encapsulation by avoiding getters & setters (as much as possible) - Tell don't ask principle

2. Tried to have an immutable state with value objects (as much as possible) to avoid concurrency issues (Thread safety)

3. Tried to have readable methods & variables naming to clear the intention - 4 rules of simple design

4. Tried to have small & logical commits

5. Tried to avoid code duplication by refactoring/reusing duplicate code - DRY

6. Didn't make interfaces as per YAGNI principles because for now, I don't feel the need for the same PS: Yes, I am
   aware of this principle also - "Program to interface rather than concrete implementation"

## Thoughts/Assumptions (might differ from person to person)

1. I could have also created Book.java as it was noun in problem statement but somehow I didn't feel right to have that
   because that model was very anaemic (Having no behaviours in it)
   I am a supporter of DDD which recommends fat models & thin services

2. Also top keep it simple (KISS) and keeping in mind MVP, I took books as string rather than an independent model

3. I could also have used mocking in my UserTest.java & then I would have also written tests for Library.java, but I
   wanted to test the behaviour of my system rather than implementation. If I had used mocked then those tests were
   coupled to the implementation. PS: As Martin Fowler suggests UserTest.java is kind of a social unit test

## Problem statement

**1. User can view books in library**

     Given, there are no books in the library
     When, I view the books in the library
     Then, I see an empty library
    
    Given, there are books in the library
    When, I view the books in the library
    Then, I see the list of books in the library

**2. User can borrow a book from the library**

    Given, there are books in the library
    When, I choose a book to add to my borrowed list
    Then, the book is added to my borrowed list
    And, the book is removed from the library

Note: Each User has a borrowing limit of 2 books at any point of time

**3. User can borrow a copy of a book from the library**

    Given, there are more than one copy of a book in the library
    When, I choose a book to add to my borrowed list
    Then, one copy of the book is added to my borrowed list
    And, the library has at least one copy of the book left
    
    Given, there is only one copy of a book in the library
    When, I choose a book to add to my borrowed list
    Then, one copy of the book is added to my borrowed list
    And, the book is removed from the library

Note: Only 1 copy of a book can be borrowed by a User at any point of time

**4. User can return books to the library**

    Given, I have 2 books in my borrowed list
    When, I return one book to the library
    Then, the book is removed from my borrowed list
    And, the library reflects the updated stock of the book
    
    Given, I have 2 books in my borrowed list
    When, I return both books to the library
    Then, my borrowed list is empty
    And, the library reflects the updated stock of the books