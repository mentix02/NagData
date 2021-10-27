package com.nagarro.NagData.queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class LinearQueueTest {

    static LinearQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new LinearQueue<>();
        for (int i = 0; i < 100; i++)
            queue.enqueue(i);
    }

    @Test
    void peek() {
        assertEquals(0, queue.peek());
    }

    @Test
    void enqueue() {
        queue = new LinearQueue<>();
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