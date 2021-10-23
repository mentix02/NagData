package com.nagarro.NagData;

import java.util.EmptyStackException;

/**
 * A <tt>Stack</tt> implemented via an array based <tt>Vector</tt>.
 *
 * @param <E> type of element to store
 * @author mentix02
 */
public class Stack<E> extends Vector<E> {

    private int top = -1;

    // Methods to read from stack

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == _capacity - 1;
    }

    @Override
    public int size() {
        return top + 1;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty())
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

}
