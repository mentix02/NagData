package com.nagarro.NagData;

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

public class Vector<E> implements NagCollection<E> {

    private int length = 0;
    protected int capacity = 10;
    protected Object[] data = new Object[capacity];

    // Constructors

    public Vector() {
    }

    public Vector(Collection<? extends E> c) {
        for (E element : c)
            append(element);
    }

    // Methods to read data from Vector

    public int size() {
        return length;
    }

    public int getCapacity() {
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

    public void append(E element) {
        if (isFull())
            growCapacity();
        data[length++] = element;
    }

    public Iterator<E> iterator() {
        return new VectorIterator<>(this);
    }

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
