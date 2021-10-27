package com.nagarro.NagData.queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class MaxPriorityQueueTest {

    static Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new MaxPriorityQueue<>();
        queue.enqueue(3);
        queue.enqueue(-3);
        queue.enqueue(6);
    }

    @Test
    void peek() {

        assertEquals(6, queue.peek());
        queue.dequeue();

        assertEquals(3, queue.peek());
        queue.dequeue();

        assertEquals(-3, queue.dequeue());
        queue.dequeue();
    }

    @Test
    void dequeue() {
        assertEquals(6, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(-3, queue.dequeue());
    }

    @Test
    void reverse() {
        queue = queue.reverse();
        assertEquals(-3, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(6, queue.dequeue());
    }
}
