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

2. I could also have used mocking in my UserTest.java & then I would have also written tests for Library.java, but I
   wanted to test the behaviour of my system rather than implementation. If I had used mocked then those tests were
   coupled to the implementation. PS: As Martin Fowler suggests UserTest.java is kind of a social unit test