package com.nagarro.NagData.Stack;

import com.nagarro.NagData.NagCollection;

import java.util.Iterator;
import java.util.EmptyStackException;

public class Stack<E> implements NagCollection<E> {

    private int top = -1;
    private int capacity = 10;
    protected Object[] data = new Object[capacity];

    public Stack() {
    }

    // Methods to read from stack

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public int getCapacity() {
        return capacity;
    }

    public int size() {
        return top + 1;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (isFull())
            throw new EmptyStackException();
        return (E) data[top];
    }

    // Methods to manipulate stack

    public void push(E element) {
        // Makes sure data[] has room to push a new element.
        if (isFull())
            growCapacity();
        data[++top] = element;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return (E) data[top--];
    }

    // Conversion & transformation methods

    public void reverse() {
        Object temp;
        int length = size();
        for (int i = 0; i < length / 2; i++) {
            temp = data[i];
            data[i] = data[length - i - 1];
            data[length - i - 1] = temp;
        }
    }

    public Iterator<E> iterator() {
        return new StackIterator<>(this);
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

    // Private methods

    private void growCapacity() {
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
