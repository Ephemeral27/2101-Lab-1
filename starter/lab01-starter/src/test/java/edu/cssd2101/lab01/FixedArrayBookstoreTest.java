package edu.cssd2101.lab01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FixedArrayBookstoreTest {

    @Test
    void removesFirstEntry() {
        FixedArrayBookstore store = new FixedArrayBookstore(3);
        store.add(new Book("1234567890", "A", "Author A", 1000, 2000));
        store.add(new Book("1234567891", "B", "Author B", 1000, 2000));
        store.add(new Book("1234567892", "C", "Author C", 1000, 2000));

        boolean result = store.removeByIsbn("1234567890");

        assertTrue(result);
        assertEquals(2, store.size());
    }

    @Test
    void removesMiddleEntry() {
        FixedArrayBookstore store = new FixedArrayBookstore(3);
        store.add(new Book("1234567890", "A", "Author A", 1000, 2000));
        store.add(new Book("1234567891", "B", "Author B", 1000, 2000));
        store.add(new Book("1234567892", "C", "Author C", 1000, 2000));

        boolean result = store.removeByIsbn("1234567891");

        assertTrue(result);
        assertEquals(2, store.size());
    }

    @Test
    void removesLastEntry() {
        FixedArrayBookstore store = new FixedArrayBookstore(3);
        store.add(new Book("1234567890", "A", "Author A", 1000, 2000));
        store.add(new Book("1234567891", "B", "Author B", 1000, 2000));
        store.add(new Book("1234567892", "C", "Author C", 1000, 2000));

        boolean result = store.removeByIsbn("1234567892");

        assertTrue(result);
        assertEquals(2, store.size());
    }

    @Test
    void returnsFalseForMissingIsbn() {
        FixedArrayBookstore store = new FixedArrayBookstore(3);
        store.add(new Book("1234567890", "A", "Author A", 1000, 2000));

        boolean result = store.removeByIsbn("0000000000");

        assertFalse(result);
        assertEquals(1, store.size());
    }

    @Test
    void emptyingStoreWorks() {
        FixedArrayBookstore store = new FixedArrayBookstore(2);
        store.add(new Book("1234567890", "A", "Author A", 1000, 2000));
        store.add(new Book("1234567891", "B", "Author B", 1000, 2000));

        assertTrue(store.removeByIsbn("1234567890"));
        assertTrue(store.removeByIsbn("1234567891"));
        assertEquals(0, store.size());
    }

    @Test
    void reusesVacatedSlotOnNextAdd() {
        FixedArrayBookstore store = new FixedArrayBookstore(2);
        store.add(new Book("1234567890", "A", "Author A", 1000, 2000));
        store.add(new Book("1234567891", "B", "Author B", 1000, 2000));

        store.removeByIsbn("1234567890");
        boolean added = store.add(new Book("1234567892", "C", "Author C", 1000, 2000));

        assertTrue(added);
        assertEquals(2, store.size());
    }

    @Test
    void duplicateAtFullCapacityReturnsFalse() {
        FixedArrayBookstore store = new FixedArrayBookstore(1);
        store.add(new Book("1234567890", "A", "Author A", 1000, 2000));

        boolean added = store.add(new Book("1234567890", "A2", "Author A2", 2000, 2010));

        assertFalse(added);
        assertEquals(1, store.size());
    }

    @Test
    void newIdentityAtFullCapacityThrows() {
        FixedArrayBookstore store = new FixedArrayBookstore(1);
        store.add(new Book("1234567890", "A", "Author A", 1000, 2000));

        assertThrows(IllegalStateException.class, () -> store.add(new Book("9999999999", "B", "Author B", 1000, 2000)));
        assertEquals(1, store.size());
    }

    @Test
    void zeroCapacityStoreBehavesConsistently() {
        FixedArrayBookstore store = new FixedArrayBookstore(0);

        assertFalse(store.removeByIsbn("1234567890"));
        assertEquals(0, store.size());
    }

    @Test
    void negativeCapacityRejectedByConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new FixedArrayBookstore(-1));
    }

}
