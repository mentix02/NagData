package com.nagarro.NagData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    static Queue<Integer> queue;
    final static int qSize = 100;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
        for (int i = 0; i < 100; i++)
            queue.enqueue(i);
    }

    @Test
    void peek() {
        assertEquals(0, queue.peek());
    }

    @Test
    void enqueue() {
        queue = new Queue<>();
        queue.enqueue(0);
        queue.enqueue(1);
        assertEquals(0, queue.peek());
        queue.dequeue();
        assertEquals(1, queue.peek());
    }

    @Test
    void dequeue() {
        assertEquals(0, queue.dequeue());
        assertEquals(1, queue.dequeue());
    }

}