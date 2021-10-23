package com.nagarro.NagData;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Collection;

class VectorIterator<E> implements Iterator<E> {

    private int count = 0;
    private final Vector<E> vector;

    public VectorIterator(Vector<E> vector) {
        this.vector = vector;
    }

    @Override
    public boolean hasNext() {
        return count < vector.size();
    }

    @SuppressWarnings("unchecked")
    public E next() {
        return (E) vector.data[count++];
    }
}

/**
 * <tt>Vector</tt> class implements an infinitely
 * growing generic array.
 * <p>
 * Every time the default array reached max capacity,
 * the old elements are copied into an array twice as big
 * as the old one and the new element is inserted at
 * the very end. Old array is replaced by new bigger one.
 *
 * @param <E> type of element to store
 * @author mentix02
 */
public class Vector<E> implements NagCollection<E> {

    public static final int MIN_INITIAL_CAPACITY = 10;

    private int length = 0;
    protected int _capacity = MIN_INITIAL_CAPACITY;
    protected Object[] data = new Object[_capacity];

    // Constructors

    public Vector() {
    }

    /**
     * Creates a <tt>Vector</tt> from a standard <tt>Collection</tt> object
     *
     * @param c collection to create array from
     */
    public Vector(Collection<? extends E> c) {
        int idx = 0;
        length = _capacity = c.size();
        data = new Object[_capacity];
        for (E el : c)
            data[idx++] = el;
    }

    // Methods to read data from Vector

    public int size() {
        return length;
    }

    public int capacity() {
        return _capacity;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean isFull() {
        return length == _capacity;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) throws IndexOutOfBoundsException {
        return (E) data[index];
    }

    // Methods to manipulate Vector

    public void clear() {
        data = new Object[0];
        length = _capacity = 0;
    }

    public void append(E element) {
        if (isFull())
            growCapacity();
        data[length++] = element;
    }

    public void set(int index, E element) throws IndexOutOfBoundsException {
        this.data[index] = element;
    }

    public Iterator<E> iterator() {
        return new VectorIterator<>(this);
    }

    /**
     * Reverses internal <tt>data</tt> array in-place in O(n) time.
     */
    public void reverse() {
        Object temp;
        int length = size();
        for (int i = 0; i < length / 2; i++) {
            temp = data[i];
            data[i] = data[length - i - 1];
            data[length - i - 1] = temp;
        }
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";

        StringBuilder stringBuilder = new StringBuilder("[ ");
        for (E e : this) {
            stringBuilder.append(e).append(", ");
        }

        // Remove last redundant comma
        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), " ]");

        return stringBuilder.toString();
    }

    public Object[] toArray() {
        return Arrays.copyOf(data, length);
    }

    protected void growCapacity() {
        // Create a new array of twice the size
        Object[] longerData = new Object[_capacity * 2];

        // Copy old elements into new array
        if (_capacity >= 0)
            System.arraycopy(data, 0, longerData, 0, _capacity);

        // Replace small old array with larger one
        data = longerData;
        // and update increased capacity
        _capacity *= 2;
    }

}
