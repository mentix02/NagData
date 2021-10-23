package com.nagarro.NagData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    static Stack<Integer> stack;
    final static int sSize = 100;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
        for (int i = 0; i < sSize; ++i)
            stack.push(i);
    }

    @Test
    void peek() {
        assertEquals(sSize - 1, stack.peek());
    }

    @Test
    void push() {
        assertEquals(sSize - 1, stack.peek());
        stack.push(100);
        assertEquals(100, stack.peek());
    }

    @Test
    void pop() {
        assertEquals(sSize - 1, stack.peek());
        assertEquals(sSize - 1, stack.pop());
        assertEquals(sSize - 2, stack.peek());
    }

    @Test
    void capacity() {
        int i, minCapacity = Stack.MIN_INITIAL_CAPACITY;
        stack = new Stack<>();
        assertEquals(stack.capacity(), minCapacity);
        for (i = 0; i < minCapacity; i++)
            stack.push(i);
        assertEquals(minCapacity, stack.capacity());
        stack.push(minCapacity + 1);
        assertEquals(minCapacity * 2, stack.capacity());
    }
}
