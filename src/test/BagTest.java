import com.example.iterable.Bag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for Bag class.
 * Covers empty bags, single and multiple items, removals, and iterator behavior.
 */
@DisplayName("Bag Tests – Comprehensive Coverage")
class BagTest {

    // ===================== CONSTRUCTOR TESTS =====================
    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Default constructor creates empty bag")
        void defaultConstructorCreatesEmptyBag() {
            Bag<String> bag = new Bag<>();
            assertTrue(bag.isEmpty());
            assertEquals(0, bag.size());
        }

        @Test
        @DisplayName("Constructor with valid initial capacity")
        void constructorWithCapacity() {
            Bag<Integer> bag = new Bag<>(10);
            assertEquals(0, bag.size());
        }

        @Test
        @DisplayName("Constructor throws exception for negative capacity")
        void constructorThrowsForNegativeCapacity() {
            assertThrows(IllegalArgumentException.class, () -> new Bag<String>(-1));
        }
    }

    // ===================== ADD TESTS =====================
    @Nested
    @DisplayName("Add Operation Tests")
    class AddTests {

        private Bag<String> bag;

        @BeforeEach
        void setUp() {
            bag = new Bag<>();
        }

        @Test
        @DisplayName("Adding one item works correctly")
        void addSingleItem() {
            bag.add("apple");
            assertFalse(bag.isEmpty());
            assertEquals(1, bag.size());
            assertTrue(bag.contains("apple"));
        }

        @Test
        @DisplayName("Adding multiple items increases size and stores all")
        void addMultipleItems() {
            bag.add("apple");
            bag.add("banana");
            bag.add("cherry");
            assertEquals(3, bag.size());
            assertTrue(bag.contains("banana"));
        }

        @Test
        @DisplayName("Adding duplicate items allowed")
        void addDuplicatesAllowed() {
            bag.add("apple");
            bag.add("apple");
            assertEquals(2, bag.size());
        }

        @Test
        @DisplayName("Adding null throws IllegalArgumentException")
        void addNullThrows() {
            assertThrows(IllegalArgumentException.class, () -> bag.add(null));
        }
    }

    // ===================== REMOVE TESTS =====================
    @Nested
    @DisplayName("Remove Operation Tests")
    class RemoveTests {

        private Bag<String> bag;

        @BeforeEach
        void setUp() {
            bag = new Bag<>();
            bag.add("apple");
            bag.add("banana");
            bag.add("cherry");
        }

        @Test
        @DisplayName("Remove existing element works")
        void removeExistingItem() {
            assertTrue(bag.remove("banana"));
            assertFalse(bag.contains("banana"));
            assertEquals(2, bag.size());
        }

        @Test
        @DisplayName("Remove one duplicate instance only")
        void removeOneDuplicateInstance() {
            bag.add("apple");
            assertEquals(4, bag.size());
            assertTrue(bag.remove("apple"));
            assertTrue(bag.contains("apple"));
            assertEquals(3, bag.size());
        }

        @Test
        @DisplayName("Remove returns false for missing element")
        void removeNonExisting() {
            assertFalse(bag.remove("orange"));
            assertEquals(3, bag.size());
        }

        @Test
        @DisplayName("Remove returns false for null")
        void removeNullReturnsFalse() {
            assertFalse(bag.remove(null));
        }

        @Test
        @DisplayName("Removing all items makes bag empty")
        void removeAllItemsEmptiesBag() {
            bag.remove("apple");
            bag.remove("banana");
            bag.remove("cherry");
            assertTrue(bag.isEmpty());
        }
    }

    // ===================== CONTAINS TESTS =====================
    @Nested
    @DisplayName("Contains Tests")
    class ContainsTests {

        private Bag<String> bag;

        @BeforeEach
        void setUp() {
            bag = new Bag<>();
            bag.add("apple");
            bag.add("banana");
        }

        @Test
        @DisplayName("Contains returns true for existing item")
        void containsExistingItem() {
            assertTrue(bag.contains("apple"));
        }

        @Test
        @DisplayName("Contains returns false for missing item")
        void containsMissingItem() {
            assertFalse(bag.contains("grape"));
        }

        @Test
        @DisplayName("Contains returns false for null")
        void containsNullReturnsFalse() {
            assertFalse(bag.contains(null));
        }

        @Test
        @DisplayName("Contains returns false for empty bag")
        void containsInEmptyBag() {
            Bag<String> emptyBag = new Bag<>();
            assertFalse(emptyBag.contains("apple"));
        }
    }

    // ===================== ITERATOR TESTS =====================
    @Nested
    @DisplayName("Iterator Behavior Tests")
    class IteratorTests {

        private Bag<String> bag;

        @BeforeEach
        void setUp() {
            bag = new Bag<>();
            bag.add("apple");
            bag.add("banana");
            bag.add("cherry");
        }

        @Test
        @DisplayName("Iterator iterates over all elements")
        void iteratorCoversAllElements() {
            Iterator<String> it = bag.iterator();
            int count = 0;
            while (it.hasNext()) {
                assertNotNull(it.next());
                count++;
            }
            assertEquals(bag.size(), count);
        }

        @Test
        @DisplayName("Iterator on empty bag has no elements")
        void iteratorEmptyBagHasNoElements() {
            Bag<String> emptyBag = new Bag<>();
            Iterator<String> it = emptyBag.iterator();
            assertFalse(it.hasNext());
        }

        @Test
        @DisplayName("Iterator throws NoSuchElementException when exhausted")
        void iteratorThrowsAfterEnd() {
            Iterator<String> it = bag.iterator();
            while (it.hasNext()) {
                it.next();
            }
            assertThrows(NoSuchElementException.class, it::next);
        }
    }

    // ===================== UTILITY METHOD TESTS =====================
    @Nested
    @DisplayName("Utility and Equality Tests")
    class UtilityTests {

        private Bag<String> bag;

        @BeforeEach
        void setUp() {
            bag = new Bag<>();
            bag.add("apple");
            bag.add("banana");
        }

        @Test
        @DisplayName("toString includes all items")
        void toStringIncludesItems() {
            String result = bag.toString();
            assertTrue(result.contains("apple"));
            assertTrue(result.contains("banana"));
        }

        @Test
        @DisplayName("clear empties the bag")
        void clearEmptiesBag() {
            bag.clear();
            assertTrue(bag.isEmpty());
            assertEquals(0, bag.size());
        }

        @Test
        @DisplayName("toArray returns all elements")
        void toArrayContainsAllItems() {
            Object[] arr = bag.toArray();
            assertEquals(2, arr.length);
        }

        @Test
        @DisplayName("equals true for identical bags")
        void equalsIdenticalBags() {
            Bag<String> b2 = new Bag<>();
            b2.add("apple");
            b2.add("banana");
            assertTrue(bag.equals(b2));
        }

        @Test
        @DisplayName("equals false for different bags")
        void equalsDifferentBags() {
            Bag<String> b2 = new Bag<>();
            b2.add("apple");
            b2.add("cherry");
            assertFalse(bag.equals(b2));
        }

        @Test
        @DisplayName("hashCode consistent between calls")
        void hashCodeConsistency() {
            int h1 = bag.hashCode();
            int h2 = bag.hashCode();
            assertEquals(h1, h2);
        }

        @Test
        @DisplayName("isEmpty reflects correctly after add/remove")
        void isEmptyCorrectlyReflectsState() {
            Bag<Integer> b = new Bag<>();
            assertTrue(b.isEmpty());
            b.add(42);
            assertFalse(b.isEmpty());
            b.remove(42);
            assertTrue(b.isEmpty());
        }
    }
}
