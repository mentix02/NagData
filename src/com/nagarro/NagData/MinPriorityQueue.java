package com.nagarro.NagData;

import java.util.Iterator;

public class MinPriorityQueue<E extends Comparable<E>> extends MinHeap<E> implements Queue<E> {

    public MinPriorityQueue() {
    }

    public MinPriorityQueue(Vector<E> vector) {
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
        return new MaxPriorityQueue<>(this.heap);
    }

    @SuppressWarnings("unchecked")
    public Object clone() {
        MinPriorityQueue<E> pq = new MinPriorityQueue<>();
        pq.heap = (Vector<E>) this.heap.clone();
        return pq;
    }

    public Iterator<E> iterator() {
        return new QueueIterator<>(this);
    }

}
