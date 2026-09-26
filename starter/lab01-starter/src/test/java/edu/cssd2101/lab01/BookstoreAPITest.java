package edu.cssd2101.lab01;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookstoreAPITest {
    //This section is testing adding new books and duplicates for ArrayListBookstore
    @Test
    void addNewBookArrayList(){
        BookstoreAPI ArrayListStore = new ArrayListBookstore();
        //test if a new book with a unique ISBN can be added to the store
        //the test should return true and pass
        Book newBook = new Book("1123456789112", "And then there were none", "Agatha Christie", (long) 14.50, 1939);
        assertTrue(ArrayListStore.add(newBook));
    }

    @Test
    void addDuplicateBookArrayList(){
        BookstoreAPI ArrayListStore = new ArrayListBookstore();
        //test if a duplicate book with a duplicate ISBN can be added to the store
        //the test should return false
        //the new book we are adding into our ArrayList store
        Book newBook = new Book("1123456789112", "And then there were none", "Agatha Christie", (long) 14.50, 1939);
        //the duplicate book with a matching ISBN but different info
        Book duplicateBook = new Book("1123456789112", "Murder on the Orient Express", "Agatha Christie", (long) 17.70, 1934);
        ArrayListStore.add(newBook);
        //we should not be allowed to add the duplicate book
        assertFalse(ArrayListStore.add(duplicateBook));
        //System.out.println(ArrayListStore.allBooks());
    }


    //This section is testing adding new books and duplicates for ArrayListBookstore
    //it also test when a user tries to add a new book, but the capacity has been reached
    @Test
    void addNewBookFixedArray(){
        BookstoreAPI FixedArrayStore = new FixedArrayBookstore(10);
        //test if a new book with a unique ISBN can be added to the store
        //the test should return true and pass
        Book newBook = new Book("1123456789112", "And then there were none", "Agatha Christie", (long) 14.50, 1939);
        assertTrue(FixedArrayStore.add(newBook));
    }

    @Test
    void addDuplicateBookFixedArray(){
        BookstoreAPI FixedArrayStore = new FixedArrayBookstore(10);
        //test if a duplicate book with a duplicate ISBN can be added to the store
        //the test should return false
        //the new book we are adding into our ArrayList store
        Book newBook = new Book("1123456789112", "And then there were none", "Agatha Christie", (long) 14.50, 1939);
        //the duplicate book with a matching ISBN but different info
        Book duplicateBook = new Book("1123456789112", "Murder on the Orient Express", "Agatha Christie", (long) 17.70, 1934);
        FixedArrayStore.add(newBook);
        //we should not be allowed to add the duplicate book
        assertFalse(FixedArrayStore.add(duplicateBook));
        //System.out.println(ArrayListStore.allBooks());
    }

    @Test
    void addNewBookToFullFixedArray(){
        BookstoreAPI FixedArrayStore = new FixedArrayBookstore(2);
        //test if a new book with a unique ISBN can be added to the store if the capacity is full
        //the test should return throw an IllegalStateException
        Book firstBook = new Book("1123456789112", "And then there were none", "Agatha Christie", (long) 14.50, 1939);
        Book secondBook = new Book("1123456789113", "Murder on the Orient Express", "Agatha Christie", (long) 17.70, 1934);
        Book thirdBook = new Book("1123456789114", "A Murder is Announced", "Agatha Christie", (long) 67.70, 1950);
        FixedArrayStore.add(firstBook);
        FixedArrayStore.add(secondBook);
        assertThrows(IllegalStateException.class, () ->FixedArrayStore.add(thirdBook));
    }

    //TODO:add a test to make sure the original book that isn't the duplicate is still there using the optional find by isbn (or assert same using .get())
    //TODO: add ISBN normalization

    //this section will test looking up a book object using ISBN
    //case a: the object exists, and thus the method should return the book with the matching isbn
    @Test
    void findExistingBookArrayList(){
        BookstoreAPI ArrayListStore = new ArrayListBookstore();

        Book firstBook = new Book("1123456789112", "And then there were none", "Agatha Christie", (long) 14.50, 1939);
        Book secondBook = new Book("1123456789113", "Murder on the Orient Express", "Agatha Christie", (long) 17.70, 1934);
        Book thirdBook = new Book("1123456789114", "A Murder is Announced", "Agatha Christie", (long) 67.70, 1950);
        ArrayListStore.add(firstBook);
        ArrayListStore.add(secondBook);
        ArrayListStore.add(thirdBook);
        Optional<Book> foundBook = ArrayListStore.findByIsbn("1123456789114");
        assertTrue(foundBook.isPresent());
        assertSame(thirdBook,foundBook.get());
    }
    //case b: the object does not exist, and thus the method should return empty
    @Test
    void findNonExistingBookArrayList(){
        BookstoreAPI ArrayListStore = new ArrayListBookstore();

        Book firstBook = new Book("1123456789112", "And then there were none", "Agatha Christie", (long) 14.50, 1939);
        Book secondBook = new Book("1123456789113", "Murder on the Orient Express", "Agatha Christie", (long) 17.70, 1934);
        Book thirdBook = new Book("1123456789114", "A Murder is Announced", "Agatha Christie", (long) 67.70, 1950);
        ArrayListStore.add(firstBook);
        ArrayListStore.add(secondBook);
        ArrayListStore.add(thirdBook);
        Optional<Book> foundBook = ArrayListStore.findByIsbn("1123456789115");
        assertFalse(foundBook.isPresent());
        //assertNotSame(thirdBook,foundBook.get());
    }

    // should not accept null or deformed ISBN
    @Test
    void nullISBNShouldCauseNullPointerExceptionArrayList(){
        BookstoreAPI ArrayListStore = new ArrayListBookstore();
        assertThrows(NullPointerException.class, ()->ArrayListStore.findByIsbn(null));
        assertThrows(IllegalArgumentException.class, ()->ArrayListStore.findByIsbn("123"));

    }

    //for fixedArray
    //case a: the object exists, and thus the method should return the book with the matching isbn
    @Test
    void findExistingBookFixedArray(){
        BookstoreAPI FixedArrayStore = new FixedArrayBookstore(5);

        Book firstBook = new Book("1123456789112", "And then there were none", "Agatha Christie", (long) 14.50, 1939);
        Book secondBook = new Book("1123456789113", "Murder on the Orient Express", "Agatha Christie", (long) 17.70, 1934);
        Book thirdBook = new Book("1123456789114", "A Murder is Announced", "Agatha Christie", (long) 67.70, 1950);
        FixedArrayStore.add(firstBook);
        FixedArrayStore.add(secondBook);
        FixedArrayStore.add(thirdBook);
        Optional<Book> foundBook = FixedArrayStore.findByIsbn("1123456789114");
        assertTrue(foundBook.isPresent());
        assertSame(thirdBook,foundBook.get());
    }
    //case b: the object does not exist, and thus the method should return empty
    @Test
    void findNonExistingBookFixedArray(){
        BookstoreAPI FixedArrayStore = new FixedArrayBookstore(5);

        Book firstBook = new Book("1123456789112", "And then there were none", "Agatha Christie", (long) 14.50, 1939);
        Book secondBook = new Book("1123456789113", "Murder on the Orient Express", "Agatha Christie", (long) 17.70, 1934);
        Book thirdBook = new Book("1123456789114", "A Murder is Announced", "Agatha Christie", (long) 67.70, 1950);
        FixedArrayStore.add(firstBook);
        FixedArrayStore.add(secondBook);
        FixedArrayStore.add(thirdBook);
        Optional<Book> foundBook = FixedArrayStore.findByIsbn("1123456789115");
        assertFalse(foundBook.isPresent());
        //assertNotSame(thirdBook,foundBook.get());
    }

    // should not accept null or deformed ISBN
    @Test
    void nullISBNShouldCauseNullPointerExceptionFixedArray(){
        BookstoreAPI FixedArrayStore = new FixedArrayBookstore(5);
        assertThrows(NullPointerException.class, ()->FixedArrayStore.findByIsbn(null));
        assertThrows(IllegalArgumentException.class, ()->FixedArrayStore.findByIsbn("123"));

    }
    //This section will handle removing a Book from the store
    //Todo: add this section once Dom completes T3 for remove by ISBN
    @Test
    void objectRemainsUnchangedAfterSnapshot() {
        //This section will handle making a snapshot of the Books in the store
        BookstoreAPI ArrayListStore = new ArrayListBookstore();

        Book firstBook = new Book("1123456789112", "And then there were none", "Agatha Christie", (long) 14.50, 1939);
        ArrayListStore.add(firstBook);
        Book[] snap = ArrayListStore.snapshotArray();
        //once you make a snapshot of the original store, check that the original store exists and that it's not null, even if we delete the snapshot's item
        snap[0] = null;
        assertNotNull(ArrayListStore);
        assertSame(firstBook, ArrayListStore.findByIsbn(firstBook.isbn()).get());
        //add an item to the original list and check that the snapshot doesn't have the new book
        //todo: add this test as a seperate test (SnapshotRemainsUnchangedAfterChangingOriginal)
    }

    @Test
    void snapshotRemainsUnchangedAfterChangingOriginal() {
        //This section will handle making a snapshot of the Books in the store
        BookstoreAPI ArrayListStore = new ArrayListBookstore();

        Book firstBook = new Book("1123456789112", "And then there were none", "Agatha Christie", (long) 14.50, 1939);
        ArrayListStore.add(firstBook);
        Book[] snap = ArrayListStore.snapshotArray();
        //once you make a snapshot of the original store,
        //add an item to the original list and check that the snapshot doesn't have the new book
        Book secondBook = new Book("1123456789113", "Murder on the Orient Express", "Agatha Christie", (long) 17.70, 1934);
        ArrayListStore.add(secondBook);
        assertEquals(1,snap.length);
        assertSame(firstBook, snap[0]);
    }

}