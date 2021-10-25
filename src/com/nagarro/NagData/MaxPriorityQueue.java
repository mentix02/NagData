package com.nagarro.NagData;

import java.util.Iterator;

/**
 * A simple max Priority Queue ADT implemented via {@link MaxHeap}.
 * This class is nothing but a wrapper around {@link MaxHeap} to
 * implement the API provided by {@link Queue}.
 *
 * @param <E> type of data to store
 */
public class MaxPriorityQueue<E extends Comparable<E>> extends MaxHeap<E> implements Queue<E> {

    public MaxPriorityQueue() {
    }

    public MaxPriorityQueue(Vector<E> vector) {
        super(vector);
    }

    @Override
    public void enqueue(E element) {
        this.add(element);
    }

    @Override
    public E dequeue() {
        return isEmpty() ? null : poll();
    }

    @Override
    public E peek() {
        return isEmpty() ? null : heap.get(0);
    }

    public Queue<E> reverse() {
        return new MinPriorityQueue<>(this.heap);
    }

    @SuppressWarnings("unchecked")
    public Object clone() {
        MaxPriorityQueue<E> pq = new MaxPriorityQueue<>();
        pq.heap = (Vector<E>) this.heap.clone();
        return pq;
    }

    public Iterator<E> iterator() {
        return new QueueIterator<>(this);
    }

}
