package com.nagarro.NagData.Stack;

import java.util.Iterator;

class StackIterator<E> implements Iterator<E> {

    private int count = 0;
    private final Stack<E> stack;

    public StackIterator(Stack<E> stack) {
        this.stack = stack;
    }

    @Override
    public boolean hasNext() {
        return count < stack.size();
    }

    @SuppressWarnings("unchecked")
    public E next() {
        return (E) stack.data[count++];
    }
}
