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

    public static final int MIN_INITIAL_CAPACITY = 1 << 4;

    private int length = 0;
    /**
     * Actual container to store data in.
     */
    protected Object[] data;
    protected int capacity = MIN_INITIAL_CAPACITY;

    // Constructors

    public Vector() {
        data = new Object[capacity];
    }

    /**
     * Initialize a <code>Vector</code> object with a provided initial capacity.
     *
     * @param capacity number of items to reserve memory for
     */
    public Vector(int capacity) {
        this.capacity = capacity;
        data = new Object[capacity];
    }

    /**
     * Creates a <tt>Vector</tt> from a standard <tt>Collection</tt> object
     *
     * @param c collection to create array from
     */
    public Vector(Collection<? extends E> c) {
        int idx = 0;
        length = capacity = c.size();
        data = new Object[capacity];
        for (E el : c)
            data[idx++] = el;
    }

    // Methods to read data from Vector

    public int size() {
        return length;
    }

    public boolean contains(E toFind) {
        for (E el : this)
            if (el.equals(toFind))
                return true;
        return false;
    }

    public int capacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean isFull() {
        return length == capacity;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) throws IndexOutOfBoundsException {
        return (E) data[index];
    }

    // Methods to manipulate Vector

    public void clear() {
        data = new Object[0];
        length = capacity = 0;
    }

    public void append(E element) {
        if (isFull())
            growCapacity();
        data[length++] = element;
    }

    public void add(E element) {
        append(element);
    }

    public void add(int index, E element) {
        insert(index, element);
    }

    /**
     * @param idx position of element to be deleted
     * @return element at idx which was deleted
     */
    @SuppressWarnings("unchecked")
    public E remove(int idx) {
        if (isEmpty())
            return null;
        else if (idx < 0)
            throw new IllegalArgumentException("Cannot delete at negative index.");
        else if (idx == 0)
            return removeFirst();

        E toReturn = (E) data[idx];
        if (length - idx >= 0)
            System.arraycopy(data, idx + 1, data, idx, length - idx);
        length--;
        return toReturn;
    }

    /**
     * Removes last element.
     *
     * @return last element before it is deleted or null if vector is empty
     */
    @SuppressWarnings("unchecked")
    public E remove() {
        if (isEmpty())
            return null;
        E toReturn = (E) data[length - 1];
        data[--length] = null;
        return toReturn;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E pop() {
        return remove();
    }

    public void insert(int index, E element) {
        if (index < 0)
            throw new IllegalArgumentException("Cannot insert at negative index.");
        else if (index >= length) {
            append(element);
            return;
        }

        if (isFull())
            growCapacity();

        length++;

        if (length - index >= 0)
            System.arraycopy(data, index, data, index + 1, length - index);

        data[index] = element;
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
    public void selfReverse() {
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

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public Object clone() {
        Vector<E> vector = new Vector<>(length);
        vector.data = this.data.clone();
        vector.length = length;
        return vector;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vector<?> vector = (Vector<?>) o;
        return length == vector.length && Arrays.equals(data, vector.data);
    }

    public Object[] toArray() {
        return Arrays.copyOf(data, length);
    }

    protected void growCapacity() {
        // Create a new array of twice the size
        Object[] longerData = new Object[capacity * 2];

        // Copy old elements into new array
        if (capacity >= 0)
            System.arraycopy(data, 0, longerData, 0, capacity);

        // Replace small old array with larger one
        data = longerData;
        // and update increased capacity
        capacity *= 2;
    }

}
