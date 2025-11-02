package com.example.iterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * A generic Bag (multiset) implementation that stores elements in no particular order.
 * Allows duplicate elements and provides basic container operations.
 *
 * <p>This implementation uses ArrayList as the backing data structure and provides
 * O(1) time for add operations and O(n) time for remove, contains, and iteration operations.</p>
 *
 * @param <E> the type of elements stored in this bag
 * @author
 * @version 2.0
 */
public class Bag<E> implements Container<E> {

    private ArrayList<E> items;

    /** Constructs an empty bag with default initial capacity. */
    public Bag() {
        items = new ArrayList<>();
    }

    /** Constructs an empty bag with the specified initial capacity. */
    public Bag(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative: " + initialCapacity);
        }
        items = new ArrayList<>(initialCapacity);
    }

    @Override
    public void add(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null items to the bag");
        }
        items.add(item);
    }

    @Override
    public boolean remove(E item) {
        if (item == null) {
            return false;
        }
        return items.remove(item);
    }

    @Override
    public boolean contains(E item) {
        if (item == null) {
            return false;
        }
        return items.contains(item);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }

    public int capacity() {
        try {
            java.lang.reflect.Field field = ArrayList.class.getDeclaredField("elementData");
            field.setAccessible(true);
            return ((Object[]) field.get(items)).length;
        } catch (Exception e) {
            return items.size();
        }
    }

    @Override
    public String toString() {
        return items.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Bag<?> other)) return false;
        return Objects.equals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

    public void clear() {
        items.clear();
    }

    public Object[] toArray() {
        return items.toArray();
    }

    public void trimToSize() {
        items.trimToSize();
    }

    public void ensureCapacity(int minCapacity) {
        items.ensureCapacity(minCapacity);
    }
}
